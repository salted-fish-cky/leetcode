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

package com.learn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Description：
 *
 * @author caokeyu
 * @since 2020/12/22
 */
public class LeetCode {

  public static void main(String[] args) {
//    System.out.println(findMedianSortedArrays(new int[]{1,3,4,5}, new int[]{2}));
//    System.out.println(longestPalindrome2("baa"));
//    System.out.println(convertNode(new ListNode(1, new ListNode(2, new ListNode(3, null)))));
//    System.out.println(reverse(-2147483647));
//    System.out.println(myAtoi(" 2147483649 aaa"));
//    System.out.println(isPalindrome(10000));
    System.out.println(threeSum(new int[] {-1,0,1,2,-1,-4}));
  }

  /**
   * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
   *
   * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍
   */
  public static int[] twoSum(int[] nums, int target) {
    int[] result = new int[2];
    for (int i = 0; i < nums.length - 1; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (i == j) {
          continue;
        }
        if (nums[i] + nums[j] == target) {
          if (i < j) {
            result[0] = i;
            result[1] = j;
          } else {
            result[0] = j;
            result[1] = i;
          }
          break;
        }
      }
    }
    return result;
  }

  public static int[] twoSum2(int[] nums, int target) {
    int[] result = new int[2];
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], i);
    }
    for (int i = 0; i < nums.length; i++) {
      int resultNum = target - nums[i];
      Integer b = map.get(resultNum);
      if (b != null) {
        if (b == i) {
          continue;
        }
        if (i < map.get(resultNum)) {
          result[0] = i;
          result[1] = b;
        } else {
          result[1] = i;
          result[0] = b;
        }
      }
    }
    return result;
  }

  /**
   * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
   */
  public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    for (ListNode l = l1; l != null; l = l.next) {
      list1.add(l.val);

    }

    for (ListNode l = l2; l != null; l = l.next) {
      list2.add(l.val);
    }
    int l1Size = list1.size();
    int l2Size = list2.size();
    if (l1Size > l2Size) {
      for (int i = 0; i < l1Size - l2Size; i++) {
        list2.add(0);
      }
    } else if (list1.size() < list2.size()) {
      for (int i = 0; i < l2Size - l1Size; i++) {
        list1.add(0);
      }
    }
    boolean flag = false;
    ListNode l = null;
    ListNode temp = null;
    l1Size = list1.size();
    for (int i = 0; i < l1Size; i++) {
      Integer result = 0;
      Integer cal = 0;
      if (flag) {
        cal = list1.get(i) + list2.get(i) + 1;
      } else {
        cal = list1.get(i) + list2.get(i);
      }

      if (cal >= 10) {
        flag = true;
        result = cal % 10;
      } else {
        flag = false;
        result = cal;
      }
      if (i == 0) {
        ListNode node = new ListNode(result);
        l = node;
        temp = node;
      } else {
        ListNode node = new ListNode(result);
        temp.next = node;
        temp = node;
      }
    }
    if (flag) {
      ListNode node = new ListNode(1);
      temp.next = node;
      temp = node;
    }
    return l;

  }

  public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {

    int temp = 0;
    ListNode l3 = new ListNode(0);
    ListNode p = l3;
    while (l1 != null || l2 != null || temp != 0) {
      int l1Val = l1 != null ? l1.val : 0;
      int l2Val = l2 != null ? l2.val : 0;
      int sumVal = l1Val + l2Val + temp;
      temp = sumVal / 10;
      p.next = new ListNode(sumVal % 10);
      p = p.next;
      if (l1 != null) l1 = l1.next;
      if (l2 != null) l2 = l2.next;
    }
    return l3.next;
  }

  /**
   * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
   *
   * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
   */
  public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int mid = (nums1.length + nums2.length) / 2 + 1;
    int nn = mid;
    if (mid == 1) {
      if (nums1.length == 1) {
        return nums1[0];
      } else if (nums2.length == 1) {
        return nums2[0];
      }
      return 0;
    }
    int offset1 = 0, offset2 = 0;
    while (true) {
      int m = mid / 2 - 1;
      int m1 = m > 0 ? m + offset1 : 0, m2 = m > 0 ? m + offset2 : 0;
      if (nums1.length <= m1) {
        if ((nums1.length + nums2.length) % 2 == 0) {
          return (nums2[nn - nums1.length - 1] + nums2[nn - nums1.length - 2]) / 2.0;
        } else {
          return (nums2[nn - nums1.length - 1]) / 2;
        }
      }
      if (nums2.length <= m2) {
        if ((nums1.length + nums2.length) % 2 == 0) {
          return (nums1[nn - nums2.length - 1] + nums1[nn - nums2.length - 2]) / 2.0;
        } else {
          return (nums1[nn - nums2.length - 1]) / 2;
        }
      }
      if (nums1[m1] > nums2[m2]) {
        offset2 += m + 1;

      } else if (nums1[m1] < nums2[m2]) {
        offset1 += m + 1;
      } else {
        offset1 += m;
        offset2 += m;
      }
      mid = mid - (mid / 2);
      if (mid == 1) {
        if ((nums1.length + nums2.length) % 2 == 0) {
          return (nums1[m1] + nums2[m2]) / 2.0;
        } else {
          if (nums1[m1] < nums2[m2]) {
            return nums1[m2];
          }
          return nums2[m1];
        }
      }
    }


  }


  public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
    int length1 = nums1.length, length2 = nums2.length;
    int totalLength = length1 + length2;
    if (totalLength % 2 == 1) {
      int midIndex = totalLength / 2;
      double median = getKthElement(nums1, nums2, midIndex + 1);
      return median;
    } else {
      int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
      double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
      return median;
    }
  }

  public static int getKthElement(int[] nums1, int[] nums2, int k) {
    /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
     * 这里的 "/" 表示整除
     * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
     * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
     * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
     * 这样 pivot 本身最大也只能是第 k-1 小的元素
     * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
     * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
     * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
     */

    int length1 = nums1.length, length2 = nums2.length;
    int index1 = 0, index2 = 0;
    int kthElement = 0;

    while (true) {
      // 边界情况
      if (index1 == length1) {
        return nums2[index2 + k - 1];
      }
      if (index2 == length2) {
        return nums1[index1 + k - 1];
      }
      if (k == 1) {
        return Math.min(nums1[index1], nums2[index2]);
      }

      // 正常情况
      int half = k / 2;
      int newIndex1 = Math.min(index1 + half, length1) - 1;
      int newIndex2 = Math.min(index2 + half, length2) - 1;
      int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
      if (pivot1 <= pivot2) {
        k -= (newIndex1 - index1 + 1);
        index1 = newIndex1 + 1;
      } else {
        k -= (newIndex2 - index2 + 1);
        index2 = newIndex2 + 1;
      }
    }
  }

  /**
   * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
   */
  public static String longestPalindrome(String s) {
    if (s.length() <= 1) {
      return s;
    }
    int[] temp = new int[128];
    for (int i = 0; i < temp.length; i++) {
      temp[i] = -1;
    }
    char[] chars = s.toCharArray();
    int left = 0;
    int right = 0;
    int maxLen = 0;
    boolean isFind = false;
    String result = "";
    while (true) {
      isFind = false;
      char c = s.charAt(right);
      char d = s.charAt(left);
      if (temp[d] > 0) {
        if (temp[d] != left && temp[d] - left <= maxLen) {
          left++;
          continue;
        }
      }
      if (temp[c] < 0) {
        temp[c] = right;
        right++;
      } else {
        if (chars[left] == c) {
          isFind = true;
          if (maxLen < (right - left)) {
            result = s.substring(left, right + 1);
            maxLen = right - left;
          }
          left++;
        }
        temp[c] = right;
        right++;
      }

      if (right == s.length() && left != (right - 1) && !isFind) {
        left++;
        right = left + 1;
      }
      if (right == s.length() && left == (right - 1)) {

        if (result.equals("")) {
          result = String.valueOf(chars[0]);
        }

        break;
      }
      if (right == s.length() && isFind) {
        break;
      }
    }
    return result;
  }

  public static String longestPalindrome2(String s) {
    if (s.length() <= 1) {
      return s;
    }
    char[] chars = s.toCharArray();
    int left = 0;
    int right = s.length() - 1;
    int maxLen = 0;
    boolean flag = false;
    String result = "";
    while (right >= left) {

      if (isEqual(chars, left, right)) {
        if (maxLen < right - left + 1) {
          maxLen = right - left + 1;
          result = s.substring(left, right + 1);
        }
        left++;
        right = s.length() - 1;

      } else {
        right--;
      }


      if (maxLen > right - left + 1) {
        left++;
        right = s.length() - 1;
        if (maxLen > right - left + 1) {
          break;
        }
      }

    }

    if (result.equals("")) {
      result = String.valueOf(chars[0]);
    }
    return result;
  }

  /**
   * 链表反转
   */
  public static ListNode convertNode(ListNode l) {
    return convertNode(l, null);
  }

  /**
   * 整数反转
   */
  public static int reverse(int x) {
    boolean flag = true;
    if (x == Integer.MIN_VALUE) {
      return 0;
    }
    if (x < 0) {
      x = x * -1;
      flag = false;
    }
    char[] chars = String.valueOf(x).toCharArray();
    int result = 0;
    for (int i = chars.length - 1; i >= 0; i--) {
      result += (chars[i] - 48) * Math.pow(10, i);
    }
    if (result == Integer.MAX_VALUE) {
      return 0;
    }
    if (flag) {
      return result;
    }
    return result * -1;
  }

  /**
   * 字符串转换整数 (atoi)
   * @param s
   * @return
   */
  public static int myAtoi(String s) {
    int result = 0;
    if (s.equals("")) {
      return result;
    }
    boolean isStart = false;
    int startIndex = 0, endIndex = 0, a = 1;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (isStart) {
        if (c >= 48 && c <= 57) {
          endIndex = i;
        } else {
          break;
        }
      } else {
        if (c == 45) {
          isStart = true;
          a = -1;
          startIndex = i + 1;
        } else if (c == 43) {
          isStart = true;
          a = 1;
          startIndex = i + 1;
        }else if (c>= 48 && c <= 57) {
          isStart = true;
          startIndex = i;
          endIndex = i;
        } else if (c != 32) {
          break;
        }
      }
    }
    for (int i = startIndex; i <= endIndex; i++) {
      char c;
      if ((c = s.charAt(i)) < 48 || c > 57) {
        break;
      }
      result += (c - 48) * Math.pow(10, endIndex - i) * a;
    }
    return result;
  }

  /**
   * 回文数
   * @param x
   * @return
   */
  public static boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }
    String s = String.valueOf(x);
    boolean isPalindrome = true;
    int count = s.length();
    for (int i = 0, j = count, temp = x; i <= (count - 1) / 2; i++, temp = temp / 10) {
      if (x / (int)(Math.pow(10, --j)) % 10 != temp % 10) {
        isPalindrome = false;
        break;
      }
    }
    return isPalindrome;
  }

  /**
   * 盛最多水的容器
   * @param height
   * @return
   */
  public int maxArea(int[] height) {
    int result = 0;
    if (height.length <= 1) {
      return result;
    }
    int temp = 0;
    for (int i = 0; i < height.length - 1; i++) {
      for (int j = i + 1; j < height.length; j++) {
       result = result > (temp = Math.min(height[i], height[j]) * (j - i)) ? result : temp;
      }
    }
    return result;
  }

  /**
   * 整数转罗马数字
   * @param num
   * @return
   */
  public String intToRoman(int num) {
    int length = 0;
    String result = "";
    while(num != 0) {
      int value = num % 10;
      switch (length) {
        case 0:
          result = intToRoman("I", "V", "X", value, result);
          break;
        case 1:
          result = intToRoman("X", "L", "C", value, result);
          break;
        case 2:
          result = intToRoman("C", "D", "M", value, result);
          break;
        case 3:
          result = intToRoman("M", "", "", value, result);
          break;
          default:
            break;
      }
      num = num / 10;
      length++;
    }
    return result;
  }
  private String intToRoman(String arg1, String arg2, String arg3, int value, String result) {
    switch (value) {
      case 1:
        result = arg1 + result;
        break;
      case 2:
        result = arg1 + arg1 + result;
        break;
      case 3:
        result = arg1 + arg1 + arg1 + result;
        break;
      case 4:
        result = arg1 + arg2 + result;
        break;
      case 5:
        result = arg2 + result;
        break;
      case 6:
        result = arg2 + arg1 + result;
        break;
      case 7:
        result = arg2 + arg1 + arg1 + result;
        break;
      case 8:
        result = arg2 + arg1 + arg1 + arg1 + result;
        break;
      case 9:
        result = arg1 + arg3 + result;
        break;
    }
    return result;
  }

  /**
   *  最长公共前缀
   * @param strs
   * @return
   */
  public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) {
      return "";
    }
    String s = strs[0];
    for (String str: strs) {
      if (str.length() == 0) {
        return "";
      }
      while (s.length() > 0) {
        if (!str.startsWith(s)) {
          s = s.substring(0, s.length() - 1);
        } else {
          break;
        }
      }
    }
    return s;
  }

  /**
   *  三数之和
   * @param nums
   * @return
   */
  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    if (nums.length < 3) {
      return list;
    } else {
      Arrays.sort(nums);
      int right = nums.length - 1;
      for (int i = 0, left = i + 1; i < nums.length; left = ++i + 1, right = nums.length - 1) {
        if (i > 0 && nums[i - 1] == nums[i]) {
          continue;
        }
        while(right > left) {
          if (right < nums.length - 1 && nums[right] == nums[right + 1]) {
            right--;
            continue;
          }
          if (nums[i] + nums[left] + nums[right] > 0) {
            right--;
          } else if (nums[i] + nums[left] + nums[right] < 0) {
            left++;
          } else {
            List<Integer> li = new ArrayList<>(3);
            li.add(nums[i]);
            li.add(nums[left]);
            li.add(nums[right]);
            list.add(li);
            left++;
            right--;
          }
        }
      }


    }
    return list;
  }

  /**
   * 最接近的三数之和
   * @param nums
   * @param target
   * @return
   */
  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int abs = Integer.MAX_VALUE, right = nums.length - 1, result = 0;
    boolean isBreak = false;
    for (int i = 0, left = i + 1; i < nums.length; left = ++i + 1, right = nums.length - 1) {
      while (right > left) {
        int temp = 0, tempAbs = 0;
        if ((temp = nums[i] + nums[left] + nums[right]) == target) {
          result = temp;
          isBreak = true;
          break;
        }
        if (target > temp) {
          left++;
        } else {
          right--;
        }
        if ((tempAbs = Math.abs(target - temp)) < abs) {
          abs = tempAbs;
          result = temp;
        }
      }
      if (isBreak) {
        break;
      }
    }
    return result;
  }

  /**
   * 有效的括号
   * @param s
   * @return
   */
  public boolean isValid(String s) {
    if ((s.length() & 1) != 0) {
      return false;
    }
    Stack<Character> stack=new Stack<>();
    for(char c:s.toCharArray()){
      if(c=='('){
        stack.push(')');
      } else if(c=='{') {
        stack.push('}');
      } else if(c=='[') {
        stack.push(']');
      } else if(stack.empty()||c!=stack.pop()) {
        return false;
      }
    }
    if(stack.empty()) {
      return true;
    }
    return false;

  }

  /**
   * 合并两个有序链表
   * @param l1
   * @param l2
   * @return
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    } else if (l1.val < l2.val) {
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists(l1, l2.next);
      return l2;
    }

  }


  private static ListNode convertNode(ListNode l, ListNode node) {

    if (l != null) {
      ListNode next = l.next;
      l.next = node;
      node = l;
      if (next == null) {
        return l;
      }
      return convertNode(next, node);
    }
    return null;
  }

  private static boolean isEqual(char[] chars, int left, int right) {
    if (chars[left] == chars[right]) {
      if (left <= right && right - 1 >= 0 && left + 1 < chars.length) {
        return isEqual(chars, left + 1, right - 1);
      }
      return true;
    }
    return false;
  }

  static class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    @Override
    public String toString() {
      return "ListNode{" +
              "val=" + val +
              ", next=" + next +
              '}';
    }
  }
}
