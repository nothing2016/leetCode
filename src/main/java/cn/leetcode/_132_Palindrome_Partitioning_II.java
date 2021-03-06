package cn.leetcode;

/**
 * 132. 分割回文串 II
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的最少分割次数。
 * <p>
 * 输入: "aab"
 * 输出: 1
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * <p>
 * 题解：尝试的思路是这样的对于s字符串来说，
 * 如果 [0,i]是一个回文串，那么[i+1,N-1]的最小分割部分是k，那么此次的最小分割部分为k + 1
 * 那么定义dp[i]为[i,n-1]上的最小部分数，如果[i,j]是回文，那么得到如下递推公式
 * dp[i] = max(dp[i],dp[j+1] + 1)  j的范围是[i,N-1]
 * 这样的话，就已经是个O(N^2)的时间复杂度，如果求[i,j]是否是回文又消耗一个O(N),那么整体的时间复杂度
 * 就是O(N^3)，这样我们想办法将求[i,j]是否是回文预处理掉，通过动态规划可以在O(N^2)得到[i,j]是否是回文
 * 所以整体是一个O(N^2)的时间复杂度
 *
 * @author oudaming
 * @date 2021/3/6 0006 10:06
 */
public class _132_Palindrome_Partitioning_II {

    public static void main(String[] args) {
        System.out.println(new _132_Palindrome_Partitioning_II().minCut(""));
        System.out.println(new _132_Palindrome_Partitioning_II().minCut("1"));
        System.out.println(new _132_Palindrome_Partitioning_II().minCut("ab"));
        System.out.println(new _132_Palindrome_Partitioning_II().minCut("abccbadd"));
        System.out.println(new _132_Palindrome_Partitioning_II().minCut("ccbadd"));
    }

    public int minCut(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return 0;
        }
        int N = s.length();
        // isP[i][j]表示s[i,j]是否是回文串
        boolean[][] isP = creatIsP(s);
        // minTimes[i]表示从[i]到[N-1]最小分割部分
        int[] minTimes = new int[N + 1];
        for (int i = 0; i < N; i++) {
            minTimes[i] = Integer.MAX_VALUE;
        }
        minTimes[N] = 0; // 补位0，没有任何数就是0部分
        for (int i = N - 1; i >= 0; i--) {
            for (int end = i; end < N; end++) {
                if (isP[i][end]) {
                    // 如果[i,end]是回文，那么这次分割的部分就是[end + 1]的最小分割部分 + 1
                    minTimes[i] = Math.min(minTimes[i], minTimes[end + 1] + 1);
                }
            }
        }
        return minTimes[0] - 1;
    }

    //使用动态规划求[i,j]是否是回文串
    //isP[i][j]表示s[i,j]是否是回文串
    private boolean[][] creatIsP(String s) {
        int N = s.length();
        boolean[][] dp = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            // [i,i]范围上一定是回文，因为单个字符一定是回文(对角线上先填写)
            dp[i][i] = true;
        }
        for (int i = 0; i < N - 1; i++) {
            // 对于只有两个字符的字符串时，只有相等时是回文
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                // 对与任意的位置来说，只有前后s[i]和s[j]相等，并中间[i+1,j-1]也是回文的时候，才是回文
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
        return dp;
    }
}
