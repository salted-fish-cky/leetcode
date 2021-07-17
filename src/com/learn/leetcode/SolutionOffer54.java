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
 * Description：剑指 Offer 54. 二叉搜索树的第k大节点
 *
 * @author caokeyu
 * @since 2021/7/3
 */
public class SolutionOffer54 {

  private int k = 0;

  public int kthLargest(TreeNode root, int k) {
    this.k = k;
    return dfs(root).val;
  }

  private TreeNode dfs(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode r = dfs(root.right);
    if (r == null) {
      r = --k == 0 ? root : null;
    }
    if (r != null) {
      return r;
    }
    TreeNode l = dfs(root.left);
    return l;
  }
}
