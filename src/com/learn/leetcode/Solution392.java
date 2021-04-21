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
 * Description：判断子序列
 *
 * @author caokeyu
 * @since 2021/4/21
 */
public class Solution392 {

  public boolean isSubsequence(String s, String t) {
    int len1, len2 = t.length(), sp = 0, tp = 0;
    if ((len1 = s.length()) == 0) {
      return true;
    }
    while(tp < len2) {
      sp = (s.charAt(sp) == t.charAt(tp++) ? sp + 1 : sp);
      if (sp == len1) {
        return true;
      }
    }
    return false;
  }
}
