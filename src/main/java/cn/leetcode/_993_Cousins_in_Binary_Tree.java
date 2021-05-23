package cn.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 993. 二叉树的堂兄弟节点
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * <p>
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 * <p>
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 * <p>
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 */
public class _993_Cousins_in_Binary_Tree {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;

        System.out.println(isCousins(treeNode1, 4, 3));
    }

    public static boolean isCousins(TreeNode root, int x, int y) {
        Map<Integer, TreeNode> parentMap = new HashMap<>();
        Map<Integer, Integer> levelMap = new HashMap<>();
        parentMap.put(root.val, new TreeNode(-1));
        levelMap.put(root.val, 0);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode pop = ((LinkedList<TreeNode>) queue).pop();
            if (pop.left != null) {
                levelMap.put(pop.left.val, levelMap.get(pop.val) + 1);
                parentMap.put(pop.left.val, pop);
                ((LinkedList<TreeNode>) queue).addLast(pop.left);
            }
            if (pop.right != null) {
                levelMap.put(pop.right.val, levelMap.get(pop.val) + 1);
                parentMap.put(pop.right.val, pop);
                ((LinkedList<TreeNode>) queue).addLast(pop.right);
            }
        }

        if (levelMap.get(x).equals(levelMap.get(y)) && !parentMap.get(x).equals(parentMap.get(y))) {
            return true;
        } else {
            return false;
        }
    }
}
