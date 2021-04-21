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
 * Description：等差数列划分
 *
 * @author caokeyu
 * @since 2021/4/21
 */
public class Solution413 {

  public int numberOfArithmeticSlices(int[] nums) {
    int len;
    if ((len = nums.length) < 3) {
      return 0;
    }
//    boolean[][] dp = new boolean[len][len];
//    int res = 0;
//    for (int i = 2; i < len; i++) {
//      for (int j = 0; j < i - 1; j++) {
//        if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
//          dp[j][i] = (i - j < 3) ? true : dp[j][i - 1];
//          res = dp[j][i] ? res + 1 : res;
//        }
//      }
//    }
//    return res;

    int[] dp = new int[len];
    int sum = 0;
    for (int i = 2; i < len; i++) {
      if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
        dp[i] = dp[i - 1] + 1;
        sum += dp[i];
      }
    }
    return sum;
  }

}
