package cn.leetcode;

/**
 * 208. 实现 Trie (前缀树)
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * @author oudaming
 * @date 2021-03-26 9:36
 */
public class _208_Implement_Trie_Prefix_Tree {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));      // 返回 false
        System.out.println(trie.startsWith("app"));  // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));      // 返回 true

    }
}

class Trie {
    private Node node;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        node = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        char[] chars = word.toCharArray();
        Node cur = this.node;
        for (int i = 0; i < chars.length; i++) {
            if (cur.nexts[chars[i] - 'a'] == null) {
                Node tmpNode = new Node();
                cur.nexts[chars[i] - 'a'] = tmpNode;
            }
            cur = cur.nexts[chars[i] - 'a'];
            if (i == chars.length - 1) {
                cur.isEnd = true;
            }
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        Node cur = this.node;
        for (int i = 0; i < chars.length; i++) {
            if (cur.nexts[chars[i] - 'a'] == null) {
                return false;
            }
            cur = cur.nexts[chars[i] - 'a'];
            if (i == chars.length - 1 && cur.isEnd) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        Node cur = this.node;
        for (int i = 0; i < chars.length; i++) {
            if (cur.nexts[chars[i] - 'a'] == null) {
                return false;
            }
            cur = cur.nexts[chars[i] - 'a'];
        }
        return true;
    }

    /**
     * 前缀树的节点定义
     */
    private static class Node {
        boolean isEnd = false;
        // 通往下个字母的路径
        Node[] nexts = new Node[26];
    }
}


