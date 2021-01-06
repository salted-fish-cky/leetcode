/**
 * Copyright (C) 2011-2021 ShenZhen iBoxChain Information Technology Co.,Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary information of iBoxChain Company of China.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the contract agreement you entered into with iBoxChain
 * inc.
 */
package com.learn.list;

import java.util.Iterator;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2021/1/4 
 */
public interface SimpleList<E> {

  void add(E e);

  void add(int i, E e);

  E get(int i);

  void remove(int i);

  int size();

  Iterator<E> iterator();

  boolean contain(Object o);
}
