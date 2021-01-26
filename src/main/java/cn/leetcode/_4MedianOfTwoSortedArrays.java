package cn.leetcode;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>
 * <p>
 * 题解： 使用归并排序的想法，先合并到一个数组中，然后直接计算中间的数即为中位数
 * 时间负责度：O(N)
 *
 * @author oudaming
 * @date 2021-01-26 14:15
 */
public class _4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {3, 4};
        double medianSortedArrays = new _4MedianOfTwoSortedArrays().findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return (double) 0;
        }

        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] help = new int[len1 + len2];
        int p1 = 0;
        int p2 = 0;
        int i = 0;
        while (p1 < len1 && p2 < len2) {
            help[i++] = nums1[p1] > nums2[p2] ? nums2[p2++] : nums1[p1++];
        }
        while (p1 < len1) {
            help[i++] = nums1[p1++];
        }
        while (p2 < len2) {
            help[i++] = nums2[p2++];
        }
        if ((len1 + len2) % 2 == 1) {
            return help[(len1 + len2) / 2];
        } else {
            return (help[(len1 + len2) / 2] + help[(len1 + len2) / 2 - 1]) / 2D;
        }

    }
}
