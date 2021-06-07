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
 * Description：141. 环形链表
 *
 * @author caokeyu
 * @since 2021/5/27
 */
public class Solution141 {

  public boolean hasCycle(ListNode head) {
    ListNode s = head, k = head;
    while (s != null && k != null && k.next != null) {
      s = s.next;
      k = k.next.next;
      if (s == k) {
        return true;
      }
    }
    return false;
  }
}
