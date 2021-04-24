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
 * Description：一和零
 *
 * @author caokeyu
 * @since 2021/4/24
 */
public class Solution474 {

  public int findMaxForm(String[] strs, int m, int n) {
    int len = strs.length;
    int[][][] dp = new int[len + 1][m + 1][n + 1];
    for (int i = 1; i <= len; i++) {
      int[] count = count(strs[i - 1]);
      for (int j = 0; j <= m; j++) {
        for (int k = 0; k <= n; k++) {
          dp[i][j][k] = dp[i - 1][j][k];
          int one = count[1];
          int zero = count[0];
          if (j >= zero && k >= one) {
            dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zero][k - one] + 1);
          }
        }
      }
    }
    return dp[len][m][n];
  }

  private int[] count(String str) {
    int[] count = new int[2];
    for (int i = 0; i < str.length(); i++) {
      count[str.charAt(i) - '0']++;
    }
    return count;
  }
}
