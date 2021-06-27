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
 * Description：25. K 个一组翻转链表
 *
 * @author caokeyu
 * @since 2021/6/27
 */
public class Solution25 {

  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode node = head, temp = null, result = null, last = null;
    int count = k;
    while (node != null) {
      if (--k == 0) {
        temp = node.next;
        node.next = null;
        ListNode reverse = reverse(head);
        if (result == null) {
          result = reverse;
        } else {
          last.next = reverse;
        }
        node = head.next = temp;
        last = head;
        head = node;
        k = count;
      } else {
        node = node.next;
      }
    }
    return result;
  }

  private ListNode reverse(ListNode node) {
    ListNode cur = null, next = null;
    while (node != null) {
      next = node.next;
      node.next = cur;
      cur = node;
      node = next;
    }
    return cur;
  }
}
