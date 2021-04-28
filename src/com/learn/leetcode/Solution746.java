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
 * Description：使用最小花费爬楼梯
 *
 * @author caokeyu
 * @since 2021/4/28
 */
public class Solution746 {

  public int minCostClimbingStairs(int[] cost) {
    int len;
    if ((len = cost.length) == 2) {
      return Math.min(cost[0], cost[1]);
    }
    int[] dp = new int[len + 1];
    dp[1] = cost[0];
    dp[2] = cost[1];
    for (int i = 3; i <= len; i++) {
      if (i == len) {
        dp[i] = Math.min(dp[i - 2] + cost[i - 1], dp[i - 1]);
      } else {
        dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i - 1];
      }

    }
    return dp[len];
  }
}
