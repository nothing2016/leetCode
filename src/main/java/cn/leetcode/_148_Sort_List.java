package cn.leetcode;

/**
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * <p>
 * 题解：将链表分段，归并排序(迭代版本)，区间长度的取值为[1,2,4,8,16...N) 2^N取值
 * 举个例子:5 8 7 6 3 4 2 1
 * 段长为 1: (5 8 7 6 3 4 2 1) 排序后——>(5 8) (6 7) (3 4) ( 1 2)
 * 段长为 2: (5 8) (7 6) (3 4) (2 1) 排序后 ——>(5 6 7 8) (1 2 3 4)
 * 段长为 4: (5 6 7 8) (1 2 3 4) 排序后 ——>(1 2 3 4 5 6 7 8)
 *
 * @author oudaming
 * @date 2021-03-09 11:21
 */
public class _148_Sort_List {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int v) {
            val = v;
        }
    }

    public ListNode sortList(ListNode head) {
        int N = 0;
        ListNode cur = head;
        // 先求出链表的长度N
        while (cur != null) {
            N++;
            cur = cur.next;
        }
        ListNode h = head;
        // 小组里面的第一个
        ListNode teamFirst = head;
        ListNode pre = null;
        for (int len = 1; len < N; len <<= 1) {

            while (teamFirst != null) {
                // 将teamFirst为头的len个数分成两个区间[0,1][2,3] ,4表示下一个区间的开头
                ListNode[] hthtn = hthtn(teamFirst, len);
                // 合并区间[0,1][2,3] 为一个大区间 [head,tail]
                ListNode[] mhmt = merge(hthtn[0], hthtn[1], hthtn[2], hthtn[3]);
                if (h == teamFirst) {
                    h = mhmt[0];
                    pre = mhmt[1];
                } else {
                    pre.next = mhmt[0];
                    pre = mhmt[1];
                }
                teamFirst = hthtn[4];
            }
            teamFirst = h;
            pre = null;
        }
        return h;
    }

    /**
     * 将teamFirst为头的len个数分成两个区间[0,1][2,3] ,4表示下一个区间的开头
     *
     * @param teamFirst
     * @param len
     * @return
     */
    public static ListNode[] hthtn(ListNode teamFirst, int len) {
        ListNode ls = teamFirst;
        ListNode le = teamFirst;
        ListNode rs = null;
        ListNode re = null;
        ListNode next = null;
        int pass = 0;
        while (teamFirst != null) {
            pass++;
            if (pass <= len) {
                le = teamFirst;
            }
            if (pass == len + 1) {
                rs = teamFirst;
            }
            if (pass > len) {
                re = teamFirst;
            }
            if (pass == (len << 1)) {
                break;
            }
            teamFirst = teamFirst.next;
        }
        // 将两个区间的最后一个end的next变成null
        le.next = null;
        if (re != null) {
            // 找到第二个区间的下一个开头
            next = re.next;
            re.next = null;
        }
        return new ListNode[]{ls, le, rs, re, next};
    }

    public static ListNode[] merge(ListNode ls, ListNode le, ListNode rs, ListNode re) {
        if (rs == null) {
            return new ListNode[]{ls, le};
        }
        ListNode head = null;
        ListNode pre = null;
        ListNode cur = null;
        ListNode tail = null;
        while (ls != le.next && rs != re.next) {
            if (ls.val <= rs.val) {
                cur = ls;
                ls = ls.next;
            } else {
                cur = rs;
                rs = rs.next;
            }
            if (pre == null) {
                head = cur;
                pre = cur;
            } else {
                pre.next = cur;
                pre = cur;
            }
        }
        if (ls != le.next) {
            while (ls != le.next) {
                pre.next = ls;
                pre = ls;
                tail = ls;
                ls = ls.next;
            }
        } else {
            while (rs != re.next) {
                pre.next = rs;
                pre = rs;
                tail = rs;
                rs = rs.next;
            }
        }
        return new ListNode[]{head, tail};
    }
}
