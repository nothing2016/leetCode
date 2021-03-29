package cn.leetcode;

/**
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 * <p>
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 * <p>
 * 题解：旋转的本质就是将倒数的几个节点放到前面就可以了
 *
 * @author oudaming
 * @date 2021/3/27 0027 07:48
 */
public class _61_Rotate_List {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        printList(node1);
        ListNode listNode = rotateRight(node1, 0);
        printList(listNode);

    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null) {
            return head;
        }
        int N = 0;
        ListNode cur = head;
        ListNode pre = head;
        while (cur != null) {
            N++;
            pre = cur;
            cur = cur.next;
        }
        ListNode tail = pre;
        k %= N;
        if (k == 0) {
            return head;
        }
        // 旋转的本质就是将倒数的几个节点放到前面就可以了
        k = N - k;
        int count = 0;
        cur = head;
        while (cur != null) {
            count++;
            if (count == k) {
                break;
            }
            cur = cur.next;
        }

        // 保留next，作为新链表的头节点
        ListNode next = cur.next;
        // cur 作为新链表的尾节点
        cur.next = null;
        // 首尾相连
        tail.next = head;
        // 更新新头部指针
        head = next;
        return head;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println("null");

    }
}
