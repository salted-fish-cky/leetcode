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

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import javax.swing.SizeRequirements;
import org.w3c.dom.Node;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2021/1/18
 */
public class SimpleLinkedList<E> implements ILinkedList<E> {

  private Node<E> first;

  private Node<E> last;

  private int size;

  private int modCount;


  @Override
  public void addFirst(E item) {
    Node<E> node = new Node<>(item);
    Node<E> f = first;
    first = node;
    if (f == null) {
      last = node;
    } else {
      f.pre = node;
      node.next = f;
    }
    modCount++;
    size++;
  }

  @Override
  public void addLast(E item) {
    Node<E> node = new Node<>(item);
    Node<E> l = last;
    last = node;
    if (l == null) {
      first = node;
    } else {
      l.next = node;
      node.pre = l;
    }
    modCount++;
    size++;
  }

  @Override
  public E getLast() {
    if (last == null) {
      throw new RuntimeException("链表为空");
    }
    return last.item;
  }

  @Override
  public E getFirst() {
    if (first == null) {
      throw new RuntimeException("链表为空");
    }
    return first.item;
  }

  @Override
  public E removeFirst() {
    if (first == null) {
      throw new RuntimeException("链表为空");
    }
    Node<E> f = first;
    E item = f.item;
    Node<E> next = f.next;
    if (next == null) {
      first = null;
      last = null;
    } else {
      first = next;
      next.pre = null;
      f.next = null;
      f.item = null;
    }
    modCount++;
    size--;
    return item;
  }

  @Override
  public E removeLast() {
    if (last == null) {
      throw new RuntimeException("链表为空");
    }
    Node<E> l = last;
    E item = l.item;
    Node<E> pre = l.pre;
    if (pre == null) {
      first = null;
      last = null;
    } else {
      last = pre;
      pre.next = null;
      l.pre = null;
      l.item = null;
    }
    modCount++;
    size--;
    return item;
  }

  @Override
  public void add(E e) {
    addLast(e);
  }

  @Override
  public void add(int i, E e) {
    checkIndexIsOutOfBound(i);
    if (i == 0) {
      addFirst(e);
    } else if (i == size - 1) {
      addLast(e);
    } else {
      Node<E> addNode = new Node<>(e);
      Node<E> node = node(i);
      Node<E> pre = node.pre;
      pre.next = addNode;
      addNode.pre = pre;
      addNode.next = node;
      node.pre = node;
      size++;
      modCount++;
    }

  }

  @Override
  public E get(int i) {
    checkIndexIsOutOfBound(i);
    return node(i).item;
  }

  private Node<E> node(int i) {
    Node<E> n = null;
    if (i < (size >> 1)) {
      n = first;
      for (int j = 0; j < i; j++) {
        n = n.next;
      }
    } else {
      n = last;
      for (int j = size - 1; j > i; j--) {
        n = n.pre;
      }
    }
    return n;
  }

  private void checkIndexIsOutOfBound(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
  }

  @Override
  public void remove(int i) {
    checkIndexIsOutOfBound(i);
    if (i == 0) {
      removeFirst();
    } else if (i == size - 1) {
      removeLast();
    } else {
      Node<E> node = node(i);
      Node<E> pre = node.pre;
      Node<E> next = node.next;
      pre.next = next;
      next.pre = pre;
      node.pre = null;
      node.next = null;
      node.item = null;
      size--;
      modCount++;
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<E> iterator() {
    return new ListItr<>();
  }

  @Override
  public boolean contain(Object o) {
    return false;
  }


  static class Node<E> {
    Node<E> pre;

    Node<E> next;

    E item;

    public Node(Node<E> pre, Node<E> next, E item) {
      this.pre = pre;
      this.next = next;
      this.item = item;
    }

    public Node(E item) {
      this.item = item;

    }
  }

  private class ListItr<E> implements ListIterator<E> {

    private int nextIndex;

    private Node<E> next;

    private int expectedModCount;

    private Node<E> lastReturned;

    public ListItr(int index) {
      next = (index == size - 1) ? null : (Node<E>) node(index);
      nextIndex = index;
      expectedModCount = modCount;
    }

    public ListItr() {
      this(0);
    }

    final void checkForComodification() {
      if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();

      }
    }

    @Override
    public boolean hasNext() {
      return nextIndex < size;
    }

    @Override
    public E next() {
      checkForComodification();
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      lastReturned = next;
      next = next.next;
      nextIndex++;
      return lastReturned.item;
    }

    @Override
    public boolean hasPrevious() {
      return nextIndex > 0;
    }

    @Override
    public E previous() {
      checkForComodification();
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      lastReturned = next = (next == null) ? (Node<E>) last : next.pre;
      // 索引位置变化
      nextIndex--;
      return lastReturned.item;
    }

    @Override
    public int nextIndex() {
      return nextIndex;
    }

    @Override
    public int previousIndex() {
      return 0;
    }

    @Override
    public void remove() {

    }

    @Override
    public void set(E e) {

    }

    @Override
    public void add(E e) {

    }
  }
}
