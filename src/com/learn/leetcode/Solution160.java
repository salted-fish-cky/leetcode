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
 * @since 2021/7/17
 */
public class Solution160 {

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }
    int lenA = 0, lenB = 0;
    ListNode hA = headA, hB = headB;
    while (hA != null || hB != null) {
      if (hA != null) {
        hA = hA.next;
        lenA++;
      }
      if (hB != null) {
        hB = hB.next;
        lenB++;
      }
    }
    int n = 0;
    if (lenA > lenB) {
      hA = headA;
      hB = headB;
      n = lenA - lenB;
    } else {
      hA = headB;
      hB = headA;
      n = lenB - lenA;
    }

    while (hA != null && hB != null) {
      if (n != 0) {
        hA = hA.next;
        n--;
      } else {
        if (hA == hB) {
          return hA;
        }
        hA = hA.next;
        hB = hB.next;
      }
    }
    return null;
  }
}
