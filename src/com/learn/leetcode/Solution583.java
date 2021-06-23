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
 * Description：583. 两个字符串的删除操作
 *
 * @author caokeyu
 * @since 2021/6/21
 */
public class Solution583 {

  public static void main(String[] args) {
    minDistance("sea", "eat");
  }

  public static int minDistance(String word1, String word2) {
    int len1 = word1.length(), len2 =  word2.length();
    if (len1 == 0 || len2 == 0) {
      return Math.max(len1, len2);
    }
    int[][] dp = new int[len1 + 1][len2 + 1];
    for (int i = 0; i <= len1; i++) {
      dp[i][0] = i;
    }
    for (int i = 0; i <= len2; i++) {
      dp[0][i] = i;
    }
    for (int i = 1; i <= len1; i++) {
      for (int j = 1; j <= len2; j++) {
        if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
          dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
        } else {
          dp[i][j] = dp[i - 1][j - 1];
        }
      }
    }
    return dp[len1][len2];
  }
}
