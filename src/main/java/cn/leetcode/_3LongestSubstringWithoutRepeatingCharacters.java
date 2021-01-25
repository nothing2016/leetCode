package cn.leetcode;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * @author oudaming
 * @date 2021-01-25 14:07
 */
public class _3LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(new _3LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("aaa"));
        System.out.println(new _3LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abba"));
        System.out.println(new _3LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new _3LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new _3LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("pwawkew"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        // 表示字符出现的最后位置
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        // 收集答案
        int len = 0;
        int pre = -1; // 重复时的字符串的最左位置
        for (int i = 0; i < str.length; i++) {
            // 曾经出现过，那现在肯定就会重复，先记录下来，而且要记录最后的位置在pre中
            if (map[str[i]] != -1) {
                pre = Math.max(pre, map[str[i]]);
                len = Math.max(len, i - pre);
                map[str[i]] = i;
            } else {
                len = Math.max(len, i - pre);
                map[str[i]] = i;
            }
        }
        return len;
    }
}
