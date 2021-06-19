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
 * Description：724. 寻找数组的中心下标
 *
 * @author caokeyu
 * @since 2021/6/18
 */
public class Solution724 {

  public int pivotIndex(int[] nums) {
    int len;
    if ((len = nums.length) == 0) {
      return -1;
    }
    int[] pre = new int[len + 1];
    for (int i = 1; i <= len; i++) {
      pre[i] = pre[i - 1] + nums[i - 1];
    }
    for (int i = 0; i < len; i++) {
      if (pre[len] - pre[i + 1] == pre[i] - pre[0]) {
        return i;
      }
    }
    return -1;
  }
}
