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
 * Description：83. 删除排序链表中的重复元素
 *
 * @author caokeyu
 * @since 2021/6/29
 */
public class Solution83 {

  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode node = head.next, pre = head;
    while (node != null) {
      if (pre.val == node.val) {
        pre.next = node.next;
      } else {
        pre = node;
      }
      node = node.next;
    }
    return head;
  }

}
