package cn.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * <p>
 * 提示：
 * <p>
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 * <p>
 * <p>
 * 题解： 经典的判断是否是有向无环图，只有有环，课程是不可能休完的
 *
 * @author oudaming
 * @date 2021-03-25 10:12
 */
public class _207_Course_Schedule {

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

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        // 节点下标和节点的对应关系
        HashMap<Integer, Node> nodes = new HashMap<>();
        // 构建这个图
        for (int[] arr : prerequisites) {
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

        // 图中节点的数量，不包含孤立节点，因为孤立节点是没有任何前置依赖的，不需要考虑
        int size = nodes.size();
        // 入度为0的节点装在队列中
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : nodes.values()) {
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        int count = 0;
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            count++;
            for (Node next : cur.nexts) {
                // 当前节点删除后，nexts的节点入度都要减一
                if (--next.in == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        // 如果结束后，count != size ,证明有环，不可能完成所有的课程
        return count == size;
    }
}
