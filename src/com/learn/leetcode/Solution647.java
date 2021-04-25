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
 * Description：回文子串
 *
 * @author caokeyu
 * @since 2021/4/25
 */
public class Solution647 {

  public int countSubstrings(String s) {
    int len;
    if ((len = s.length()) == 0) {
      return 0;
    }
    int[][] dp = new int[len][len];
    for (int i = 0; i < len; i++) {
      dp[i][i] = 1;
    }
    int sum = 0;
    for (int i = len - 2; i >= 0; i--) {
      for (int j = i + 1; j < len; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          dp[i][j] = dp[i][j - 1] + 1;
        } else {
          dp[i][j] = dp[i + 1][j];
        }
        sum += 1;
      }
    }
    return sum;
  }
}
