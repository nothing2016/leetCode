package cn.leetcode;

/**
 * 518. 零钱兑换 II
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 * <p>
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 * <p>
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 * <p>
 * <p>
 * 注意:
 * <p>
 * 你可以假设：
 * <p>
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 * <p>
 * <p>
 * 题解： 使用一个样本做行，一个样本做列的对应模型
 * 定义dp[i][j] 为前i个硬币拼成金额为j的方法数
 * 1.第一行为，如果 j % coins[0] == 0(被整除)，那么dp[0][j] == 1，如果被整除，就刚好是一种方法数
 * 2.第一列为1，因为钱i个硬币拼成金额为0的方法数就是什么都不选，即一种
 * 3.对于通用位置i，j的值如何填写
 * a.如果第i个硬币一个都不选，那么值就是dp[i-1][j - coins[i] * 0]
 * b.如果第i个硬币选择1个，那么值就是dp[i-1][j - coins[i] * 1]
 * c.如果第i个硬币选择2个，那么值就是dp[i-1][j - coins[i] * 2]
 * ...
 * x.如果第i个硬币选择k个，那么值就是dp[i-1][j - coins[i] * k]
 * 4.所以得到方程式为dp[i][j] = sum(dp[i-1][j - coins[i] * k])
 * 具体代码参考如下：
 */
public class _518_Coin_Change_2 {
    public static void main(String[] args) {
        System.out.println(change(5, new int[]{1, 2, 5}));
        System.out.println(change(3, new int[]{2}));
        System.out.println(change(10, new int[]{10}));
    }

    public static int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        if (amount == 0) {
            return 1;
        }
        int N = coins.length;
        int M = amount;
        int[][] dp = new int[N][M + 1];
        for (int j = 0; j <= M; j++) {
            dp[0][j] = j % coins[0] == 0 ? 1 : 0;
        }
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int k = 0; k <= j / coins[i]; k++) {
                    dp[i][j] += dp[i - 1][j - coins[i] * k];
                }
            }
        }
        return dp[N - 1][M];
    }
}
