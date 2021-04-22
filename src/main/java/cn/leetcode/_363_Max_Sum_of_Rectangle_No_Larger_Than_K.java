package cn.leetcode;

import java.util.TreeSet;

/**
 * 363. 矩形区域不超过 K 的最大数值和
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 * <p>
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出：2
 * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 示例 2：
 * <p>
 * 输入：matrix = [[2,2,-1]], k = 3
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -10^5 <= k <= 10^5
 * <p>
 * <p>
 * 进阶：如果行数远大于列数，该如何设计解决方案？
 *
 * @author oudaming
 * @date 2021-04-22 9:38
 */
public class _363_Max_Sum_of_Rectangle_No_Larger_Than_K {
    public static void main(String[] args) {
        System.out.println(maxSumSubmatrix(new int[][]{{1, 0, 1}, {0, -2, 3}}, 2));
        System.out.println(maxSumSubmatrix2(new int[][]{{1, 0, 1}, {0, -2, 3}}, 2));

        System.out.println(maxSumSubmatrix(new int[][]{{2, 2, -1}}, 3));
        System.out.println(maxSumSubmatrix2(new int[][]{{2, 2, -1}}, 3));
    }


    /**
     * 将多行压缩成一行，变成一维数组来求解
     * 正解O(m^2 * nlog n)
     * <p>
     * Sl,Sr 表示从（0，0） 到（x,y） 的压缩一维数组
     * <p>
     * 那么中间矩阵的值就是  Sr - Sl, 现在要求的就是 Sr-Sl <= k， 要让Sr-Sl 尽量接近k
     * 所以使用有序表来加速寻找的过程，求ceiling的过程是二分的，时间复杂度为logN
     * 所有整体的时间复杂度为O(m^2 * nlog n)
     *
     * @param matrix
     * @param k
     * @return
     */
    public static int maxSumSubmatrix2(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int N = matrix.length, M = matrix[0].length;
        for (int i = 0; i < N; ++i) { // 枚举上边界
            int[] sum = new int[M];
            for (int j = i; j < N; ++j) { // 枚举下边界
                for (int c = 0; c < M; ++c) {
                    sum[c] += matrix[j][c]; // 更新每列的元素和
                }
                TreeSet<Integer> sumSet = new TreeSet<Integer>();
                sumSet.add(0);
                int Sr = 0;
                for (int v : sum) {
                    Sr += v;
                    Integer Sl = sumSet.ceiling(Sr - k);
                    if (Sl != null) {
                        ans = Math.max(ans, Sr - Sl);
                    }
                    sumSet.add(Sr);
                }
            }
        }
        return ans;
    }

    /**
     * 时间复杂度O(N^2 * M^2)
     *
     * @param matrix
     * @param k
     * @return
     */
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        NumMatrix numMatrix = new NumMatrix(matrix);
        TreeSet<Long> set = new TreeSet<>();
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int r = i; r < N; r++) {
                    for (int c = j; c < M; c++) {
                        int sum = numMatrix.sumRegion(i, j, r, c);
                        if (sum == k) {
                            return k;
                        } else {
                            set.add((long) sum);
                        }
                    }
                }
            }
        }
        Long floor = set.floor((long) k);
        return Integer.valueOf(floor.toString());
    }

    private static class NumMatrix {
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
    }
}
