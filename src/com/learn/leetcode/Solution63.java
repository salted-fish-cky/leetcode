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
 * Description：不同路径 II
 *
 * @author caokeyu
 * @since 2021/4/17
 */
public class Solution63 {

  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m, n;
    if ((m = obstacleGrid.length) == 0 || (n = obstacleGrid[0].length) == 0) {
      return 0;
    }
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (obstacleGrid[i][j] == 1) {
          continue;
        }
        if (i == 0 && j == 0) {
          dp[i][j] = 1;
          continue;
        }
        if (i == 0) {
          dp[i][j] = dp[i][j - 1];
          continue;
        }
        if (j == 0) {
          dp[i][j] = dp[i - 1][j];
          continue;
        }
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }

    }
    return dp[m - 1][n - 1];
  }
}
