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
 *
 * @author oudaming
 * @date 2021-01-29 17:48
 */
public class _32LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        // dp代表记录以当前i下标的值为最后一个字符，得到的最大有效的长度
        int[] dp = new int[len];
        char[] chars = s.toCharArray();
        // 002040
        // )()())
        // 012345
        for (int i = 1; i < len; i++) {
            if (chars[i] == ')') {
                if (i - dp[i - 1] - 1 >= 0) {

                }
                dp[i] = dp[i - dp[i - 1] - 1];
            }
        }
        return 0;
    }
}
