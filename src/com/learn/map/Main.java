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

package com.learn.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2021/1/14
 */
public class Main {

  public static void main(String[] args) {
    Map<String, String> map = new HashMap<>(4);
    map.put("qq", "bb");
    map.put("aa", "aa");
    map.put("bb", "bb");
    map.put("qq", "zz");
    Map<String, String> m = new SimpleHashMap<>(4);
    m.putAll(map);
    for (SimpleHashMap.Entry<String, String> entry: m.entrySet()) {
      System.out.println(entry.getKey() + ":" + entry.getValue());
    }
  }
}
