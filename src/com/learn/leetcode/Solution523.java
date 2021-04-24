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
 * Description：连续的子数组和
 *
 * @author caokeyu
 * @since 2021/4/24
 */
public class Solution523 {

  public static void main(String[] args) {

  }

  public static boolean checkSubarraySum(int[] nums, int k) {
    int len;
    if ((len = nums.length) < 2) {
      return false;
    }
    int[] dp = new int[len];
    dp[0] = nums[0];
    for (int i = 1; i < len; i++) {
      dp[i] = dp[i - 1] + nums[i];
      if (dp[i] % k == 0) {
        return true;
      }
    }
    for (int i = len - 2; i >= 0; i--) {
      for (int j = i + 1; j < len; j++) {
        if ((dp[j] - dp[i] + nums[i]) % k == 0) {
          return true;
        }
      }
    }
    return false;
  }
}
