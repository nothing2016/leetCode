package cn.leetcode.dynamicPlanning;

import java.util.stream.IntStream;

/**
 * TODO   目前还没有找到解
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1。
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
        if (coins == null || coins.length == 0 || amount == 0) {
            return 0;
        }
        // 数组逆序，贪心算法
        coins = IntStream.of(coins).boxed().sorted((a, b) -> b - a).mapToInt(Integer::intValue).toArray();
        int number = getNumber(coins, 0, amount);
        return number == 0 ? -1 : number;
    }

    /**
     * @param coins
     * @param index 当前要哪一个硬币的下标
     * @param rest  要组成多少钱
     * @return
     */
    public int getNumber(int[] coins, int index, int rest) {
        if (rest < 0 || index >= coins.length) {
            return 0;
        }
        if (rest == 0) {
            return index == coins.length ? 1 : 0;
        }
        // 使用最少的硬币个数
        int sum = Integer.MAX_VALUE;
        // i代表多少个coins[index]的硬币
        for (int i = 0; i * coins[index] < rest; i++) {
            int number = getNumber(coins, index + 1, rest - (coins[index] * i));
            if (number >= 0) {
                sum = Math.min(sum, number + i);
            }
        }
        return sum == Integer.MAX_VALUE ? 0 : sum;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        int sum = new _322CoinChange().coinChange(coins, amount);
        System.out.println(sum);
    }
}
