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
 * Description：115. 不同的子序列
 *
 * @author caokeyu
 * @since 2021/6/19
 */
public class Solution115 {

  public static void main(String[] args) {
    numDistinct("rabbbit", "rabbit");
  }

  public static int numDistinct(String s, String t) {
    int len1 = s.length(), len2 = t.length();
    int[][] dp = new int[len2 + 1][len1 + 1];
    for (int j = 0; j <= len1; j++) {
      dp[0][j] = 1;
    }
    for (int i = 1; i <= len2; i++) {
      for (int j = 1; j <= len1; j++) {
        if (t.charAt(i - 1) == s.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
        } else {
          dp[i][j] = dp[i][j - 1];
        }
      }
    }
    return dp[len2][len1];
  }
}
