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
 * Description：189. 旋转数组
 *
 * @author caokeyu
 * @since 2021/6/27
 */
public class Solution189 {

  public void rotate(int[] nums, int k) {
    int len = nums.length, temp = nums[0], count = len, targetIndex = 0, distinct = 0;
    while (len > 0) {
      int idx = (targetIndex + k) % count;
      int num = nums[idx];
      nums[idx] = temp;
      temp = num;
      targetIndex = idx;
      if (targetIndex == distinct && len != count) {
        temp = nums[distinct = ++targetIndex];
      }
      len--;
    }
  }
}
