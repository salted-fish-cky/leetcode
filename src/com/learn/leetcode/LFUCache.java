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
    table = new Node[getCapacity(this.capacity = capacity) * 2];
  }

  public static void main(String[] args) {
    LFUCache lfuCache = new LFUCache(1);
//    lfuCache.put(1,1);
//    lfuCache.put(2,2);
//    lfuCache.put(3,3);
//    lfuCache.put(4, 4);
//
//    lfuCache.get(4);
//    lfuCache.get(3);
//    lfuCache.get(2);
//    lfuCache.get(1);
//    lfuCache.put(5, 5);
//    lfuCache.get(1);
//    lfuCache.get(2);
//    lfuCache.get(3);
//    lfuCache.get(4);
    lfuCache.get(0);
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
            p.next = n = new Node(key, value, null, null, null, 1);
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
      n = new Node(key, value, null, null, null, 1);
      table[h] = n;
    }
    if (l != null) {
      afterNodeAccess(l);
      return;
    }
    size++;
    afterNodeInsertion(n);
  }

  private boolean checkBound() {
    return capacity == 0;
  }

  /**
   * 对操作的节点进行使用数+1，然后插到大于它的前节点
   * @param node
   */
  private void afterNodeAccess(Node node) {
    int useCount = (node.useCount += 1);
    Node p = node, l;
    if (node != tail) {
      for (;;) {
        if ((l = p.after) == null) {
          break;
        }
        if (useCount < l.useCount) {
          break;
        }
        p = l;
      }
      // p等于node说明节点不需要移动
      if (p == node) {
        return;
      }
      // 把node节点移动到p节点的后面
      if (node == head) {
        head = node.after;
        head.before = null;
        node.after = null;
      } else {
        node.before.after = node.after;
        node.after.before = node.before;
      }
      if (p == tail) {
        node.before = tail;
        tail.after = node;
        tail = node;
        node.after = null;
      } else {
        node.after = p.after;
        p.after.before = node;
        p.after = node;
        node.before = p;
      }

    }
  }

  private void afterNodeInsertion(Node node) {
    if (size > capacity) {
      Node n = head;
      head = n.after;
      if (head != null) {
        head.before = null;
      }
      n.after = null;
      remove(n);
    }
    Node p = head, l;
    int useCount = node.useCount;
    if (p != null) {
      if (useCount >= p.useCount) {
        for (;;) {
          if ((l = p.after) == null) {
            break;
          }
          if (node.useCount < l.useCount) {
            break;
          }
          p = l;
        }
        if (p != tail) {
          p.after.before = node;
          node.after = p.after;
          p.after = node;
          node.before = p;
        } else {
          tail.after = node;
          node.before = tail;
          tail = node;
        }
      } else {
        node.after = head;
        head.before = node;
        head = node;
      }
    } else {
      head = tail = node;
    }
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

  private int index(int key) {
    return key & (table.length - 1);
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
