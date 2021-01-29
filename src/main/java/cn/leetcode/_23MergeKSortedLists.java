package cn.leetcode;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 题解： 使用小根堆优先队列，将链表放到队列中，然后弹出，得到最小值为头，然后将当前这个cur.next放到队列中，继续弹出即可
 *
 * @author oudaming
 * @date 2021-01-29 14:41
 */
public class _23MergeKSortedLists {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;


        ListNode node7 = new ListNode(2);
        ListNode node8 = new ListNode(6);
        node7.next = node8;
        ListNode[] listNodes = new ListNode[]{null, null};
        ListNode node = new _23MergeKSortedLists().mergeKLists(listNodes);
        print(node);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        // 优先队列，使用ListNode的值从小到大排序
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        if (lists == null || lists.length == 0) {
            return null;
        }

        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        ListNode head = queue.poll();
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        if (cur.next != null) {
            queue.add(cur.next);
        }
        while (!queue.isEmpty()) {
            ListNode item = queue.poll();
            cur.next = item;
            if (item.next != null) {
                queue.add(item.next);
            }
            cur = item;
        }
        return head;
    }

    private static void print(ListNode one) {
        while (one != null) {
            System.out.print(one.val + ",");
            one = one.next;
        }
        System.out.println("");
    }
}
