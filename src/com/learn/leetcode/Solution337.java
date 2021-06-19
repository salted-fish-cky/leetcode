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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Description：337. 打家劫舍 III
 *
 * @author caokeyu
 * @since 2021/6/16
 */
public class Solution337 {

  Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
  Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

  public int rob(TreeNode root) {
    dfs(root);
    return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
  }

  public void dfs(TreeNode node) {
    if (node == null) {
      return;
    }
    dfs(node.left);
    dfs(node.right);
    f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
    g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) +
            Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
  }

  public int rob1(TreeNode root) {
    int[] dp = dfsRob(root);
    return Math.max(dp[0], dp[1]);
  }
  private int[] dfsRob(TreeNode node) {
    if (node == null) {
      return new int[2];
    }
    int[] l = dfsRob(node.left);
    int[] r = dfsRob(node.right);
    int rob = l[0] + r[0] + node.val;
    int notRob = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
    return new int[]{notRob, rob};
  }
}
