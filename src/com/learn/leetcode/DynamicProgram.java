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
import java.util.Arrays;
import java.util.List;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2021/4/9
 */
public class DynamicProgram {
  public static void main(String[] args) {
    maxProfit(2, new int[]{3,2,6,5,0,3});
  }

  /**
   * 5. 最长回文子串 dp
   * @param s
   * @return
   */
  public String longestPalindrome(String s) {
    int len = s.length();
    if (len < 2) {
      return s;
    }
    boolean[][] dp = new boolean[len][len];
    int max = 1, r = 1, l = 0, start = 0, end = 1;
    while(r < len) {
      l = 0;
      while (l < r) {
        if (s.charAt(l) != s.charAt(r)) {
          dp[l][r] = false;
        } else {
          if (r - l < 3) {
            dp[l][r] = true;
          } else {
            dp[l][r] = dp[l + 1][r - 1];
          }
        }
        if (dp[l][r] && r - l + 1 > max) {
          max = r - l + 1;
          start = l;
          end = r + 1;
        }
        l++;
      }
      r++;
    }
    return s.substring(start, end);
  }

  /**
   * 300. 最长递增子序列 dp
   * @param s
   * @return
   */
  public int lengthOfLIS(int[] nums) {
    int len = 0;
    int[] dp = new int[len = nums.length];
    for (int i = 0; i < len; i++) {
      dp[i] = 1;

    }
    int max = 1;
    for (int i = 1; i < len; i++) {
      for (int j = 0; j <= i; j++) {
        if (nums[j] < nums[i]) {
          dp[i] = (max = Math.max(dp[j], max)) + 1;
        }
      }
      max = 1;
    }
    for (int i = 0; i < len; i++) {
      max = Math.max(max, dp[i]);
    }
    return max;
  }

  /**
   * 152. 乘积最大子数组 dp
   * @param nums
   * @return
   */
  public int maxProduct2(int[] nums) {
    int len = nums.length, max = nums[0], min = nums[0], res = nums[0];
    for (int i = 1; i < len; i++) {
      int a = nums[i];
      int ma = max;
      int mi = min;
      max = Math.max(ma * a, Math.max(mi * a, a));
      min = Math.min(mi * a, Math.min(ma * a, a));
      res = Math.max(res, max);
    }
    return res;
  }

  /**
   * 887. 鸡蛋掉落
   * @param k
   * @param n
   * @return
   */
  public int superEggDrop(int k, int n) {
    if (k == 1) {
      return n;
    }
    if (n == 1) {
      return 1;
    }
    int[][] dp = new int[k + 1][n + 1];
    for (int i = 0; i < n + 1; i++) {
      dp[0][i] = 0;
      dp[1][i] = i;
    }
    for (int i = 1; i < k + 1; i++) {
      dp[i][1] = 1;
      dp[i][0] = 0;
    }
    for (int i = 2; i < k + 1; i++) {
      for (int j = 2; j < n + 1; j++) {
        dp[i][j] = n;
        for (int l = 1; l <= j; l++) {
          dp[i][j] = Math.min(dp[i][j], Math.max(dp[i][j - l], dp[i - 1][l - 1]) + 1);
        }
      }
    }
    return dp[k][n];
  }

