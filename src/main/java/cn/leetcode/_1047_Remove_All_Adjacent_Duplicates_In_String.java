package cn.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * <p>
 * 给出由小写字母组成的字符串S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * <p>
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 * <p>
 * 题解：使用栈来解决这个问题
 *
 * @author oudaming
 * @date 2021-03-09 10:58
 */
public class _1047_Remove_All_Adjacent_Duplicates_In_String {
    public static void main(String[] args) {
        System.out.println(new _1047_Remove_All_Adjacent_Duplicates_In_String().removeDuplicates("abbaca"));
        System.out.println(new _1047_Remove_All_Adjacent_Duplicates_In_String().removeDuplicates("aa"));
        System.out.println(new _1047_Remove_All_Adjacent_Duplicates_In_String().removeDuplicates("abgg"));
    }

    public String removeDuplicates(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }
        int N = S.length();
        char[] chars = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        stack.push(chars[0]);
        for (int i = 1; i < N; i++) {
            if (!stack.isEmpty() && stack.peek() == chars[i]) {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        if (stack.isEmpty()) {
            return "";
        }
        List<Character> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        Collections.reverse(list);
        StringBuilder stringBuilder = new StringBuilder("");
        for (Character c : list) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
