package cn.leetcode;

/**
 * 121. 买卖股票的最佳时机
 * <p>
 * 给定一个数组 prices ，它的第i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * 题解：构建一个预处理数组 max[i],表示 第i(不包含i)天后的股票最大值，这样只要当前买入，prices[i] < max[i],就能得到当前买入后，卖出的最大收益
 * 遍历一遍，求最大值即可
 *
 * @author oudaming
 * @date 2021-03-04 14:04
 */
public class _121_Best_Time_to_Buy_and_Sell_Stock {
    public static void main(String[] args) {
        System.out.println(new _121_Best_Time_to_Buy_and_Sell_Stock().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0;
        int N = prices.length;
        int[] help = new int[N];
        for (int i = N - 2; i >= 0; i--) {
            help[i] = Math.max(prices[i + 1], help[i + 1]);
        }
        for (int i = 0; i < N; i++) {
            ans = Math.max(help[i] - prices[i], ans);
        }
        return ans;
    }
}
