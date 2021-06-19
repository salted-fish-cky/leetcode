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
 * Description：713. 乘积小于K的子数组
 *
 * @author caokeyu
 * @since 2021/6/18
 */
public class Solution713 {


  public int numSubarrayProductLessThanK(int[] nums, int k) {
    int len = nums.length;
    if (k <= 1) {
      return 0;
    }
    int l = 0, r, num = 0, sum = 1;
    for (r = 0; r < len; r++) {
      sum *= nums[r];
      while(sum >= k) {
        sum /= nums[l++];
      }
      num += r - l + 1;
    }
    return num;
  }
}
