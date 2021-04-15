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

package com.learn.datastructure;

import javax.swing.tree.TreeNode;

/**
 * Description：红黑树
 *
 * @author caokeyu
 * @since 2021/4/14
 */
public class RedBlackTree {

  TreeNode root;

  public static void main(String[] args) {
    RedBlackTree redBlackTree = new RedBlackTree();
    redBlackTree.insert(1, 1);
    redBlackTree.insert(2, 2);
    redBlackTree.insert(3, 3);
    redBlackTree.insert(4, 4);
    redBlackTree.insert(5, 5);
    redBlackTree.insert(6, 6);
    redBlackTree.insert(7, 7);
    redBlackTree.insert(8, 8);
    redBlackTree.insert(9, 9);
    redBlackTree.insert(10, 10);
    System.out.println(redBlackTree);
    System.out.println(redBlackTree.find(10));
    System.out.println(redBlackTree.find(11));

  }

  public void insert(int key, int value) {
    if (root == null) {
      root = new TreeNode(key, value, null);
    } else {
      TreeNode x = new TreeNode(key, value);
      TreeNode node = findNode(key);
      if (node.key < key) {
        node.right = x;
        x.parent = node;
      } else if (node.key > key) {
        node.left = x;
        x.parent = node;
      } else {
        node.value = value;
      }
      balanceInsertion(x);
    }
  }

  public Integer find(int key) {
    TreeNode node = findNode(key);
    if (node == null) {
      return null;
    }
    if (key == node.value) {
      return node.value;
    }
    return null;
  }

  public TreeNode findNode(int key) {
    if (root == null) {
      return null;
    }
    TreeNode p = root;
    for(;;) {
      if (p.key < key) {
        if (p.right == null) {
          return p;
        }
        p = p.right;
      } else if (p.key > key) {
        if (p.left == null) {
          return p;
        }
        p = p.left;
      } else {
        return p;
      }
    }
  }

  private void balanceInsertion(TreeNode x) {
    x.isRed = true;
    for (TreeNode xp, xpp, xppl, xppr;;) {
      if ((xp = x.parent) == null) {
        x.isRed = false;
        root = x;
        return;
      } else if (!xp.isRed || (xpp = xp.parent) == null) {
          xp.isRed = false;
          return;
      }

      if (xp == (xppl = xpp.left)) {
        if ((xppr = xpp.right) != null && xppr.isRed) {
          xppr.isRed = false;
          xp.isRed = false;
          xpp.isRed = true;
          x = xpp;
        } else {
          if (x == xp.right) {
            rotateLeft(x = xp);
            xpp = (xp = x.parent) == null ? null : xp.parent;
          }
          if (xp != null) {
            xp.isRed = false;
            if (xpp != null) {
              xpp.isRed = true;
              rotateRight(xpp);
            }
          }
        }
      } else {
        if (xppl != null && xppl.isRed) {
          xp.isRed = false;
          xppl.isRed = false;
          xpp.isRed = true;
          x = xpp;
        } else {
          if (x == xp.left) {
            rotateRight(x = xp);
            xpp = (xp = x.parent) == null ? null : xp.parent;
          }
          if (xp != null) {
            xp.isRed = false;
            if (xpp != null) {
              xpp.isRed = true;
              rotateLeft(xpp);
            }
          }
        }
      }
    }
  }

  private void rotateLeft(TreeNode x) {
    TreeNode xp, r, rl;
    if (x != null && (r = x.right) != null) {
      if ((rl = x.right = r.left) != null) {
        rl.parent = x;
      }
      if ((xp = r.parent = x.parent) == null) {
        (root = r).isRed = false;
      } else if (xp.left == x) {
        xp.left = r;
      } else {
        xp.right = r;
      }
      r.left = x;
      x.parent = r;
    }
  }

  private void rotateRight(TreeNode x) {
    TreeNode xp, l, lr;
    if (x != null && (l = x.left) != null) {
      if ((lr = x.left = l.right) != null) {
        lr.parent = x;
      }
      if ((xp = l.parent = x.parent) == null) {
        (root = l).isRed = false;
      } else if (x == xp.right) {
        xp.right = l;
      } else {
        xp.left = l;
      }
      l.right = x;
      x.parent = l;
    }
  }

  static class TreeNode {
    final int key;
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode parent;
    boolean isRed;

    public TreeNode(int key, int value) {
      this.key = key;
      this.value = value;
    }

    public TreeNode(int key, int value, TreeNode parent) {
      this.key = key;
      this.value = value;
      this.parent = parent;
    }
  }

  @Override
  public String toString() {
    return "RedBlackTree{" +
            "root=" + root +
            '}';
  }
}
