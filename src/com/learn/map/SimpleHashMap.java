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

package com.learn.map;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import org.w3c.dom.Node;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2021/1/12
 */
public class SimpleHashMap<K, V> implements Map<K, V> {

  private int size = 0;

  private static final int DEFAULT_CAPACITY = 1 << 4;

  private static final int MAXIMUM_CAPACITY = 1 << 30;

  private static final float DEFAULT_LOAD_FACTOR = 0.75f;

  Set<Map.Entry<K, V>> entrySet;

  final float loadFactor;

  private Node<K, V>[] table;

  private int threshold;

  private int modeCount;

  public SimpleHashMap() {
    loadFactor = DEFAULT_LOAD_FACTOR;

  }

  public SimpleHashMap(int initCapacity) {
    this(initCapacity, DEFAULT_LOAD_FACTOR);
  }

  public SimpleHashMap(int initCapacity, float loadFactor) {
    if (initCapacity < 0 || loadFactor < 0) {
      throw new RuntimeException("容量和扩容因子不能小于0");
    }
    if (initCapacity > MAXIMUM_CAPACITY) {
      throw new RuntimeException("容量设置过大");
    }
    this.loadFactor = loadFactor;
    this.threshold = tableSizeFor(initCapacity);
  }

  private static int tableSizeFor(int initCapacity) {
    int n = initCapacity - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return n >= MAXIMUM_CAPACITY ? MAXIMUM_CAPACITY : n + 1;
  }

  static class Node<K, V> implements Map.Entry<K, V> {
    final K key;
    V value;
    final int hash;
    Node<K, V> next;

    public Node(K key, V value, int hash) {
      this.key = key;
      this.value = value;
      this.hash = hash;
    }

    public int getHash() {
      return hash;
    }

    @Override
    public K getKey() {
      return this.key;
    }

    @Override
    public V getValue() {
      return this.value;
    }

    @Override
    public V setValue(V value) {
      this.value = value;
      return value;
    }

    public Node<K, V> getNext() {
      return next;
    }

    public void setNext(Node<K, V> next) {
      this.next = next;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Node<?, ?> node = (Node<?, ?>) o;
      return hash == node.hash &&
              Objects.equals(key, node.key) &&
              Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(key, value, hash);
    }
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean containsKey(Object key) {
    return getNode(key, hash(key)) != null;
  }

  @Override
  public boolean containsValue(Object value) {
    return false;
  }

  @Override
  public V get(Object key) {
    Node<K, V> node = getNode(key, hash(key));
    return node == null ? null : node.getValue();
  }

  private Node<K, V> getNode(Object key, int hash) {
    Node<K, V> [] tab;
    int n;
    if ((tab = table) != null && (n = tab.length) > 0) {
      Node<K, V> node = tab[hash & (n - 1)];
      if (node == null) {
        return null;
      }
      for (Node<K, V> p = node; p != null; p = p.next) {
        if (key.equals(p.getKey())) {
          return p;
        }
      }
    }
    return null;
  }

  @Override
  public V put(K key, V value) {
    return putVal(key, value, hash(key));
  }

  private static int hash(Object key) {
    return key == null ? 0 : key.hashCode();
  }

  private V putVal(K key, V value, int hash) {
    if (key == null) {
      return null;
    }
    Node<K, V>[] tab;
    int n;
    if ((tab = table) == null || (n = tab.length) == 0) {
      n = (tab = resize()).length;
    }
    int index = hash & (n - 1);
    Node<K, V> e = new Node<>(key, value, hash);
    Node<K, V> node = tab[index];
    Node<K, V> next = null;
    if (node == null) {
      tab[index] = e;
    } else if (node.key.equals(key)) {
        node.value = value;
    } else {
      while(true) {
        if ((next = node.next) == null) {
          node.next = e;
          break;
        }
        if (key.equals(next.getKey())) {
          next.value = value;
          break;
        }
        node = next;
      }
    }
    modeCount++;
    if (++size > threshold) {
      resize();
    }
    return null;
  }

