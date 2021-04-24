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
 * Description：出界的路径数
 *
 * @author caokeyu
 * @since 2021/4/24
 */
public class Solution576 {

  public int findPaths(int m, int n, int N, int i, int j) {
    if (N == 0) {
      return 0;
    }
    int[][][] dp = new int[m][n][N + 1];
    for (int l = 1; l <= N; l++) {
      for (int k = 0; k < m; k++) {
        dp[k][0][l]++;
        dp[k][n - 1][l]++;
      }
      for (int k = 0; k < n; k++) {
        dp[0][k][l]++;
        dp[m - 1][k][l]++;
      }
    }
    int[][] opType = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    for (int l = 1; l <= N; l++) {
      for (int x = 0; x < m; x++) {
        for (int y = 0; y < n; y++) {
          for(int[] t : opType) {
            int nx = x + t[0];
            int ny = y + t[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
              dp[x][y][l] += dp[nx][ny][l - 1];
              dp[x][y][l] %= (int)1e9+7;
            }
          }
        }
      }
    }
    return dp[i][j][N];
  }
}
