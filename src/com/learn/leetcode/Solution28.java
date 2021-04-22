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
 * Description：实现 strStr()
 *
 * @author caokeyu
 * @since 2021/4/20
 */
public class Solution28 {

  public int strStr(String haystack, String needle) {
    int m, n;
    if ((n = needle.length()) == 0) {
      return 0;
    }
    if ((m = haystack.length()) < n) {
      return -1;
    }
    int l1 = 0, l2 = 0;
    while(m > l1 && n > l2 && m - l1 >= n - l2) {
      if (haystack.charAt(l1) == needle.charAt(l2)) {
        l1++;
        l2++;
      } else {
        l1 = l1 - l2 + 1;
        l2 = 0;
      }
      if (l2 == n) {
        return l1 - l2;
      }

    }
    return -1;
  }
}
