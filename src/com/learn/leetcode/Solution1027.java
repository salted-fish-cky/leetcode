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
 * Description：1027. 最长等差数列
 *
 * @author caokeyu
 * @since 2021/6/22
 */
public class Solution1027 {

  public int longestArithSeqLength(int[] nums) {
    int len;
    if ((len = nums.length) < 3) {
      return len;
    }
    int max = 0;
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>((int)(len/0.75f) + 1);
    map.put(0, new HashMap<>());
    for (int i = 1; i < len; i++) {
        map.put(i, new HashMap<>());
      for (int j = i - 1; j >= 0; j--) {
        if (map.get(i).containsKey(nums[i] - nums[j])) {
          continue;
        }
        int cur = map.get(j).getOrDefault(nums[i] - nums[j], 1);
        max = Math.max(max, cur + 1);
        map.get(i).put(nums[i] - nums[j], cur + 1);
      }
    }
    return max;
  }
}
