package cn.leetcode;


/**
 * 395. 至少有 K 个重复字符的最长子串
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * <p>
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * <p>
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * <p>
 * 题解：1.将s遍历一遍，根据字符统计词频，任何包含词频小于k的字符一定不符合条件，这样以这些字符为分隔点，答案比在其中
 * 2.分割后的split[i]中，不一定是符合答案的比如aabbbcab,k =2,c作为分割点，aabbb符合条件，但是ab不符合，所以要对每个split[i]分治递归求解一次，比较出最大的那个即可
 * 3.递归的出口在哪里?只要单个string中，词频都超过k,那么就返回string的长度即可
 * 4.如何优化?当s.length < k的时候，答案一定是0
 *
 * @author oudaming
 * @date 2021-03-01 10:54
 */
public class _395_Longest_Substring_with_At_Least_K_Repeating_Characters {
    public static void main(String[] args) {
//        String s = "aafbbgcc";
//        String[] a = s.split("[gf]");
//        for (String item : a) {
//            System.out.println(item);
//        }
        System.out.println(longestSubstring("aaabb", 3));
        System.out.println(longestSubstring("ababbc", 2));
        System.out.println(longestSubstring("aabbbcab", 2));
        System.out.println(longestSubstring("a", 2));
    }

    public static int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0 && count[i] < k) {
                builder.append((char) ('a' + i));
            }
        }
        // 如果没有找到分隔点，那么整个s都是符合要求的
        if (builder.toString().equals("")) {
            return s.length();
        }

        String[] split = s.split("[" + builder.toString() + "]");
        int len = 0;
        for (int i = 0; i < split.length; i++) {
            len = Math.max(len, longestSubstring(split[i], k));
        }
        return len;
    }
}
