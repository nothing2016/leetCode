package cn.leetcode;

/**
 * 110. 平衡二叉树
 * <p>
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1
 *
 * @author oudaming
 * @date 2021-03-03 16:09
 */
public class _110_Balanced_Binary_Tree {
    public boolean isBalanced(TreeNode root) {
        Info info = process(root);
        return info.isOk;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, true);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        if (leftInfo.isOk && rightInfo.isOk && Math.abs(leftInfo.h - rightInfo.h) <= 1) {
            return new Info(Math.max(leftInfo.h, rightInfo.h) + 1, true);
        } else {
            return new Info(0, false);
        }
    }

    private static class Info {
        // 树的高度
        int h;
        // 是否是平衡二叉树
        boolean isOk;

        public Info(int h, boolean isOk) {
            this.h = h;
            this.isOk = isOk;
        }
    }
}
