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
 * Description：879. 盈利计划
 *
 * @author caokeyu
 * @since 2021/6/11
 */
public class Solution879 {

  public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
    int len = group.length, mod = (int) 1e9 + 7;
    int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
    dp[0][0][0] = 1;
    for (int i = 1; i <= len; i++) {
      for (int j = 0; j <= n; j++) {
        for (int k = 0; k <= minProfit; k++) {
          if (j < group[i - 1]) {
            dp[i][j][k] = dp[i - 1][j][k];
          } else {
            dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - group[i - 1]][Math.max(0, k - profit[i - 1])]) % mod;
          }
        }
      }
    }
    int sum = 0;
    for (int i = 0; i <= n; i++) {
      sum = (sum + dp[len][i][minProfit]) % mod;
    }
    return sum;
  }
}
