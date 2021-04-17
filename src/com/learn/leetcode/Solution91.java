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

import java.util.concurrent.ForkJoinPool;

/**
 * Description：解码方法
 *
 * @author caokeyu
 * @since 2021/4/17
 */
public class Solution91 {

  public static void main(String[] args) {
    numDecodings("226");
  }

  public static int numDecodings(String s) {
    int len;
    int[] dp = new int[len = s.length()];
    if (s.charAt(0) == '0') {
      return 0;
    }
    dp[0] = 1;
    for (int i = 1; i < len; i++) {
      int num;
      if ((num = s.charAt(i) - '0') == 0) {
        if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
          if (i == 1) {
            dp[i] = 1;
          } else {
            dp[i] = dp[i - 2];
          }
        } else {
          return 0;
        }
      } else {
        int n = num + (s.charAt(i - 1) - '0') * 10;
        if (n >= 11 && n <= 26) {
          if (i == 1) {
            dp[i] = dp[i - 1] + 1;
          } else {
            dp[i]= dp[i - 1] + dp[i - 2];
          }
        } else {
          dp[i] = dp[i - 1];
        }
      }
    }
    return dp[len - 1];
  }

  private static int dfs(String s ,int startIndex, int endIndex, int len, int count) {
    if (startIndex != endIndex && endIndex >= len) {
      return 0;
    }
    if (endIndex >= len) {
      return 1;
    }
    int n1 = s.charAt(startIndex) - '0';
    if (startIndex == endIndex && n1 == 0) {
      return 0;
    } else if (startIndex != endIndex && (n1 == 0 || n1 > 2 || s.charAt(endIndex) - '0' > 6)) {
      return 0;
    }
    count += dfs(s, endIndex + 1, endIndex + 1, len, 0);
    count += dfs(s, endIndex + 1, endIndex + 2, len, 0);
    return count;
  }
}
