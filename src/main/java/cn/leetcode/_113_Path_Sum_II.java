package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 *
 * @author oudaming
 * @date 2021-03-03 17:09
 */
public class _113_Path_Sum_II {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;

        node3.left = node5;
        node3.right = node6;

        node4.left = node7;
        node4.right = node8;

        node6.right = node9;
        List<List<Integer>> lists = new _113_Path_Sum_II().pathSum(node1, 22);
        for (List<Integer> item : lists) {
            System.out.println(item);
        }

    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        int sum = 0;
        process(root, ans, item, sum, targetSum);
        return ans;
    }

    private void process(TreeNode root, List<List<Integer>> ans, List<Integer> item, int sum, int targetSum) {
        if (root.right == null && root.left == null) {
            if (sum + root.val == targetSum) {
                List<Integer> integers = new ArrayList<>(item);
                integers.add(root.val);
                ans.add(integers);
                return;
            }
        }
        if (root.left != null) {
            List<Integer> integers = new ArrayList<>(item);
            integers.add(root.val);
            process(root.left, ans, integers, sum + root.val, targetSum);
        }
        if (root.right != null) {
            List<Integer> integers = new ArrayList<>(item);
            integers.add(root.val);
            process(root.right, ans, integers, sum + root.val, targetSum);
        }
    }
}
