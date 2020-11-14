package cn.leetcode.dynamicPlanning;

/**
 * 53. 最大子序和  动态规划
 * dp[i]=max(dp[i-1]+A[i],A[i])
 * 先找到包含当前位置的最大子序和，然后遍历一遍就可以了
 * 每个位置的和如何计算：
 * 1.首先要包含自己，而且必须要包含，因为不包含的已经在前一个计算出来了
 * 2.比较 “上一个子序列的值加上自己” 和 自己 比较
 * 3.值大的就是当前的最佳值
 *
 * @author oudaming
 * @date 2020/11/14 0014 17:04
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSubarray().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
