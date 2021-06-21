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
 * Description：1477. 找两个和为目标值且不重叠的子数组
 *
 * @author caokeyu
 * @since 2021/6/16
 */
public class Solution1477 {

  public int minSumOfLengths(int[] arr, int target) {
    int len, ts;
    int[][] dp = new int[(len = arr.length) + 1][(ts = target * 2) + 1];
    for (int i = 0; i <= len; i++) {
      for (int j = 0; j <= ts; j++) {
        dp[i][j] = i == 0 ? 0 : Integer.MAX_VALUE;
      }
    }
    for (int i = 1; i <= len; i++) {
      int t = arr[i - 1];
      for (int j = 1; j <= ts; j++) {
        if (j >= t) {
          int tt = dp[i - 1][j - t] + 1;
          dp[i][j] = Math.min(dp[i - 1][j], tt);
        }
      }

    }
    return dp[len][ts] < 2 ? -1 : dp[len][ts];
  }
}
