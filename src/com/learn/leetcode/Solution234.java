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

import java.util.Deque;
import java.util.LinkedList;

/**
 * Description：234. 回文链表
 *
 * @author caokeyu
 * @since 2021/6/23
 */
public class Solution234 {

  public static void main(String[] args) {
    isPalindrome(new ListNode(1, new ListNode(2, new ListNode(7, new ListNode(6, new ListNode(2, new ListNode(1)))))));
  }

  public static boolean isPalindrome(ListNode head) {
    ListNode node = head;
    Deque<Integer> deque = new LinkedList<>();
    while (node != null) {
      deque.offer(node.val);
      node = node.next;
    }
    while (deque.size() > 1) {
      if (!deque.pollFirst().equals(deque.pollLast())) {
        return false;
      }
    }
    return true;
  }
}
