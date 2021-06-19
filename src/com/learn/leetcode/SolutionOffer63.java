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
 * Description：剑指 Offer 63. 股票的最大利润
 *
 * @author caokeyu
 * @since 2021/6/16
 */
public class SolutionOffer63 {

  public int maxProfit(int[] prices) {
    int len, res = 0;
    if ((len = prices.length) == 0) {
      return 0;
    }
    int[][][] dp = new int[len][2][2];
    dp[0][0][1] = -prices[0];
    for (int i = 1; i < len; i++) {
      dp[i][1][1] = 0;
      dp[i][0][0] = 0;
      dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][0][0] - prices[i]);
      dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][0][1] + prices[i]);
      res = Math.max(res, dp[i][1][0]);
    }
    return res;
  }
}
