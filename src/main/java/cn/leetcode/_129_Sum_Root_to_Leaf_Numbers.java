package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 129. 求根到叶子节点数字之和
 * <p>
 * 给定一个二叉树，它的每个结点都存放一个0-9的数字，每条从根到叶子节点的路径都代表一个数字。
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * 计算从根到叶子节点生成的所有数字之和。
 * 说明:叶子节点是指没有子节点的节点。
 * <p>
 * 输入: [4,9,0,5,1]
 * ----4
 * ---/ \
 * --9   0
 * -/ \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 *
 * @author oudaming
 * @date 2021-03-05 17:20
 */
public class _129_Sum_Root_to_Leaf_Numbers {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        System.out.println(new _129_Sum_Root_to_Leaf_Numbers().sumNumbers(node1));
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> ans = new ArrayList<>();
        dfs(root, 0, ans);
        int sum = 0;
        for (Integer i : ans) {
            sum += i;
        }
        return sum;
    }

    private void dfs(TreeNode root, int item, List<Integer> ans) {
        if (root.right == null && root.left == null) {
            ans.add(item * 10 + root.val);
        }
        if (root.left != null) {
            dfs(root.left, item * 10 + root.val, ans);
        }
        if (root.right != null) {
            dfs(root.right, item * 10 + root.val, ans);
        }

    }
}
