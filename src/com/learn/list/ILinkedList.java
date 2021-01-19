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

/**
 * Description：
 *
 * @author caokeyu
 * @since 2021/1/18 
 */
public interface ILinkedList<E> extends SimpleList<E> {

  void addFirst(E item);

  void addLast(E item);

  E getLast();

  E getFirst();

  E removeFirst();

  E removeLast();



}
