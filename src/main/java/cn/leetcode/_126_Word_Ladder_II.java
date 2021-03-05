package cn.leetcode;

import java.util.*;

/**
 * 126. 单词接龙 II
 * 字典wordList 中从单词 beginWord和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * <p>
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典wordList 中的单词。
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * <p>
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * 输出:
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 *
 * @author oudaming
 * @date 2021-03-05 14:22
 */
public class _126_Word_Ladder_II {
    public static void main(String[] args) {
//        String beginWord = "hit";
//        String endWord = "cog";
        String beginWord = "a";
        String endWord = "c";
        List<String> wordList = new ArrayList<>();
//        wordList.addAll(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        wordList.addAll(Arrays.asList("a", "b", "c"));
        System.out.println(new _126_Word_Ladder_II().findLadders(beginWord, endWord, wordList));
    }

    public List<List<String>> findLadders(String start, String to, List<String> list) {
        int minPath = Integer.MAX_VALUE;
        if (!list.contains(to)) {
            return new ArrayList<>();
        }
        // 将开始位置也放到这个图里来
        list.add(start);
        HashMap<String, ArrayList<String>> nexts = getNexts(list);
        // 从开始节点到key节点的距离
        HashMap<String, Integer> distanceMap = new HashMap<>();
        distanceMap.put(start, 1);
        // 记录节点是否访问过
        HashSet<String> visited = new HashSet<>();
        visited.add(start);
        // 使用队列宽度优先遍历
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            Integer distance = distanceMap.get(cur);
            for (String next : nexts.get(cur)) {

                if (!visited.contains(next)) {
                    // 如果已经到达终点了,直接返回
                    if (next.equals(to)) {
                        minPath = distance + 1;
                    }
                    visited.add(next);
                    queue.add(next);
                    distanceMap.put(next, distance + 1);
                }
            }
        }

        if (minPath == Integer.MAX_VALUE) {
            // 没有找到结果
            return new ArrayList<>();
        }
        List<List<String>> ans = new ArrayList<>();
        List<String> item = new ArrayList<>();
        dfs(start, item, nexts, to, distanceMap, ans, minPath);
        return ans;
    }

    /**
     * 使用深度优先遍历搜集结果
     *
     * @param start
     * @param item
     * @param distanceMap
     * @param ans
     */
    private void dfs(String start, List<String> item, HashMap<String, ArrayList<String>> nexts, String to, HashMap<String, Integer> distanceMap, List<List<String>> ans, int minPath) {
        ArrayList<String> temp = new ArrayList<>(item);
        temp.add(start);
        if (distanceMap.getOrDefault(start, -1) == minPath && start.equals(to)) {
            ans.add(temp);
            return;
        }
        ArrayList<String> strings = nexts.get(start);
        for (String s : strings) {
            if (distanceMap.get(start) + 1 == distanceMap.get(s)) {
                ArrayList<String> temp2 = new ArrayList<>(item);
                temp2.add(start);
                dfs(s, temp2, nexts, to, distanceMap, ans, minPath);
            }
        }
    }


    /**
     * 获取到key这个单词的邻居们
     *
     * @param words
     * @return
     */
    public static HashMap<String, ArrayList<String>> getNexts(List<String> words) {
        HashSet<String> dict = new HashSet<>(words);
        HashMap<String, ArrayList<String>> nexts = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            nexts.put(words.get(i), getNext(words.get(i), dict));
        }
        return nexts;
    }

    // 应该根据具体数据状况决定用什么来找邻居
    // 1)如果字符串长度比较短，字符串数量比较多，以下方法适合
    // 2)如果字符串长度比较长，字符串数量比较少，以下方法不适合
    public static ArrayList<String> getNext(String word, HashSet<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char[] chs = word.toCharArray();
        for (char cur = 'a'; cur <= 'z'; cur++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] != cur) {
                    char tmp = chs[i];
                    chs[i] = cur;
                    if (dict.contains(String.valueOf(chs))) {
                        res.add(String.valueOf(chs));
                    }
                    chs[i] = tmp;
                }
            }
        }
        return res;
    }
}
