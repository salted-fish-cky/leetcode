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

import java.util.Arrays;

/**
 * Description：最长递增子序列的个数
 *
 * @author caokeyu
 * @since 2021/4/26
 */
public class Solution673 {


  public int findNumberOfLIS(int[] nums) {
    int len;
    if ((len = nums.length) <= 0) {
      return len;
    }
    int[] max = new int[len];
    int[] count = new int[len];
    Arrays.fill(max, 1);
    Arrays.fill(count, 1);
    int maxLen = 0;
    for (int i = 1; i < len; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          int n;
          if ((n = max[j] + 1) > max[i]) {
            max[i] = n;
            count[i] = count[j];
          }else if (n == max[i]) {
            count[i] += count[j];
          }
        }
      }
      maxLen = Math.max(maxLen, max[i]);
    }
    int res = 0;
    for (int i = 0; i < len; i++) {
      if (maxLen == max[i]) {
        res += count[i];
      }
    }
    return res;
  }
}
