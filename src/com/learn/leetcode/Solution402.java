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

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Description：402. 移掉 K 位数字
 *
 * @author caokeyu
 * @since 2021/7/14
 */
public class Solution402 {

  public String removeKdigits(String num, int k) {
    Deque<Character> stack = new LinkedList<>();
    for (int i = 0; i < num.length(); i++) {
      while (!stack.isEmpty() && stack.peek() > num.charAt(i) && k > 0) {
        stack.pop();
        k--;
      }
      if (num.charAt(i) == '0' && stack.isEmpty()) {
        continue;
      }
      stack.push(num.charAt(i));
    }
    while (k-- > 0 && !stack.isEmpty()) {
      stack.pop();
    }
    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pollLast());
    }
    return sb.length() == 0 ? "0" : sb.toString();
  }
}
