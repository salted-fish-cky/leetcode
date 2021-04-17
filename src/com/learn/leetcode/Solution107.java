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

import java.util.ArrayList;
import java.util.List;

/**
 * Description：二叉树的层序遍历 II
 *
 * @author caokeyu
 * @since 2021/4/17
 */
public class Solution107 {

  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> list = new ArrayList<>();
    dfs(list, root, 0);
    int left = 0, right = list.size() - 1;
    List<Integer> temp;
    while (left < right) {
      temp = list.get(left);
      list.set(left++, list.get(right));
      list.set(right--, temp);
    }
    return list;
  }

  private void dfs(List<List<Integer>> list, TreeNode node, int level) {
    if (node == null) {
      return;
    }
    if (level >= list.size()) {
      List<Integer> l = new ArrayList<>();
      list.add(l);
    }
    list.get(level).add(node.val);
    dfs(list, node.left, level + 1);
    dfs(list, node.right, level + 1);
  }
}
