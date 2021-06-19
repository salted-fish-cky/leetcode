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

import java.util.HashMap;
import java.util.Map;

/**
 * Description：560. 和为K的子数组
 *
 * @author caokeyu
 * @since 2021/6/17
 */
public class Solution560 {

  public int subarraySum(int[] nums, int k) {
    int len = nums.length, count = 0, pre = 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    for (int i = 0; i < len; i++) {
      pre += nums[i];
      if (map.containsKey(pre - k)) {
        count += map.get(pre - k);
      }
      map.put(pre, map.getOrDefault(pre, 0) + 1);
    }
    return count;
  }
}
