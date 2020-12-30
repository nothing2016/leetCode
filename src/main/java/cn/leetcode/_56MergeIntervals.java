package cn.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 56.给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * @author oudaming
 * @date 2020-12-30 15:29
 */
public class _56MergeIntervals {
    public static void main(String[] args) {
//        int[][] example = new int[4][2];
//        example[0] = new int[]{1, 3};
//        example[3] = new int[]{2, 6};
//        example[2] = new int[]{8, 10};
//        example[1] = new int[]{15, 18};

        int[][] example = new int[2][2];
        example[0] = new int[]{1, 4};
        example[1] = new int[]{4, 5};

        int[][] merge = new _56MergeIntervals().merge(example);
        System.out.println(merge);
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            Node node = new Node(intervals[i][0], intervals[i][1]);
            list.add(node);
        }
        Collections.sort(list, (a, b) -> a.first - b.first);
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

    class Node {
        int first;
        int second;

        public Node(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

}
