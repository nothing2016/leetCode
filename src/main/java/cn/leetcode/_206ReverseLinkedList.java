package cn.leetcode;

/**
 * 206. 反转链表
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * @author oudaming
 * @date 2021/1/9 0009 16:04
 */
public class _206ReverseLinkedList {
    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        one.next = two;
        two.next = three;
        printList(one);

        ListNode listNode = new _206ReverseLinkedList().reverseList(one);
        printList(listNode);


    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = null;
        ListNode next = null;
        ListNode cur = head;
        while (cur != null) {
            // 先记录一下下一个node
            next = cur.next;

            // cur作为逆序新的头部
            cur.next = newHead;
            newHead = cur;

            // 跳动下一个节点
            cur = next;
        }
        return newHead;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println("null");

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
