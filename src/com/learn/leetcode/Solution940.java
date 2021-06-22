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
 * Description：940. 不同的子序列 II
 *
 * @author caokeyu
 * @since 2021/6/20
 */
public class Solution940 {

  public int distinctSubseqII(String s) {
    int len, mod = 1000_000_000 + 7;
    if ((len = s.length()) <= 1) {
      return len;
    }
    int[] dp = new int[len + 1];
    int[] arr = new int[26];
    for (int i = 0; i < 26; i++) {
      arr[i] = -1;
    }
    dp[0] = 1;
    for (int i = 0; i < len; i++) {
      dp[i + 1] = dp[i] * 2 % mod;
      int x = s.charAt(i) - 'a';
      if (arr[x] >= 0) {
        dp[i + 1] -= dp[arr[x]];
      }
      dp[i + 1] %= mod;
      arr[x] = i;
    }
    return --dp[len] < 0 ? dp[len] += mod : dp[len];
  }
}
