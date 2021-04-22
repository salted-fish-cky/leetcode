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
 * Description：环绕字符串中唯一的子字符串
 *
 * @author caokeyu
 * @since 2021/4/22
 */
public class Solution467 {

  public int findSubstringInWraproundString(String p) {
    int[] dp = new int[26];
    char[] chars = p.toCharArray();
    int cn = 1, sum = 0;
    dp[chars[0] - 'a'] = 1;
    for (int i = 1; i < chars.length; i++) {
      int index = chars[i] - 'a';
      if (check(chars[i - 1], chars[i])) {
        cn++;
      } else {
        cn = 1;
      }
      dp[index] = Math.max(dp[index], cn);
    }

    for (int i = 0; i < 26; i++) {
      sum += dp[i];
    }
    return sum;
  }

  private boolean check(char a, char b) {
    if (a == 'z' && b == 'a') {
      return true;
    }
    return b - a == 1;
  }
}
