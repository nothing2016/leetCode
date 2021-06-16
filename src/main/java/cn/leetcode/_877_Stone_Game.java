package cn.leetcode;

/**
 * 877. 石子游戏
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 * <p>
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * <p>
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 * <p>
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[5,3,4,5]
 * 输出：true
 * 解释：
 * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
 * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= piles.length <= 500
 * piles.length 是偶数。
 * 1 <= piles[i] <= 500
 * sum(piles) 是奇数。
 *
 * @author oudaming
 * @create 2021-06-16 15:55
 **/
public class _877_Stone_Game {

    public boolean stoneGame(int[] piles) {
        return win2(piles);
    }

    /**
     * 返回先手和后手的分数，较大的就是赢家的分数
     *
     * @param arr
     * @return
     */
    public static boolean win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        // 先手的分数
        int f = f(arr, 0, arr.length - 1);
        // 后手的分数
        int s = s(arr, 0, arr.length - 1);

        return f > s;
    }

    // L....R
    // F  S  L+1..R
    // L..R-1

    /**
     * f： first 表示第一个拿牌的人（先手），在L到R范围上能获得的最大分数
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int f(int[] arr, int L, int R) {
        // 如果只有一张牌，直接拿走
        if (L == R) {
            return arr[L];
        }
        // 从左边或右边那一张牌，但拿完后，剩下的过程自己就变成后手了
        // 所以要选择最大的值
        return Math.max(
                arr[L] + s(arr, L + 1, R),
                arr[R] + s(arr, L, R - 1)
        );
    }

    // arr[L..R]

    /**
     * s： second 表示第二个拿牌的人（后手），能获得的最大分数
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int s(int[] arr, int L, int R) {
        // 如果是后手，只有一张牌，肯定被先手拿走了，自己肯定什么都没有拿到，所以是0
        if (L == R) {
            return 0;
        }
        // 如果自己是后手，那么先手肯定将先手自己的牌拿走后，剩下最不利的给后手
        // 所以自己变成先手时，肯定拿到最小的牌
        // 注意： 这都是从先手的角度考虑的问题
        return Math.min(
                f(arr, L + 1, R), // arr[i]
                f(arr, L, R - 1)  // arr[j]
        );
    }

    /**
     * 动态规划的方式求解
     *
     * @param arr
     * @return
     */
    public static boolean win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];
        for (int i = 0; i < N; i++) {
            f[i][i] = arr[i];
        }
        // s[i][i] = 0;
        for (int i = 1; i < N; i++) {
            int L = 0;
            int R = i;
            while (L < N && R < N) {

                f[L][R] = Math.max(
                        arr[L] + s[L + 1][R],
                        arr[R] + s[L][R - 1]
                );
                s[L][R] = Math.min(
                        f[L + 1][R], // arr[i]
                        f[L][R - 1]  // arr[j]
                );

                L++;
                R++;

            }
        }
        return f[0][N - 1] > s[0][N - 1];
    }

    public static void main(String[] args) {
//        int[] arr = {4, 7, 9, 5, 19, 29, 80, 4};
        int[] arr = new int[]{5, 3, 4, 5};
        // A 4 9
        // B 7 5
        System.out.println(win1(arr));
        System.out.println(win2(arr));

    }
}
