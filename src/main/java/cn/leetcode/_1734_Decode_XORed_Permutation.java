package cn.leetcode;

/**
 * 1734. 解码异或后的排列
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 * <p>
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 * <p>
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：encoded = [3,1]
 * 输出：[1,2,3]
 * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 * 示例 2：
 * <p>
 * 输入：encoded = [6,5,4,6]
 * 输出：[2,4,1,5,3]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= n < 105
 * n 是奇数。
 * encoded.length == n - 1
 * <p>
 * 题解：首先“数组 perm ，它是前 n 个正整数的排列” 这点非常的重要，例如 encoded = [6,5,4,6]，那么perm 就一定是[1,2,3,4,5]这个集合的某一个排列
 * 我们找到第一个后，就可以使用 1720 求解的方式解答这个问题了
 * <p>
 * 我们可以使用A, B, C, D, E代表整数数组perm，注意：它是前 n 个正整数的排列，且 n 是奇数。
 * 为了表达的方便，可以这么定义：将A XOR B（A和B进行异或运算）简写为AB。
 * <p>
 * 思路步骤：
 * 既然我们知道了perm = [A, B, C, D, E]，那么encoded = [AB, BC, CD, DE]；
 * 根据perm，我们可以得到ABCDE,根据encoded的BC和DE，我们可以得到BCDE；
 * 将ABCDE和BCDE进行异或运算，得到A，即perm的第一个元素。这时候，今天的题目转换成1720 的题即可。
 */
public class _1734_Decode_XORed_Permutation {
    public static void main(String[] args) {
        int[] decode = decode(new int[]{6, 5, 4, 6});
        for (int i : decode) {
            System.out.println(i);
        }
    }

    public static int[] decode(int[] encoded) {
        int first = 0;
        int N = encoded.length + 1;
        for (int i = 1; i <= N; i++) {
            first ^= i;
        }
        for (int i = 1; i < encoded.length; i += 2) {
            first ^= encoded[i];
        }
        int[] ans = new int[N];
        ans[0] = first;
        for (int i = 1; i < N; i++) {
            ans[i] = ans[i - 1] ^ encoded[i - 1];
        }
        return ans;
    }
}
