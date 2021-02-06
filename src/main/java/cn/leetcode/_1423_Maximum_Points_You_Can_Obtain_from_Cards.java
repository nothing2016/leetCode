package cn.leetcode;

import java.util.Arrays;

/**
 * 1423. 可获得的最大点数
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * <p>
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 * <p>
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 * <p>
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 * <p>
 * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
 * 输出：12
 * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
 * <p>
 * <p>
 * [53,14,91,35,51,9,80,27,6,15,77,86,34,62,55,45,91,45,23,75,66,42,62,13,34,18,89,67,93,83,100,14,92,73,48,2,47,93,99,100,88,84,48]
 * 43
 *
 * @author oudaming
 * @date 2021/2/6 0006 23:21
 */
public class _1423_Maximum_Points_You_Can_Obtain_from_Cards {
    public static void main(String[] args) {
        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore2(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore3(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));

        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore(new int[]{2, 2, 2}, 2));
        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore2(new int[]{2, 2, 2}, 2));
        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore3(new int[]{2, 2, 2}, 2));

        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7));
        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore2(new int[]{9, 7, 7, 9, 7, 7, 9}, 7));
        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore3(new int[]{9, 7, 7, 9, 7, 7, 9}, 7));

        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore(new int[]{1, 1000, 1}, 1));
        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore2(new int[]{1, 1000, 1}, 1));
        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore3(new int[]{1, 1000, 1}, 1));

        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3));
        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore2(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3));
        System.out.println(new _1423_Maximum_Points_You_Can_Obtain_from_Cards().maxScore3(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3));
    }

    public int maxScore(int[] cardPoints, int k) {
        if (k > cardPoints.length) {
            return 0;
        }
        int N = cardPoints.length;
        return process(cardPoints, 0, N - 1, k);
    }

    /**
     * 在范围[l,r]上获取k次得到的最大点数
     *
     * @param cardPoints
     * @param l
     * @param r
     * @param k
     * @return
     */
    private int process(int[] cardPoints, int l, int r, int k) {
        if (k == 0) {
            return 0;
        }
        // 1.选择左边
        int p1 = cardPoints[l] + process(cardPoints, l + 1, r, k - 1);
        // 2.选择右边
        int p2 = cardPoints[r] + process(cardPoints, l, r - 1, k - 1);
        return Math.max(p1, p2);
    }

    public int maxScore2(int[] cardPoints, int K) {
        if (K > cardPoints.length) {
            return 0;
        }
        int N = cardPoints.length;
        int[][][] dp = new int[N + 1][N + 1][K + 1];
        for (int k = 1; k <= K; k++) {
            for (int l = N - 1; l >= 0; l--) {
                for (int r = 1; r < N; r++) {
                    dp[l][r][k] = Math.max(cardPoints[l] + dp[l + 1][r][k - 1], cardPoints[r] + dp[l][r - 1][k - 1]);
                }
            }
        }
        return dp[0][N - 1][K];
    }

    /**
     * 由于剩余卡牌是连续的，使用一个固定长度为 n-kn−k 的滑动窗口对数组
     * cardPoints 进行遍历，求出滑动窗口最小值，然后用所有卡牌的点数之和减去该最小值，
     * 即得到了拿走卡牌点数之和的最大值
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore3(int[] cardPoints, int k) {
        int n = cardPoints.length;
        // 滑动窗口大小为 n-k
        int windowSize = n - k;
        // 选前 n-k 个作为初始值
        int sum = 0;
        for (int i = 0; i < windowSize; ++i) {
            sum += cardPoints[i];
        }
        int minSum = sum;
        for (int i = windowSize; i < n; ++i) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            sum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, sum);
        }
        return Arrays.stream(cardPoints).sum() - minSum;
    }

}
