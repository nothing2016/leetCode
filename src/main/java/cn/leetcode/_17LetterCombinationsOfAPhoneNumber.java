package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * @author oudaming
 * @date 2021-01-28 17:09
 */
public class _17LetterCombinationsOfAPhoneNumber {
    private static String[] code = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        List<String> strings = new _17LetterCombinationsOfAPhoneNumber().letterCombinations("23");
        for (String s : strings) {
            System.out.println(s);
        }
    }


    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return ans;
        }
        char[] chars = digits.toCharArray();
        List<String> str = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            str.add(code[chars[i] - '0']);
        }
        f(0, "", str, ans);
        return ans;
    }

    /**
     * f的含义是：得到当前index为开头的全排列，结果放到ans中
     * 全排列，通过str中的字符串全排列
     * 题解：当前选择的全排列的起点位置 index,item是当前组成的字符，那么就使用str[index]中的所有字符 + f(index + 1,item)的全排列即可
     *
     * @param str
     * @param ans
     */
    private void f(int index, String item, List<String> str, List<String> ans) {
        if (index == str.size()) {
            ans.add(item);
            return;
        }
        String s = str.get(index);
        for (int i = 0; i < s.length(); i++) {
            f(index + 1, item + s.charAt(i), str, ans);
        }
    }
}
