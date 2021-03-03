package cn.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * * --3
 * * -/ \
 * * 9  20
 * * --/  \
 * * -15   7
 *
 * @author oudaming
 * @date 2021-03-03 15:20
 */
public class _106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {

    public static void main(String[] args) {
        int[] preorder = {9, 3, 15, 20, 7};
        int[] inorder = {9, 15, 7, 20, 3};
        TreeNode treeNode = new _106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal().buildTree(preorder, inorder);
        List<List<Integer>> lists = new _102_Binary_Tree_Level_Order_Traversal().levelOrder(treeNode);
        for (List<Integer> item : lists) {
            System.out.println(item);
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int N = inorder.length;
        if (inorder == null || inorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        int head = postorder[N - 1];
        TreeNode treeNode = new TreeNode(head);
        int index = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == head) {
                index = i;
                break;
            }
        }

        int[] leftIn = Arrays.copyOfRange(inorder, 0, index);
        int[] rightIn = Arrays.copyOfRange(inorder, index + 1, N);

        int[] leftPost = Arrays.copyOfRange(postorder, 0, index);
        int[] rightPost = Arrays.copyOfRange(postorder, index, N - 1);

        treeNode.left = buildTree(leftIn, leftPost);
        treeNode.right = buildTree(rightIn, rightPost);

        return treeNode;
    }
}
