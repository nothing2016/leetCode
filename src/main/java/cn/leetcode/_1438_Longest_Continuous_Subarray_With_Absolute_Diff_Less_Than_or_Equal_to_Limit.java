package cn.leetcode;

import java.util.TreeMap;

/**
 * 1438. 绝对差不超过限制的最长连续子数组
 * <p>
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * 如果不存在满足条件的子数组，则返回 0 。
 * <p>
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * <p>
 * 输入：nums = [10,1,2,4,7,2], limit = 5
 * 输出：4
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * <p>
 * 题解：滑动窗口 + 有序表(TreeMap)
 *
 * @author oudaming
 * @date 2021/2/21 0021 09:38
 */
public class _1438_Longest_Continuous_Subarray_With_Absolute_Diff_Less_Than_or_Equal_to_Limit {
    public static void main(String[] args) {
//        System.out.println(longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5));
//        System.out.println(longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0));
//        System.out.println(longestSubarray(new int[]{1, 1, 1}, 0));
    }

    public static int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int n = nums.length;
        int left = 0, right = 0;
        int ret = 0;
        while (right < n) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                // left往右滑动
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            // 结算
            ret = Math.max(ret, right - left + 1);
            // right往右滑动
            right++;
        }
        return ret;
    }
}
