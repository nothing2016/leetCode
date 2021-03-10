package cn.leetcode;

/**
 * 162. 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给你一个输入数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设nums[-1] = nums[n] = -∞ 。
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * <p>
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 * <p>
 * 题解：二分查找，每次都往上山走,如果已经越界，返回其下标即可
 *
 * @author oudaming
 * @date 2021-03-10 14:16
 */
public class _162_Find_Peak_Element {
    public static void main(String[] args) {
//        System.out.println(new _162_Find_Peak_Element().findPeakElement(new int[]{1, 2, 3, 4, 5}));
        System.out.println(new _162_Find_Peak_Element().findPeakElement(new int[]{5, 4, 3, 2, 1}));
//        System.out.println(new _162_Find_Peak_Element().findPeakElement(new int[]{1, 2, 3, 1}));
//        System.out.println(new _162_Find_Peak_Element().findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
//        System.out.println(new _162_Find_Peak_Element().findPeakElement(new int[]{1, 2}));
//        System.out.println(new _162_Find_Peak_Element().findPeakElement(new int[]{2, 1}));
//        System.out.println(new _162_Find_Peak_Element().findPeakElement(new int[]{2}));
//        System.out.println(new _162_Find_Peak_Element().findPeakElement(new int[]{3, 4, 3, 2, 1}));
    }

    public int findPeakElement(int[] nums) {
        int N = nums.length;
        int l = 0;
        int r = N - 1;
        int mid = 0;
        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        }
        while (l <= r) {
            mid = (l + r) >> 1;
            if (mid == N - 1) {
                break;
            }
            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

}
