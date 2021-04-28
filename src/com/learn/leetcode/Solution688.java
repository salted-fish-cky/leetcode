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
 * Description：“马”在棋盘上的概率
 *
 * @author caokeyu
 * @since 2021/4/27
 */
public class Solution688 {

  public static void main(String[] args) {
    knightProbability(3,2,0,0);
  }

  public static double knightProbability(int n, int k, int row, int column) {
    if (k == 0) {
      return 1;
    }
    double[][][] dp = new double[k + 1][n][n];
    int[][] bound = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        dp[0][i][j] = 1;
      }
    }
    for (int l = 1; l <= k; l++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          dp[l][i][j] = 0;
          for (int m = 0; m < 8; m++) {
            int ni = bound[m][0] + i;
            int nj = bound[m][1] + j;
            if (ni >= 0  && nj >= 0 && ni < n && nj < n) {
              dp[l][i][j] += dp[l - 1][ni][nj];
            }
          }
        }
      }
    }
    return dp[k][row][column] / Math.pow(8, k);
  }
}
