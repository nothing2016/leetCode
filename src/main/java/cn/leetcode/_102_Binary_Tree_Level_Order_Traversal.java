package cn.leetcode;

import java.util.*;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * @author oudaming
 * @date 2021-03-03 14:11
 */
public class _102_Binary_Tree_Level_Order_Traversal {

    public static void main(String[] args) {


        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(67);
        TreeNode node3 = new TreeNode(67);
        TreeNode node4 = new TreeNode(18);
        TreeNode node5 = new TreeNode(18);
        TreeNode node6 = new TreeNode(-1);
        TreeNode node7 = new TreeNode(-64);
        TreeNode node8 = new TreeNode(-64);
        TreeNode node9 = new TreeNode(-1);
        TreeNode node10 = new TreeNode(61);
        TreeNode node11 = new TreeNode(-20);
        TreeNode node12 = new TreeNode(-20);
        TreeNode node13 = new TreeNode(61);

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


        List<List<Integer>> lists = new _102_Binary_Tree_Level_Order_Traversal().levelOrder(new TreeNode(10));
        for (List<Integer> item : lists) {
            System.out.println(item);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
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
        for (Integer key : map.keySet()) {
            ans.add(map.get(key));
        }

        return ans;
    }
}
