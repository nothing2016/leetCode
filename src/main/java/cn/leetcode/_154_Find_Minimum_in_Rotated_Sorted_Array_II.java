package cn.leetcode;

/**
 * 154. 寻找旋转排序数组中的最小值 II
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,5]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,0,1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *
 * @author oudaming
 * @date 2021-04-09 9:37
 */
public class _154_Find_Minimum_in_Rotated_Sorted_Array_II {
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3, 1, 2}));
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(findMin(new int[]{11, 13, 15, 17}));
        System.out.println(findMin(new int[]{11, 11, 11}));
        System.out.println(findMin(new int[]{2, 2, 2, 0, 1}));
    }

    public static int findMin(int[] nums) {
        int N = nums.length;
        int l = 0, r = N - 1;
        while (l <= r) {
            // 原本就是有序的，直接返回第一个
            if (nums[l] < nums[r]) {
                return nums[l];
            }
            if (r - l == 2) {
                return Math.min(Math.min(nums[l], nums[r]), nums[l + 1]);
            }
            if (r - l == 1) {
                return Math.min(nums[l], nums[r]);
            }
            if (r == l) {
                return nums[l];
            }
            int mid = (l + r) / 2;
            // 排除相同时，不是到哪一部分有序的干扰
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                l++;
                r--;
                continue;
            }

            if (nums[mid] < nums[l]) { // 中点在旋转数组的右半部分, [mid,r]有序，那么最小值一定要从左边中点左边找
                r = mid;
            } else { // 中点在旋转数组的左半部分, [l,mid]有序, ，那么最小值一定要从左边中点右边找
                l = mid;
            }
        }
        return 0;
    }
}
