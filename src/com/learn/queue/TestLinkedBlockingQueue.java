/*
 * Copyright (C) 2011-2020 ShenZhen iBOXCHAIN Information Technology Co.,Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary
 * information of iBoxChain Company of China.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with iBoxChain inc.
 *
 */

package com.learn.queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.w3c.dom.Node;

/**
 * Description：链表阻塞队列
 *
 * @author caokeyu
 * @since 2020/12/31
 */
public class TestLinkedBlockingQueue<E> implements TestBlockingQueue<E> {

  /**
   * 队列总大小
   */
  private final Integer capacity;

  /**
   * 队列当前大小
   */
  private AtomicInteger count = new AtomicInteger(0);

  /**
   * 头结点
   */
  private volatile Node<E> header;

  /**
   * 尾结点
   */
  private volatile Node<E> tail;

  /**
   * 消费锁
   */
  private final Lock putLock = new ReentrantLock();

  /**
   * 生产锁
   */
  private final Lock takeLock = new ReentrantLock();

  private final Condition putCondition = putLock.newCondition();

  private final Condition takeCondition = takeLock.newCondition();

  public TestLinkedBlockingQueue(Integer capacity) {
    if (capacity <= 0) {
      throw new RuntimeException("容量设置错误");
    }
    this.capacity = capacity;
    header = tail = new Node<>(null, null);
  }

  public TestLinkedBlockingQueue() {
    this(Integer.MAX_VALUE);
  }

  class Node<E> {
    E item;
    Node<E> next;
    public Node(E item, Node<E> next) {
      this.item = item;
      this.next = next;
    }
  }

  @Override
  public E take() throws InterruptedException {
    E e = null;
    int c = -1;
    takeLock.lockInterruptibly();
    try {
      while (count.get() == 0) {
        takeCondition.await();
      }
      e = dequeue();
      c = count.getAndDecrement();
      if (c - 1 > 0) {
        takeCondition.signal();
      }
    } finally {
      takeLock.unlock();
    }
    if (c <= capacity) {
      notifyProducer();
    }
    return e;
  }

  private void notifyProducer()  throws InterruptedException {
    putLock.lockInterruptibly();
    try {
      putCondition.signal();
    } finally {
      putLock.unlock();
    }
  }

  private E dequeue() {
    Node<E> h = header;
    Node<E> first = header.next;
    // 移除引用，帮助gc
    h.next = h;
    header = first;
    E e = first.item;
    first.item = null;
    return e;
  }

  @Override
  public void put(E e) throws InterruptedException {
    if (e == null) {
      throw new RuntimeException("对象为空");
    }
    int c = -1;
    try {
      putLock.lockInterruptibly();
      while (capacity == count.get()) {
        putCondition.await();
      }
      Node<E> node = new Node<>(e, null);
      enqueue(node);
      c = count.getAndIncrement();
      // 队列没满，唤醒一个生产者生产数据
      if (c + 1 < capacity) {
        putCondition.signal();
      }
    } finally {
      putLock.unlock();
    }
    // 队列中有数据，去唤醒一个消费者消费数据
    if (c == 0) {
      notifyConsumer();
    }

  }

  private void notifyConsumer() throws InterruptedException {
    takeLock.lockInterruptibly();
    try {
      takeCondition.signal();
    } finally {
      takeLock.unlock();
    }
  }

  /**
   * 入队
   * @param node
   */
  private void enqueue(Node<E> node) {
    tail = tail.next = node;
  }

  @Override
  public boolean offer(E e, long timeOut, TimeUnit unit) throws InterruptedException {
    if (e == null) {
      throw new RuntimeException("对象为空");
    }
    int c = -1;
    try {
      putLock.lockInterruptibly();
      long time = unit.toNanos(timeOut);
      while (capacity == count.get()) {
        long nanos = putCondition.awaitNanos(time);
        if (nanos <= 0) {
          return false;
        }
      }
      Node<E> node = new Node<>(e, null);
      enqueue(node);
      c = count.getAndIncrement();
      // 队列没满，唤醒一个生产者生产数据
      if (c + 1 < capacity) {
        putCondition.signal();
      }
    } finally {
      putLock.unlock();
    }
    // 队列中有数据，去唤醒一个消费者消费数据
    if (c >= 0) {
      notifyConsumer();
    }
    return c >= 0;
  }

  @Override
  public E poll(long timeOut, TimeUnit unit) throws InterruptedException {
    E e = null;
    int c = 0;
    try {
      takeLock.lockInterruptibly();
      long time = unit.toNanos(timeOut);
      while (count.get() == 0) {
        long l = takeCondition.awaitNanos(time);
        if (l <= 0) {
          return null;
        }
      }
      e = dequeue();
      c = count.getAndDecrement();
      if (c - 1 > 0) {
        takeCondition.signal();
      }
    } finally {
      takeLock.unlock();
    }
    if (c <= capacity) {
      notifyProducer();
    }
    return e;
  }
}
