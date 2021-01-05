package cn.leetcode;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author oudaming
 * @date 2020-12-31 17:19
 */
public class _21MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(4);
        one.next = two;
        two.next = three;

        ListNode four = new ListNode(1);
        ListNode five = new ListNode(3);
        ListNode six = new ListNode(4);
        four.next = five;
        five.next = six;

        print(one);
        print(four);
        ListNode result = new _21MergeTwoSortedLists().mergeTwoLists(one, four);
        print(result);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        ListNode tail = null;
        ListNode curLeft = l1;
        ListNode curRight = l2;
        if (curLeft.val <= curRight.val) {
            head = curLeft;
            tail = curLeft;
            curLeft = curLeft.next;
        } else {
            head = curRight;
            tail = curRight;
            curRight = curRight.next;
        }
        while (curLeft != null && curRight != null) {
            if (curLeft.val <= curRight.val) {
                tail.next = curLeft;
                tail = curLeft;
                curLeft = curLeft.next;
            } else {
                tail.next = curRight;
                tail = curRight;
                curRight = curRight.next;
            }
        }
        if (curLeft == null) {
            tail.next = curRight;
        } else {
            tail.next = curLeft;
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


