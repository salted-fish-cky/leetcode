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
 * Description：10. 正则表达式匹配
 *
 * @author caokeyu
 * @since 2021/7/8
 */
public class Solution10 {

  public boolean isMatch(String s, String p) {
    int m = s.length(), n = p.length();
    if (m == 0 && n == 0) {
      return true;
    }
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    for (int i = 0; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (p.charAt(j - 1) == '*') {
          dp[i][j] = dp[i][j - 2];
          if (match(i, j - 1, s, p)) {
            dp[i][j] = dp[i][j] || dp[i - 1][j];
          }
        } else {
          if (match(i, j, s, p)) {
            dp[i][j] = dp[i - 1][j - 1];
          }
        }
      }
    }
    return dp[m][n];
  }

  private boolean match(int i, int j, String s, String p) {
    if (i == 0) {
      return false;
    }
    if (p.charAt(j - 1) == '.') {
      return true;
    }
    return p.charAt(j - 1) == s.charAt(i - 1);
  }

}
