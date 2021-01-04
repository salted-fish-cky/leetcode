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

/**
 * Description：
 *
 * @author caokeyu
 * @since 2021/1/4
 */
public class SimpleArrayList<E> implements SimpleList<E> {

  private int size;

  private int modeCount;

  private Object[] elementData;

  private static final Object[] EMPTY_ELEMENT = {};

  private static final int DEFAULT_CAPACITY = 10;

  private static final int MAX_SIZE = Integer.MAX_VALUE - 8;

  public SimpleArrayList() {
    elementData = EMPTY_ELEMENT;
  }

  public SimpleArrayList(int size) {
    if (size <= 0) {
      throw new RuntimeException("容量大小设置错误");
    }
    elementData = new Object[size];
  }
  @Override
  public void add(E e) {
    int index = size + 1;
    resize(index);
    elementData[++size] = e;
    modeCount++;
  }

  @Override
  public void add(int i, E e) {
    if (i < 0 || i > elementData.length - 1) {
      throw new IndexOutOfBoundsException(i +"");
    }
    int index = size + 1;
    resize(index);
    System.arraycopy(elementData, i, elementData, i + 1, size - i);
    elementData[i] = e;
    size++;
  }

  @Override
  public E get(int i) {
    return getElement(i);
  }

  @Override
  public void remove(int i) {
    if (i < 0 || i > elementData.length - 1) {
      throw new IndexOutOfBoundsException(i +"");
    }
    int index = size - i - 1;
    if (index > 0) {
      System.arraycopy(elementData, i + 1, elementData, i,
              index);
    }
    modeCount++;
    elementData[size--] = null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator iterator() {
    return new Itr();
  }

  private E getElement(int i) {
    return (E)elementData[i];
  }

  private void resize(int minCapacity) {
    if (elementData == EMPTY_ELEMENT) {
      minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    if (minCapacity > elementData.length) {
      grow(minCapacity);
    }
  }

  private void grow(int minCapacity) {
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + oldCapacity >> 1;
    if (newCapacity < minCapacity) {
      newCapacity = minCapacity;
    }
    if (newCapacity > MAX_SIZE) {
      if (minCapacity < 0) {
        throw new OutOfMemoryError("out of memory");
      }
      newCapacity = minCapacity > MAX_SIZE ? Integer.MAX_VALUE : MAX_SIZE;
    }

    elementData = Arrays.copyOf(elementData, newCapacity);
  }

  private class Itr implements Iterator<E> {

    private int cursor;

    @Override
    public boolean hasNext() {
      return cursor++ != size;
    }

    @Override
    public E next() {
      return getElement(cursor);
    }
  }
}
