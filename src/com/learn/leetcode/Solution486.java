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
 * Description： 预测赢家
 *
 *
 * @author caokeyu
 * @since 2021/4/24
 */
public class Solution486 {

  public boolean PredictTheWinner(int[] nums) {
    int len;
    int[][] dp = new int[len = nums.length][len];
    for (int i = 0; i < len; i++) {
      dp[i][i] = nums[i];
    }
    for (int i = len - 2; i >= 0; i--) {
      for (int j = i + 1; j < len; j++) {
        dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
      }
    }
    return dp[0][len - 1] >= 0;
  }
}
