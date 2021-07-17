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
 * Description：215. 数组中的第K个最大元素
 *
 * @author caokeyu
 * @since 2021/7/2
 */
public class Solution215 {

  public static void main(String[] args) {
    Solution215 s = new Solution215();
    s.findKthLargest(new int[]{3,2,1,5,6,4}, 2);
  }

  public int findKthLargest(int[] nums, int k) {
    return heap(nums, k);
  }

  private int heap(int[] nums, int k) {
    for (int i = nums.length / 2 - 1; i >= 0; i--) {
      toHeap(nums, i, nums.length);
    }
    int res = 0;
    for (int i = nums.length - 1; i >= 0 ; i--) {
      toHeap(nums, 0, i + 1);
      int temp = nums[0];
      nums[0] = nums[i];
      nums[i] = temp;
      if (--k == 0) {
        res = temp;
        break;
      }
    }
    return res;
  }

  private void toHeap(int[] nums, int index, int len) {
    int target = nums[index];
    int k = 2 * index + 1;
    for (; k < len; k = 2 * k + 1) {
      if (k < len - 1 && nums[k] < nums[k + 1]) {
        k++;
      }
      if (target < nums[k]) {
        nums[index] = nums[k];
        index = k;
      } else {
        break;
      }
    }
    nums[index] = target;
  }
}
