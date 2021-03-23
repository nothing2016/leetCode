package cn.leetcode;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * <p>
 * 题解：从左到右的尝试模型，
 * 来到了第i家门口
 * 如果偷第i家，下一次就只能偷[i + 2...N-1]范围的了
 * 如果偷第i + 1家，下一次就只能偷[i + 3...N-1]范围的了
 * 没有直接偷i + 2的情况，因为这样肯会偷 i ,因为num[i] + dp[i+2]肯定必dp[i+2]要多
 * 所以dp[i + 2]又是来到 i+2位置的考虑了
 *
 * @author oudaming
 * @date 2021-03-23 14:35
 */
public class _198_House_Robber {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob2(new int[]{1, 2, 3, 1}));

        System.out.println(rob(new int[]{2, 7, 9, 3, 1, 4}));
        System.out.println(rob2(new int[]{2, 7, 9, 3, 1, 4}));

        System.out.println(rob(new int[]{2, 7, 9, 3, 1, 100, 45, 6, 32}));
        System.out.println(rob2(new int[]{2, 7, 9, 3, 1, 100, 45, 6, 32}));

    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(process(0, nums), process(1, nums));
    }

    /**
     * @param i    i位置之后都可以选择
     * @param nums
     * @return
     */
    private static int process(int i, int[] nums) {
        // 如果已经越界，那么得到的结果就是0
        if (i >= nums.length) {
            return 0;
        }
        int sum1 = nums[i] + process(i + 2, nums);
        int sum2 = 0;
        if (i + 1 < nums.length) {
            sum2 = nums[i + 1] + process(i + 3, nums);
        }
        return Math.max(sum1, sum2);
    }

    /**
     * 动态规划的方法
     *
     * @param nums
     * @return
     */
    public static int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int N = nums.length;
        // dp[i] 代表[i,n-1]范围上能偷到的最大金钱
        int[] dp = new int[N + 1];
        dp[N - 1] = nums[N - 1];
        dp[N - 2] = Math.max(dp[N - 1], nums[N - 2]);
        for (int i = N - 3; i >= 0; i--) {
            dp[i] = nums[i] + dp[i + 2];
            if (i + 1 < N) {
                dp[i] = Math.max(dp[i], nums[i + 1] + dp[i + 3]);
            }
        }

        return dp[0];
    }
}
