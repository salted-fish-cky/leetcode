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

/**
 * Description：112. 路径总和
 *
 * @author caokeyu
 * @since 2021/6/28
 */
public class Solution112 {

  public boolean hasPathSum(TreeNode root, int targetSum) {
    return dfs(root, targetSum, 0);
  }

  private boolean dfs(TreeNode node, int targetNum, int sum) {
    if (node == null) {
      return false;
    }
    sum += node.val;
    if (node.left == null && node.right == null) {
      return sum == targetNum;
    }
    if (dfs(node.left, targetNum, sum) || dfs(node.right, targetNum, sum)) {
      return true;
    }
    return false;
  }
}
