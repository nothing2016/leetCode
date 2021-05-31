package cn.leetcode;

/**
 * 58. 最后一个单词的长度
 * 给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。
 * <p>
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "Hello World"
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：s = " "
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 */
public class _58_Length_of_Last_Word {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("hello world"));
        System.out.println(lengthOfLastWord("   "));
        System.out.println(lengthOfLastWord("jsdifj"));
    }

    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String[] s1 = s.split(" ");
        if (s1.length == 0) {
            return 0;
        }
        return s1[s1.length - 1].length();
    }
}
