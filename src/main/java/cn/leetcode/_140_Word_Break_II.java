package cn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 140. 单词拆分 II
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * <p>
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * <p>
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 *
 * @author oudaming
 * @date 2021-03-08 14:33
 */
public class _140_Word_Break_II {
    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode", new ArrayList<>(Arrays.asList("leet", "code"))));
        System.out.println(wordBreak("catsanddog", new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"))));
        System.out.println(wordBreak("pineapplepenapple", new ArrayList<>(Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"))));
    }

    /**
     * 前缀树的节点定义
     */
    public static class Node {
        // 整条路径的值
        public String path;
        public boolean end;
        public Node[] nexts;

        public Node() {
            path = null;
            end = false;
            nexts = new Node[26];
        }
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        char[] str = s.toCharArray();
        Node root = gettrie(wordDict);
        boolean[] dp = getdp(s, root);
        ArrayList<String> path = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        process(str, 0, root, dp, path, ans);
        return ans;
    }

    /**
     * 深度优先遍历
     * 根据得到的dp结果获取结果的可能值
     *
     * @param str
     * @param index 从哪个下标开始搜集
     * @param root  前缀树的根节点
     * @param dp
     * @param path  一条路径下的单词结果
     * @param ans
     */
    public static void process(char[] str, int index, Node root, boolean[] dp, ArrayList<String> path,
                               List<String> ans) {
        if (index == str.length) {
            // 如果已经到结尾了，收集答案
            ans.add(String.join(" ", path));
        } else {
            // 从整颗树的根开始对比结果
            Node cur = root;
            for (int end = index; end < str.length; end++) {
                int road = str[end] - 'a';
                // 这条路不存在结果，跳过
                if (cur.nexts[road] == null) {
                    break;
                }
                cur = cur.nexts[road];
                // 如果cur是单词的结尾，dp[end+1]又是true,那么cur就是一个有效的答案
                if (cur.end && dp[end + 1]) {
                    path.add(cur.path);
                    process(str, end + 1, root, dp, path, ans);
                    // 回溯
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    /**
     * 构建前缀树
     *
     * @param wordDict
     * @return
     */
    public static Node gettrie(List<String> wordDict) {
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
            node.path = str;
            node.end = true;
        }
        return root;
    }

    public static boolean[] getdp(String s, Node root) {
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
        return dp;
    }
}
