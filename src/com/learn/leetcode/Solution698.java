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
 * Description：划分为k个相等的子集
 *
 * @author caokeyu
 * @since 2021/4/27
 */
public class Solution698 {

  public static void main(String[] args) {
    Solution698 solution698 = new Solution698();
    solution698.canPartitionKSubsets(new int[]{2,11,1,10,4,10,1,4,2}, 3);
  }

  public boolean canPartitionKSubsets(int[] nums, int k) {
    int len, avg, sum = 0;
    for (int i = 0; i < (len = nums.length); i++) {
      sum += nums[i];
    }
    if (sum % k > 0) {
      return false;
    }
    avg = sum / k;
    int c = 0;
    quickSort(0, len - 1, nums);
    boolean[] use = new boolean[len];
    for (int i = len - 1; i >= 0; i--) {
      if (use[i]) {
        continue;
      }
      if (nums[i] == avg) {
        ++c;
        continue;
      }
      use[i] = true;
      if (!dfs(avg, nums, len, use, nums[i]) || nums[i] > avg) {
        return false;
      }
      if (++c == k) {
        return true;
      }
    }
    return true;
  }

  private boolean dfs(int avg, int[] nums, int len, boolean[] use, int count) {
    for (int i = 0; i < len; i++) {
      if (!use[i]) {
        if (nums[i] + count < avg) {
          use[i] = true;
          if (dfs(avg, nums, len, use, count + nums[i])) {
            return true;
          }
          use[i] = false;
        } else if (nums[i] + count == avg) {
          use[i] = true;
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
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
