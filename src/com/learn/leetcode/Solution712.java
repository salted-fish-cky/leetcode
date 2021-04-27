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
 * Description：两个字符串的最小ASCII删除和
 *
 * @author caokeyu
 * @since 2021/4/27
 */
public class Solution712 {

  public static void main(String[] args) {
    minimumDeleteSum("delete", "leet");
  }

  public static int minimumDeleteSum(String s1, String s2) {
    int len1 = s1.length(), len2 = s2.length();
    int[][] dp = new int[len1 + 1][len2 + 1];
    dp[0][0] = 0;
    for (int i = 1; i <= len1; i++) {
      dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
    }
    for (int i = 1; i <= len2; i++) {
      dp[0][i] = dp[0][i - 1] + s2.charAt(i - 1);
    }
    for (int i = 1; i <= len1; i++) {
      for (int j = 1; j <= len2; j++) {
        char c1;
        char c2;
        if ((c1 = s1.charAt(i - 1)) == (c2 = s2.charAt(j - 1))) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(dp[i][j - 1] + c2, dp[i - 1][j] + c1);
        }
      }
    }
    return dp[len1][len2];
  }
}
