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
 * Description：1049. 最后一块石头的重量 II
 *
 * @author caokeyu
 * @since 2021/6/15
 */
public class Solution1049 {

  public static void main(String[] args) {
    lastStoneWeightII(new int[] {31,26,33,21,40});
  }

  public static int lastStoneWeightII(int[] stones) {
    int sum = 0, len, l;
    for (int x : stones) {
      sum += x;
    }
    int[][] dp = new int[(len = stones.length) + 1][(l = sum / 2) + 1];
    for (int i = 1; i <= len; i++) {
      for (int j = 1; j <= l; j++) {
        if (j < stones[i - 1]) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
        }
      }
    }
    return sum - 2 * dp[len][l];
  }
}
