/*
 * Copyright (C) 2011-2020 ShenZhen iBOXCHAIN Information Technology Co.,Ltd.
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

package com.learn.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2020/12/31
 */
public class Main {

  static boolean isRun = true;
  static TestBlockingQueue<String> queue = new TestLinkedBlockingQueue(128);
  static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue(128);
  public static void main(String[] args) throws InterruptedException {
    Runnable runnable1 = () -> {
      try {
        for (int i = 0; i < 1000; i++) {
          System.out.println("生产数据数据 ：" + (i + 1));
          queue.put(i + 1 + "");
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
    new Thread(runnable1).start();

    Runnable runnable2 = () -> {
      try {
        while (isRun) {
          String take = queue.take();
          System.out.println("消费数据 ：" + take);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
    new Thread(runnable2).start();
    Thread.sleep(1000);
    isRun = false;
  }
}
