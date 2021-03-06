package cn.leetcode;

import java.util.*;

/**
 * 815. 公交路线
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 * <p>
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 * <p>
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * 输出：2
 * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 * 示例 2：
 * <p>
 * 输入：routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * 输出：-1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 10^5
 * routes[i] 中的所有值 互不相同
 * sum(routes[i].length) <= 10^5
 * 0 <= routes[i][j] < 10^6
 * 0 <= source, target < 10^6
 * <p>
 * 题解思路：https://leetcode-cn.com/problems/bus-routes/solution/java815gong-jiao-lu-xian-yi-li-jie-ba-ch-0kfv/
 *
 * @author oudaming
 * @create 2021-06-28 10:27
 **/
public class _815_Bus_Routes {

    public static void main(String[] args) {
        System.out.println(numBusesToDestination(new int[][]{{1, 2, 7}, {3, 6, 7}, {6}}, 1, 6));
//        System.out.println(numBusesToDestination(new int[][]{{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}}, 15, 12));
    }

    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        //<station,{bus}>-每个站都被哪些公交车经过
        HashMap<Integer, List<Integer>> s2b = new HashMap<>();
        for (int b = 0; b < routes.length; b++) {
            for (int s : routes[b]) {
                if (!s2b.containsKey(s)) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(b);
                    s2b.put(s, list);
                } else {
                    s2b.get(s).add(b);
                }
            }
        }
        //记录已经坐了哪些公交车
        int[] memory2b = new int[routes.length];
        //bfs-收集当前station辐射到的station
        Queue<Integer> q = new LinkedList<>();
        q.offer(source); //先压入起始车站
        int count = 0; //坐过多少公交车

        //bfs
        while (!q.isEmpty()) {
            int size = q.size();
            count++;
            //每次q收集cur辐射到的所有station，都是cur可以不用换乘到达的车站
            //while(size--)结束，没有找到target说明需要换乘一次，count++
            //
            while (size-- > 0) {
                int cur = q.poll();
                //经过cur的所有车
                for (int car : s2b.get(cur)) {
                    if (memory2b[car] == 1) continue;
                    memory2b[car] = 1;  //标记已经访问过的car

                    for (int s : routes[car]) {
                        if (s == target) return count;
                        if (s == cur) continue;
                        q.offer(s);
                    }
                }
            }
        }
        return -1;
    }


}
