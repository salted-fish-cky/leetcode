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
 * Description：461. 汉明距离
 *
 * @author caokeyu
 * @since 2021/5/27
 */
public class Solution461 {

  public int hammingDistance(int x, int y) {
    int xCount = 0, yCount = 0, x1 = x, y1 = y;
    while(x1 != 0) {
      x1 = x1 & (x1 - 1);
      xCount++;
    }
    while (y1 != 0) {
      y1 = y1 & (y1 - 1);
      yCount++;
    }
    x1 = x & y;
    y1 = 0;
    while (x1 != 0) {
      x1 = x1 & (x1 - 1);
      y1++;
    }
    return xCount - y1 + yCount - y1;
  }
}
