package cn.leetcode;

/**
 * 48. 旋转图像(旋转矩阵)
 * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * @author oudaming
 * @date 2021-02-18 15:19
 */
public class _48_Rotate_Image {
    public static void rotate(int[][] matrix) {
        // 左上角
        int a = 0;
        int b = 0;
        // 右下角
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        // 这两个点可以定位一个矩阵
        while (a < c) {
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    //两个点：左上角 (a,b)  右下角 (c,d)
    public static void rotateEdge(int[][] m, int a, int b, int c, int d) {
        int tmp = 0;
        for (int i = 0; i < d - b; i++) {
            tmp = m[a][b + i];
            m[a][b + i] = m[c - i][b];
            m[c - i][b] = m[c][d - i];
            m[c][d - i] = m[a + i][d];
            m[a + i][d] = tmp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}
