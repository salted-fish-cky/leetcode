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
 * Description：最长回文子序列
 *
 * @author caokeyu
 * @since 2021/4/24
 */
public class Solution516 {

  public int longestPalindromeSubseq(String s) {
    int len;
    int[][] dp = new int[len = s.length()][len];
    for (int i = 0; i < len; i++) {
      dp[i][i] = 1;
    }
    for (int k = 1; k <= len; k++) {
      for (int i = 0; i < len - k; i++) {
        int j = i + k;
        dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
        if (s.charAt(i) == s.charAt(j)) {
          dp[i][j] = Math.max(dp[i][j],dp[i + 1][j - 1] + 2);
        }
      }
    }
    return dp[0][len - 1];
  }
}
