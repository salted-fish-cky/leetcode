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
import java.util.Objects;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2020/12/2
 */
public class ThreadLocalContext {

  private static final ThreadLocal<Map<Object, Object>> THREAD_LOCAL = new ThreadLocal<>();

  public static void put(Object key, Object value) {
    Map<Object, Object> map = THREAD_LOCAL.get();
    if (Objects.isNull(map)) {
      map = new HashMap<>();
      THREAD_LOCAL.set(map);
    }
    map.put(key, value);

  }

  public static Object get(Object key) {
    Map<Object, Object> map = THREAD_LOCAL.get();
    if (Objects.isNull(map)) {
      return null;
    }
    return map.get(key);
  }

  public static Map<Object, Object> getThreadMap(){
    return THREAD_LOCAL.get();
  }

  public static void setThreadMap(Map<Object, Object> map){
    THREAD_LOCAL.set(map);
  }

  public static void remove() {
    THREAD_LOCAL.remove();
  }


}
