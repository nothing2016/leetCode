package cn.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * 108. 将有序数组转换为二叉搜索树
 * <p>
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * <p>
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * <p>
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 *
 * @author oudaming
 * @date 2021-03-03 15:49
 */
public class _108_Convert_Sorted_Array_to_Binary_Search_Tree {

    public static void main(String[] args) {
        int[] order = {-10, -3, 0, 5, 9};
        TreeNode treeNode = new _108_Convert_Sorted_Array_to_Binary_Search_Tree().sortedArrayToBST(new int[]{1});
        List<List<Integer>> lists = new _102_Binary_Tree_Level_Order_Traversal().levelOrder(treeNode);
        for (List<Integer> item : lists) {
            System.out.println(item);
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        int N = nums.length;
        int mid = N >> 1;
        TreeNode head = new TreeNode(nums[mid]);
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid + 1, N);
        head.left = sortedArrayToBST(left);
        head.right = sortedArrayToBST(right);
        return head;
    }
}
