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

  }

  public void put(int key, int value) {
    Node node, p = null, l = null;
    int h;
    if ((node = find(key, h = index(key))) != null) {
      p = node;
      if (p.key == key) {
        p.value = value;
      } else {
        for(;;) {
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
    }
    afterNodeInsertion();
  }

  private void afterNodeAccess(Node node) {
    node.useCount += 1;
    Node p;
    if (node != tail) {
      for (;;) {

      }

      if (node.useCount >= node.after.useCount) {
        if (node == head) {
          p = head = node.after;
          head.before = null;

        } else {
          node.before.after = node.after;
          node.after.before = node.before;
        }
        node.before = tail;
        tail.after = node;
        tail = node;
      }
    }
  }

  private void afterNodeInsertion() {
    if (size > capacity) {
      Node n = head;
      head = n.after;
      head.before = null;
      n.after = null;
      remove(n);
    }
  }

  private void remove(Node node) {
    int h;
    Node n = table[h = index(node.key)], l;
    if (n == node) {
      table[h] = null;
    } else {
      while((l = n.next) != null) {
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
