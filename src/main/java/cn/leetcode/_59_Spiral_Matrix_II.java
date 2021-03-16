package cn.leetcode;

/**
 * 59. 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 20
 *
 * @author oudaming
 * @date 2021-03-16 9:23
 */
public class _59_Spiral_Matrix_II {
    public static void main(String[] args) {
        printArray(new _59_Spiral_Matrix_II().generateMatrix(1));
        printArray(new _59_Spiral_Matrix_II().generateMatrix(3));
        printArray(new _59_Spiral_Matrix_II().generateMatrix(6));
    }

    public static void printArray(int[][] ints) {
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[0].length; j++) {
                System.out.print(ints[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println("=============");
    }

    public int[][] generateMatrix(int n) {
        int N = n;
        int[][] matrix = new int[N][N];
        int M = N;
        int sR = 0;
        int sC = 0;
        int eR = N - 1;
        int eC = M - 1;
        int num = 1;
        while (sR <= eR && sC <= eC) {
            num = printArray(sR, sC, eR, eC, matrix, num);
            sR++;
            sC++;
            eR--;
            eC--;
        }
        return matrix;
    }


    private int printArray(int sR, int sC, int eR, int eC, int[][] matrix, int num) {
        int row = sR;
        int col = sC;
        if (sR == eR) {
            while (col <= eC) {
                matrix[row][col++] = num++;
            }
            return num;
        }
        if (sC == eC) {
            while (row <= eR) {
                matrix[row++][col] = num++;
            }
            return num;
        }

        while (col < eC) {
            matrix[row][col++] = num++;
        }
        while (row < eR) {
            matrix[row++][col] = num++;
        }
        while (col > sC) {
            matrix[row][col--] = num++;
        }
        while (row > sR) {
            matrix[row--][col] = num++;
        }
        return num;
    }
}
