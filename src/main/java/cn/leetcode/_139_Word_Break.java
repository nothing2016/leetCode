package cn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * 注意你可以重复使用字典中的单词。
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * @author oudaming
 * @date 2021-03-08 10:08
 */
public class _139_Word_Break {
    public static void main(String[] args) {
        System.out.println(new _139_Word_Break().wordBreak("leetcode", new ArrayList<>(Arrays.asList("leet", "code"))));
        System.out.println(wordBreakDpWay("leetcode", new ArrayList<>(Arrays.asList("leet", "code"))));

//        System.out.println(new _139_Word_Break().wordBreak("applepenapple", new ArrayList<>(Arrays.asList("apple", "pen"))));
//        System.out.println(wordBreakDpWay("applepenapple", new ArrayList<>(Arrays.asList("apple", "pen"))));
//
//        System.out.println(new _139_Word_Break().wordBreak("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"))));
//        System.out.println(wordBreakDpWay("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"))));

//        System.out.println(new _139_Word_Break().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", new ArrayList<>(Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"))));
        System.out.println(wordBreakDpWay("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", new ArrayList<>(Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"))));
//        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
//                ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        HashSet<String> set = new HashSet<>(wordDict);
        return process(s, set);
    }

    /**
     * s 是否可以分隔成set里面的单词
     *
     * @param s
     * @param set
     * @return
     */
    private boolean process(String s, HashSet<String> set) {
        // 从左到右的尝试
        int N = s.length();
        // 如果N==0,证明所有的尝试都是正确的了
        if (N == 0) {
            return true;
        }
        for (int i = 1; i <= N; i++) {
            if (set.contains(s.substring(0, i))) {
                boolean ok = process(s.substring(i, N), set);
                if (ok) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean wordBreakDpWay(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return false;
        }
        HashSet<String> set = new HashSet<>(wordDict);
        int N = s.length();
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        for (int i = N - 1; i >= 0; i--) {
            for (int end = i + 1; end <= N; end++) {
                if (set.contains(s.substring(i, end))) {
                    dp[i] = dp[end];
                    if (dp[i]) {
                        break;
                    }
                }
            }
        }
        return dp[0];
    }


    /**
     * 最优解： 前缀树的解法
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak2(String s, List<String> wordDict) {
        Node root = new Node();
        for (String str : wordDict) {
            char[] chs = str.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
            }
            node.end = true;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;
        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            for (int end = i; end < N; end++) {
                int path = str[end] - 'a';
                if (cur.nexts[path] == null) {
                    break;
                }
                cur = cur.nexts[path];
                if (cur.end && dp[end + 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    public static class Node {
        public boolean end;
        public Node[] nexts;

        public Node() {
            end = false;
            nexts = new Node[26];
        }
    }
}
