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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * Description：最大整除子集
 *
 * @author caokeyu
 * @since 2021/4/20
 */
public class Solution368 {

  public List<Integer> largestDivisibleSubset(int[] nums) {
    int len;
    quickSort(0, (len = nums.length) - 1, nums);
    List<Integer>[] dp = new List[len];
    dp[0] = Arrays.asList(nums[0]);
    int cSize = 0, index = 0;
    for (int i = 1; i < len; i++) {
      List<Integer> l = new LinkedList<>();
      for (int j = 0; j < i; j++) {
        if (nums[i] % nums[j] == 0 && dp[j].size() >= l.size() + 1) {
          l.clear();
          l.addAll(dp[j]);
        }
      }
      l.add(nums[i]);
      dp[i] = l;
      if (l.size() > cSize) {
        index = i;
        cSize = l.size();
      }
    }
    return dp[index];
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
