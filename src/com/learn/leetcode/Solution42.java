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
 * Description：42. 接雨水
 *
 * @author caokeyu
 * @since 2021/6/24
 */
public class Solution42 {

  public int trap(int[] height) {
    int len;
    if ((len = height.length) == 0) {
      return 0;
    }
    int[] leftMax = new int[len];
    int[] rightMax = new int[len];
    for (int i = 1; i < len - 1; i++) {
      leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
    }
    for (int i = len - 2; i > 0 ; i--) {
      rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
    }
    int res = 0;
    for (int i = 1; i < len - 1; i++) {
      int max = Math.min(leftMax[i], rightMax[i]);
      if (max > height[i]) {
        res += max - height[i];
      }
    }
    return res;
  }
}
