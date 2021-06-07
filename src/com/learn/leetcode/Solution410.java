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
 * Description： 410. 分割数组的最大值
 *
 * @author caokeyu
 * @since 2021/6/7
 */
public class Solution410 {

  public int splitArray(int[] nums, int m) {
    int len = nums.length;
    int[][] dp = new int[len + 1][m + 1];
    for (int i = 0; i <= len; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    int[] sub = new int[len + 1];
    for (int i = 0; i < len; i++) {
      sub[i + 1] = sub[i] + nums[i];
    }
    dp[0][0] = 0;
    for (int i = 1; i <= len; i++) {
      int l = Math.min(i, m);
      for (int j = 1; j <= l; j++) {
        for (int k = j - 1; k < i; k++) {
          dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sub[i] - sub[k]));
        }
      }
    }
    return dp[len][m];
  }
}
