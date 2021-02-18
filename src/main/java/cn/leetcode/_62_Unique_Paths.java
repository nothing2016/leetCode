package cn.leetcode;

/**
 * 62. 不同路径(机器人从左上角到右下角一共有多少路径)
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * <p>
 * 输入：m = 3, n = 7
 * 输出：28
 * <p>
 * 输入：m = 3, n = 2
 * 输出：3
 * <p>
 * 输入：m = 7, n = 3
 * 输出：28
 * <p>
 * 输入：m = 3, n = 3
 * 输出：6
 * <p>
 * 题解；这个题就是依赖左边的位置和上边的位置，动态规划
 *
 * @author oudaming
 * @date 2021-02-18 11:00
 */
public class _62_Unique_Paths {
    public static void main(String[] args) {
        System.out.println(new _62_Unique_Paths().uniquePaths(3, 7));
        System.out.println(new _62_Unique_Paths().uniquePaths2(3, 7));

        System.out.println(new _62_Unique_Paths().uniquePaths(3, 2));
        System.out.println(new _62_Unique_Paths().uniquePaths2(3, 2));

        System.out.println(new _62_Unique_Paths().uniquePaths(7, 3));
        System.out.println(new _62_Unique_Paths().uniquePaths2(7, 3));

        System.out.println(new _62_Unique_Paths().uniquePaths(3, 3));
        System.out.println(new _62_Unique_Paths().uniquePaths2(3, 3));
    }

    public int uniquePaths(int m, int n) {
        return process(m - 1, n - 1, m - 1, n - 1);
    }

    /**
     * 定义从(0,0)点出发，到底指定点(x,y)的方法数
     *
     * @param x
     * @param y
     * @param row
     * @param col
     * @return
     */
    private int process(int x, int y, int row, int col) {
        if (x < 0 || y < 0) {
            return 0;
        }
        if (x == 0 && y == 0) {
            return 1;
        }
        return process(x, y - 1, row, col) + process(x - 1, y, row, col);
    }

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int x = 1; x < m; x++) {
            for (int y = 1; y < n; y++) {
                dp[x][y] = dp[x - 1][y] + dp[x][y - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

}
