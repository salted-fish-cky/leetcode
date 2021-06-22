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
 * Description：75. 颜色分类
 *
 * @author caokeyu
 * @since 2021/6/22
 */
public class Solution75 {

  public void sortColors(int[] nums) {
    quickSort(nums, 0, nums.length - 1);
  }

  private void quickSort(int[] nums, int left, int right) {
    int l = left, r = right;
    if (left < right) {
      int target = nums[left];
      while (left < right) {
        while (left < right && nums[right] > target) {
          right--;
        }
        if (left < right) {
          nums[left++] = nums[right];
        }
        while (left < right && nums[left] <= target) {
          left++;
        }
        if (left < right) {
          nums[right--] = nums[left];
        }
      }
      nums[left] = target;
      quickSort(nums, l, left - 1);
      quickSort(nums, left + 1, r);
    }
  }
}
