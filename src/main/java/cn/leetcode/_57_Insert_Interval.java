package cn.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 57. 插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 *
 * @author oudaming
 * @date 2021-02-19 16:22
 */
public class _57_Insert_Interval {
    public static void main(String[] args) {
//        int[][] insert = new _57_Insert_Interval().insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5});
        int[][] insert = new _57_Insert_Interval().insert(new int[][]{{1, 5}}, new int[]{0, 3});

//        int[][] insert = new _57_Insert_Interval().insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8});
//        int[][] insert = new _57_Insert_Interval().insert(new int[][]{}, new int[]{2, 5});
//        int[][] insert = new _57_Insert_Interval().insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}},
//        new int[]{4, 8});
        for (int i = 0; i < insert.length; i++) {
            System.out.println(insert[i][0] + "," + insert[i][1]);
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] ans = new int[intervals.length + 1][2];
        if (intervals.length == 0) {
            ans[0] = newInterval;
            return ans;
        }
        int j = intervals.length;
        boolean insertOk = false;
        for (int i = intervals.length - 1; i >= 0; i--) {
            if (!insertOk && newInterval[0] >= intervals[i][0]) {
                ans[j--] = newInterval;
                insertOk = true;
            }
            ans[j--] = intervals[i];
        }
        if (!insertOk) {
            ans[j--] = newInterval;
        }
        int[][] merge = merge(ans);
        return merge;
    }


    private int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            Node node = new Node(intervals[i][0], intervals[i][1]);
            list.add(node);
        }
        List<Node> result = new ArrayList<>();
        int i = 0;
        while (i < list.size()) {
            Node node = list.get(i);
            // 如果当前节点的second 大于下一个的first,合并
            if (i + 1 < list.size() && node.second >= list.get(i + 1).first) {
                Node newNode = new Node(node.first, Math.max(node.second, list.get(i + 1).second));
                list.set(i + 1, newNode);
                list.set(i, null);
            } else {
                result.add(node);
            }
            i++;
        }
        List<Node> collect = list.stream().filter(e -> e != null).collect(Collectors.toList());
        int[][] res = new int[collect.size()][2];
        for (int k = 0; k < collect.size(); k++) {
            res[k][0] = collect.get(k).first;
            res[k][1] = collect.get(k).second;
        }

        return res;
    }

    private static class Node {
        int first;
        int second;

        public Node(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
