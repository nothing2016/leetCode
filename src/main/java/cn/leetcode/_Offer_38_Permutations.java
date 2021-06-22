package cn.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * <p>
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= s 的长度 <= 8
 *
 * @author oudaming
 * @date 2021-02-18 13:53
 */
public class _Offer_38_Permutations {
    public static void main(String[] args) {
        String[] items = permutation("abcc");
        for (String item : items) {
            System.out.println(item);
        }
    }

    public static String[] permutation(String s) {
        if (s == null || s.length() == 0) {
            return new String[0];
        }
        char[] chars = s.toCharArray();
        // 保存答案的list
        List<String> list = new ArrayList<>();
        process(list, 0, chars);
        String[] ans = list.toArray(new String[list.size()]);
        return ans;
    }

    /**
     * 全排列的定义就是，对于任意位置i,它的后续节点[i+1,length -1]都有机会去i占位，也就是交换
     * 所以定义函数为：i之前的已经做好了选择，[i,length-1]等待做出选择
     * 如何去重？将i位置占过位的都记录到set中记录
     *
     * @param list
     * @param i
     * @param chars
     */
    private static void process(List<String> list, int i, char[] chars) {
        if (i == chars.length - 1) {
            list.add(new String(chars));
            return;
        }
        Set<Character> set = new HashSet<>();
        // i后面的来占位,让[i,length-1]都有到i位置的权力,注意，这里必须包含i,不然全排列中就不会包含字符串本身
        for (int j = i; j < chars.length; j++) {
            if (!set.contains(chars[j])) {
                set.add(chars[j]);
                swap(chars, i, j);
                // 现在i之前已经做好选择，等待[i+1,length-1]来做出选择
                process(list, i + 1, chars);
                swap(chars, i, j);
            }

        }
    }

    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
