package cn.leetcode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * @author oudaming
 * @date 2021-01-28 17:46
 */
public class _19RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
//        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        printLinkNode(node1);
        ListNode node = new _19RemoveNthNodeFromEndOfList().removeNthFromEnd(node1, 1);
        printLinkNode(node);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }


        int x = len - n + 1;
        ListNode preNode = head;
        cur = head;
        int index = 0;

        if (len == 1 && n == 1) {
            return null;
        }
        if (x == 1) {
            return head.next;
        }
        while (cur != null) {
            index++;
            if (index == x) {
                preNode.next = cur.next;
                preNode = cur.next;
            } else {
                preNode = cur;
            }
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
