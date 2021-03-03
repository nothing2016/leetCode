package cn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 题解： 先将有序链表变成 数组，将有序数组变成平衡树就很简单了
 *
 * @author oudaming
 * @date 2021-03-03 16:00
 */
public class _109_Convert_Sorted_List_to_Binary_Search_Tree {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int N = list.size();
        int arr[] = new int[N];
        int i = 0;
        for (Integer a : list) {
            arr[i++] = a;
        }
        return sortedArrayToBST(arr);
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
