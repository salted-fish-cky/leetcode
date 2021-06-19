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
 * Description：525. 连续数组
 *
 * @author caokeyu
 * @since 2021/6/17
 */
public class Solution525 {

  public int findMaxLength(int[] nums) {
    int len = nums.length, count = 0, maxLen = 0;
    Map<Integer, Integer> map = new HashMap<>((int)(len / 0.75f) + 1);
    map.put(0, -1);
    for (int i = 0; i < len; i++) {
      count = nums[i] == 0 ? count - 1 : count + 1;
      if (map.containsKey(count)) {
        maxLen = Math.max(maxLen, i - map.get(count));
      } else {
        map.put(count, i);
      }
    }
    return maxLen;
  }
}
