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
 * Description：最大加号标志
 *
 * @author caokeyu
 * @since 2021/4/28
 */
public class Solution764 {

  public int orderOfLargestPlusSign(int N, int[][] mines) {
    int mineLen;
    if ((mineLen = mines.length) == 0) {
      if ((N & 1) == 0) {
        return N / 2;
      }
      return N / 2 + 1;
    }
    int[][][] dp = new int[N][N][4];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < 4; k++) {
          dp[i][j][k] = 1;
        }
      }
    }
    for(int[] arr : mines) {
      for (int i = 0; i < 4; i++) {
        dp[arr[0]][arr[1]][i] = 0;
      }
    }
    for (int i = 1; i < N; i++) {
      for (int j = 1; j < N; j++) {
        if (dp[i][j][0] != 0) {
          dp[i][j][0] = dp[i - 1][j][0] + 1;
          dp[i][j][1] = dp[i][j - 1][1] + 1;
        }

      }
    }
    for (int i = N - 2; i >= 0; i--) {
      for (int j = N - 2; j >= 0; j--) {
        if(dp[i][j][2] != 0) {
          dp[i][j][2] = dp[i + 1][j][2] + 1;
          dp[i][j][3] = dp[i][j + 1][3] + 1;
        }
      }
    }
    int max = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        max = Math.max(max, Math.min(dp[i][j][0],
                Math.min(dp[i][j][1], Math.min(dp[i][j][2], dp[i][j][3]))));
      }
    }
    return max;
  }
}
