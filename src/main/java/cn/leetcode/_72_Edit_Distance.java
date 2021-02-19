package cn.leetcode;

/**
 * 72. 编辑距离
 * <p>
 * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 题解：编辑距离问题,这里将copy的编辑代价看成0，插入，删除，替换的代价为1
 *
 * @author oudaming
 * @date 2021-02-19 15:22
 */
public class _72_Edit_Distance {
    public static void main(String[] args) {
        System.out.println(new _72_Edit_Distance().minDistance("abc", "abd"));
        System.out.println(new _72_Edit_Distance().minDistance("horse", "ros"));
        System.out.println(new _72_Edit_Distance().minDistance("intention", "execution"));
    }

    public int minDistance(String word1, String word2) {
        int N1 = word1.length();
        int N2 = word2.length();
        int[][] dp = new int[N1 + 1][N2 + 1];
        for (int i = 1; i <= N1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= N2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= N1; i++) {
            for (int j = 1; j <= N2; j++) {
                // 将[i]变成[j]的编辑代价是1
                dp[i][j] = dp[i - 1][j - 1] + 1;
                // 如果[i]和[j]的位置相同，那么就是copy的编辑代价，直接使用dp[i - 1][j - 1]
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 将[0...i-1]变成[j],再去掉[i]的代价
                // 将[0...i]变成[j-1],再加上[j]的代价
                // 四种可能取最小
                dp[i][j] = Math.min(dp[i][j], Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }
        return dp[N1][N2];
    }

}
