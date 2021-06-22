package cn.leetcode;

/**
 * 31. 下一个排列
 * <p>
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * <p>
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * <p>
 * * 输入：[1,3,2]
 * * 输出：[2,1,3]
 *
 * @author oudaming
 * @date 2021-02-25 9:58
 */
public class _31_Next_Permutation {
    public static void main(String[] args) {
        int[] nums = {4, 5, 2, 6, 3, 1};
        print(nums);
        new _31_Next_Permutation().nextPermutation(nums);
        print(nums);
    }

    private static void print(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
        System.out.println("===================");
    }

    //    [4,5,2,6,3,1] ->
    //    [4,5,3,1,2,6],
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 从尾部找到第一个极高值的前一位，这里找到2
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            // 从尾部找到第一个大于nums[i]的值
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            // 将大的值交换到i位置
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // [start,length-1]的区间逆序
    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
