package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 *
 * @author oudaming
 * @date 2021-03-02 17:39
 */
public class _94_Binary_Tree_Inorder_Traversal {
    public static void main(String[] args) {
        TreeNode node1 = null;
//        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
//        node1.right = node2;
//        node2.left = node3;
        List<Integer> integers = new _94_Binary_Tree_Inorder_Traversal().inorderTraversal(node1);
        System.out.println(integers);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ans.addAll(inorderTraversal(root.left));
        ans.add(root.val);
        ans.addAll(inorderTraversal(root.right));
        return ans;
    }
}
