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
 * Description：279. 完全平方数
 *
 * @author caokeyu
 * @since 2021/6/11
 */
public class Solution279 {

  public int numSquares(int n) {
    if (isSquareNum(n)) {
      return 1;
    }
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      if (isSquareNum(i)) {
        dp[i] = 1;
        continue;
      }
      for (int j = 1; j < i; j++) {
        dp[i] = Math.min(dp[j] + dp[i - j], dp[i]);
      }
    }
    return dp[n];

  }

  public boolean isSquareNum(int n) {
    if (n == 1) {
      return true;
    }
    int m = n / 2, r;
    for (int i = 1; i <= m; i++) {
      if ((r = i * i) == n) {
        return true;
      } else if (r > n) {
        return false;
      }
    }
    return false;
  }
}
