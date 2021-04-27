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
 * Description：删除与获得点数
 *
 * @author caokeyu
 * @since 2021/4/27
 */
public class Solution740 {

  public static void main(String[] args) {
    deleteAndEarn(new int[]{3, 4, 2});
  }
  public static int deleteAndEarn(int[] nums) {
    int len;
    if ((len = nums.length) == 1) {
      return nums[0];
    }
    int max = 0;
    for (int i = 0; i < len; i++) {
      max = Math.max(max, nums[i]);
    }
    int[] a = new int[max + 1];
    int[] dp = new int[max + 1];
    for (int n : nums) {
      a[n]++;
    }
    dp[1] = a[1] * 1;
    for (int i = 2; i <= max; i++) {
      dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * a[i]);
    }
    return dp[max];
  }
}
