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

import java.util.Arrays;

/**
 * Description：34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * @author caokeyu
 * @since 2021/6/22
 */
public class Solution34 {

  public int[] searchRange(int[] nums, int target) {
    int len;
    int[] res = new int[2];
    Arrays.fill(res, -1);
    if ((len = nums.length) == 0) {
      return res;
    }
    int l = 0, r = len - 1, mid;
    while (l <= r) {
      mid = (l + r) / 2;
      if (nums[mid] > target) {
        r = mid - 1;
      } else if (nums[mid] < target) {
        l = mid + 1;
      } else {
        res[0] = res[1] = mid;
        for (int i = mid + 1; i <= r; i++) {
          if (nums[i] == target) {
            res[1] = i;
          }
        }
        for (int i = mid - 1; i >= l; i--) {
          if (nums[i] == target) {
            res[0] = i;
          }
        }
        break;
      }
    }
    return res;
  }
}
