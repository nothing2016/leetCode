package cn.leetcode;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * <p>
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * <p>
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * <p>
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * <p>
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 *
 * @author oudaming
 * @date 2021-03-10 11:11
 */
public class _10_Regular_Expression_Matching {
    public static void main(String[] args) {
//        System.out.println(new _10_Regular_Expression_Matching().isMatch("123", "12."));
//        System.out.println(new _10_Regular_Expression_Matching().isMatch("aa", "a."));
        System.out.println(new _10_Regular_Expression_Matching().isMatch("aa", "a*"));
        System.out.println(new _10_Regular_Expression_Matching().isMatch("ab", ".b"));
        System.out.println(new _10_Regular_Expression_Matching().isMatch("", ".*"));
    }

    public boolean isMatch(String s, String p) {
        int N = s.length();
        int M = p.length();

        // dp[i][j] 表示s[0,i]的数据能否匹配上p[0,j]的正则表达式
        boolean[][] dp = new boolean[N + 1][M + 1];
        // 0 位置放空字符串
        dp[0][0] = true;
        // 第一列都为false, 因为除了空字符串能匹配空字符串，其他的都匹配不上

        // 单独出来第一行
        for (int j = 1; j <= M; j++) {
            // 因为s= '' p = 'a*' 是匹配的
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
            // 因为s= '' p = '.' 是不匹配的，所以为'.'的情况不需要讨论，只有s的值不为空，’.‘才有讨论的意义
        }
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= M; ++j) {

                if (p.charAt(j - 1) == '*') {
                    // s[i] 和 p[j-1] 匹配不上的情况，如 s= "aaa" p = "ab*" 那就只能用aaa 来匹配  a了，b* 变成0次
                    // 为什么不担心 j-2 <0 的溢出，应为*不可能出现在p的第一位
                    dp[i][j] = dp[i][j - 2];
                    // s[i] 和 p[j-1] 匹配上的情况
                    // 如s= "aaaaa" p = "aaa*"
                    // dp[i][j] = dp[i][j-2]   a* 是’‘的情况
                    // dp[i][j] = dp[i-1][j-2]   a* 是’a‘的情况
                    // dp[i][j] = dp[i-2][j-2]   a* 是’aa‘的情况
                    // dp[i][j] = dp[i-3][j-2]   a* 是’aaa‘的情况
                    // 经过观察，以上的情况的值就是 dp[i-1][j]的值，所以有以下动态转移方程
                    if (matches(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    // 如果该字符是字母或'.'
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[N][M];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

}
