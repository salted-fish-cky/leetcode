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
 * Description：
 *
 * @author caokeyu
 * @since 2021/4/27
 */
public class Solution718 {

  public int findLength(int[] nums1, int[] nums2) {
    int len1 = nums1.length, len2 = nums2.length;
    int[][] dp = new int[len1][len2];
    int max = 0;
    for (int i = 0; i < len1; i++) {
      for (int j = 0; j < len2; j++) {
        if (i == 0 || j == 0) {
          if (nums1[i] == nums2[j]) {
            dp[i][j] = 1;
          }
        } else {
          if (nums1[i] == nums2[j]) {
            if (nums1[i - 1] == nums2[j - 1]) {
              dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
              dp[i][j] = 1;
            }
          }
        }
        max = Math.max(max, dp[i][j]);
      }
    }
    return max;
  }
}
