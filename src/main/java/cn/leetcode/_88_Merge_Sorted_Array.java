package cn.leetcode;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 *
 * @author oudaming
 * @date 2021-02-24 10:44
 */
public class _88_Merge_Sorted_Array {
    public static void main(String[] args) {
        int[] nums1 = {1, 0};
        int m = 1;
        int[] nums2 = {2};
        int n = 1;
        merge(nums1, m, nums2, n);
        printArray(nums1);
    }

    public static void printArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }

    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] ints = Arrays.copyOf(nums1, nums1.length);
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            nums1[k++] = ints[i] <= nums2[j] ? ints[i++] : nums2[j++];
        }
        while (i < m) {
            nums1[k++] = ints[i++];
        }
        while (j < n) {
            nums1[k++] = nums2[j++];
        }
    }
}
