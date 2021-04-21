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
 * Description：组合总和 Ⅳ
 *
 * @author caokeyu
 * @since 2021/4/20
 */
public class Solution377 {

  public int combinationSum4(int[] nums, int target) {
    int len;
    quickSort(0, (len = nums.length) - 1, nums);
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for (int i = 0; i <= target; i++) {
      for (int j = 0; j < len; j++) {
        if (i < nums[j]) {
          break;
        }
        dp[i] += dp[i - nums[j]];
      }
    }
    return dp[target];
  }

  private void quickSort(int start, int end, int[] nums) {
    if (start < end) {
      int l = start, r = end;
      int temp = nums[start];
      while(start < end) {
        while(start < end && nums[end] > temp) {
          end--;
        }
        if (start < end) {
          nums[start++] = nums[end];
        }
        while (start < end && nums[start] <= temp) {
          start++;
        }
        if (start < end) {
          nums[end--] = nums[start];
        }
      }
      nums[start] = temp;
      quickSort(l, start - 1, nums);
      quickSort(start + 1, r, nums);
    }
  }
}
