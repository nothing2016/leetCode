package cn.leetcode;

/**
 * 111. 二叉树的最小深度
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 *
 * @author oudaming
 * @date 2021-03-03 16:23
 */
public class _111_Minimum_Depth_of_Binary_Tree {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        node1.right = node2;
        node2.right = node3;
        node3.right = node4;
        node4.right = node5;
        int i = new _111_Minimum_Depth_of_Binary_Tree().minDepth(new TreeNode(19));
        System.out.println(i);
    }

    static int min = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        min = Integer.MAX_VALUE;
        process(root, 1);
        return min;
    }

    private void process(TreeNode root, int h) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            min = Math.min(min, h);
        }
        process(root.left, h + 1);
        process(root.right, h + 1);
    }
}
