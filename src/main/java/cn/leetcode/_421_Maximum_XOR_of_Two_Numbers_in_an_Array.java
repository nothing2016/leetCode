package cn.leetcode;

/**
 * 421. 数组中两个数的最大异或值
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * <p>
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：nums = [2,4]
 * 输出：6
 * 示例 4：
 * <p>
 * 输入：nums = [8,10,2]
 * 输出：10
 * 示例 5：
 * <p>
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2 * 10^4
 * 0 <= nums[i] <= 2^31 - 1
 * <p>
 * 题解：1.使用暴力方法求解
 * 2.最优解是使用前缀树来求解
 *
 * @author oudaming
 * @date 2021/5/16 0016 23:26
 */
public class _421_Maximum_XOR_of_Two_Numbers_in_an_Array {
    public static void main(String[] args) {
        System.out.println(findMaximumXOR(new int[]{3}));
        System.out.println(findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
        System.out.println(findMaximumXOR(new int[]{14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70}));
    }

    /**
     * 暴力求解法
     *
     * @param nums
     * @return
     */
    public static int findMaximumXOR(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                ans = Math.max(ans, nums[i] ^ nums[j]);
            }
        }
        return ans;
    }
}
