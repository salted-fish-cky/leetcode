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
 * Description：只有两个键的键盘
 *
 * @author caokeyu
 * @since 2021/4/25
 */
public class Solution650 {

  public static void main(String[] args) {
    minSteps(4);
  }

  public static int minSteps(int n) {
    int[] dp = new int[n + 1];
    dp[1] = 0;
    for (int i = 2; i <= n; i++) {
      dp[i] = Integer.MAX_VALUE;
      for (int j = 1; j <= i/2; j++) {
        if (i % j == 0) {
          dp[i] = Math.min(dp[i], dp[j] + i/j);
        }
      }
    }
    return dp[n];
  }
}
