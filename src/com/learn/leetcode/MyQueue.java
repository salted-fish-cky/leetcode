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

import java.util.Stack;

/**
 * Description：232. 用栈实现队列
 *
 * @author caokeyu
 * @since 2021/5/27
 */
public class MyQueue {

  private Stack<Integer> pushStack;
  private Stack<Integer> popStack;

  /** Initialize your data structure here. */
  public MyQueue() {
    pushStack = new Stack<>();
    popStack = new Stack<>();
  }

  /** Push element x to the back of queue. */
  public void push(int x) {
    popToPush();
    pushStack.push(x);
  }

  /** Removes the element from in front of queue and returns that element. */
  public int pop() {
    Integer value = 0;
    pushToPop();
    return (value = popStack.pop()) == null ? 0 : value;
  }

  private void pushToPop() {
    while (!pushStack.isEmpty()) {
      popStack.push(pushStack.pop());
    }
  }

  private void popToPush() {
    while (!popStack.isEmpty()) {
      pushStack.push(popStack.pop());
    }
  }

  /** Get the front element. */
  public int peek() {
    Integer value = 0;
    pushToPop();
    return (value = popStack.peek()) == null ? 0 : value;
  }

  /** Returns whether the queue is empty. */
  public boolean empty() {
    return popStack.isEmpty() && pushStack.isEmpty();
  }
}
