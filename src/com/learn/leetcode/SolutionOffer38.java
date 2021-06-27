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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description：剑指 Offer 38. 字符串的排列
 *
 * @author caokeyu
 * @since 2021/6/26
 */
public class SolutionOffer38 {

  public String[] permutation(String s) {
    int len;
    if ((len = s.length()) == 1) {
      return new String[]{s};
    }
    boolean[] ch = new boolean[len];
    Set<String> set = new HashSet<>();
    dfs(set, ch, s, new StringBuilder(), 0);
    String[] res = new String[set.size()];
    return set.toArray(res);
  }
  private void dfs(Set<String> set, boolean[] ch, String s, StringBuilder sb, int level) {
    if (level == s.length()) {
      String target = sb.toString();
      set.add(target);
      return;
    }
    int index = 0;
    while (index < s.length()) {
      if (!ch[index]) {
        char c = s.charAt(index);
        sb.append(c);
        ch[index] = true;
        dfs(set, ch, s, sb, level + 1);
        ch[index] = false;
        sb.delete(level, level + 1);
      }
      index++;
    }
  }
}
