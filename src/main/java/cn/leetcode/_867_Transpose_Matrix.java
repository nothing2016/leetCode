package cn.leetcode;

/**
 * 867. 转置矩阵
 * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
 * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[1,4,7],[2,5,8],[3,6,9]]
 *
 * @author oudaming
 * @date 2021-02-25 9:16
 */
public class _867_Transpose_Matrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}};
        print(matrix);
        int[][] ans = transpose(matrix);
        print(ans);
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println("================");
    }

    public static int[][] transpose(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;

        int[][] ans = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                ans[i][j] = matrix[j][i];
            }
        }
        return ans;
    }
}
