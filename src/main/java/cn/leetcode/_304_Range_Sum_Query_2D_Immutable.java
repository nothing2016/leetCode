package cn.leetcode;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 * <p>
 * 给定 matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * <p>
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * <p>
 * 提示：
 * 你可以假设矩阵不可变。
 * 会多次调用 sumRegion 方法。
 * 你可以假设 row1 ≤ row2 且 col1 ≤ col2 。
 *
 * @author oudaming
 * @date 2021-03-02 10:37
 */
public class _304_Range_Sum_Query_2D_Immutable {

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }
}

class NumMatrix {
    private int[][] matrix;
    private int[][] sum;
    private int[][] all;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        this.matrix = matrix;
        int N = matrix.length;
        int M = matrix[0].length;
        sum = new int[N][M];

        for (int i = 0; i < N; i++) {
            int total = 0;
            for (int j = 0; j < M; j++) {
                total += matrix[i][j];
                sum[i][j] = total;
            }
        }
        all = new int[N][M];
        for (int j = 0; j < M; j++) {
            int total = 0;
            for (int i = 0; i < N; i++) {
                total += sum[i][j];
                all[i][j] = total;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (all == null) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        if (row1 < 0 || row1 >= N
                || row2 < 0 || row2 >= N
                || col1 < 0 || col1 >= M
                || col2 < 0 || col2 >= M) {
            return 0;
        }

        int sub1 = row1 - 1 >= 0 ? all[row1 - 1][col2] : 0;
        int sub2 = col1 - 1 >= 0 ? all[row2][col1 - 1] : 0;
        int ans = all[row2][col2] - sub1 - sub2;

        if (row1 - 1 >= 0 && col1 - 1 >= 0) {
            ans += all[row1 - 1][col1 - 1];
        }
        return ans;
    }

    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println("===================================");
    }
}