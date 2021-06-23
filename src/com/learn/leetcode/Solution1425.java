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
 * Description：1425. 带限制的子序列和
 *
 * @author caokeyu
 * @since 2021/6/20
 */
public class Solution1425 {

  public int constrainedSubsetSum(int[] nums, int k) {
    int len;
    if ((len = nums.length) == 1) {
      return nums[0];
    }
    int[][] dp = new int[len + 1][2];
    dp[1][0] = Integer.MIN_VALUE;
    dp[1][1] = nums[0];
    for (int i = 2; i <= len; i++) {
      dp[i][0] = dp[i - 1][0];
      dp[i][1] = dp[i - k][1] + nums[i - 1];
      for (int j = 1; j <= Math.min(k, i); j++) {
        dp[i][0] = Math.max(dp[i][0], dp[i - j][1]);
        dp[i][1] = Math.max(dp[i][1], dp[i - j][1] + nums[i - 1]);
      }
    }
    return Math.max(dp[len][0], dp[len][1]);
  }
}
