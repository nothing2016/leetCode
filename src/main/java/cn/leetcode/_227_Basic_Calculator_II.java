package cn.leetcode;

import java.util.LinkedList;

/**
 * 227. 基本计算器 II
 * <p>
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 * <p>
 * 输入：s = "3+2*2"
 * 输出：7
 * <p>
 * 输入：s = " 3/2 "
 * 输出：1
 * <p>
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 * <p>
 * * 输入：s = " (2+5*2) / 2 + 1 "
 * * 输出：7
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 *
 * @author oudaming
 * @date 2021-03-11 9:22
 */
public class _227_Basic_Calculator_II {
    public static void main(String[] args) {
//        System.out.println(new _224_Basic_Calculator().calculate("1 + 1"));
        System.out.println(new _224_Basic_Calculator().calculate(" 2-1 + 2 "));
        System.out.println(new _227_Basic_Calculator_II().calculate("(1+(10*5 - 20)/3+2)"));
        System.out.println(new _227_Basic_Calculator_II().calculate("0+0"));
//        System.out.println(new _227_Basic_Calculator_II().calculate("(1+8)"));
    }

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.replaceAll(" ", "");
        char[] chars = s.toCharArray();
        int[] ans = process(chars, 0);
        return ans[0];
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    public static int[] process(char[] str, int i) {
        LinkedList<String> que = new LinkedList<String>();
        int cur = 0;
        int[] bra = null;
        // 从i出发，开始撸串
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 遇到的是运算符号
                addNum(que, cur);
                que.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else { // 遇到左括号了
                bra = process(str, i + 1);
                cur = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(que, cur);
        return new int[]{getNum(que), i};
    }

    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top);
            } else {
                cur = Integer.valueOf(que.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        que.addLast(String.valueOf(num));
    }

    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

//    /**
//     * 计算 从i出发，到 )或结束的位置的值
//     * 返回[求的值，下一个表达式开始的下标]
//     *
//     * @param chars
//     * @param i
//     * @return
//     */
//    private int[] process(char[] chars, int i) {
//        int N = chars.length;
//        LinkedList<String> linkedList = new LinkedList();
//        int cur = 0;
//        while (i < N) {
//            if (chars[i] != '+' && chars[i] != '-' && chars[i] != '*' && chars[i] != '/' && chars[i] != '(' && chars[i] != ')') {
//                // chars[i] 是数字
//                cur = cur * 10 + (chars[i] - '0');
//                i++;
//            } else if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/') {
//                // chars[i] 是符号
//                if (cur != 0) {
//                    addToLinkList(linkedList, String.valueOf(cur));
//                }
//                addToLinkList(linkedList, String.valueOf(chars[i]));
//                cur = 0;
//                i++;
//            } else if (chars[i] == '(') {
//                int[] nums = process(chars, i + 1);
//                addToLinkList(linkedList, String.valueOf(nums[0]));
//                i = nums[1];
//                cur = 0;
//            } else if (chars[i] == ')') {
//                // 是右括号的情况
//                addToLinkList(linkedList, String.valueOf(cur));
//                int num = getNumFromLinkList(linkedList);
//                return new int[]{num, i + 1};
//            }
//        }
//        addToLinkList(linkedList, String.valueOf(cur));
//        int num = getNumFromLinkList(linkedList);
//        return new int[]{num, i + 1};
//    }
//
//    private int getNumFromLinkList(LinkedList<String> linkedList) {
//        int ans = Integer.valueOf(linkedList.pollFirst());
//        while (!linkedList.isEmpty()) {
//            String s = linkedList.pollFirst();
//            if (s.equals("+")) {
//                ans = ans + Integer.valueOf(linkedList.pollFirst());
//            } else if (s.equals("-")) {
//                ans = ans - Integer.valueOf(linkedList.pollFirst());
//            }
//        }
//        return ans;
//    }
//
//    private void addToLinkList(LinkedList<String> linkedList, String op) {
//        if ("*".equals(linkedList.peekLast())) {
//            linkedList.pollLast();
//            String number = linkedList.pollLast();
//            int ans = Integer.valueOf(number) * Integer.valueOf(op);
//            linkedList.addLast(String.valueOf(ans));
//        } else if ("/".equals(linkedList.peekLast())) {
//            linkedList.pollLast();
//            String number = linkedList.pollLast();
//            int ans = Integer.valueOf(number) / Integer.valueOf(op);
//            linkedList.addLast(String.valueOf(ans));
//        } else {
//            linkedList.addLast(op);
//        }
//    }
}
