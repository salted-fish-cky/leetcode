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
import java.util.concurrent.TimeUnit;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2020/12/31
 */
public class Main {

  static boolean isRun = true;
  static SimpleBlockingQueue<String> queue = new SimpleLinkedBlockingQueue(128);
  static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue(128);
  public static void main(String[] args) throws InterruptedException {
    Runnable runnable1 = () -> {
      try {
        for (int i = 0; i < 1000; i++) {
          System.out.println("生产数据数据 ：" + (i + 1));
          queue.offer(i + 1 + "", 1, TimeUnit.SECONDS);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
    Thread thread1 = new Thread(runnable1);
    thread1.start();
    Runnable runnable2 = () -> {
      try {
        while (!Thread.currentThread().isInterrupted()) {
          String take = queue.poll(1, TimeUnit.SECONDS);
          System.out.println("消费数据 ：" + take);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };
    Thread thread2 = new Thread(runnable2);
    thread2.start();
    Thread.sleep(2000);
    thread1.interrupt();
    thread2.interrupt();
  }
}
