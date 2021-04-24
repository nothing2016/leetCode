package cn.leetcode;

import java.util.Arrays;

/**
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 示例 2：
 * <p>
 * 输入：nums = [9], target = 3
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 * <p>
 * <p>
 * 进阶：如果给定的数组中含有负数会发生什么？
 * 问题会产生何种变化？
 * 如果允许负数出现，需要向题目中添加哪些限制条件？
 *
 * @author oudaming
 * @date 2021/4/24 0024 09:31
 * <p>
 * 题解：使用动态规划，dp[i]定义为总和为i的方法数，dp[i]包含了以num[j]为结尾的方法数的累加 (j 属于 [0,N-1])，
 * 那么要找的结果就是dp[target]
 * dp[i]的每一项如何计算了，典型的从左到右的尝试模型，以num[j]为集合的结尾的
 * 方法数是多少，所以dp[i] += dp[i - nmu[j]] (j 属于 [0,N-1])
 */
public class _377_Combination_Sum_IV {
    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 4));
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 0));
    }

    public static int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 排序能加速结果的获取
        Arrays.sort(nums);
        int[] dp = new int[target + 1];
        dp[0] = 1;// 总数为0的方法数就是一个不选，所以是1
        int N = nums.length;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < N; j++) {
                // 集合以num[j]结尾的情况，但条件是num[j] <= i
                if (nums[j] <= i) {
                    dp[i] += dp[i - nums[j]];
                } else {
                    // 提前排序的，如果nums[j] > i了，之后的nums[j+1]肯定是 > i
                    // 所以不需要再尝试了
                    break;
                }
            }
        }
        return dp[target];
    }
}
