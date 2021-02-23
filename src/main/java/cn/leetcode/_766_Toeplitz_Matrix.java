package cn.leetcode;

/**
 * 766. 托普利茨矩阵
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * <p>
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * 输出：true
 * 解释：
 * 在上述矩阵中, 其对角线为:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
 * 各条对角线上的所有元素均相同, 因此答案是 True 。
 *
 * @author oudaming
 * @date 2021-02-22 9:23
 */
public class _766_Toeplitz_Matrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}};
//        int[][] matrix = {{1, 2}, {2, 3}};
        System.out.println(isToeplitzMatrix(matrix));
    }

    public static boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null) {
            return false;
        }
        // N行
        int N = matrix.length;
        // M列
        int M = matrix[0].length;
        int startRow = N - 1;
        int startCol = 0;
        while (startCol < M) {
            boolean ans = check(matrix, startRow, startCol);
            if (!ans) {
                return false;
            }
            if (startRow == 0) {
                startCol++;
            } else {
                startRow--;
            }
        }
        return true;
    }

    /**
     * 检查从当前点一路右下的路径是不是合法
     *
     * @param matrix
     * @param startRow
     * @param startCol
     * @return
     */
    private static boolean check(int[][] matrix, int startRow, int startCol) {
        int tmp = matrix[startRow][startCol];
        while (startRow != matrix.length && startCol != matrix[0].length) {
            if (tmp != matrix[startRow][startCol]) {
                return false;
            }
            startCol++;
            startRow++;
        }
        return true;
    }
}
