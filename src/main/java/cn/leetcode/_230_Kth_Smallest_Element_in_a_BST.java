package cn.leetcode;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 10^4
 * 0 <= Node.val <= 10^4
 *
 * @author oudaming
 * @date 2021-05-07 16:38
 */
public class _230_Kth_Smallest_Element_in_a_BST {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node4.left = node6;
        int i = kthSmallest(node1, 3);
        System.out.println(i);
    }

    private static boolean isFound = false;
    private static int ans = Integer.MAX_VALUE;
    private static int index = 0;

    public static int kthSmallest(TreeNode root, int k) {
        isFound = false;
        ans = Integer.MAX_VALUE;
        index = 0;
        dfs(root, k);
        return ans;
    }

    private static void dfs(TreeNode root, int k) {
        if (root == null || isFound) {
            return;
        }
        dfs(root.left, k);
        index++;
        if (index == k) {
            ans = root.val;
            isFound = true;
            return;
        }
        dfs(root.right, k);
    }
}
