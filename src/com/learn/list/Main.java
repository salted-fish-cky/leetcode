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

package com.learn.list;

import java.util.Iterator;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2021/1/4
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {
    SimpleList<Integer> list = new SimpleCopyOnWriteArrayList<>();

    for (int i = 0; i < 100; i++) {
      final int value = i + 1;
      new Thread(() -> {list.add(value);}).start();
    }
    while (list.size() != 100) {

    }
    list.add(0, 1000);
    System.out.println(list.contain(101));
    Iterator<Integer> iterator = list.iterator();
    while (iterator.hasNext()) {
      Integer next = iterator.next();
      System.out.println(next);
    }
  }
}
