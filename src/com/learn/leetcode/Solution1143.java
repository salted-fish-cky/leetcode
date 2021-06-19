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
 * Description：1143. 最长公共子序列
 *
 * @author caokeyu
 * @since 2021/6/19
 */
public class Solution1143 {

  public static void main(String[] args) {
    longestCommonSubsequence("abcde", "ace");
  }

  public static int longestCommonSubsequence(String text1, String text2) {
    int len1, len2;
    int[][] dp = new int[(len1 = text1.length()) + 1][(len2 = text2.length()) + 1];
    for (int j = 1; j <= len2; j++) {
      for (int i = 1; i <= len1; i++) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j]= Math.max(dp[i][j - 1], dp[i - 1][j]);
        }
      }
    }
    return dp[len1][len2];
  }
}
