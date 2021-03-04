package cn.leetcode;

/**
 * 122. 买卖股票的最佳时机 II
 * <p>
 * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * @author oudaming
 * @date 2021-03-04 16:26
 */
public class _122_Best_Time_to_Buy_and_Sell_Stock_II {
    public static void main(String[] args) {
        System.out.println(new _122_Best_Time_to_Buy_and_Sell_Stock_II().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(new _122_Best_Time_to_Buy_and_Sell_Stock_II().maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    public int maxProfit(int[] prices) {
        int N = prices.length;
        if (prices == null || N == 0 || N == 1) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < N; i++) {
            if (prices[i] > prices[i - 1]) {
                ans += (prices[i] - prices[i - 1]);
            }
        }
        return ans;
    }
}
