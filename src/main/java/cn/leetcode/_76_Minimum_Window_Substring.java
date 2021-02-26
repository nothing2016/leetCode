package cn.leetcode;

import java.util.HashMap;
import java.util.Set;

/**
 * 76. 最小覆盖子串
 * <p>
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * <p>
 * 题解：使用滑动窗口
 *
 * @author oudaming
 * @date 2021-02-26 16:20
 */
public class _76_Minimum_Window_Substring {
    public static void main(String[] args) {
//        System.out.println(new _76_Minimum_Window_Substring().minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(new _76_Minimum_Window_Substring().minWindow("ABC", "ABC"));
    }

    public String minWindow(String s, String t) {
        // 存放t的字符词频
        HashMap<Character, Integer> tCount = new HashMap();
        for (Character c : t.toCharArray()) {
            tCount.put(c, tCount.getOrDefault(c, 0) + 1);
        }
        // 存放整个s的字符词频
        HashMap<Character, Integer> map = new HashMap<>();
        // 当前结果的长度
        int minLen = Integer.MAX_VALUE;
        // 当前结果的答案
        String ans = "";
        int r = 0;
        int l = 0;
        int N = s.length();
        char[] chars = s.toCharArray();
        while (r < N) {
            // 来到r位置，准备放r位置的值到map中
            map.put(chars[r], map.getOrDefault(chars[r], 0) + 1);
            r++;
            while (check(tCount, map)) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    ans = s.substring(l, r);
                }

                Integer integer = map.getOrDefault(chars[l], 0);
                if (integer <= 1) {
                    map.remove(chars[l]);
                } else {
                    map.put(chars[l], map.get(chars[l]) - 1);
                }
                l++;
            }
        }
        return ans;
    }

    // 如果当前[l,r]范围能覆盖t,返回true
    private boolean check(HashMap<Character, Integer> tCount, HashMap<Character, Integer> map) {
        Set<Character> characters = tCount.keySet();
        for (Character c : characters) {
            // 对与tCount中每一个c的词频，map中的都必要要大，才能返回true
            if (map.getOrDefault(c, 0) < tCount.get(c)) {
                return false;
            }
        }
        return true;
    }
}
