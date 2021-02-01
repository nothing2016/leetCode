package cn.leetcode;

/**
 * 1513. 仅含 1 的子串数
 * 给你一个二进制字符串 s（仅由 '0' 和 '1' 组成的字符串）。
 * 返回所有字符都为 1 的子字符串的数目。
 * 由于答案可能很大，请你将它对 10^9 + 7 取模后返回。
 * <p>
 * 输入：s = "0110111"
 * 输出：9
 * 解释：共有 9 个子字符串仅由 '1' 组成
 * "1" -> 5 次
 * "11" -> 3 次
 * "111" -> 1 次
 * <p>
 * 题解：定义f(x)为计算次数的函数，则有 f(0110111) = f(11) + f(111),0刚好是分隔符，而计算全部为1的函数直接使用等差和公式即可
 * sum = ((a1 + an) * n)/2
 * <p>
 * 注意： 这道题有个坑,s的长度备注为：1 <= s.length <= 10^5,计算的使用count使用int类型，但是溢出了，因为给的测试用例超过了10^5次方，后面改成long就直接通过了
 *
 * @author oudaming
 * @date 2021-02-01 10:52
 */
public class _1513NumberOfSubstringsWithOnly1s {

    public static void main(String[] args) {

//        System.out.println(new _1513NumberOfSubstringsWithOnly1s().numSub("0110111"));
//        System.out.println(new _1513NumberOfSubstringsWithOnly1s().numSub("101"));
//        System.out.println(new _1513NumberOfSubstringsWithOnly1s().numSub("111111"));
    }

    public int numSub(String s) {
        int MOD = 1000000007;
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        long ans = 0;
        // 连续的1有多少个?
        long count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                count++;
            } else {
                ans = (ans + (((1 + count) * count) / 2)) % MOD;
                count = 0;
            }
        }
        // 最后结算一次，因为最后一位不一定是0
        ans = (ans + (((1 + count) * count) / 2)) % MOD;
        return (int) ans;
    }

}
