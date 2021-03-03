package cn.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * @author oudaming
 * @date 2021-03-03 14:46
 */
public class _104_Maximum_Depth_of_Binary_Tree {
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


        int max = new _104_Maximum_Depth_of_Binary_Tree().maxDepth(node1);
        System.out.println(max);
    }

    /**
     * 使用的是宽度优先遍历，再使用stack计算每一层是否是对称
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }


        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);


        int max = 1;
        HashMap<TreeNode, Integer> level = new HashMap<>();
        level.put(root, 1);

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();

            TreeNode left = poll.left;
            TreeNode right = poll.right;

            if (left != null) {
                queue.add(left);
                level.put(left, level.get(poll) + 1);
                max = Math.max(max, level.get(poll) + 1);
            }
            if (right != null) {
                queue.add(right);
                level.put(right, level.get(poll) + 1);
                max = Math.max(max, level.get(poll) + 1);
            }
        }
        return max;
    }
}
