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
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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
    int avg, sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }
    if (sum % k > 0) {
      return false;
    }
    avg = sum / k;
    boolean[] used = new boolean[nums.length];
    return backtrack(k, 0, nums,0, used, avg);
  }

  private boolean backtrack(int k, int bucket,
                    int[] nums, int start, boolean[] used, int target) {
    // base case
    if (k == 0) {
      // 所有桶都被装满了，而且 nums 一定全部用完了
      // 因为 target == sum / k
      return true;
    }
    if (bucket == target) {
      // 装满了当前桶，递归穷举下一个桶的选择
      // 让下一个桶从 nums[0] 开始选数字
      return backtrack(k - 1, 0 ,nums, 0, used, target);
    }

    // 从 start 开始向后探查有效的 nums[i] 装入当前桶
    for (int i = start; i < nums.length; i++) {
      // 剪枝
      if (used[i]) {
        // nums[i] 已经被装入别的桶中
        continue;
      }
      if (nums[i] + bucket > target) {
        // 当前桶装不下 nums[i]
        continue;
      }
      // 做选择，将 nums[i] 装入当前桶中
      used[i] = true;
      bucket += nums[i];
      // 递归穷举下一个数字是否装入当前桶
      if (backtrack(k, bucket, nums, i + 1, used, target)) {
        return true;
      }
      // 撤销选择
      used[i] = false;
      bucket -= nums[i];
    }
    // 穷举了所有数字，都无法装满当前桶
    return false;
  }
}
