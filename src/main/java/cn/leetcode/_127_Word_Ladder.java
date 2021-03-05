package cn.leetcode;

import java.util.*;

/**
 * 127. 单词接龙
 * 字典wordList 中从单词 beginWord和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * <p>
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典wordList 中的单词。
 * 给你两个单词 beginWord和 endWord 和一个字典 wordList ，找到从beginWord 到endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * 题解：此题考查的是图的宽度优先遍历，先找到每个单词的邻居，再宽度优先遍历即可
 *
 * @author oudaming
 * @date 2021-03-05 14:22
 */
public class _127_Word_Ladder {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.addAll(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        System.out.println(ladderLength(beginWord, endWord, wordList));
    }

    public static int ladderLength(String start, String to, List<String> list) {
        if (!list.contains(to)) {
            return 0;
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
                // 如果已经到达终点了,直接返回
                if (next.equals(to)) {
                    return distance + 1;
                }
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                    distanceMap.put(next, distance + 1);
                }
            }

        }
        return 0;
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
