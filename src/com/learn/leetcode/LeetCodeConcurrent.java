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
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
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

  /**
   * H2O生成
   */
  class H2O {

    Semaphore o = new Semaphore(1);
    Semaphore h = new Semaphore(2);
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

      // releaseHydrogen.run() outputs "H". Do not change or remove this line.
      execute(h, releaseHydrogen);
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

      // releaseOxygen.run() outputs "O". Do not change or remove this line.
      execute(o, releaseOxygen);
    }

    private void execute(Semaphore semaphore, Runnable runnable) throws InterruptedException {
      semaphore.acquire();
      try {
        cyclicBarrier.await();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }
      runnable.run();
      semaphore.release();
    }
  }


  /**
   * 交替打印字符串
   */
  static class FizzBuzz {

    private volatile int count = 1;
    private Semaphore number = new Semaphore(0);
    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzbuzz = new Semaphore(0);

    private int n;

    public static void main(String[] args) {
      FizzBuzz fizzBuzz = new FizzBuzz(15);
      new Thread(() -> {
        try {
          fizzBuzz.fizz(() -> System.out.println("fizz"));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();

      new Thread(() -> {
        try {
          fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();

      new Thread(() -> {
        try {
          fizzBuzz.buzz(() -> System.out.println("buzz"));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();

      new Thread(() -> {
        try {
          fizzBuzz.number(e -> System.out.println(e));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }

    public FizzBuzz(int n) {
      this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
      execute(fizz, printFizz);
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
      execute(buzz, printBuzz);
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
      execute(fizzbuzz, printFizzBuzz);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
      while (count <= n) {
        if (count % 3 == 0 && count % 5 == 0) {
          fizzbuzz.release();
          number.acquire();
        } else if (count % 3 == 0) {
          fizz.release();
          number.acquire();
        } else if (count % 5 == 0) {
          buzz.release();
          number.acquire();
        } else {
          printNumber.accept(count);
        }
        count++;
      }
      fizzbuzz.release();
      fizz.release();
      buzz.release();
    }

    private void execute(Semaphore semaphore, Runnable runnable) throws InterruptedException {
      while (count <= n) {
        semaphore.acquire();
        if (count > n) {
          break;
        }
        runnable.run();
        number.release();
      }
    }
  }
}
