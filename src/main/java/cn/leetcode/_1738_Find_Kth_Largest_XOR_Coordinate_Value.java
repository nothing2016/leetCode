package cn.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1738. 找出第 K 大的异或坐标值
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 * <p>
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 * <p>
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[5,2],[1,6]], k = 1
 * 输出：7
 * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 * 示例 2：
 * <p>
 * 输入：matrix = [[5,2],[1,6]], k = 2
 * 输出：5
 * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 * 示例 3：
 * <p>
 * 输入：matrix = [[5,2],[1,6]], k = 3
 * 输出：4
 * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 * 示例 4：
 * <p>
 * 输入：matrix = [[5,2],[1,6]], k = 4
 * 输出：0
 * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 1000
 * 0 <= matrix[i][j] <= 10^6
 * 1 <= k <= m * n
 */
public class _1738_Find_Kth_Largest_XOR_Coordinate_Value {
    public static void main(String[] args) {
        System.out.println(kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 1));
        System.out.println(kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 2));
        System.out.println(kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 3));
        System.out.println(kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 4));
    }

    public static int kthLargestValue(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] xorSum = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j == 0) {
                    xorSum[i][j] = matrix[i][j];
                } else {
                    xorSum[i][j] = matrix[i][j] ^ xorSum[i][j - 1];
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < M; j++) {
            int ans = xorSum[0][j];
            list.add(ans);
            for (int i = 1; i < N; i++) {
                ans ^= xorSum[i][j];
                list.add(ans);
            }
        }

        Collections.sort(list, (a, b) -> b - a);
        return list.get(k - 1);
    }
}