  public static int coinChange(int[] coins, int amount) {
    int[] arr = new int[amount + 1];
    for (int i = 0; i <= amount; i++) {
      arr[i] = amount + 1;
    }
    arr[0] = 0;
    for (int i = 0; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        if(i - coins[j] < 0) {
          continue;
        }
        arr[i] = Math.min(arr[i], 1 + arr[i - coins[j]]);
      }
    }
    return arr[amount] == amount + 1 ? -1 : arr[amount];
  }

  /**
   * 213. 打家劫舍 II
   * @param nums
   * @return
   */
  public int rob(int[] nums) {
    if(nums.length == 0) {
      return 0;
    }
    if(nums.length == 1) {
      return nums[0];
    }
    return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
            myRob(Arrays.copyOfRange(nums, 1, nums.length)));

  }

  private int myRob(int[] nums) {
    int pre = 0, cur = 0, tmp;
    for(int num : nums) {
      tmp = cur;
      cur = Math.max(pre + num, cur);
      pre = tmp;
    }
    return cur;
  }

  /**
   * 70. 爬楼梯
   * @param n
   * @return
   */
  public int climbStairs(int n) {
    int len = n + 1;
    int[] dp = new int[len];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i < len; i++) {
      dp[i]  = dp[i - 2] + dp[i - 1];
    }
    return dp[n];
  }

  /**
   * 518. 零钱兑换 II
   * @param amount
   * @param coins
   * @return
   */
  public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;
    for (int i = 0; i < coins.length; i++) {
      for (int j = coins[i]; j <= amount; j++) {
        dp[j] += dp[j - coins[i]];
      }
    }
    return dp[amount];
  }

  /**
   * 494. 目标和
   * @param nums
   * @param S
   * @return
   */
  public int findTargetSumWays(int[] nums, int S) {
    int sum = 0, len;
    for (int i = 0; i < (len = nums.length); i++) {
      sum += nums[i];
    }
    if (Math.abs(S) > sum) {
      return 0;
    }
    int t = 2*sum + 1;
    int[][] dp = new int[len][t];
    dp[0][sum + nums[0]] = 1;
    dp[0][sum - nums[0]] += 1;

    for (int i = 1; i < len; i++) {
      for (int j = 0; j < t; j++) {
        int l = j - nums[i] < 0 ? 0 : j - nums[i];
        int r = j + nums[i] >= t ? t - 1 : j + nums[i];
        dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
      }
    }
    return dp[len - 1][sum + S];

  }

  /**
   * 120. 三角形最小路径和
   * @param triangle
   * @return
   */
  public static int minimumTotal(List<List<Integer>> triangle) {
    int size;
    int[] dp = new int[triangle.get((size = triangle.size()) - 1).size()];
    for (int i = 0; i < dp.length; i++) {
      dp[i] = triangle.get(size - 1).get(i);
    }
    for (int i = size - 2; i >= 0 ; i--) {
      List<Integer> l = triangle.get(i);
      for (int j = 0; j < l.size(); j++) {
        dp[j] = Math.min(dp[j], dp[j + 1]) + l.get(j);
      }
    }
    return dp[0];
  }

  /**
   * 188. 买卖股票的最佳时机 IV
   * @param k
   * @param prices
   * @return
   */
  public static int maxProfit(int k, int[] prices) {
    int len;
    if (k == 0 || (len = prices.length) == 0) {
      return 0;
    }
    k = Math.min(k, len/2);
    int[][][] dp = new int[len][k + 1][2];
    int res = 0;
    for (int i = 0; i <= k; i++) {
      dp[0][i][1] = -prices[0];
    }
    for (int i = 1; i < len; i++) {
      for (int j = 1; j <= k; j++) {
        dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
        dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
      }
    }
    for (int i = 0; i <= k; i++) {
      res = Math.max(res, dp[len - 1][i][0]);
    }
    return res;
  }

  /**
   * 123. 买卖股票的最佳时机 III
   * @param prices
   * @return
   */
  public int maxProfit(int[] prices) {
    int len;
    if ((len = prices.length) == 0) {
      return 0;
    }
    int[][][] dp = new int[len][3][2];
    int res = 0;
    for (int i = 0; i <= 2; i++) {
      dp[0][i][1] = -prices[0];
    }
    for (int i = 1; i < len; i++) {
      for (int j = 1; j <= 2; j++) {
        dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
        dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
      }
    }
    for (int i = 0; i <= 2; i++) {
      res = Math.max(res, dp[len - 1][i][0]);
    }
    return res;
  }

  /**
   *309. 最佳买卖股票时机含冷冻期
   * @param prices
   * @return
   */
  public int maxProfit2(int[] prices) {
    int len;
    if ((len = prices.length) <= 1) {
      return 0;
    }
    int[][] dp = new int[len][3];
    dp[0][0] = -prices[0];
    for (int i = 1; i < len; i++) {
      dp[i][0] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][0]);
      dp[i][1] = dp[i - 1][0] + prices[i];
      dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
    }
    return Math.max(dp[len - 1][1], dp[len - 1][2]);
  }

  /**
   * 72. 编辑距离
   * @param word1
   * @param word2
   * @return
   */
  public int minDistance(String word1, String word2) {
    int len1, len2;
    int [][] dp = new int[(len1 = word1.length()) + 1][(len2 = word2.length()) + 1];
    for (int i = 0; i <= len1; i++) {
      dp[i][0] = i;
    }
    for (int i = 0; i <= len2; i++) {
      dp[0][i] = i;
    }
    for (int i = 1; i <= len1; i++) {
      for (int j = 1; j <= len2; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]) + 1);
        } else {
          dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
        }
      }

    }
    return dp[len1][len2];
  }
}
