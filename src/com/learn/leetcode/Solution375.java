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
 * Description：猜数字大小 II
 *
 * @author caokeyu
 * @since 2021/4/21
 */
public class Solution375 {

  public int getMoneyAmount(int n) {
    int[][] dp = new int[n + 1][n + 1];
    for (int len = 2; len <= n; len++) {
      for (int start = 1; start <= n - len + 1; start++) {
        int minres = Integer.MAX_VALUE;
        for (int piv = start; piv < start + len - 1; piv++) {
          int res = piv + Math.max(dp[start][piv - 1], dp[piv + 1][start + len - 1]);
          minres = Math.min(res, minres);
        }
        dp[start][start + len - 1] = minres;
      }
    }
    return dp[1][n];
  }
}
