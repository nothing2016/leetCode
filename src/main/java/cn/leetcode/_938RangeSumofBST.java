package cn.leetcode;

/**
 * 938. 二叉搜索树的范围和
 * <p>
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 * <p>
 * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 * 输出：32
 *
 * @author oudaming
 * @date 2020-12-31 17:39
 */
public class _938RangeSumofBST {
    public static void main(String[] args) {
        TreeNode one = new TreeNode(10);
        TreeNode two = new TreeNode(5);
        TreeNode three = new TreeNode(15);
        TreeNode four = new TreeNode(3);
        TreeNode five = new TreeNode(7);
        TreeNode six = new TreeNode(18);
        one.left = two;
        one.right = three;
        two.left = four;
        two.right = five;
        three.right = six;
        int i = new _938RangeSumofBST().rangeSumBST(one, 7, 15);
        System.out.println(i);
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        int sum = dfs(root, low, high);
        return sum;
    }

    private int dfs(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        int value = 0;
        if (root.val >= low && root.val <= high) {
            value = root.val;
        }
        return value + dfs(root.left, low, high) + dfs(root.right, low, high);
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}