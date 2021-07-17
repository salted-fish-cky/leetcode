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
 * Description：148. 排序链表
 *
 * @author caokeyu
 * @since 2021/7/1
 */
public class Solution148 {

  public ListNode sortList2(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode node = head, next = node.next, newHead = head, temp;
    while (next != null) {
      if (next.val < node.val) {
        if (next.val < newHead.val) {
          node.next = next.next;
          next.next = newHead;
          newHead = next;
        } else {
          temp = newHead.next;
          ListNode prv = newHead;
          while (temp != null) {
            if (next.val < temp.val) {
              node.next = next.next;
              next.next = temp;
              prv.next = next;
              break;
            }
            prv = temp;
            temp = temp.next;
          }
        }
      } else {
        node = next;
      }
      next = node.next;
    }
    return newHead;
  }

  public static void main(String[] args) {
    Solution148 solution148 = new Solution148();
    solution148.sortList(new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3, null)))));
  }

  public ListNode sortList(ListNode head) {
    if (head == null) {
      return null;
    } else if (head.next == null) {
      return head;
    }
    ListNode mid = findMidNode(head);
    ListNode lHead = mid.next;
    mid.next = null;
    ListNode l = sortList(head);
    ListNode r = sortList(lHead);
    return mergeNodeList(l, r);
  }

  private ListNode mergeNodeList(ListNode l, ListNode r) {
    ListNode mergeNodeList = new ListNode(-1, null);
    ListNode ll = mergeNodeList;
    while (l != null && r != null) {
      if (l.val < r.val) {
        ll.next = l;
        l = l.next;
      } else {
        ll.next = r;
        r = r.next;
      }
      ll = ll.next;
    }
    ll.next = l != null ? l : r;
    return mergeNodeList.next;
  }

  private ListNode findMidNode(ListNode head) {
    if (head.next == null) {
      return head;
    }
    ListNode quick = head, slow = head;
    while (quick.next != null) {
      quick = quick.next.next;
      if (quick == null) {
        break;
      }
      slow = slow.next;
    }
    return slow;
  }
}
