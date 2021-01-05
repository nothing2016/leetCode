package cn.leetcode.dynamicPlanning;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * @author oudaming
 * @date 2021/1/5 0005 23:20
 */
public class _322CoinChange {
    public int coinChange(int[] coins, int amount) {
        int length = coins.length;
        int sum = 0;
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        for (int i = 0; i < length; i++) {
            sum += coinChange(coins, amount - coins[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        int sum = new _322CoinChange().coinChange(coins, amount);
        System.out.println(sum);
    }
}
