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
 * Description：1477. 找两个和为目标值且不重叠的子数组
 *
 * @author caokeyu
 * @since 2021/6/18
 */
public class Solution1447 {
  public int minSumOfLengths(int[] arr, int target) {
    int len;
    if ((len = arr.length) == 1) {
      return -1;
    }
    int[] dp = new int[len + 1];
    dp[0] = len + 1;
    int left = 0, right, sum = 0, minLen = Integer.MAX_VALUE;
    for (right = 0; right < len; right++) {
      sum += arr[right];
      while(sum > target) {
        sum -= arr[left++];
      }
      if (sum == target) {
        int l = right - left + 1;
        minLen = Math.min(minLen, l + dp[left]);
        dp[right + 1] = Math.min(dp[right], l);
      } else {
        dp[right + 1] = dp[right];
      }

    }
    return minLen > len ? -1 : minLen;
  }

}
