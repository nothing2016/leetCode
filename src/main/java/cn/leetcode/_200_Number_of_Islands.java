package cn.leetcode;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 * @author oudaming
 * @date 2021-03-23 15:33
 */
public class _200_Number_of_Islands {
    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'},
        };

        System.out.println(numIslands(grid));

        System.out.println(numIslands(new char[][]{{'0'}}));
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int ans = 0;
        int N = grid.length;
        int M = grid[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    process(i, j, N, M, grid);
                }
            }
        }
        return ans;
    }

    /**
     * 感染函数，将上下左右的位置全部变成2
     *
     * @param i
     * @param j
     */
    private static void process(int i, int j, int N, int M, char[][] grid) {
        if (i >= 0 && i < N && j >= 0 && j < M && grid[i][j] == '1') {
            grid[i][j] = '2';
            process(i + 1, j, N, M, grid);
            process(i - 1, j, N, M, grid);
            process(i, j - 1, N, M, grid);
            process(i, j + 1, N, M, grid);
        }
    }
}
