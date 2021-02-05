package cn.leetcode;

/**
 * 1208. 尽可能使字符串相等
 * 给你两个长度相同的字符串，s 和 t。
 * 将 s中的第i个字符变到t中的第 i 个字符需要|s[i] - t[i]|的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 * 用于变更字符串的最大预算是maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 * <p>
 * 输入：s = "abcd", t = "bcdf", cost = 3
 * 输出：3
 * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
 * <p>
 * 输入：s = "abcd", t = "cdef", cost = 3
 * 输出：1
 * 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
 * <p>
 * 输入：s = "abcd", t = "acde", cost = 0
 * 输出：1
 * 解释：你无法作出任何改动，所以最大长度为 1。
 * <p>
 * <p>
 * 题解：双指针算法，求出每一个位置的代价值，使用双指针，当sum < maxCost ,r++; sum <= maxCost => l++ 遍历一遍，
 * 求出区间中累加和小于等于 maxCost 的最大长度
 *
 * @author oudaming
 * @date 2021-02-05 15:52
 */
public class _1208_Get_Equal_Substrings_Within_Budget {
    public static void main(String[] args) {
        System.out.println(new _1208_Get_Equal_Substrings_Within_Budget().equalSubstring("abcd", "bcdf", 3));
        System.out.println(new _1208_Get_Equal_Substrings_Within_Budget().equalSubstring("abcd", "cdef", 3));
        System.out.println(new _1208_Get_Equal_Substrings_Within_Budget().equalSubstring("abcd", "acde", 0));
        System.out.println(new _1208_Get_Equal_Substrings_Within_Budget().equalSubstring("abcd", "bcde", 4));
        System.out.println(new _1208_Get_Equal_Substrings_Within_Budget().equalSubstring("krrgw", "zjxss", 19));

        System.out.println(new _1208_Get_Equal_Substrings_Within_Budget().equalSubstring("aabcd", "zzbcd", 3));
    }

    public int equalSubstring(String s, String t, int maxCost) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() != t.length()) {
            return 0;
        }
        int N = s.length();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        // [l,r)
        int l = 0;
        int r = 1;
        int sum = a[0];
        int max = 0;
        while (l < N && r <= N) {
            if (sum <= maxCost) {
                max = Math.max(r - l, max);
                if (r != N) {
                    sum += a[r];
                }
                r++;
            } else {
                sum -= a[l++];
            }
        }
        return max;
    }
}
