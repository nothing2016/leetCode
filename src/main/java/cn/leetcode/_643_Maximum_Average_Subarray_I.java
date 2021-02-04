package cn.leetcode;

/**
 * 643. 子数组最大平均数 I
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * 输入：[1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *
 * @author oudaming
 * @date 2021/2/4 0004 20:44
 */
public class _643_Maximum_Average_Subarray_I {
    public static void main(String[] args) {
        System.out.println(new _643_Maximum_Average_Subarray_I().findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
        System.out.println(new _643_Maximum_Average_Subarray_I().findMaxAverage(new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 4));
    }

    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return 0;
        }
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        for (int i = 1; i < N; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        int max = dp[k - 1];
        for (int i = k; i < N; i++) {
            max = Math.max(max, dp[i] - dp[i - k]);
        }
        return (double) max / k;
    }
}
