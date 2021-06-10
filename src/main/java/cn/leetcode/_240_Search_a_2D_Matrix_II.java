package cn.leetcode;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10^9 <= matix[i][j] <= 10^9
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -10^9 <= target <= 10^9
 * <p>
 * 题解：查找的过程，从右上角开始
 * 1.如果当前的值 > target,往左走
 * 2.如果当前的值 < target，往下走
 * 3,如果当前的值 == target, return true
 */
public class _240_Search_a_2D_Matrix_II {
    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}}, 500));
    }

    public static boolean searchMatrix(int[][] m, int target) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return false;
        }
        int N = m.length;
        int M = m[0].length;
        int row = 0;
        int col = M - 1;
        while (row < N && col >= 0) {
            if (m[row][col] > target) {
                col--;
            } else if (m[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }
}
