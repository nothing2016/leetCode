package cn.leetcode;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * <p>
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 * <p>
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 题解：定义dp[i]为必须以[i]为结尾的子串中，最大的累加和是多少，这样只要求出dp所有值，遍历一遍即可
 *
 * @author oudaming
 * @date 2021-03-09 16:16
 */
public class Offer_42 {
    public static void main(String[] args) {
        System.out.println(new Offer_42().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] dp = new int[N];
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
