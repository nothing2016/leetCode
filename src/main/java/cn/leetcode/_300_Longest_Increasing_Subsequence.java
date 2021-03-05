package cn.leetcode;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 * @author oudaming
 * @date 2021-03-05 10:33
 */
public class _300_Longest_Increasing_Subsequence {
    public static void main(String[] args) {
        System.out.println(new _300_Longest_Increasing_Subsequence().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(new _300_Longest_Increasing_Subsequence().lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(new _300_Longest_Increasing_Subsequence().lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = 1;
        int N = nums.length;
        // dp[i] 表示i位置结尾的 最长递增子序列的长度
        int[] dp = new int[N];
        dp[0] = 1;
        // ends[i]表示i+1长度的最长递增子序列的结尾值是ends[i]，那么ends[i]左边有多少个值就是i+1
        int[] ends = new int[N];
        ends[0] = nums[0];
        // l , r为了使用二分找到 >= nums[i] 的最左位置
        int l = 0;
        int r = 0;
        // 表示ends的有效的范围下标
        int right = 0;
        for (int i = 1; i < N; i++) {
            // 因为right值会更新，我们需要在ends数组中找到 >= nums[i] 的最左位置
            l = 0;
            r = right;
            while (l <= r) {
                int m = (l + r) >> 1;
                if (nums[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }

            // l的值就是扩充的有效区
            right = Math.max(right, l);
            ends[l] = nums[i];
            //ends[i]左边有多少个值就是i+1,也是dp[i]需要的长度
            dp[i] = l + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
