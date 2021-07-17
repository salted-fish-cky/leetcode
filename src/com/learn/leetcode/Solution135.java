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
 * Description：135. 分发糖果
 *
 * @author caokeyu
 * @since 2021/7/17
 */
public class Solution135 {

  public int candy(int[] ratings) {
    int n = ratings.length;
    int[] left = new int[n];
    for (int i = 0; i < n; i++) {
      if (i > 0 && ratings[i] > ratings[i - 1]) {
        left[i] = left[i - 1] + 1;
      } else {
        left[i] = 1;
      }
    }
    int right = 0, ret = 0;
    for (int i = n - 1; i >= 0; i--) {
      if (i < n - 1 && ratings[i] > ratings[i + 1]) {
        right++;
      } else {
        right = 1;
      }
      ret += Math.max(left[i], right);
    }
    return ret;
  }

}
