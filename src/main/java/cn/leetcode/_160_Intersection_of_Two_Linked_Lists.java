package cn.leetcode;

/**
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * <p>
 * 题解：如果一个链表长度为6，一个链表长度为4，那么相交的节点一定在长链表的后面四个节点，所以长链表的前面两个是不会相交的
 * 所以只要找到长链表适合的开始节点，两个链表同步走，就能走到相交节点。那么适合的开始节点如何计算，就是如果从适合的开始节点开始走
 * 走到结尾时，短链表也到结尾，这个适合的开始节点使得长链表的’长‘度跟短链表的长度一致，所以要从长链表中 len(长链表) - len(短链表)的节点 开始走
 *
 * @author oudaming
 * @date 2021-03-09 17:31
 */
public class _160_Intersection_of_Two_Linked_Lists {
    public class ListNode {
        int val;
        ListNode next;
    }

    public ListNode getIntersectionNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        // cur1  end1
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        // cur2 end2，两个链表都走到了终点，如果两个尾指针不相等的话，证明没有相交的节点
        if (cur1 != cur2) {
            return null;
        }
        // cur1 长的链表头
        cur1 = n > 0 ? head1 : head2; // 谁是长链表，谁把头节点，给cur1赋值
        // cur2 短的链表头
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        // 如果一个链表长度为6，一个链表长度为4，那么相交的节点一定在长链表的后面四个节点，所以长链表的前面两个是不会相交的
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        // 两个同时从一个对应位置同步走，一定能相遇在相交点上
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }
}
