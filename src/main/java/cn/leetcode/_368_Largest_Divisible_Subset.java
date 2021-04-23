package cn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. 最大整除子集
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 109
 * nums 中的所有整数 互不相同
 * <p>
 * 题解： 如果num[i]是集合中最大整数的倍数，那么num[i]可以加到s集合中来
 * dp[i] 表示必须以nums[i]结尾的整除子集的大小size，动态方程为：dp[i] = Math.max(dp[i], dp[j] + 1);  j 属于[0,i-1]
 * 得到dp[i]后反推结果即可
 *
 * @author oudaming
 * @date 2021-04-23 9:31
 */
public class _368_Largest_Divisible_Subset {
    public static void main(String[] args) {
//        List<Integer> integers = largestDivisibleSubset(new int[]{1, 2, 4, 8, 12, 16});
//        List<Integer> integers = largestDivisibleSubset(new int[]{1, 2, 3});
//        List<Integer> integers = largestDivisibleSubset(new int[]{4, 8, 10, 240});
        List<Integer> integers = largestDivisibleSubset(new int[]{5, 9, 18, 54, 108, 540, 90, 180, 360, 720}); // [9,18,90,180,360,720]
        integers.forEach(System.out::println);
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);

        // 第 1 步：动态规划找出最大子集的个数、最大子集中的最大整数
        // dp[i] 表示必须以nums[i]结尾的整除子集的大小size
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        int maxVal = dp[0];
//        nums	2	4	7	8	9	12	16	20
//        dp	1	2	1	3	1	3	4	3
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                // 题目中说「没有重复元素」很重要，如果有重复元素，就一定会出现错误，比如1，4，4，8 ，按照我们的逻辑，8只能得到3，但是结果是4
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }

        // 第 2 步：倒推获得最大子集
        List<Integer> res = new ArrayList<Integer>();
        if (maxSize == 1) {
            res.add(nums[0]);
            return res;
        }

        for (int i = N - 1; i >= 0 && maxSize > 0; i--) {
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                res.add(nums[i]);
                maxVal = nums[i];
                maxSize--;
            }
        }
        return res;
    }
}
