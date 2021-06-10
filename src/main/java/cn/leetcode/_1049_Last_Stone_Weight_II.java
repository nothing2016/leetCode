package cn.leetcode;

/**
 * 1049. 最后一块石头的重量 II
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * <p>
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * 示例 2：
 * <p>
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 * 示例 3：
 * <p>
 * 输入：stones = [1,2]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 * <p>
 * 题解：首先达成一个共识就是，数组中的每一个数添加 + 或 - ，计算最后的结果，结果大于0，且最接近0的就是答案，暴力方法可以求解如下
 * 1.如果我们获得结果后，将+号和-号分成两堆的话，正号的累加  与  负号的累加的差值的绝对值就是答案
 * 2.这样转换成，在数组中找出n个石头，使得累加和 tmpSum<=  sum/2 (sum是所有值的累加)，并最靠近sum/2的值
 * 3.最后的答案就是  Math.abs(sum - tmpSum - tmpSum)
 * 4.这就转换成从左到右的尝试模型，选择和不选择的背包模型了
 * 5.定义dp[i][j] 为 在0到i的石头中，找到最接近累加和为j的价值，价值就是stones[i]本身
 * 6.第一行的填写为：stone[i] > j ,dp[0][j] = 0, 否则 dp[0][j] = stone[i]
 * 7.第一列全部为0，因为累加和接近0的，只有0个石头，所以是0
 * 8.对于任意行，一个位置可以 选择和不选
 * == 8.1  不选就是dp[i][j] = dp[i-1][j]， 不选的情况有 j-stone[i]]< 0,已经超过容量的情况
 * == 8.2  选择就是dp[i][j] = dp[i-1][j-stone[i]] + stone[i]
 * ==  所以方程式就是： dp[i][j] = max(dp[i-1][j-1],dp[i-1][j-stone[i]] + stone[i])
 * 9.这个尝试模型就是从左到右，从上到下的尝试模型
 */
public class _1049_Last_Stone_Weight_II {
    public static void main(String[] args) {
        System.out.println(lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1}));
        System.out.println(lastStoneWeightII_dp(new int[]{2, 7, 4, 1, 8, 1}));
        System.out.println(lastStoneWeightII(new int[]{31, 26, 33, 21, 40}));
        System.out.println(lastStoneWeightII_dp(new int[]{31, 26, 33, 21, 40}));
        System.out.println(lastStoneWeightII(new int[]{2, 1}));
        System.out.println(lastStoneWeightII_dp(new int[]{2, 1}));
        System.out.println(lastStoneWeightII(new int[]{10, 9, 9, 8}));
        System.out.println(lastStoneWeightII_dp(new int[]{10, 9, 9, 8}));
    }

    /**
     * 动态规划方法
     *
     * @param stones
     * @return
     */
    public static int lastStoneWeightII_dp(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        if (stones.length == 1) {
            return stones[0];
        }
        int N = stones.length;
        int sum = 0;
        for (int num : stones) {
            sum += num;
        }
        int M = sum / 2;
        // 定义dp[i][j] 为 在0到i的石头中，找到最接近累加和为j的价值
        int[][] dp = new int[N][M + 1];
        // 第一行填写
        for (int j = 0; j <= M; j++) {
            dp[0][j] = j >= stones[0] ? stones[0] : 0;
        }
        // 第一列全部是0，省略
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= M; j++) {
                // stones[i] 太大了的情况
                if (j - stones[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                }
            }
        }

        int tmpSum = dp[N - 1][M];
        int ans = sum - tmpSum - tmpSum;
        return ans;
    }


    private static int ans = Integer.MAX_VALUE;

    /**
     * 暴力方式，超时
     *
     * @param stones
     * @return
     */
    public static int lastStoneWeightII(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        if (stones.length == 1) {
            return stones[0];
        }
        ans = Integer.MAX_VALUE;
        proccess(0, 0, stones);
        return ans;
    }

    /**
     * 暴力直接会超时
     *
     * @param index
     * @param sum
     * @param stones
     */
    private static void proccess(int index, int sum, int[] stones) {
        if (index == stones.length) {
            if (sum >= 0 && sum < ans) {
                ans = sum;
            }
            return;
        }
        proccess(index + 1, sum + stones[index], stones);
        proccess(index + 1, sum - stones[index], stones);
    }
}
