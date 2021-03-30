package cn.leetcode;

/**
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 *
 * @author oudaming
 * @date 2021-03-30 9:26
 */
public class _74_Search_a_2D_Matrix {
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 10));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            if (matrix[i][M - 1] == target) {
                return true;
            }
            if (matrix[i][M - 1] > target) {
                for (int j = M - 2; j >= 0; j--) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                    if (matrix[i][j] < target) {
                        return false;
                    }
                }
                return false;
            }
        }
        return false;
    }
}
