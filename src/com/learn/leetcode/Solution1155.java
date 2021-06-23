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
 * Description：1155. 掷骰子的N种方法
 *
 * @author caokeyu
 * @since 2021/6/21
 */
public class Solution1155 {

  public int numRollsToTarget(int d, int f, int target) {
    int mod = 1000_000_007;
    int[][] dp = new int[d + 1][target + 1];
    int min = Math.min(f, target);
    for (int i = 1; i <= min; i++) {
      dp[1][i] = 1;
    }
    int targetMax = d * f;
    for (int i = 2; i <= d; i++) {
      for (int j = i; j <= targetMax; j++) {
        for (int k = 1; j - k >= 0 && k <= f; k++) {
          dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % mod;
        }
      }
    }
    return dp[d][target];
  }
}
