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

package com.learn.leetcode;

import java.util.Arrays;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2021/4/17
 */
public class LRUCache {

  private int capacity;

  private Node[] table;

  private int size = 0;

  private Node head;

  private Node tail;

  public LRUCache(int capacity) {
    table = new Node[getCapacity((this.capacity = capacity) * 2)];
  }

  private int getCapacity(int capacity) {
    capacity--;
    capacity |= capacity >>> 1;
    capacity |= capacity >>> 2;
    capacity |= capacity >>> 4;
    capacity |= capacity >>> 8;
    capacity |= capacity >>> 16;
    capacity |= capacity >>> 32;
    return capacity == 0 ? 2 : ++capacity;

  }

  private int index(int key) {
    return key & (table.length - 1);
  }

  private boolean checkBound() {
    return capacity == 0;
  }

  public int get(int key) {
    if (checkBound()) {
      return - 1;
    }
    Node n = find(key, index(key));
    if (n != null) {
      afterNodeAccess(n);
      return n.value;
    }
    return -1;
  }

  private Node find(int key, int h) {
    Node node;
    if ((node = table[h]) != null) {
      for (Node n = node; n != null; n = n.next) {
        if (key == n.key) {
          return n;
        }
      }
    }
    return null;
  }

  public void put(int key, int value) {
    if (checkBound()) {
      return;
    }
    Node node, p, n = null, l = null;
    int h;
    if ((node = table[h = index(key)]) != null) {
      p = node;
      if (p.key == key) {
        p.value = value;
        l = p;
      } else {
        for (; ; ) {
          if ((l = p.next) == null) {
            p.next = n = createNode(key, value);
            break;
          }
          if (l.key == key) {
            l.value = value;
            break;
          }
          p = l;
        }
      }
    } else {
      n = createNode(key, value);
      table[h] = n;
    }
    if (l != null) {
      afterNodeAccess(l);
      return;
    }
    if (++size > capacity) {
      afterNodeInsertion();
    }
  }

  private void afterNodeInsertion() {
    Node n = head;
    if (n.after != null) {
      head = n.after;
      n.after = null;
      head.before = null;
    } else {
      head = tail = null;
    }
    remove(n);
  }

  private void remove(Node node) {
    int h;
    Node n = table[h = index(node.key)], l;
    if (n == node) {
      table[h] = n.next;
    } else {
      while ((l = n.next) != null) {
        if (l == node) {
          n.next = l.next;
          l.next = null;
          break;
        }
        n = l;
      }
    }
    size--;
  }

  /**
   * 把最近操作的节点放到链表尾部
   */
  private void afterNodeAccess(Node node) {
    if (node != tail) {
      if (node != head) {
        node.before.after = node.after;
        node.after.before = node.before;
      } else {
        head = node.after;
        head.before = null;
      }
      tail.after = node;
      node.before = tail;
      node.after = null;
      tail = node;
    }
  }

  private Node createNode(int key, int value) {
    Node node = new Node(key, value);
    if (head == null) {
      head = tail = node;
    } else {
      tail.after = node;
      node.before = tail;
      tail = node;
    }
    return node;
  }

  static class Node {
    int key;
    int value;
    Node next;
    Node after;
    Node before;

    public Node(int key, int value, Node next, Node after, Node before) {
      this.key = key;
      this.value = value;
      this.next = next;
      this.after = after;
      this.before = before;
    }

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }
}
