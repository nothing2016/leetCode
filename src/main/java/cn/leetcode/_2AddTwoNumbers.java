package cn.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * <p>
 * <p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * @author oudaming
 * @date 2021-01-25 10:41
 */
public class _2AddTwoNumbers {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(9);
        ListNode listNode2 = new ListNode(9);
        ListNode listNode3 = new ListNode(9);
        ListNode listNode4 = new ListNode(9);
        ListNode listNode5 = new ListNode(9);
        ListNode listNode6 = new ListNode(9);
        ListNode listNode7 = new ListNode(9);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;


        ListNode listNode8 = new ListNode(9);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode10 = new ListNode(9);
        ListNode listNode11 = new ListNode(9);

        listNode8.next = listNode9;
        listNode9.next = listNode10;
        listNode10.next = listNode11;

        printLinkNode(listNode1);
        printLinkNode(listNode8);

        ListNode listNode = new _2AddTwoNumbers().addTwoNumbers(listNode1, listNode8);
        printLinkNode(listNode);

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        Queue<Integer> queue = new LinkedList();
        // up表示进位
        int up = 0;
        while (l1 != null && l2 != null) {
            int value = l1.val + l2.val + up;
            queue.add(value % 10);
            up = value / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null) {
            while (l2 != null) {
                int value = l2.val + up;
                queue.add(value % 10);
                up = value / 10;
                l2 = l2.next;
            }
        }
        if (l2 == null) {
            while (l1 != null) {
                int value = l1.val + up;
                queue.add(value % 10);
                up = value / 10;
                l1 = l1.next;
            }
        }
        if (up != 0) {
            queue.add(up);
        }
        ListNode head = new ListNode(queue.poll());
        ListNode cur = head;
        while (!queue.isEmpty()) {
            ListNode listNode = new ListNode(queue.poll());
            cur.next = listNode;
            cur = cur.next;
        }
        return head;
    }


    private static void printLinkNode(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + ",");
            listNode = listNode.next;
        }
        System.out.println();
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
}