  private Node<K, V>[] resize() {
    Node<K, V>[] oldTab = table;
    int oldCap = oldTab == null ? 0 : oldTab.length;
    int newCap, newThr = 0;
    int oldThr = threshold;
    if (oldCap > 0) {
      if (oldCap > MAXIMUM_CAPACITY) {
        return oldTab;
      } else if ((newCap = oldCap << 1) <= MAXIMUM_CAPACITY && oldCap >= DEFAULT_CAPACITY) {
        newThr = oldThr << 1;
      }
    } else if (oldThr > 0) {
      newCap = oldThr;
      newThr = (int) (newCap * loadFactor);
    } else {
      newCap = DEFAULT_CAPACITY;
      newThr = (int) (newCap * loadFactor);
    }

    threshold = newThr;
    Node<K, V>[] newTab = new Node[newCap];
    table = newTab;
    if (oldTab != null) {

      for (int i = 0; i < oldCap; i++) {
        Node<K, V> node = oldTab[i];
        if (node != null) {
          if (node.next == null) {
            table[node.hash & (newCap - 1)] = node;
          } else {
            Node<K, V> oldHead = null, oldTail = null;
            Node<K, V> newHead = null, newTail = null;
            do {
              if ((node.hash & oldCap) == 0) {
                if (oldTail == null) {
                  oldHead = node;
                } else {
                  oldTail.next = node;
                }
                oldTail = node;
              } else {
                if (newHead == null) {
                  newHead = node;
                } else {
                  newTail.next = node;
                }
                newTail = node;
              }
              node = node.next;
            } while (node != null);
            if (oldHead != null) {
              newTab[i] = oldHead;
            }
            if (newHead != null) {
              newTab[i + oldCap] = newHead;
            }
          }
        }

      }
    }
    return newTab;

  }

  @Override
  public V remove(Object key) {
    Node<K, V> [] tab;
    int n;

    if ((tab = table) != null && (n = tab.length) > 0) {
      int index = hash(key) & (n - 1);
      Node<K, V> node = tab[index];
      if (node == null) {
        return null;
      }
      if (node.key.equals(key)) {
        tab[index] = node.next;
        node.next = null;
        modeCount++;
        return node.value;
      }
      for(Node<K, V> p = node, pre = node; p != null; pre = p, p = p.next) {
        if (p.key.equals(key)) {
          if (p.next != null) {
            pre.next = p.next;
            p.next = null;
          } else {
            pre.next = null;
          }
          modeCount++;
          return p.value;
        }
      }
    }
    return null;
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> m) {
    for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
      put(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public void clear() {
    if (table != null && table.length > 0) {
      for (int i = 0; i < table.length; i++) {
        table[i] = null;
      }
    }
  }

  @Override
  public Set<K> keySet() {
    return null;
  }

  @Override
  public Collection<V> values() {
    return null;
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    Set<Map.Entry<K, V>> es;
    return (es = entrySet) == null ? (entrySet = new EntrySet()) : es;
  }

  final class EntrySet extends AbstractSet<Entry<K,V>> {
    @Override
    public Iterator<Entry<K, V>> iterator() {
      return new SimpleEntryItr();
    }

    public final int size()                 { return size; }
    public final void clear()               {SimpleHashMap.this.clear(); }

  }

  final class SimpleEntryItr implements Iterator<Entry<K, V>> {

    Node<K, V> current;

    Node<K, V> next;

    Node<K, V> [] t;

    int index;

    public SimpleEntryItr() {
      t = table;
      index = 0;
      current = next = null;
      if (t != null && size > 0) {
        do {} while (index < t.length && (next = t[index++]) == null);
      }
    }
    @Override
    public boolean hasNext() {
      return next != null;
    }

    @Override
    public Entry<K, V> next() {
      Node<K, V> e = next;
      if ((next = (current = e).next) == null && (t = table) != null) {
        do {} while (index < t.length && (next = t[index++]) == null);
      }
      return e;
    }
  }
}
