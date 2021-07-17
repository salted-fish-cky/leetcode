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
 * Description：151. 翻转字符串里的单词
 *
 * @author caokeyu
 * @since 2021/7/3
 */
public class Solution151 {


  public String reverseWords(String s) {
    String[] strs = s.split(" ");
    StringBuilder sb = new StringBuilder();
    for (int i = strs.length - 1; i >= 0; i--) {
      String st = strs[i];
      if ("".equals(st)) {
        continue;
      }
      sb.append(st).append(" ");
    }
    return sb.toString().trim();
  }
}
