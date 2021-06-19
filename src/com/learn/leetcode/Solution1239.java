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

import java.util.Iterator;
import java.util.List;

/**
 * Description：1239. 串联字符串的最大长度
 *
 * @author caokeyu
 * @since 2021/6/19
 */
public class Solution1239 {

  public int maxLength(List<String> arr) {
    int[] nums = new int[26];
    Iterator<String> iterator = arr.iterator();
    while (iterator.hasNext()) {
      String s = iterator.next();
      if (hasAlikeCharacter(nums, s)) {
        iterator.remove();
      }
    }
    int len;
    if ((len = arr.size()) == 0) {
      return len;
    }
    int[][] dp = new int[len][len];
    for (int i = 0; i < len; i++) {
      dp[i][i] = arr.get(i).length();
    }
    for (int i = len - 2; i >= 0; i--) {
      for (int j = i + 1; j < len; j++) {
        String si = arr.get(i);
        String sj = arr.get(j);
        if (hasAlikeCharacter(nums, si + sj)) {
          dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
        } else {
          if (dp[i][j - 1] - si.length() == dp[i + 1][j] - sj.length()) {
            dp[i][j] = dp[i][j - 1] + arr.get(j).length();
          } else {
            dp[i][j] = Math.max(Math.max(dp[i][j - 1], dp[i + 1][j]), si.length() + sj.length());
          }
        }
      }
    }
    return dp[0][len - 1];
  }

  private boolean hasAlikeCharacter(int[] nums, String s) {
    int index = 0;
    boolean returnValue = false;
    while(index < s.length()) {
      int idx;
      nums[idx =s.charAt(index++) - 'a'] += 1;
      if (nums[idx] > 1) {
        break;
      }
    }
    for (int i = 0; i < 26; i++) {
      if (nums[i] > 1) {
        returnValue = true;
      }
      nums[i] = 0;
    }
    return returnValue;
  }
}
