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
 * Description：134. 加油站
 *
 * @author caokeyu
 * @since 2021/7/12
 */
public class Solution134 {

  public int canCompleteCircuit(int[] gas, int[] cost) {
    int cur = 0, total = 0, n = gas.length, start = 0;
    for (int i = 0; i < n; i++) {
      cur += gas[i] - cost[i];
      total += gas[i] - cost[i];
      if (cur < 0) {
        start = i + 1;
        cur = 0;
      }
    }
    return total < 0 ? -1 : start;
  }
}
