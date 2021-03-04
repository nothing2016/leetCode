package cn.leetcode;

/**
 * 115. 不同的子序列
 * <p>
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 * <p>
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * <p>
 * 题解： 一个样本做行，一个样本做列的对应模型，定义dp[i][j] 是s上0~i的字符字串子序列中, t上0~j出现的个数
 * 这样的话，dp[0][0] 代表的是 s=’‘ 和 t=’‘的出现次数，肯定是1，那么对应任意一个字串s，t=’‘在其中出现的次数一定是1，只要第0列的值都是1
 * 对于通用的位置来说，
 * 第一种情况：举个例子，s= 'rac',t = 'ra',最后一位c != a, 那么，就是s中有c和没有c是一样的，也就是 dp[i][j] = dp[i-1][j]
 * 第二种情况：举个列子，s='rcc', t= 'rc', 最后一位c = c, 那么就转换成，rc 和 r 的问题了(即dp[i-1][j-1])，那已经结束了吗？没有，因为 还要用s的
 * 前两位 rc 和 t= 'rc'进行比较，因为s没有最后一个一的情况不正是 最后一位s和最后一位t不相等的情况吗(第一种情况)，这种情况要加上的，
 * <p>
 * 所以最后得到：
 * 当s[i] != t[i]   => dp[i][j] = dp[i-1][j]
 * 当s[i] == t[i]   => dp[i][j] = dp[i-1][j - 1] + dp[i-1][j]
 * <p>
 * 注意：s[0] 和 t[0]都要预留出来，代表的是空字符串’‘的结果
 *
 * @author oudaming
 * @date 2021-03-04 9:23
 */
public class _115_Distinct_Subsequences {
    public static void main(String[] args) {
        System.out.println(new _115_Distinct_Subsequences().numDistinct("", ""));
        System.out.println(new _115_Distinct_Subsequences().numDistinct("rabbbit", "rabbit"));
        System.out.println(new _115_Distinct_Subsequences().numDistinct("babgbag", "bag"));
    }

    public int numDistinct(String s, String t) {
        if (t == null || s == null) {
            return 0;
        }
        int N = s.length();
        int M = t.length();
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][M];
    }
}
