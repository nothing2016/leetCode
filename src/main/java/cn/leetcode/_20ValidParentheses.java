package cn.leetcode;

import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * <p>
 * 题解： 使用stack即可
 *
 * @author oudaming
 * @date 2021-01-29 13:53
 */
public class _20ValidParentheses {
    public static void main(String[] args) {
        System.out.println(new _20ValidParentheses().isValid("()[]{}"));
        System.out.println(new _20ValidParentheses().isValid("([)]"));
        System.out.println(new _20ValidParentheses().isValid(""));
    }

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char item = chars[i];
            switch (item) {
                case '(':
                    stack.push(item);
                    break;
                case '{':
                    stack.push(item);
                    break;
                case '[':
                    stack.push(item);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') {
                        return false;
                    }
                    stack.pop();
                    break;
                case '}':
                    if (stack.isEmpty() || stack.peek() != '{') {
                        return false;
                    }
                    stack.pop();
                    break;
                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') {
                        return false;
                    }
                    stack.pop();
                    break;
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }
}
