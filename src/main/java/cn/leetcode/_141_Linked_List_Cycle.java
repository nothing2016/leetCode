package cn.leetcode;

/**
 * 141. 环形链表
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <p>
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * <p>
 * 题解：
 * 1.这里如果使用空间复杂度为O(N)的话，非常的简单，只需要遍历过的放到一个set中，当再次遍历到的时候，这个就是环的交叉点
 * 2.如果是空间复杂度为O(1)的话，就使用快慢指针。这个是一个小学奥数的数学原理题，记住就可以。
 * 定义一个快指针fast，一次跳两步；定义一个慢指针slow，一次跳一次；两个指针不相同的时候一直跳，
 * 当相同时，fast跳到head上，一次跳一步，最终和slow相遇，相遇点就是环形的入口点
 *
 * @author oudaming
 * @date 2021-03-08 17:00
 */
public class _141_Linked_List_Cycle {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
//        ListNode listNode4 = new ListNode(4);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//        listNode4.next = listNode2;
        System.out.println(new _141_Linked_List_Cycle().hasCycle(listNode1));
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        ListNode cycleNode = getCycleNode(head);
        return cycleNode != null ? true : false;
    }

    private ListNode getCycleNode(ListNode head) {
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (slow == null || slow.next == null) {
                return null;
            }
            slow = slow.next;

            if (fast == null || fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;

        }
        // 如果到了这个地方，代表一定有环
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
