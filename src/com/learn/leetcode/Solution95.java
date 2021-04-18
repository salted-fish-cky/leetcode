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
 * Description：不同的二叉搜索树 II
 *
 * @author caokeyu
 * @since 2021/4/18
 */
public class Solution95 {

  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return new ArrayList<>();
    }
    return generateTrees(1, n);
  }

  private List<TreeNode> generateTrees(int start, int end) {
    List<TreeNode> list = new ArrayList<>();
    if (start > end) {
      list.add(null);
    }
    for (int i = start; i <= end; i++) {
      List<TreeNode> l = generateTrees(start, i - 1);
      List<TreeNode> r = generateTrees(i + 1, end);

      for(TreeNode rNode : r) {
        for (TreeNode lNode : l) {
          TreeNode n = new TreeNode(i);
          n.left = lNode;
          n.right = rNode;
          list.add(n);
        }
      }
    }
    return list;
  }

}
