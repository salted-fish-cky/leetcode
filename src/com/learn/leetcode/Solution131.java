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
import java.util.LinkedList;
import java.util.List;

/**
 * Description：分割回文串
 *
 * @author caokeyu
 * @since 2021/4/18
 */
public class Solution131 {

  public static void main(String[] args) {
    Solution131 solution131 = new Solution131();
    solution131.partition("abbab");
  }

  public List<List<String>> partition(String s) {
    int len;
    boolean[][] dp = new boolean[len = s.length()][len];
    for (int i = 0; i < len; i++) {
      dp[i][i] = true;
    }
    for (int i = 1; i < len; i++) {
      for (int j = 0; j < i; j++) {
        boolean b = s.charAt(i) == s.charAt(j);
        if (i - 1 < j + 1) {
          dp[i][j] = b;
        } else {
          dp[i][j] = dp[i - 1][j + 1] && b;
        }
      }
    }
    List<List<String>> list = new LinkedList<>();
    LinkedList<String> l = new LinkedList<>();
    partition(s, 0, len, list, dp, l);
    return list;
  }

  private void partition(String s, int start, int len, List<List<String>> list, boolean[][] dp, LinkedList<String> l) {
    if (start == len) {
      List<String> ls = new LinkedList<>();
      ls.addAll(l);
      list.add(ls);
      return;
    }
    for (int i = start; i < len; i++) {
      if (dp[i][start]) {
        l.add(s.substring(start, i + 1));
        partition(s, i + 1, len, list, dp, l);
        l.removeLast();
      }
    }
  }
}
