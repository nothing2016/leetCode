package cn.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1707. 与数组中元素的最大异或值
 * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
 * <p>
 * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
 * <p>
 * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
 * 输出：[3,3,7]
 * 解释：
 * 1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
 * 2) 1 XOR 2 = 3.
 * 3) 5 XOR 2 = 7.
 * 示例 2：
 * <p>
 * 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
 * 输出：[15,-1,5]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length, queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= nums[j], xi, mi <= 10^9
 * <p>
 * 题解： 前缀树 +　排序
 * 1.前缀树可以很快找到一颗前缀树中的最大异或和
 * 2.利用排序的思想可以复用已经排序好的树
 *
 * @author oudaming
 * @date 2021/5/23 0023 09:55
 */
public class _1707_Maximum_XOR_With_an_Element_From_Array {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        int numQ = queries.length;
        int[][] newQueries = new int[numQ][3];
        for (int i = 0; i < numQ; ++i) {
            newQueries[i][0] = queries[i][0];
            newQueries[i][1] = queries[i][1];
            newQueries[i][2] = i;
        }
        Arrays.sort(newQueries, new Comparator<int[]>() {
            public int compare(int[] query1, int[] query2) {
                return query1[1] - query2[1];
            }
        });

        int[] ans = new int[numQ];
        NumTrie trie = new NumTrie();
        // idx 是nums的下标到了什么位置，构建树的过程的复用的，不需要重新构建
        int idx = 0, n = nums.length;
        for (int[] query : newQueries) {
            int x = query[0], m = query[1], qid = query[2];
            while (idx < n && nums[idx] <= m) {
                trie.add(nums[idx]);
                ++idx;
            }
            if (idx == 0) { // 字典树为空
                ans[qid] = -1;
            } else {
                ans[qid] = trie.maxXor(x);
            }
        }
        return ans;
    }


    // 前缀树的节点类型，每个节点向下只可能有走向0或1的路
    // node.nexts[0] == null 0方向没路
    // node.nexts[0] != null 0方向有路
    public static class Node {
        public Node[] nexts = new Node[2];
    }

    // 基于本题，定制前缀树的实现
    public static class NumTrie {
        // 头节点
        public Node head = new Node();

        // 把某个数字newNum加入到这棵前缀树里
        // num是一个32位的整数，所以加入的过程一共走32步
        public void add(int newNum) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                // 从高位到低位，取出每一位的状态，如果当前状态是0，
                // path(int) = 0
                // ，如果当前状态是1
                // path(int) = 1
                int path = ((newNum >> move) & 1);
                // 无路新建、有路复用
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        // 该结构之前收集了一票数字，并且建好了前缀树
        // sum,和 谁 ^ 最大的结果（把结果返回）
        public int maxXor(int sum) {
            Node cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                int path = (sum >> move) & 1;
                // 期待的路，第一位，符号位，总是期待是一样的符号，这样就能得到整数
                // 如果不是第一位，那么希望是相反的数，这样 0^1 = 1
                // 无论第一位是0还是1，后面的位数永远期待是1，这样才能得到最大值
                int best = move == 31 ? path : (path ^ 1);
                // 实际走的路
                best = cur.nexts[best] != null ? best : (best ^ 1);
                // (path ^ best) 当前位位异或完的结果
                res |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return res;
        }
    }
}

