package cn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 * <p>
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为11（即，2+3+5+1= 11）。
 *
 * @author oudaming
 * @date 2021-03-05 11:43
 */
public class _120_Triangle {
    public static void main(String[] args) {
        //  [[2],[3,4],[6,5,7],[4,1,8,3]]
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(2));
//        list.add(Arrays.asList(3, 4));
//        list.add(Arrays.asList(6, 5, 7));
//        list.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(new _120_Triangle().minimumTotal(list));
        System.out.println(new _120_Triangle().minimumTotal2(list));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int N = triangle.size();
        return process(triangle, 0, 0, N);
    }

    /**
     * 从(i,j)点出发，到达叶子节点的最小路径和
     *
     * @param triangle
     * @param i
     * @param j
     * @return
     */
    private int process(List<List<Integer>> triangle, int i, int j, int N) {
        if (i == N - 1) {
            return triangle.get(i).get(j);
        }
        int left = process(triangle, i + 1, j, N);
        int right = process(triangle, i + 1, j + 1, N);
        return Math.min(left, right) + triangle.get(i).get(j);
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int N = triangle.size();
        int M = triangle.get(N - 1).size();
        int[][] dp = new int[N][M];
        for (int i = 0; i < M; i++) {
            dp[N - 1][i] = triangle.get(N - 1).get(i);
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
