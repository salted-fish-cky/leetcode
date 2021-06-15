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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：350. 两个数组的交集 II
 *
 * @author caokeyu
 * @since 2021/6/15
 */
public class Solution350 {

  public int[] intersect(int[] nums1, int[] nums2) {
    int len1 = nums1.length, len2 = nums2.length;
    Map<Integer, Integer> map = new HashMap<>((int)(len1 / 0.75f) + 1);
    for (int i : nums1) {
      Integer num = map.get(i) == null ? 0 : map.get(i);
      map.put(i, ++num);
    }
    List<Integer> result = new ArrayList<>();
    for (int j : nums2) {
      Integer num = map.get(j);
      if (num != null && num > 0) {
        map.put(j, --num);
        result.add(j);
      }
    }
    int[] n = new int[result.size()];
    for (int i = 0; i < result.size(); i++) {
      n[i] = result.get(i);
    }
    return n;
  }

}
