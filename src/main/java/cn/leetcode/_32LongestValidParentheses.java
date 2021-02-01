package cn.leetcode;

/**
 * 32. 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * <p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * <p>
 * 题解： 使用动态规划，dp[i] = dp[pre - 1] + dp[i - 1] + 2  (pre = i - dp[i - 1] - 1)
 * pre表示跟 “)” 匹配的 “(” 的位置下标
 *
 * @author oudaming
 * @date 2021-01-29 17:48
 */
public class _32LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(new _32LongestValidParentheses().longestValidParentheses(")()())"));
        System.out.println(new _32LongestValidParentheses().longestValidParentheses("()"));
        System.out.println(new _32LongestValidParentheses().longestValidParentheses("())"));
        System.out.println(new _32LongestValidParentheses().longestValidParentheses("("));
        System.out.println(new _32LongestValidParentheses().longestValidParentheses(")(((()()()))"));
        System.out.println(new _32LongestValidParentheses().longestValidParentheses(""));
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        // dp代表记录以当前i下标的值为最后一个字符，得到的最大有效的长度
        int[] dp = new int[len];
        char[] chars = s.toCharArray();
        int max = 0;
        // 002040
        // )()())
        // 012345
        // )(((()()()) )
        // 0000020406810
        for (int i = 1; i < len; i++) {
            if (chars[i] == ')') {
                // 上一个匹配的位置
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chars[pre] == '(') {
                    if (pre - 1 >= 0) {
                        dp[i] = dp[pre - 1] + dp[i - 1] + 2;
                    } else {
                        dp[i] = dp[i - 1] + 2;
                    }
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }
}
