package cn.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

/**
 * 150. 逆波兰表达式求值
 * 根据 逆波兰表示法，求表达式的值。
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * <p>
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * <p>
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * <p>
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: 该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * <p>
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 * 该算式转化为常见的中缀算术表达式为：
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 * @author oudaming
 * @date 2021-03-09 14:42
 */
public class _150_Evaluate_Reverse_Polish_Notation {
    public static void main(String[] args) {
        System.out.println(new _150_Evaluate_Reverse_Polish_Notation().evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(new _150_Evaluate_Reverse_Polish_Notation().evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        System.out.println(new _150_Evaluate_Reverse_Polish_Notation().evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    public int evalRPN(String[] tokens) {
        // +, -, *, /
        String plus = "+";
        String dec = "-";
        String multi = "*";
        String divide = "/";
        HashSet<String> set = new HashSet(Arrays.asList(plus, dec, multi, divide));
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (set.contains(tokens[i])) {
                int second = Integer.valueOf(stack.pop());
                int first = Integer.valueOf(stack.pop());
                int ans = 0;
                if (plus.equals(tokens[i])) {
                    ans = first + second;
                } else if (dec.equals(tokens[i])) {
                    ans = first - second;
                } else if (multi.equals(tokens[i])) {
                    ans = first * second;
                } else {
                    ans = first / second;
                }
                stack.push(String.valueOf(ans));
            } else {
                stack.push(tokens[i]);
            }
        }
        String pop = stack.pop();
        return Integer.valueOf(pop);
    }
}
