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
 * Description： 剑指 Offer 42. 连续子数组的最大和
 *
 * @author caokeyu
 * @since 2021/6/16
 */
public class Solution42 {

  public int maxSubArray(int[] nums) {
    int len;
    int[] dp = new int[(len = nums.length) + 1];
    int res = Integer.MIN_VALUE;
    for (int i = 1; i <= len; i++) {
      dp[i] = nums[i - 1] > dp[i - 1] + nums[i - 1] ? nums[i - 1] : dp[i - 1] + nums[i - 1];
      res = Math.max(res, dp[i]);
    }
    return res;
  }
}
