package cn.leetcode;

/**
 * 64. 最小路径和
 * <p>
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 * <p>
 * 题解： 动态规划，dp[i][j]表示从[i][j]出发，到达右下角的最小路径和
 *
 * @author oudaming
 * @date 2021-02-25 13:55
 */
public class _64_Minimum_Path_Sum {
    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
        System.out.println(minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }

    public static int minPathSum(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[][] dp = new int[N][M];
        dp[N - 1][M - 1] = grid[N - 1][M - 1];
        for (int i = N - 2; i >= 0; i--) {
            dp[i][M - 1] = dp[i + 1][M - 1] + grid[i][M - 1];
        }
        for (int i = M - 2; i >= 0; i--) {
            dp[N - 1][i] = dp[N - 1][i + 1] + grid[N - 1][i];
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
            }
        }
        return dp[0][0];
    }
}
