package cn.leetcode;

/**
 * 124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * <p>
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * <p>
 * 题解：树上的递归套路，如果在任意一个节点上，那么我需要向左右子树要什么样的数据，才能凑成我需要返回的数据呢?
 * 1)经过左（右）根节点的最大路径和
 * 2)左右子树上的最大路径和（不管是否经过根节点）
 *
 * @author oudaming
 * @date 2021-03-04 14:25
 */
public class _124_Binary_Tree_Maximum_Path_Sum {


    public static void main(String[] args) {

//        TreeNode node1 = new TreeNode(5);
//        TreeNode node2 = new TreeNode(4);
//        TreeNode node3 = new TreeNode(8);
//        TreeNode node4 = new TreeNode(11);
//        TreeNode node5 = new TreeNode(13);
//        TreeNode node6 = new TreeNode(4);
//        TreeNode node7 = new TreeNode(7);
//        TreeNode node8 = new TreeNode(2);
//        TreeNode node9 = new TreeNode(1);
//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        node3.right = node5;
//        node3.right = node6;
//        node4.right = node7;
//        node4.right = node8;
//        node6.right = node9;
//
//        System.out.println(new _124_Binary_Tree_Maximum_Path_Sum().maxPathSum(node1));


        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        System.out.println(maxPathSum(node1));

//        TreeNode node1 = new TreeNode(-10);
//        TreeNode node2 = new TreeNode(9);
//        TreeNode node3 = new TreeNode(20);
//        TreeNode node4 = new TreeNode(15);
//        TreeNode node5 = new TreeNode(7);
//        node1.left = node2;
//        node1.right = node3;
//        node3.left = node4;
//        node3.right = node5;
//
//        System.out.println(new _124_Binary_Tree_Maximum_Path_Sum().maxPathSum(node1));

//        System.out.println(new _124_Binary_Tree_Maximum_Path_Sum().maxPathSum(new TreeNode(-19)));
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxPathSum;
    }

    public static class Info {
        // 整棵树的路径最大和，各种情况都包含
        public int maxPathSum;
        // 从头节点出发路径的最大和
        public int maxPathSumFromHead;

        public Info(int path, int head) {
            maxPathSum = path;
            maxPathSumFromHead = head;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.maxPathSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.maxPathSum;
        }
        int p3 = x.val;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = x.val + leftInfo.maxPathSumFromHead;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p5 = x.val + rightInfo.maxPathSumFromHead;
        }
        int p6 = Integer.MIN_VALUE;
        if (leftInfo != null && rightInfo != null) {
            p6 = x.val + leftInfo.maxPathSumFromHead + rightInfo.maxPathSumFromHead;
        }
        int maxSum = Math.max(Math.max(Math.max(p1, p2), Math.max(p3, p4)), Math.max(p5, p6));
        int maxSumFromHead = Math.max(p3, Math.max(p4, p5));
        return new Info(maxSum, maxSumFromHead);
    }

}
