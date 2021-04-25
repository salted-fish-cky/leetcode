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
 * Description：最长数对链
 *
 * @author caokeyu
 * @since 2021/4/25
 */
public class Solution646 {


  public int findLongestChain(int[][] pairs) {
    int len;
    quickSort(0, (len = pairs.length) - 1, pairs);
    int max = 1, temp = pairs[0][1];
    for (int i = 1; i < len; i++) {
      if (pairs[i][0] > temp) {
        max++;
        temp = pairs[i][1];
      }
    }
    return max;
  }

  private void quickSort(int start, int end, int[][] nums) {
    if (start < end) {
      int l = start, r = end;
      int[] temp = nums[start];
      while(start < end) {
        while(start < end && nums[end][1] > temp[1]) {
          end--;
        }
        if (start < end) {
          nums[start++] = nums[end];
        }
        while (start < end && nums[start][1] <= temp[1]) {
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
