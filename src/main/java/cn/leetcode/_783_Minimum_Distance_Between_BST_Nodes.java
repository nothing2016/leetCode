package cn.leetcode;

/**
 * 783. 二叉搜索树节点最小距离
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * <p>
 * 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [2, 100] 内
 * 0 <= Node.val <= 10^5
 * <p>
 * 题解：搜索树的中序遍历是有序的，最小值一定在两个相邻节点之间，所以问题就很容易解决了
 *
 * @author oudaming
 * @date 2021-04-13 9:20
 */
public class _783_Minimum_Distance_Between_BST_Nodes {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(90);
        TreeNode node2 = new TreeNode(69);
        TreeNode node3 = new TreeNode(49);
        TreeNode node4 = new TreeNode(89);
        TreeNode node5 = new TreeNode(52);

        node1.left = node2;
        node2.left = node3;
        node2.right = node4;
        node3.right = node5;

        int i = minDiffInBST(node1);
        System.out.println(i);
    }

    private static int abs = Integer.MAX_VALUE;
    // 记录中序遍历的上一个值
    private static int pre = -1;

    public static int minDiffInBST(TreeNode root) {
        abs = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return abs;
    }

    private static void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        dfs(root.left);
        // 如果上一个有值，结算
        if (pre != -1) {
            abs = Math.min(abs, root.val - pre);
        }
        // 将当前节点记录为上一个节点
        pre = root.val;

        dfs(root.right);


    }
}
