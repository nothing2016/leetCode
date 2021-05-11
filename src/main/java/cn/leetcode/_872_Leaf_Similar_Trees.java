package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 872. 叶子相似的树
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 * <p>
 * <p>
 * <p>
 * 举个例子，如上图所示，给定一棵叶值序列为 (6, 7, 4, 9, 8) 的树。
 * <p>
 * 如果有两棵二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 * <p>
 * 如果给定的两个根结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：root1 = [1], root2 = [1]
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：root1 = [1], root2 = [2]
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：root1 = [1,2], root2 = [2,2]
 * 输出：true
 * 示例 5：
 * <p>
 * <p>
 * <p>
 * 输入：root1 = [1,2,3], root2 = [1,3,2]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 给定的两棵树可能会有 1 到 200 个结点。
 * 给定的两棵树上的值介于 0 到 200 之间。
 */
public class _872_Leaf_Similar_Trees {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node1 = null;

        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(2);
        node4.left = node5;
        node4.right = node6;
        System.out.println(new _872_Leaf_Similar_Trees().leafSimilar(node1, node4));
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaf1 = getLeafList(root1);
        List<Integer> leaf2 = getLeafList(root2);
        int len1 = leaf1.size();
        int len2 = leaf2.size();
        if (len1 != len2) {
            return false;
        }
        for (int i = 0; i < len1; i++) {
            if (!leaf1.get(i).equals(leaf2.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取一棵树的叶子节点
     *
     * @param root1
     * @return
     */
    private List<Integer> getLeafList(TreeNode root1) {
        List<Integer> leafList = new ArrayList<>();
        dfs(root1, leafList);
        return leafList;
    }

    /**
     * 中序遍历，当是叶子节点的时候，就加入到leafList中
     *
     * @param root1
     * @param leafList
     */
    private void dfs(TreeNode root1, List<Integer> leafList) {
        if (root1 == null) {
            return;
        }
        if (root1.left == null && root1.right == null) {
            leafList.add(root1.val);
            return;
        }
        dfs(root1.left, leafList);
        dfs(root1.right, leafList);
    }
}
