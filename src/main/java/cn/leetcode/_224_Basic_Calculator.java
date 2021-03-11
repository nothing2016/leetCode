package cn.leetcode;

import java.util.LinkedList;

/**
 * 224. 基本计算器
 * <p>
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * 输入：s = "1 + 1"
 * 输出：2
 * <p>
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * <p>
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * <p>
 * 题解：计算 从i出发，到 )或结束的位置的值
 * 返回[求的值，下一个表达式开始的下标]
 *
 * @author oudaming
 * @date 2021-03-10 10:09
 */
public class _224_Basic_Calculator {
    public static void main(String[] args) {
//        System.out.println(new _224_Basic_Calculator().calculate("1 + 1"));
//        System.out.println(new _224_Basic_Calculator().calculate(" 2-1 + 2 "));
        System.out.println(new _224_Basic_Calculator().calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(new _224_Basic_Calculator().calculate("(1+8)"));
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

    /**
     * 计算 从i出发，到 )或结束的位置的值
     * 返回[求的值，下一个表达式开始的下标]
     *
     * @param chars
     * @param i
     * @return
     */
    private int[] process(char[] chars, int i) {
        int N = chars.length;
        LinkedList<String> linkedList = new LinkedList();
        int cur = 0;
        while (i < N) {
            if (chars[i] != '+' && chars[i] != '-' && chars[i] != '(' && chars[i] != ')') {
                // chars[i] 是数字
                cur = cur * 10 + (chars[i] - '0');
                i++;
            } else if (chars[i] == '+' || chars[i] == '-') {
                // chars[i] 是符号
                addToLinkList(linkedList, String.valueOf(cur));
                addToLinkList(linkedList, String.valueOf(chars[i]));
                cur = 0;
                i++;
            } else if (chars[i] == '(') {
                int[] nums = process(chars, i + 1);
                addToLinkList(linkedList, String.valueOf(nums[0]));
                i = nums[1];
                cur = 0;
            } else if (chars[i] == ')') {
                // 是右括号的情况
                addToLinkList(linkedList, String.valueOf(cur));
                int num = getNumFromLinkList(linkedList);
                return new int[]{num, i + 1};
            }
        }
        addToLinkList(linkedList, String.valueOf(cur));
        int num = getNumFromLinkList(linkedList);
        return new int[]{num, i + 1};
    }

    private int getNumFromLinkList(LinkedList<String> linkedList) {
        int ans = Integer.valueOf(linkedList.pollFirst());
        while (!linkedList.isEmpty()) {
            String s = linkedList.pollFirst();
            if (s.equals("+")) {
                ans = ans + Integer.valueOf(linkedList.pollFirst());
            } else if (s.equals("-")) {
                ans = ans - Integer.valueOf(linkedList.pollFirst());
            }
        }
        return ans;
    }

    private void addToLinkList(LinkedList<String> linkedList, String op) {
        linkedList.addLast(op);
    }
}
