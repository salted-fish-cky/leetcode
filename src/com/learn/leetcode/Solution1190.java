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
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Description：1190.反转每对括号间的子串
 *
 * @author caokeyu
 * @since 2021/5/26
 */
public class Solution1190 {

  public String reverseParentheses(String s) {
    int len;
    if ((len = s.length()) < 2) {
      return s;
    }
    Stack<Character> stack = new Stack();
    List<Character> l = new LinkedList<>();
    int index = 0;
    while (index < len) {
      char c = s.charAt(index++);
      if (c == ')') {
        char cc = stack.pop();
        while(cc != '(') {
          l.add(cc);
          cc = stack.pop();
        }
        Iterator<Character> iterator = l.iterator();
        while (iterator.hasNext()) {
          stack.push(iterator.next());
        }
        l.clear();
      } else {
        stack.push(c);
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < stack.size(); i++) {
      sb.append(stack.get(i));
    }
    return sb.toString();
  }
}
