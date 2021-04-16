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

import org.w3c.dom.Node;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2021/4/16
 */
public class LFUCache {

  private int capacity;

  private Node[] table;

  private int size = 0;

  private Node head;

  private Node tail;

  public LFUCache(int capacity) {
    table = new Node[this.capacity = capacity];
  }

  public int get(int key) {
    Node n = find(key, index(key));
    if (n != null) {
      afterNodeAccess(n);
      return n.value;
    }
    return -1;
  }

  public void put(int key, int value) {
    Node node, p = null, l = null;
    int h;
    if ((node = find(key, h = index(key))) != null) {
      p = node;
      if (p.key == key) {
        p.value = value;
      } else {
        for (; ; ) {
          if ((l = p.next) == null) {
            p.next = createNode(key, value);
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
      node = createNode(key, value);
      table[h] = node;
    }
    if (l != null) {
      afterNodeAccess(l);
      return;
    }
    if (++size > capacity) {
      afterNodeInsertion();
    }
  }

  private void afterNodeAccess(Node node) {
    node.useCount += 1;
    Node p = node, l;
    if (node != tail) {
      for (; ; ) {
        if ((l = p.next) == null) {
          break;
        }
        if (l.useCount > p.useCount) {
          break;
        }
      }
      if (p == node) {
        return;
      }
      if (node == head) {
        head = node.after;
        head.before = null;
      } else {
        node.before.after = node.after;
        node.after.before = node.before;
      }
      if (p == tail) {
        node.before = tail;
        tail.after = node;
        tail = node;
      } else {
        node.after = p.after;
        p.after.before = node;
        p.after = node;
        node.before = p;
      }

    }
  }

  private void afterNodeInsertion() {
    Node n = head;
    head = n.after;
    head.before = null;
    n.after = null;
    remove(n);

  }

  private void remove(Node node) {
    int h;
    Node n = table[h = index(node.key)], l;
    if (n == node) {
      table[h] = null;
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

  }

  private int index(int key) {
    return key % capacity;
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

  private Node createNode(int key, int value) {
    Node node = new Node(key, value, null, null, null, 1);
    if (head == null) {
      head = tail = node;
    } else {
      tail.after = node;
      node.before = tail;
      tail = node;
    }
  }

  static class Node {
    int key;
    int value;
    Node next;
    Node after;
    Node before;
    int useCount;

    public Node(int key, int value, Node next, Node after, Node before, int useCount) {
      this.key = key;
      this.value = value;
      this.next = next;
      this.after = after;
      this.before = before;
      this.useCount = useCount;
    }
  }
}
