package cn.leetcode;

/**
 * 100. 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * @author oudaming
 * @date 2021-03-03 15:38
 */
public class _100_Same_Tree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null) {
            if (p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) {
                return true;
            }
        }
        // 一个是空，一个不是空，那就证明不是同一颗树
        return false;
    }
}
