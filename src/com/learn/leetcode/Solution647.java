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
    int sum = 0;
    boolean[][] dp = new boolean[len][len];
    for (int i = 0; i < len; i++) {
      dp[i][i] = true;
      sum++;
    }
    for (int i = 1; i < len; i++) {
      for (int j = 0; j < i; j++) {
        boolean b = s.charAt(i) == s.charAt(j);
        dp[j][i] = i - j < 2 ? b : dp[j + 1][i - 1] && b;
        sum = dp[j][i] ? sum + 1 : sum;
      }
    }
    return sum;
  }
}
