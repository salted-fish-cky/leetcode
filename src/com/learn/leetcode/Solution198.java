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
 * Description：打家劫舍
 *
 * @author caokeyu
 * @since 2021/4/19
 */
public class Solution198 {

  public int rob(int[] nums) {
    int len;
    if ((len = nums.length) == 0) {
      return 0;
    }
    int[] dp = new int[len + 1];
    dp[0] = 0;
    for (int i = 1; i <= len; i++) {
      dp[i] = i == 1 ? nums[i - 1] : (i == 2 ? Math.max(nums[i - 1], nums[i - 2]) :
              Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]));
    }
    return dp[len];
  }
}
