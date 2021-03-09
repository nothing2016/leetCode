package cn.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 149. 直线上最多的点数
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * ^
 * |
 * |     o
 * |   o
 * | o
 * +------------->
 * 0 1 2 3 4
 *
 * @author oudaming
 * @date 2021-03-09 13:50
 */
public class _149_Max_Points_on_a_Line {
    public static void main(String[] args) {
        System.out.println(new _149_Max_Points_on_a_Line().maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        System.out.println(new _149_Max_Points_on_a_Line().maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}));
        System.out.println(new _149_Max_Points_on_a_Line().maxPoints(new int[][]{{4, 5}, {4, -1}, {4, 0}}));
    }

    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0) {
            return 0;
        }
        int N = points.length;
        if (N == 1 || N == 2) {
            return N;
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int x = 1;
            int y = 1;
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                int a = points[j][1] - points[i][1];
                int b = points[j][0] - points[i][0];

                // 同y轴
                if (a == 0) {
                    y++;
                    ans = Math.max(ans, y);
                    // 同x轴
                } else if (b == 0) {
                    x++;
                    ans = Math.max(ans, x);
                } else {
                    // 同斜率
                    int common = getCommon(a, b);
                    String key = a / common + "-" + b / common;
                    // 这里的默认值是一，表示同斜率的也先包含自己
                    map.put(key, map.getOrDefault(key, 1) + 1);
                    ans = Math.max(ans, map.get(key));
                }


            }
        }
        return ans;
    }

    /**
     * 最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static int getCommon(int a, int b) {
        return b == 0 ? a : getCommon(b, a % b);
    }

}
