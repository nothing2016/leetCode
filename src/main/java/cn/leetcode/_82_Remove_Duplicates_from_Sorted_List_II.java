package cn.leetcode;

/**
 * 82. 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，
 * 只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 返回同样按升序排列的结果链表。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * 示例 2：
 * <p>
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * <p>
 * 提示：
 * <p>
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 *
 * @author oudaming
 * @date 2021-03-25 9:35
 */
public class _82_Remove_Duplicates_from_Sorted_List_II {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
//        ListNode node4 = new ListNode(3);
//        ListNode node5 = new ListNode(4);
//        ListNode node6 = new ListNode(4);
//        ListNode node7 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
        print(node1);

        ListNode node = deleteDuplicates(node1);
        print(node);
    }


    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 链表前加一个虚拟节点
        ListNode first = new ListNode(Integer.MAX_VALUE);
        first.next = head;
        head = first;

        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            // 有重复的值
            if (cur.next != null && cur.val == cur.next.val) {
                int dup = cur.val;
                // 需要删除掉值为dup的数据
                while (cur != null && cur.val == dup) {
                    pre.next = cur.next;
                    cur = cur.next;
                }
                continue;

            }
            pre = cur;
            cur = cur.next;
        }

        return first.next;
    }

    private static void print(ListNode one) {
        while (one != null) {
            System.out.print(one.val + ",");
            one = one.next;
        }
        System.out.println("");
    }
}
