package cn.leetcode;

/**
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
 * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 * 题解：从左到右的尝试模型，
 * 来到了第i家门口
 * 如果偷第i家，下一次就只能偷[i + 2...N-1]范围的了
 * 如果偷第i + 1家，下一次就只能偷[i + 3...N-1]范围的了
 * 没有直接偷i + 2的情况，因为这样肯会偷 i ,因为num[i] + dp[i+2]肯定必dp[i+2]要多
 * 所以dp[i + 2]又是来到 i+2位置的考虑了
 * <p>
 * 以上是不是圆环的解法，其实圆环的解法也是一样的，就是分成两种情况
 * 1.获取[0,n-2]的值 dp[0]
 * 2.获取[1,n-1]的值 dp2[1]
 * 这样就一定不会存在环的问题，然后返回max(dp[0],dp2[1])即可
 *
 * @author oudaming
 * @date 2021-03-29 10:30
 */
public class _213_House_Robber_II {

    public static void main(String[] args) {
        System.out.println(rob(new int[]{2, 3, 2}));
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{7, 2, 3, 1, 2}));
    }

    /**
     * 动态规划的方法
     *
     * @param nums
     * @return
     */
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
        int N = nums.length;
        // dp[i] 代表[i,n-1]范围上能偷到的最大金钱
        int[] dp = new int[N + 1];
        dp[N - 1] = nums[N - 1];
        dp[N - 2] = Math.max(dp[N - 1], nums[N - 2]);
        for (int i = N - 3; i >= 1; i--) {
            dp[i] = nums[i] + dp[i + 2];
            if (i + 1 < N) {
                dp[i] = Math.max(dp[i], nums[i + 1] + dp[i + 3]);
            }
        }
        // 从1位置开始选择到N-1的位置
        int ans = dp[1];


        // 从0位置开始选择到N-2的位置
        int[] dp2 = new int[N];
        dp2[N - 2] = nums[N - 2];
        dp2[N - 3] = Math.max(dp2[N - 2], nums[N - 3]);
        for (int i = N - 4; i >= 0; i--) {
            dp2[i] = nums[i] + dp2[i + 2];
            if (i + 1 < N) {
                dp2[i] = Math.max(dp2[i], nums[i + 1] + dp2[i + 3]);
            }
        }
        ans = Math.max(ans, dp2[0]);
        return ans;
    }
}
