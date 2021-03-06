package cn.leetcode;

/**
 * 123. 买卖股票的最佳时机 III
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。z
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
 * <p>
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * <p>
 * <p>
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * @author oudaming
 * @date 2021-03-04 16:36
 */
public class _123_Best_Time_to_Buy_and_Sell_Stock_III {
    public static void main(String[] args) {
        System.out.println(new _123_Best_Time_to_Buy_and_Sell_Stock_III().maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(new _123_Best_Time_to_Buy_and_Sell_Stock_III().maxProfit(new int[]{1}));
        System.out.println(new _123_Best_Time_to_Buy_and_Sell_Stock_III().maxProfit(new int[]{}));
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        return dp(2, prices);
    }

    // 这个题是动态规划中的斜率优化，很难，需要理解再看一次视频
    public static int dp(int K, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        // 如果次数大于N/2 认为K是无限次的
        if (K >= N / 2) {
            return allTrans(prices);
        }
        // dp[i][j] 表示在[0,i]上的股票范围内进行不超过j次的交易
        // 那么dp[0][0,j]肯定是0，因为在一个时间点买入和卖出，得到的钱是0
        // dp[0,i][0]肯定也是0，因为进行0次买卖，得到的钱肯定是0
        /**
         * dp[i][j] 依赖下面的结果
         * 1）dp[i-1][j]  不在[i]位置进行买卖，与i无关
         *    下面就是与i位置有关的情况
         * 2）dp[i][j-1] + [i] - [i]  在[i]位置买入，并在i位置卖掉
         *    dp[i-1][j-1] + [i] - [i-1]  在[i-1]位置买入，并在i位置卖掉
         *    dp[i-2][j-1] + [i] - [i-2]  在[i-2]位置买入，并在i位置卖掉
         *    .......
         *    dp[0][j-1] + [i] - [0]  在[0]位置买入，并在i位置卖掉
         *    2的这种情况中找到max即可，再与1取max
         *
         *
         *  在1和2两种大的可能性中找最大的值，经观察2的情况可以优化，在下次计算时复用
         */
        int[][] dp = new int[N][K + 1];
        int ans = 0;
        for (int j = 1; j <= K; j++) {
            // 每次优化的结果
            int t = dp[0][j - 1] - prices[0];
            for (int i = 1; i < N; i++) {
                t = Math.max(t, dp[i][j - 1] - prices[i]);
                dp[i][j] = Math.max(dp[i - 1][j], t + prices[i]);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    public static int allTrans(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }
}
