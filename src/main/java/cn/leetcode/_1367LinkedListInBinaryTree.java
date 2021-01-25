package cn.leetcode;

/**
 * 给你一棵以root为根的二叉树和一个head为第一个节点的链表。
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以head为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 * <p>
 * 输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
 * 输出：true
 * 解释：树中蓝色的节点构成了与链表对应的子路径。
 *
 * @author oudaming
 * @date 2021-01-25 9:44
 */
public class _1367LinkedListInBinaryTree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(8);
        TreeNode node6 = new TreeNode(1);
        TreeNode node7 = new TreeNode(3);

        node1.right = node2;
        node2.left = node3;
        node3.left = node4;
        node3.right = node5;
        node5.left = node6;
        node5.right = node7;


        TreeNode node8 = new TreeNode(4);
        TreeNode node9 = new TreeNode(2);
        TreeNode node10 = new TreeNode(1);
        node1.left = node8;
        node8.right = node9;
        node9.left = node10;

        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(0);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        System.out.println(new _1367LinkedListInBinaryTree().isSubPath(listNode1, node1));

    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null || head == null) {
            return false;
        }
        // 只要有一颗树包含，就一定包含了，直接返回即可
        boolean headIn = isInTree(head, root);
        if (headIn) {
            return true;
        }
        // 递归左树上是否能满足要求，满足就直接返回即可
        boolean leftIn = isSubPath(head, root.left);
        if (leftIn) {
            return true;
        }
        // 递归右树上是否能满足要求，满足就直接返回即可
        boolean rightIn = isSubPath(head, root.right);
        if (rightIn) {
            return true;
        }
        return false;
    }

    /**
     * 以head为头节点的链表是否在root树中
     *
     * @param head
     * @param root
     * @return
     */
    private boolean isInTree(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return head == null ? true : false;
        }
        boolean eq = head.val == root.val;
        if (!eq) {
            return false;
        }
        boolean inLeft = isInTree(head.next, root.left);
        boolean inRight = isInTree(head.next, root.right);
        return inLeft || inRight;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
