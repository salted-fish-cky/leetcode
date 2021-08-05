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

import java.util.Arrays;

/**
 * Description：3. 无重复字符的最长子串
 *
 * @author caokeyu
 * @since 2021/7/5
 */
public class Solution3 {

  public int lengthOfLongestSubstring(String s) {
    int len;
    if ((len = s.length()) < 2) {
      return len;
    }
    int[] words = new int[128];
    Arrays.fill(words, -1);
    int left = 0, right = 0, maxLen = 0;
    while (right < len) {
      int idx = words[s.charAt(right)];
      if (idx != -1) {
        maxLen = Math.max(right - left, maxLen);
        if (idx + 1 > left) {
          left = idx + 1;
        }
      }
      words[s.charAt(right)] = right;
      right++;
    }
    maxLen = Math.max(len - left, maxLen);
    return maxLen;
  }
}
