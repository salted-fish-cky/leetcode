/**
 * Copyright (C) 2011-2020 ShenZhen iBoxChain Information Technology Co.,Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary information of iBoxChain Company of China.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the contract agreement you entered into with iBoxChain
 * inc.
 */
package com.learn.queue;

import java.util.concurrent.TimeUnit;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2020/12/31 
 */
public interface SimpleBlockingQueue<E> {

  E take() throws InterruptedException;

  void put(E e) throws InterruptedException;

  boolean offer(E e, long timeOut, TimeUnit unit) throws InterruptedException;

  E poll(long timeOut, TimeUnit unit) throws InterruptedException;

  int size();
}
