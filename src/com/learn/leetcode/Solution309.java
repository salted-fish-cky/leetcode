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
 * Description：309. 最佳买卖股票时机含冷冻期
 *
 * @author caokeyu
 * @since 2021/6/16
 */
public class Solution309 {

  public int maxProfit(int[] prices) {
    int len;
    if ((len = prices.length) == 0) {
      return 0;
    }
    int[][] dp = new int[len][2];
    dp[0][1] = -prices[0];
    for (int i = 1; i < len; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
      if (i - 2 > 0) {
        dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
      } else {
        dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
      }
    }
    return dp[len][0];
  }
}
