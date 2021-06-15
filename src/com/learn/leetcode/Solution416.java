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
 * Description：
 *
 * @author caokeyu
 * @since 2021/4/22
 */
public class Solution416 {

  public boolean canPartition(int[] nums) {
    int n;
    if ((n = nums.length) < 2) {
      return false;
    }
    int max = 0, sum = 0, target;
    for (int i = 0; i < n; i++) {
      sum += nums[i];
      max = Math.max(max, nums[i]);
    }
    if ((sum & 1) == 1) {
      return false;
    }
    target = sum / 2;
    if (max > target) {
      return false;
    }
    boolean[][] dp = new boolean[n][target + 1];
    dp[0][0] = true;
    dp[0][nums[0]] = true;
    for (int i = 1; i < n; i++) {
      dp[i][0] = true;
      for (int j = 1; j <= target; j++) {
        if (nums[i] <= j) {
          dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[n - 1][target];
  }

  public boolean canPartition2(int[] nums) {
    int n, m;
    if ((n = nums.length) < 2) {
      return false;
    }
    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += nums[i];
    }
    if ((sum & 1) == 1) {
      return false;
    }
    int[][] dp = new int[n + 1][(m = sum / 2) + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        int num =  nums[i - 1];
        dp[i][j] = j < num ? dp[i - 1][j] :
                Math.max(dp[i - 1][j], dp[i - 1][j - num] + num);
      }
    }
    return m - dp[n][m] == 0;
  }
}
