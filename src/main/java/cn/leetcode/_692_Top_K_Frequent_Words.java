package cn.leetcode;

import java.util.*;

/**
 * 692. 前K个高频单词
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * <p>
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * <p>
 * 示例 1：
 * <p>
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * 注意，按字母顺序 "i" 在 "love" 之前。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 * 出现次数依次为 4, 3, 2 和 1 次。
 * <p>
 * <p>
 * 注意：
 * <p>
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 * <p>
 * <p>
 * 扩展练习：
 * <p>
 * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 */
public class _692_Top_K_Frequent_Words {

    public static void main(String[] args) {
        List<String> strings = topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 1);
        strings.forEach(e -> System.out.println(e));
    }

    /**
     * 时间O(n log k)， 空间O(n)
     *
     * @param words
     * @param k
     * @return
     */
    public static List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0 || k == 0) {
            return new ArrayList<>();
        }
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }


        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> {
            if (a.count == b.count) {
                return b.word.compareTo(a.word);
            } else {
                return a.count - b.count;
            }
        });

        for (String key : countMap.keySet()) {
            if (queue.size() < k) {
                queue.add(new Node(key, countMap.get(key)));
            } else {
                if (countMap.get(key) > queue.peek().count) {
                    queue.poll();
                    queue.add(new Node(key, countMap.get(key)));
                } else if (countMap.get(key) == queue.peek().count && key.compareTo(queue.peek().word) < 0) {
                    queue.poll();
                    queue.add(new Node(key, countMap.get(key)));
                }
            }
        }
        List<String> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            ans.add(queue.poll().word);
        }

        Collections.reverse(ans);
        return ans;
    }

    private static class Node {
        String word;
        Integer count;

        public Node(String word, Integer count) {
            this.word = word;
            this.count = count;
        }
    }
}
