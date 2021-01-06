package cn.leetcode.dynamicPlanning;

/**
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
        if (coins == null || coins.length == 0 || amount < 0) {
            return -1;
        }
        return process(coins, 0, amount);
    }

    /**
     * @param arr  数组
     * @param i    当前来到的点
     * @param rest 剩下的钱
     * @return
     */
    public int process(int[] arr, int i, int rest) {
        if (i == arr.length) {
            // 刚好到最后时，如果为0，那么用0的面值组成0，使用的是0张
            return rest == 0 ? 0 : -1;
        }
        // 默认是没有找到任何解
        int result = -1;
        // 使用k张面值为arr[i]的零钱
        for (int k = 0; k * arr[i] <= rest; k++) {
            int next = process(arr, i + 1, rest - k * arr[i]);
            // 后面的值有效的情况，无效默认是-1了
            if (next != -1) {
                result = result == -1 ? k + next : Math.min(result, k + next);
            }
        }
        return result;
    }

    /**
     * dp解法
     *
     * @param arr
     * @param aim
     * @return
     */
    public int coinChange2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        // 初始化为-1
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        dp[arr.length][0] = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                // dp[i][rest - arr[i]] 的位置是有效的
                if (rest - arr[i] >= 0 && dp[i][rest - arr[i]] != -1) {

                    if (dp[i + 1][rest] == -1) {
                        dp[i][rest] = dp[i][rest - arr[i]] + 1;
                    } else {
                        // dp[i][rest - arr[i]] 和dp[i + 1][rest] 的位置同时有效
                        dp[i][rest] = Math.min(dp[i + 1][rest], dp[i][rest - arr[i]] + 1);
                    }
                } else {
                    dp[i][rest] = dp[i + 1][rest];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        int sum = new _322CoinChange().coinChange(coins, amount);
        int sum2 = new _322CoinChange().coinChange2(coins, amount);
        System.out.println(sum);
        System.out.println(sum2);
    }
}
