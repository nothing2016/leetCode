package cn.leetcode;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 * <p>
 * 题解：如果是大于N/2的数，如果两个不同的值一起删除掉，那么一定剩下的就是大于N/2的值
 *
 * @author oudaming
 * @date 2021/3/14 0014 09:04
 */
public class _169_Majority_Element {
    public static int majorityElement(int[] nums) {
        // 候选的值
        int cand = 0;
        // 候选值被抵消后的次数
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                cand = nums[i];
                count = 1;
            } else if (nums[i] == cand) {
                count++;
            } else {
                count--;
            }
        }
        return cand;
    }
}
