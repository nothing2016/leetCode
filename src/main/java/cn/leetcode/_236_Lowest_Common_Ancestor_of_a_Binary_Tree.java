package cn.leetcode;

import java.util.LinkedList;

/**
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 * <p>
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [2, 105] 内。
 * -10^9 <= Node.val <= 10^9
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 * <p>
 * 题解：1.先找到root根节点到target目标节点的路径
 * 2.定义dfs是在当前root树下搜索target节点，返回的值为是否包含，如果包含的话，将自己放到路径中，这样得到整个路径后就能找到最近公共祖先
 */
public class _236_Lowest_Common_Ancestor_of_a_Binary_Tree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        node2.left = node4;
        node2.right = node5;
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);
        node3.left = node6;
        node4.right = node7;
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(4);
        node5.left = node8;
        node5.right = node9;

        TreeNode treeNode = lowestCommonAncestor(node1, node2, node3);
        System.out.println(treeNode.val);

    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> pPath = new LinkedList<>();
        LinkedList<TreeNode> qPath = new LinkedList<>();
        dfs(root, p, pPath);
        dfs(root, q, qPath);
        TreeNode ans = null;
        for (int i = 0; i < pPath.size() && i < qPath.size(); i++) {
            if (pPath.get(i) == qPath.get(i)) {
                ans = pPath.get(i);
            } else {
                break;
            }
        }
        return ans;
    }


    /**
     * 获取target的路径
     *
     * @param root
     * @param target
     * @param list   路径list
     * @return 返回root为根的树，是否包含target
     */
    private static boolean dfs(TreeNode root, TreeNode target, LinkedList<TreeNode> list) {
        if (root == null) {
            return false;
        }
        // 如果当前节点是需要找的，那么就将其值加入到路径中
        if (root.val == target.val) {
            list.add(root);
            return true;
        }
        boolean left = dfs(root.left, target, list);
        boolean right = dfs(root.right, target, list);
        // 如果子树包含了target,那么父节点肯定是包含的
        if (left || right) {
            list.addFirst(root);
            return true;
        } else {
            return false;
        }
    }
}
