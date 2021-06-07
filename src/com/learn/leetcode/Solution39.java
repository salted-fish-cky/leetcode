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
 * Description：剑指 Offer 39. 数组中出现次数超过一半的数字
 *
 * @author caokeyu
 * @since 2021/5/27
 */
public class Solution39 {
  public int majorityElement(int[] nums) {
    int len = nums.length;
    Map<Integer, Integer> map = new HashMap<>((int)(len / 0.75f) + 1);
    Integer temp = 0;
    int max = 0;
    for(int n : nums) {
      int count = (temp = map.get(n)) == null ? 0 : temp;
      if (++count > len / 2) {
        return n;
      }
      map.put(n, count);
    }
    return 0;
  }
}
