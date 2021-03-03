package cn.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * --3
 * -/ \
 * 9  20
 * --/  \
 * -15   7
 *
 * @author oudaming
 * @date 2021-03-03 14:52
 */
public class _105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode treeNode = new _105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal().buildTree(preorder, inorder);
        List<List<Integer>> lists = new _102_Binary_Tree_Level_Order_Traversal().levelOrder(treeNode);
        for (List<Integer> item : lists) {
            System.out.println(item);
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int N = inorder.length;
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        int head = preorder[0];
        TreeNode treeNode = new TreeNode(head);
        int index = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == head) {
                index = i;
                break;
            }
        }

        int[] leftPre = Arrays.copyOfRange(preorder, 1, 1 + index);
        int[] rightPre = Arrays.copyOfRange(preorder, 1 + index, N);


        int[] leftIn = Arrays.copyOfRange(inorder, 0, index);
        int[] rightIn = Arrays.copyOfRange(inorder, index + 1, N);

        treeNode.left = buildTree(leftPre, leftIn);
        treeNode.right = buildTree(rightPre, rightIn);

        return treeNode;
    }
}
