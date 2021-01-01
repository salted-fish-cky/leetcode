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

package com.learn.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2020/11/30
 */
public class Main {

  public static void main(String[] args) {
    ExecutorService executorService =
            new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(128));

    for (int i = 0; i < 100; i++) {
      ThreadLocalContext.put("key1", "value" + (i + 1));
//      executorService.execute(new AsyncRunnable(() -> execute()));
      executorService.submit(new AsyncCallable(() -> execute()));
    }

  }

  public static String execute() {
    System.out.println(ThreadLocalContext.get("key1"));
    return ThreadLocalContext.get("key1").toString();
  }

  static class AsyncCallable implements Callable {

    private Callable callable;

    private Map<Object, Object> map = new HashMap<>();

    public AsyncCallable(Callable callable) {
      this.callable = callable;
      this.map.putAll(ThreadLocalContext.getThreadMap());
    }


    @Override
    public Object call() throws Exception {
      try {
        if (callable != null) {
          ThreadLocalContext.setThreadMap(map);
          return callable.call();
        }
      } finally {
        ThreadLocalContext.remove();
      }
      return null;
    }
  }

  static class AsyncRunnable implements Runnable {

    private Runnable runnable;

    private Map<Object, Object> map = new HashMap<>();

    public AsyncRunnable(Runnable runnable) {
      this.runnable = runnable;
      copyData();
    }

    @Override
    public void run() {
      if (runnable != null) {
        ThreadLocalContext.setThreadMap(map);
        runnable.run();
      }
    }

    private void copyData() {
      try {
        map.putAll(ThreadLocalContext.getThreadMap());
      } catch (Exception e) {
        ThreadLocalContext.remove();
      }
    }
  }
}
