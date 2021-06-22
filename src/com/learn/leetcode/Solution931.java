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
 * Description：931. 下降路径最小和
 *
 * @author caokeyu
 * @since 2021/6/21
 */
public class Solution931 {

  public int minFallingPathSum(int[][] matrix) {
    int len = matrix.length;
    if (len == 1) {
      return matrix[0][0];
    }
    int[][] dp = new int[len][len];
    for (int i = 0; i < len; i++) {
      dp[0][i] = matrix[0][i];
    }
    int min = Integer.MAX_VALUE;
    for (int i = 1; i < len; i++) {
      for (int j = 0; j < len; j++) {
        if (j == 0) {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j];
        } else if (j == len - 1) {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + matrix[i][j];
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1])) + matrix[i][j];
        }
        if (i == len - 1) {
          min = Math.min(min, dp[i][j]);
        }
      }
    }
    return min;
  }
}
