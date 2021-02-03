package cn.leetcode;

/**
 * 44. 通配符匹配
 * <p>
 * 定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * <p>
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释:第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * <p>
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 * <p>
 * 题解：使用递归来尝试，然后使用dp来修改即可
 *
 * @author oudaming
 * @date 2021-02-03 17:20
 */
public class _44_Wildcard_Matching {
    public static void main(String[] args) {
        System.out.println(new _44_Wildcard_Matching().isMatch("adceb", "*a*b"));
        System.out.println(new _44_Wildcard_Matching().isMatch2("adceb", "*a*b"));
        System.out.println("=========");
        System.out.println(new _44_Wildcard_Matching().isMatch("acdcb", "a*c?b"));
        System.out.println(new _44_Wildcard_Matching().isMatch2("acdcb", "a*c?b"));
        System.out.println("=========");
        System.out.println(new _44_Wildcard_Matching().isMatch("adcebf", "*a*b?"));
        System.out.println(new _44_Wildcard_Matching().isMatch2("adcebf", "*a*b?"));
        System.out.println("=========");
        System.out.println(new _44_Wildcard_Matching().isMatch("q", "*"));
        System.out.println(new _44_Wildcard_Matching().isMatch2("q", "*"));
        System.out.println("=========");
        System.out.println(new _44_Wildcard_Matching().isMatch("aa", "a"));
        System.out.println(new _44_Wildcard_Matching().isMatch2("aa", "a"));
    }

    public boolean isMatch(String s, String p) {
        char[] s1 = s.toCharArray();
        char[] p1 = p.toCharArray();
        return process1(s1, p1, 0, 0);
    }

    public boolean isMatch2(String s, String p) {
        char[] s1 = s.toCharArray();
        char[] p1 = p.toCharArray();
        return dpWay(s1, p1, 0, 0);
    }

    // s[si....] 能否被 p[pi....] 匹配出来
    public static boolean process1(char[] s, char[] p, int si, int pi) {
        if (si == s.length) { // s -> ""
            if (pi == p.length) { // p -> ""
                return true;
            } else {
                // p -> "..."
                // p[pi] == '*' && p[pi+1...] -> "
                return p[pi] == '*' && process1(s, p, si, pi + 1);
            }
        }
        if (pi == p.length) { // p -> "" s
            return si == s.length;
        }
        // s从si出发.... p从pi出发...
        // s[si] -> 小写字母
        // p[pi] -> 小写、?、*
        if (p[pi] != '?' && p[pi] != '*') {
            return s[si] == p[pi] && process1(s, p, si + 1, pi + 1);
        }
        // si.. pi.. pi ? *
        if (p[pi] == '?') {
            return process1(s, p, si + 1, pi + 1);
        }
        // if(p[pi] == '*')的情况，如adceb -> *b
        // 就使用如下匹配： adceb -> b ,dceb -> b,ceb -> b,eb->b,b->b
        // 最后一个能匹配上，就算是匹配上了
        for (int len = 0; len <= s.length - si; len++) {
            if (process1(s, p, si + len, pi + 1)) {
                return true;
            }
        }
        return false;
    }

    // s[si....] 能否被 p[pi....] 匹配出来
    public static boolean dpWay(char[] s, char[] p, int si, int pi) {
        int N = s.length;
        int M = p.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;
        for (int i = M - 1; i >= 0; i--) {
            dp[N][i] = (p[i] == '*' && dp[N][i + 1]);
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                if (p[j] != '?' && p[j] != '*') {
                    dp[i][j] = (s[i] == p[j] && dp[i + 1][j + 1]);
                    continue;
                }
                if (p[j] == '?') {
                    dp[i][j] = dp[i + 1][j + 1];
                    continue;
                }
                for (int len = 0; len <= N - i; len++) {
                    if (dp[i + len][j + 1]) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }

        }
        return dp[0][0];
    }

}
