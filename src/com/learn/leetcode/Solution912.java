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
 * Description：912. 排序数组
 *
 * @author caokeyu
 * @since 2021/6/23
 */
public class Solution912 {

  public int[] sortArray(int[] nums) {
    int[] dup = new int[nums.length];
    mergeSort(nums, dup, 0, nums.length - 1);
    return nums;
  }

  /**
   * 快排
   * @param nums
   * @param start
   * @param end
   */
  private void quickSort(int[] nums, int start, int end) {
    if (start < end) {
      int l = start, r = end;
      int middle = nums[l];
      while (l < r) {
        while (l < r && middle <= nums[r]) {
          r--;
        }
        if (l < r) {
          nums[l++] = nums[r];
        }
        while (l < r && middle > nums[l]) {
          l++;
        }
        if (l < r) {
          nums[r--] = nums[l];
        }
      }
      nums[l] = middle;
      quickSort(nums, start, l - 1);
      quickSort(nums, l + 1, end);
    }
  }

  /**
   * 归并排序
   * @param nums
   * @param dup
   * @param start
   * @param end
   */
  private void mergeSort(int[] nums, int[] dup, int start, int end) {
    if (start >= end) {
      return;
    }
    int mid = (start + end) / 2;
    mergeSort(nums, dup, start, mid);
    mergeSort(nums, dup, mid + 1, end);
    if (nums[mid] <= nums[mid + 1]) {
      return;
    }
    merge(nums, dup, start, end, mid);
  }

  private void merge(int[] nums, int[] dup, int start, int end, int mid) {
    int k = start, l = start,  lEnd = mid, rStart = mid + 1;
    while (start <= lEnd && rStart <= end) {
      dup[k++] = nums[start] < nums[rStart] ? nums[start++] : nums[rStart++];
    }
    while (start <= lEnd) {
      dup[k++] = nums[start++];
    }
    while (rStart <= end) {
      dup[k++] = nums[rStart++];
    }
    for (int i = l; i < k; i++) {
      nums[i] = dup[i];
    }
  }

  /**
   * 堆排序
   * @param nums
   * @return
   */
  private int[] heapSort(int[] nums) {
    int len;
    int index = (len = nums.length) / 2 - 1;
    for (int i = index; i >= 0; i--) {
      toHeap(index, nums, len);
    }
    int temp;
    for (int i = len - 1; i >= 0; i--) {
      temp = nums[0];
      nums[0] = nums[i];
      nums[i] = temp;
      toHeap(0, nums, i);
    }
    return nums;
  }
  private void toHeap(int index, int[] nums, int end) {
    int target = nums[index];
    int k = 2 * index + 1;
    for (; k < end; k = 2 * k + 1) {
      if (k + 1 < end && nums[k + 1] > nums[k]) {
        k++;
      }
      if (nums[k] > target) {
        nums[index] = nums[k];
        index = k;
      } else {
        break;
      }
    }
    nums[index] = target;
  }
}
