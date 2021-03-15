package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 54.螺旋矩阵
 * <p>
 * 给定一个包含m x n个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * <p>
 * <p>
 * 思路：0:向左走，1:向下走，2:向左走，3:向上走
 * 使用一个int[][]数组记录走过的路，走过标记为 -1
 * 当走到头或走到已经标记走过的边界时，通过 (++i % 4) 改变方向即可
 *
 * @author oudaming
 * @date 2020-12-30 17:42
 */
public class _54SpiralMatrix {


    public static void main(String[] args) {
//        int[][] matrix = {
//                {1, 2, 3, 4, 15},
//                {5, 6, 8, 9, 16},
//                {10, 11, 12, 13, 17},
//        };
//        int[][] matrix = new int[4][3];
//        matrix[0] = new int[]{19, 12, 45};
//        matrix[1] = new int[]{23, 1, 67};
//        matrix[2] = new int[]{50, 34, 0};
//        matrix[3] = new int[]{9, 8, 7};
//        int[][] matrix = new int[2][1];
//        matrix[0] = new int[]{1};
//        matrix[1] = new int[]{2};
//        int[][] matrix = new int[2][1];
//        matrix[0] = new int[]{1};
//        matrix[1] = new int[]{3};
        int[][] matrix = {
                {1},
                {2},
                {3},
                {4},
                {5},
        };
        List<Integer> integers = new _54SpiralMatrix().spiralOrder2(matrix);
        integers.forEach(System.out::println);
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return ans;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int sR = 0;
        int sC = 0;
        int eR = N - 1;
        int eC = M - 1;
        while (sR <= eR && sC <= eC) {
            printArray(sR, sC, eR, eC, ans, matrix);
            sR++;
            sC++;
            eR--;
            eC--;
        }

        return ans;
    }

    private void printArray(int sR, int sC, int eR, int eC, List<Integer> ans, int[][] matrix) {
        int row = sR;
        int col = sC;
        if (sR == eR) {
            while (col <= eC) {
                ans.add(matrix[row][col++]);
            }
            return;
        }
        if (sC == eC) {
            while (row <= eR) {
                ans.add(matrix[row++][col]);
            }
            return;
        }

        while (col < eC) {
            ans.add(matrix[row][col++]);
        }
        while (row < eR) {
            ans.add(matrix[row++][col]);
        }
        while (col > sC) {
            ans.add(matrix[row][col--]);
        }
        while (row > sR) {
            ans.add(matrix[row--][col]);
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int direction = 0;
        int[][] visit = new int[matrix.length][matrix[0].length];
        List<Integer> result = new ArrayList<>();

        int row = 0; // 行
        int col = 0; // 列
        // 先从第一行第一列开始
        result.add(matrix[row][col]);
        visit[row][col] = -1;

        if (matrix.length == 1 && matrix[0].length == 1) {
            return result;
        }

        while (true) {
            if (direction == 0) {
                if (col + 1 != matrix[0].length && visit[row][col + 1] == -1) {
                    return result;
                }
                if (col + 1 == matrix[0].length) {
                    direction = (direction + 1) % 4;
                } else {
                    while (true) {
                        if (col + 1 < matrix[0].length && visit[row][col + 1] != -1) {
                            result.add(matrix[row][++col]);
                            visit[row][col] = -1;
                        } else {
                            direction = (direction + 1) % 4;
                            break;
                        }
                    }
                }

            }
            if (direction == 1) {
                if (row + 1 != matrix.length && visit[row + 1][col] == -1) {
                    return result;
                }
                if (row + 1 == matrix.length) {
                    direction = (direction + 1) % 4;
                } else {
                    while (true) {
                        if (row + 1 < matrix.length && visit[row + 1][col] != -1) {
                            result.add(matrix[++row][col]);
                            visit[row][col] = -1;
                        } else {
                            direction = (direction + 1) % 4;
                            break;
                        }
                    }
                }

            }
            if (direction == 2) {
                if (col - 1 >= 0 && visit[row][col - 1] == -1) {
                    return result;
                }
                if (col - 1 < 0) {
                    direction = (direction + 1) % 4;
                } else {
                    while (true) {
                        if (col - 1 >= 0 && visit[row][col - 1] != -1) {
                            result.add(matrix[row][--col]);
                            visit[row][col] = -1;
                        } else {
                            direction = (direction + 1) % 4;
                            break;
                        }
                    }
                }

            }
            if (direction == 3) {
                if (row - 1 >= 0 && visit[row - 1][col] == -1) {
                    return result;
                }
                if (row - 1 < 0) {
                    direction = (direction + 1) % 4;
                } else {
                    while (true) {
                        if (row - 1 >= 0 && visit[row - 1][col] != -1) {
                            result.add(matrix[--row][col]);
                            visit[row][col] = -1;
                        } else {
                            direction = (direction + 1) % 4;
                            break;
                        }
                    }
                }

            }
        }
    }
}
