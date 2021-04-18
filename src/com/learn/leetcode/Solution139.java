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

import java.util.ArrayList;
import java.util.List;

/**
 * Description：单词拆分
 *
 * @author caokeyu
 * @since 2021/4/18
 */
public class Solution139 {

  public static void main(String[] args) {
    List<String> l = new ArrayList<>();
    l.add("aaaa");
    l.add("aaa");
    wordBreak("aaaaaaa", l);
  }
  public static boolean wordBreak(String s, List<String> wordDict) {
    int len;
    boolean[] dp = new boolean[len = s.length()];
    dp[0] = wordDict.contains(s.substring(0, 1));
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < i; j++) {
        if (dp[j] && (dp[i] = wordDict.contains(s.substring(j + 1, i + 1)))) {
          break;
        }
      }
      if (i > 0) {
        dp[i] = (dp[i - 1] && wordDict.contains(s.substring(i, i + 1))) || dp[i] || wordDict.contains(s.substring(0, i + 1));
      }
    }
    return dp[len - 1];
  }
}
