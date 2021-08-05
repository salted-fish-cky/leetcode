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
 * Description：1109. 航班预订统计
 *
 * @author caokeyu
 * @since 2021/6/23
 */
public class Solution1109 {

  public int[] corpFlightBookings(int[][] bookings, int n) {
    int[] res = new int[n];
    int[] diff = new int[n + 1];
    for (int i = 0; i < bookings.length; i++) {
      diff[bookings[i][0]] += bookings[i][2];
      if (bookings[i][1] == n) {
        continue;
      }
      diff[bookings[i][1] + 1] -= bookings[i][2];
    }
    res[0] = diff[1];
    for (int i = 1; i < n; i++) {
      res[i] = res[i - 1] + diff[i + 1];
    }
    return res;
  }
}
