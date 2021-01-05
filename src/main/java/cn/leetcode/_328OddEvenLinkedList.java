package cn.leetcode;

/**
 * 328. 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 *
 * @author oudaming
 * @date 2020-12-31 10:10
 */
public class _328OddEvenLinkedList {
    public static void main(String[] args) {
        ListNode one = new ListNode(2);
        ListNode two = new ListNode(1);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(5);
        ListNode five = new ListNode(6);
        ListNode six = new ListNode(4);
        ListNode seven = new ListNode(7);

        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;

        print(one);
        ListNode result = oddEvenList(one);
        print(result);
    }

    private static void print(ListNode one) {
        while (one != null) {
            System.out.print(one.val + ",");
            one = one.next;
        }
        System.out.println("");
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode oddHead = head;
        ListNode oddTail = head;

        ListNode evenHead = head.next;
        ListNode evenTail = head.next;

        ListNode cur = evenTail.next;
        // 当前编号是否是奇数，默认第三个，是
        boolean isOdd = true;
        while (cur != null) {
            if (isOdd) {
                oddTail.next = cur;
                oddTail = cur;
            } else {
                evenTail.next = cur;
                evenTail = cur;
            }
            // 下一个
            cur = cur.next;
            isOdd = !isOdd;
        }
        oddTail.next = evenHead;
        evenTail.next = null;
        return oddHead;
    }

}

class ListNode {
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
