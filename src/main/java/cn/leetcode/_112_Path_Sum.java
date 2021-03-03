package cn.leetcode;

/**
 * 112. 路径总和
 * <p>
 * 给你二叉树的根节点root 和一个表示目标和的整数targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum 。
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 *
 * @author oudaming
 * @date 2021-03-03 16:49
 */
public class _112_Path_Sum {
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

        System.out.println(new _112_Path_Sum().hasPathSum(node1, 18));

    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return process(root, 0, targetSum);
    }

    private boolean process(TreeNode root, int sum, int targetSum) {
        if (root.right == null && root.left == null) {
            if (sum + root.val == targetSum) {
                return true;
            }
        }
        if (root.left != null) {
            boolean p1 = process(root.left, sum + root.val, targetSum);
            if (p1) {
                return true;
            }
        }
        if (root.right != null) {
            boolean p2 = process(root.right, sum + root.val, targetSum);
            if (p2) {
                return true;
            }
        }
        return false;
    }
}
