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
 * Description：买卖股票的最佳时机含手续费
 *
 * @author caokeyu
 * @since 2021/4/27
 */
public class Solution714 {

  public int maxProfit(int[] prices, int fee) {
    int len = prices.length;
    int[][] dp = new int[len][2];
    for (int i = 0; i < len; i++) {
      dp[i][1] = -prices[i];
    }
    for (int i = 1; i < len; i++) {
      dp[i][0] = Math.max(dp[i - 1][1] + prices[i] - fee, dp[i - 1][0]);
      dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
    }
    return dp[len - 1][0];
  }
}
