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
 * Description：交错字符串
 *
 * @author caokeyu
 * @since 2021/4/18
 */
public class Solution97 {

  public boolean isInterleave(String s1, String s2, String s3) {
    int len1, len2 = 0;
    if ((len1 = s1.length()) + (len2 = s2.length()) != s3.length()) {
      return false;
    }
    boolean[][] dp = new boolean[len1 + 1][len2 + 1];
    dp[0][0] = true;
    int l;
    for (int i = 0; i <= len1; i++) {
      for (int j = 0; j <= len2; j++) {
        l = i + j - 1;
        if (i > 0) {
          dp[i][j] = dp[i][j] || dp[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(l));
        }
        if (j > 0) {
          dp[i][j] = dp[i][j] || dp[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(l));
        }
      }

    }

    return dp[len1][len2];

  }
}
