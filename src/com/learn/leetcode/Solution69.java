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
 * Description：69. x 的平方根
 *
 * @author caokeyu
 * @since 2021/7/14
 */
public class Solution69 {

  public int mySqrt(int x) {
    int l = 0, r = x, ans = -1;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      if ((long)mid * mid <= x) {
        ans = mid;
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    return ans;
  }
}
