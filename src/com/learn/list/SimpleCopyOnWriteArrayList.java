/*
 * Copyright (C) 2011-2021 ShenZhen iBOXCHAIN Information Technology Co.,Ltd.
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

package com.learn.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2021/1/6
 */
public class SimpleCopyOnWriteArrayList<E> implements SimpleList<E> {


  private volatile Object[] array;

  private final ReentrantLock lock = new ReentrantLock();


  public SimpleCopyOnWriteArrayList() {
    array = new Object[0];
  }

  private Object[] getArray() {
    return array;
  }

  private void setArray(Object[] array) {
    this.array = array;
  }

  @Override
  public void add(E e) {
    if (e == null) {
      throw new NullPointerException();
    }
    lock.lock();
    try {
      Object[] elements = getArray();
      int len = elements.length + 1;
      elements = Arrays.copyOf(elements, len);
      elements[elements.length - 1] = e;
      setArray(elements);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void add(int i, E e) {
    if (e == null) {
      throw new NullPointerException();
    }
    checkBounds(i);
    lock.lock();
    try {
      Object[] elements = getArray();
      Object oldValue = elements[i];
      if (!e.equals(oldValue)) {
        elements = Arrays.copyOf(elements, elements.length);
        elements[i] = e;
      }
      setArray(elements);
    } finally {
      lock.unlock();
    }
  }


  @Override
  public E get(int i) {
    if (i > array.length - 1) {
      throw new IndexOutOfBoundsException();
    }
    return (E) array[i];
  }

  @Override
  public void remove(int i) {
    checkBounds(i);
    lock.lock();
    try {
      Object[] elements = getArray();
      int moveLen = size() - 1 - i;
      System.arraycopy(elements, i + 1, elements, i, moveLen);
      elements = Arrays.copyOf(elements, elements.length - 1);
      setArray(elements);
    } finally {
      lock.unlock();
    }
  }

  private void checkBounds(int index) {
    if (index > array.length - 1) {
      throw new IndexOutOfBoundsException();
    }
  }

  @Override
  public int size() {
    return array.length;
  }

  @Override
  public Iterator<E> iterator() {
    return new CopyItr(getArray());
  }

  @Override
  public boolean contain(Object o) {
    return indexOf(o, getArray(), 0, size()) >= 0;
  }

  private int indexOf(Object o, Object[] elements, int index, int fen) {
    int find = -1;
    if (o == null) {
      for (int i = 0; i < fen; i++) {
        if (elements[i] == null) {
          if (find++ == index) {
            return i;
          }
        }
      }
    } else {
      for (int i = 0; i < fen; i++) {
        if (o.equals(elements[i])) {
          if (++find == index) {
            return i;
          }
        }
      }
    }
    return -1;
  }

  static class CopyItr<E> implements Iterator<E> {

    private Object[] snapshot;

    private int cursor;

    public CopyItr(Object[] snapshot) {
      this.snapshot = snapshot;
    }
    @Override
    public boolean hasNext() {
      return cursor != snapshot.length;
    }

    @Override
    public E next() {
      E e = (E) snapshot[cursor];
      cursor++;
      return e;
    }
  }
}
