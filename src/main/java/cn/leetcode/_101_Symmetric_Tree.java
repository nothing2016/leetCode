package cn.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 * @author oudaming
 * @date 2021-03-03 11:05
 */
public class _101_Symmetric_Tree {
    public static void main(String[] args) {
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(2);
//        TreeNode node4 = new TreeNode(3);
//        TreeNode node5 = new TreeNode(3);
//        node1.left = node2;
//        node1.right = node3;
//        node2.left = node4;
//        node3.right = node5;


//        TreeNode node1 = new TreeNode(2);
//        TreeNode node2 = new TreeNode(3);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(4);
//        TreeNode node5 = new TreeNode(5);
//        TreeNode node6 = new TreeNode(5);
//        TreeNode node7 = new TreeNode(4);
//        TreeNode node8 = new TreeNode(8);
//        TreeNode node9 = new TreeNode(9);
//        TreeNode node10 = new TreeNode(9);
//        TreeNode node11 = new TreeNode(8);
//
//        node1.left = node2;
//        node1.right = node3;
//
//        node2.left = node4;
//        node2.right = node5;
//
//        node3.left = node6;
//        node3.right = node7;
//
//        node5.left = node8;
//        node5.right = node9;
//
//        node6.left = node10;
//        node6.right = node11;


        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(67);
        TreeNode node3 = new TreeNode(67);
        TreeNode node4 = new TreeNode(18);
        TreeNode node5 = new TreeNode(18);
        TreeNode node6 = new TreeNode(-1);
        TreeNode node7 = new TreeNode(-64);
        TreeNode node8 = new TreeNode(-64);
        TreeNode node9 = new TreeNode(-1);
        TreeNode node10 = new TreeNode(61);
        TreeNode node11 = new TreeNode(-20);
        TreeNode node12 = new TreeNode(-20);
        TreeNode node13 = new TreeNode(61);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = null;

        node3.left = null;
        node3.right = node5;

        node4.left = node6;
        node4.right = node7;

        node5.left = node8;
        node5.right = node9;

        node6.left = null;
        node6.right = node10;

        node7.left = node11;
        node7.right = null;

        node8.left = null;
        node8.right = node12;

        node9.left = null;
        node9.right = node13;


        boolean symmetric = new _101_Symmetric_Tree().isSymmetric(node1);
        System.out.println(symmetric);
    }

    /**
     * 使用的是宽度优先遍历，再使用stack计算每一层是否是对称
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        stack.add(root.val);
        HashMap<TreeNode, Integer> level = new HashMap<>();
        level.put(root, 1);

        HashMap<TreeNode, Integer> isLeftMap = new HashMap<>();
        isLeftMap.put(root, 0);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();

//            System.out.print(poll.val + ",");

            if (!stack.isEmpty() && stack.peek() == poll.val && isLeftMap.getOrDefault(poll, 1) == 0) {
                stack.pop();
            } else {
                stack.add(poll.val);
            }
            // 这里的-999有点投机取巧了，如果测试用例中有-999，那么就会得到一个错误的答案
            if (poll.val != -999) {
                TreeNode node1 = poll.left != null ? poll.left : new TreeNode(-999, null, null);
                queue.add(node1);
                TreeNode node2 = poll.right != null ? poll.right : new TreeNode(-999, null, null);
                queue.add(node2);

                if (level.containsKey(poll)) {
                    isLeftMap.put(node1, 1);
                    isLeftMap.put(node2, 0);
                } else {
                    isLeftMap.put(node1, isLeftMap.get(poll));
                    isLeftMap.put(node2, isLeftMap.get(poll));
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isSymmetric2(TreeNode root) {
        return isMirror(root, root);
    }

    // 一棵树是原始树  head1
    // 另一棵是翻面树  head2

    /**
     * 递归判断两颗树是否是对称二叉树
     *
     * @param head1
     * @param head2
     * @return
     */
    public static boolean isMirror(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1 != null && head2 != null) {
            return head1.val == head2.val
                    && isMirror(head1.left, head2.right)
                    && isMirror(head1.right, head2.left);
        }
        // 一个为空，一个不为空  false
        return false;
    }
}
