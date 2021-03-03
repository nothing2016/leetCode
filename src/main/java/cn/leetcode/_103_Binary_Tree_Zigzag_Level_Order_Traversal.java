package cn.leetcode;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * @author oudaming
 * @date 2021-03-03 14:11
 */
public class _103_Binary_Tree_Zigzag_Level_Order_Traversal {

    public static void main(String[] args) {


        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(67);
        TreeNode node3 = new TreeNode(68);
        TreeNode node4 = new TreeNode(18);
        TreeNode node5 = new TreeNode(19);
        TreeNode node6 = new TreeNode(-1);
        TreeNode node7 = new TreeNode(-64);
        TreeNode node8 = new TreeNode(-65);
        TreeNode node9 = new TreeNode(-1);
        TreeNode node10 = new TreeNode(62);
        TreeNode node11 = new TreeNode(-20);
        TreeNode node12 = new TreeNode(-20);
        TreeNode node13 = new TreeNode(64);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = null;

        node3.left = null;
        node3.right = node5;

        node4.left = node6;
        node4.right = node7;

        node5.left = node8;
        node5.right = node9;

        node6.left = null;
        node6.right = node10;

        node7.left = node11;
        node7.right = null;

        node8.left = null;
        node8.right = node12;

        node9.left = null;
        node9.right = node13;


        List<List<Integer>> lists = new _103_Binary_Tree_Zigzag_Level_Order_Traversal().zigzagLevelOrder(node1);
        for (List<Integer> item : lists) {
            System.out.println(item);
        }
    }

    /**
     * 使用的是宽度优先遍历，再使用stack计算每一层是否是对称
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        HashMap<TreeNode, Integer> level = new HashMap<>();
        level.put(root, 1);

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (map.containsKey(level.get(poll))) {
                map.get(level.get(poll)).add(poll.val);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(poll.val);
                map.put(level.get(poll), list);
            }

            TreeNode left = poll.left;
            TreeNode right = poll.right;

            if (left != null) {
                queue.add(left);
                level.put(left, level.get(poll) + 1);
            }
            if (right != null) {
                queue.add(right);
                level.put(right, level.get(poll) + 1);
            }
        }
        int count = 0;
        for (Integer key : map.keySet()) {
            List<Integer> integers = map.get(key);
            if ((count & 1) == 1) {
                Collections.reverse(integers);
            }
            ans.add(integers);
            count++;
        }

        return ans;
    }
}
