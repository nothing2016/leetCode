package cn.leetcode;

/**
 * 206. 反转链表II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @author oudaming
 * @date 2021/1/9 0009 16:04
 */
public class _92ReverseLinkedList_II {
    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = null;
        three.next = null;
        four.next = five;
        printList(one);
        ListNode listNode = new _92ReverseLinkedList_II().reverseList(one, 1, 2);
        printList(listNode);
    }

    public ListNode reverseList(ListNode head, int m, int n) {
        if (head == null || m == 0 || n == 0) {
            return null;
        }
        ListNode lastTail = null;

        ListNode newHead = null;
        ListNode newTail = null;

        ListNode next = null;
        ListNode cur = head;
        ListNode pre = null;
        int index = 1;
        while (cur != null) {
            // 先记录一下下一个node
            next = cur.next;
            // 如果n == m ,其实就是不用反转
            if (index >= m && index <= n && n != m) {
                if (index == m) {
                    lastTail = pre;
                    newTail = cur;
                }
                // 反转
                // cur作为逆序新的头部
                cur.next = newHead;
                newHead = cur;

                if (index == n) {
                    if (lastTail != null) {
                        lastTail.next = newHead;
                    } else {
                        // 只有从第一位就反转，这样上一个链表的lastTail才为空
                        // 这样直接设置head头即可
                        head = newHead;
                    }

                    newTail.next = next;
                }
            }


            // 记录上一个节点
            pre = cur;
            // 跳动下一个节点
            cur = next;
            index++;
        }
        return head;
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
