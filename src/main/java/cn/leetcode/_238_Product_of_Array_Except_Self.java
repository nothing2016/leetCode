package cn.leetcode;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * <p>
 * <p>
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * <p>
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
public class _238_Product_of_Array_Except_Self {
    // 该方法使用了除法，不符合题目要求
    public int[] productExceptSelf(int[] nums) {
        int zeros = 0;
        int all = 1;
        // 先求出0的数量和除了0之外的数的累乘
        for (int num : nums) {
            if (num == 0) {
                zeros++;
            } else {
                all *= num;
            }
        }
        // 如果 0 的数量大于1，那么任何index上的值都是0
        if (zeros > 1) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = 0;
            }
        } else {
            if (zeros == 0) {
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = all / nums[i];
                }
            } else {
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = nums[i] == 0 ? all : 0;
                }
            }
        }
        return nums;
    }

    /**
     * 不使用除法，符合题意
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int length = nums.length;

        // L 和 R 分别表示左右两侧的乘积列表
        int[] L = new int[length];
        int[] R = new int[length];

        int[] answer = new int[length];

        // L[i] 为索引 i 左侧所有元素的乘积
        // 对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
        L[0] = 1;
        for (int i = 1; i < length; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }

        // R[i] 为索引 i 右侧所有元素的乘积
        // 对于索引为 'length-1' 的元素，因为右侧没有元素，所以 R[length-1] = 1
        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }

        // 对于索引 i，除 nums[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
        for (int i = 0; i < length; i++) {
            answer[i] = L[i] * R[i];
        }

        return answer;
    }
}
