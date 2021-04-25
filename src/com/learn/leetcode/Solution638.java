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
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Description：大礼包
 *
 * @author caokeyu
 * @since 2021/4/25
 */
public class Solution638 {

  public static void main(String[] args) {
    List<Integer> price = new ArrayList<>();
    price.add(4);
    price.add(3);
    price.add(2);
    price.add(9);
    price.add(8);
    price.add(8);
    List<List<Integer>> special = new ArrayList<>();
    List<Integer> l = new ArrayList<>();
    l.add(1);
    l.add(5);
    l.add(5);
    l.add(1);
    l.add(4);
    l.add(0);
    l.add(18);
    special.add(l);
    l = new ArrayList<>();
    l.add(3);
    l.add(3);
    l.add(6);
    l.add(6);
    l.add(4);
    l.add(2);
    l.add(32);
    special.add(l);
    List<Integer> needs = new ArrayList<>();
    needs.add(6);
    needs.add(5);
    needs.add(5);
    needs.add(6);
    needs.add(4);
    needs.add(1);
    System.out.println(shoppingOffers(price, special, needs));
  }

  public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    Iterator<List<Integer>> iterator = special.iterator();
    while (iterator.hasNext()) {
      List<Integer> l = iterator.next();
      for (int i = 0; i < price.size(); i++) {
        if (l.get(i) > needs.get(i)) {
          iterator.remove();
          break;
        }
      }
    }
    for (int i = 0; i < price.size(); i++) {
      List<Integer> l = new ArrayList<>(price.size() + 1);
      for (int j = 0; j < price.size(); j++) {
        if (i == j) {
          l.add(1);
        } else {
          l.add(0);
        }
      }
     l.add(price.get(i));
      special.add(l);
    }

    return dfs(special, needs, Integer.MAX_VALUE, 0);
  }

  private static int dfs(List<List<Integer>> special, List<Integer> needs, int min, int cost) {
    boolean isFinish = true;
    for(Integer num : needs) {
      if (num != 0) {
        isFinish = false;
        break;
      }
    }
    if (isFinish) {
      return cost;
    }
    for (int i = 0; i < special.size(); i++) {
      List<Integer> l = special.get(i);
      boolean flag = false;
      for (int j = 0; j < l.size() - 1; j++) {
        if (l.get(j) > needs.get(j)) {
          flag = true;
          break;
        }
      }
      if (!flag) {
        if (cost + l.get(l.size() - 1) >= min) {
          continue;
        }
        for (int j = 0; j < l.size() - 1; j++) {
          needs.set(j, needs.get(j) - l.get(j));
        }
        min = dfs(special, needs, min, cost + l.get(l.size() - 1));
        for (int j = 0; j < l.size() - 1; j++) {
          needs.set(j, needs.get(j) + l.get(j));
        }
      }
    }
    return min;
  }
}
