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
 * Description：摆动序列
 *
 * @author caokeyu
 * @since 2021/4/21
 */
public class Solution376 {

  public static void main(String[] args) {
    wiggleMaxLength(new int[]{5,2,9,4,7,1});
  }
  public static int wiggleMaxLength(int[] nums) {
    int len;
    if ((len = nums.length) < 2) {
      return len;
    }
    int[] dp1 = new int[len];
    int[] dp2 = new int[len];
    int max = 0;
    dp1[0] = dp2[0] = 1;
    for (int i = 1; i < len; i++) {
        if (nums[i] > nums[i - 1]) {
          dp1[i] = Math.max(dp2[i - 1] + 1, dp1[i - 1]);
          dp2[i] = dp2[i - 1];
        } else if (nums[i] < nums[i - 1]) {
          dp2[i] = Math.max(dp1[i - 1] + 1, dp2[i - 1]);
        } else {
          dp1[i] = dp1[i - 1];
          dp2[i] = dp2[i - 1];
        }
    }
    max = Math.max(max, Math.max(dp1[len - 1], dp2[len - 1]));
    return max;
  }
}
