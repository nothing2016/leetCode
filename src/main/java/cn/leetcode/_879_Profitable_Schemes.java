package cn.leetcode;

/**
 * 879. 盈利计划
 * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * 输出：2
 * 解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
 * 总的来说，有两种计划。
 * 示例 2：
 * <p>
 * 输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * 输出：7
 * 解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
 * 有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 100
 * 0 <= minProfit <= 100
 * 1 <= group.length <= 100
 * 1 <= group[i] <= 100
 * profit.length == group.length
 * 0 <= profit[i] <= 100
 * <p>
 * 题解： 使用动态规划
 * dp[i][j][k] 表示在前 i 个工作中选择了 j个员工，并且满足工作利润至少为 k 的情况下的盈利计划的总数目
 * j - members < 0  => dp[i][j][k] = dp[i - 1][j][k]
 * j - members >= 0 => dp[i][j][k] = dp[i - 1][j][k] + dp[i - 1][j - members][Math.max(0, k - earn)]
 */
public class _879_Profitable_Schemes {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int len = group.length, MOD = (int) 1e9 + 7;
        //其中 dp[i][j][k] 表示在前 i 个工作中选择了 j个员工，并且满足工作利润至少为 k 的情况下的盈利计划的总数目
        int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;// 表示前0个工作，选择0个员工，利润至少为0的方法数，那就是1种（什么都不选）
        for (int i = 1; i <= len; i++) {
            // 第i个工作需要的人数
            int members = group[i - 1];
            // 第i个工作挣的利润
            int earn = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    // 如果人数不够，那么就是i-1项工作，选择j个员工的，润至少为 k的计划总数
                    if (j < members) {
                        dp[i][j][k] = dp[i - 1][j][k];
                        // 如果人数是够的
                    } else {
                        // Math.max(0, k - earn) 表示利润不可以为负数
                        // dp[i - 1][j][k] 当前人数不够的方案数  和 dp[i - 1][j - members][Math.max(0, k - earn)]  当前人数够的方案数
                        // 那当前i这个方案数不计算吗？其实是计算的，因为只加了一种工作的话，总的工作数是没有发生变化的
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - members][Math.max(0, k - earn)]) % MOD;
                    }
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= n; j++) {
            sum = (sum + dp[len][j][minProfit]) % MOD;
        }
        return sum;
    }
}
