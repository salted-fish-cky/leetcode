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

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2021/1/22
 */
public class LeetCodeConcurrent {

  /**
   * 哲学家换手问题
   */
  class DiningPhilosophers {
    Object[] locks;

    public DiningPhilosophers() {
      locks = new Object[5];
      for (int i = 0; i < locks.length; i++) {
        locks[i] = new Object();
      }
    }


    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

      int index = (philosopher + 1) % locks.length;
      Object leftLock = null;
      Object rightLock = null;
      leftLock = locks[Math.max(index, philosopher)];
      rightLock = locks[Math.min(index, philosopher)];
      synchronized (leftLock) {
        synchronized (rightLock) {
          pickLeftFork.run();
          pickRightFork.run();
          eat.run();
          putRightFork.run();
          putLeftFork.run();
        }
      }
    }
  }

  /**
   * 顺序执行
   */
  class Foo {

    CountDownLatch count1 = new CountDownLatch(1);
    CountDownLatch count2 = new CountDownLatch(1);
    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

      // printFirst.run() outputs "first". Do not change or remove this line.

      printFirst.run();
      count1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {

      // printSecond.run() outputs "second". Do not change or remove this line.
        count1.await();
        printSecond.run();
        count2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {

      // printThird.run() outputs "third". Do not change or remove this line.
        count2.await();
        printThird.run();

    }
  }

  /**
   * 交替打印
   */
  class FooBar {
    private int n;
    private volatile boolean flag = true;

    public FooBar(int n) {
      this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
      for (int i = 0; i < n; i++) {

        // printFoo.run() outputs "foo". Do not change or remove this line.
        while(!flag) {
          Thread.yield();
        }
        printFoo.run();
        flag = false;
      }
    }

    public void bar(Runnable printBar) throws InterruptedException {
      for (int i = 0; i < n; i++) {

        // printBar.run() outputs "bar". Do not change or remove this line.
        while(flag) {
          Thread.yield();
        }
        printBar.run();
        flag = true;
      }
    }
  }


  /**
   * 打印零与奇偶数
   */
  static class ZeroEvenOdd {
    private int n;


    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    private volatile int flag = 0;
    private boolean isOutPutZero = false;

    public ZeroEvenOdd(int n) {
      this.n = n;
    }

    public static void main(String[] args) throws InterruptedException {
      ZeroEvenOdd z = new ZeroEvenOdd(5);
      new Thread(() ->
      {
        try {
          z.odd(e -> System.out.println(e));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      ).start();
      new Thread(() ->
      {
        try {
          z.zero(e -> System.out.println(e));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      ).start();
      new Thread(() ->
      {
        try {
          z.even(e -> System.out.println(e));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      ).start();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
      for (int i = 0; i < n; i++) {
        lock.lock();
        try {
          printNumber.accept(0);
          isOutPutZero = true;
          if (flag == 0 || flag == 1) {
            flag = 1;
            condition2.signal();
          } else if(flag == 2) {
            condition3.signal();
          }
          condition1.await();
        } finally {
          lock.unlock();
        }
      }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
      for (int i = 1; i <= n; i++) {
        if ((i & 1) == 0) {
          lock.lock();
          try {
            if (flag != 2 || !isOutPutZero) {
              condition3.await();
            }
            printNumber.accept(i);
            isOutPutZero = false;
            flag = 1;
            condition1.signal();
            if (i != n - 1 && i != n) {
              condition3.await();
            }


          } finally {
            lock.unlock();
          }
        }
      }


    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
      for (int i = 1; i <= n; i++) {
        if ((i & 1) == 1) {
          lock.lock();
          try {
            if (flag != 1 || !isOutPutZero) {
              condition2.await();
            }
            printNumber.accept(i);
            isOutPutZero = false;
            flag = 2;
            condition1.signal();
            if (i != n - 1 && i != n) {
              condition2.await();
            }

          } finally {
            lock.unlock();
          }
        }
      }
    }
  }

}
