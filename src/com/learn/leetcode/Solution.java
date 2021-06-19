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

import java.util.Arrays;

/**
 * Description：801. 使序列递增的最小交换次数
 *
 * @author caokeyu
 * @since 2021/6/19
 */
public class Solution {

  public int minSwap(int[] nums1, int[] nums2) {
    int len;
    if ((len = nums1.length) <= 1) {
      return 0;
    }
    int[][] dp = new int[len + 1][2];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[1][0] = 0;
    dp[1][1] = 1;
    for (int i = 1; i < len; i++) {
      if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
        if (nums2[i] > nums1[i - 1] && nums1[i] > nums2[i - 1]) {
          dp[i + 1][0] = Math.min(dp[i][0], dp[i][1]);
          dp[i + 1][1] = Math.min(dp[i][0], dp[i][1]) + 1;
        } else {
          dp[i + 1][0] = dp[i][0];
          dp[i + 1][1] = dp[i][1] + 1;
        }
      } else {
        dp[i + 1][0] = dp[i][1];
        dp[i + 1][1] = dp[i][0] + 1;
      }
    }
    return Math.min(dp[len][0], dp[len][1]);
  }
}
