package cn.leetcode;

import java.util.*;

/**
 * 210. 课程表 II
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * <p>
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2:
 * <p>
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 说明:
 * <p>
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 *
 * @author oudaming
 * @date 2021-03-26 18:20
 */
public class _210_Course_Schedule_II {

    public static void main(String[] args) {
        int[] order = findOrder(1, new int[][]{{}});
//        int[] order = findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        print(order);
    }

    private static void print(int[] order) {
        if (order == null || order.length == 0) {
            System.out.println("数组为空");
        }
        for (int i = 0; i < order.length; i++) {
            System.out.println(order[i] + ",");
        }
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return new int[]{};
        }
        // 节点下标和节点的对应关系
        HashMap<Integer, Node> nodes = new HashMap<>();
        // 构建这个图
        for (int[] arr : prerequisites) {
            if (arr != null && arr.length != 0) {
                int to = arr[0];
                int from = arr[1];
                // 节点不存在，就先放到map中
                if (!nodes.containsKey(to)) {
                    nodes.put(to, new Node(to));
                }
                if (!nodes.containsKey(from)) {
                    nodes.put(from, new Node(from));
                }
                Node t = nodes.get(to);
                Node f = nodes.get(from);
                f.nexts.add(t);
                // to节点的入度+1
                t.in++;
            }
        }

        // 图中节点的数量，不包含孤立节点，因为孤立节点是没有任何前置依赖的，不需要考虑
        int size = nodes.size();
        // 入度为0的节点装在队列中
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : nodes.values()) {
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        // 记录 孤立的点
        List<Node> alone = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!nodes.containsKey(i)) {
                alone.add(new Node(i));
            }
        }
        List<Node> ans = new ArrayList<>();
        int count = 0;
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            ans.add(cur);
            count++;
            for (Node next : cur.nexts) {
                // 当前节点删除后，nexts的节点入度都要减一
                if (--next.in == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        if (count != size) {
            return new int[]{};
        }
        int[] ret = new int[ans.size() + alone.size()];
        // 孤立的节点一定是放在最前面的
        alone.addAll(ans);
        for (int i = 0; i < alone.size(); i++) {
            ret[i] = alone.get(i).n;
        }
        // 如果结束后，count != size ,证明有环，不可能完成所有的课程
        return ret;
    }

    // 一个node，就是一个课程
    // n是课程的编号
    // in是课程的入度
    public static class Node {
        public int n;
        public int in;
        // 下面一些课程
        public ArrayList<Node> nexts;

        public Node(int n) {
            this.n = n;
            in = 0;
            nexts = new ArrayList<>();
        }
    }
}
