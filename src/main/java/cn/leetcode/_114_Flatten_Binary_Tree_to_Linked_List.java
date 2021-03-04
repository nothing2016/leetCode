package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 114. 二叉树展开为链表
 * <p>
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * @author oudaming
 * @date 2021-03-04 13:50
 */
public class _114_Flatten_Binary_Tree_to_Linked_List {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<TreeNode> ans = new ArrayList<>();
        pre(root, ans);
        TreeNode cur = ans.get(0);
        cur.left = null;
        cur.right = null;
        for (int i = 1; i < ans.size(); i++) {
            TreeNode node = ans.get(i);
            node.left = null;
            node.right = null;
            cur.right = node;
            cur = node;
        }

    }

    private void pre(TreeNode root, List<TreeNode> ans) {
        if (root == null) {
            return;
        }
        ans.add(root);
        pre(root.left, ans);
        pre(root.right, ans);
    }
}
