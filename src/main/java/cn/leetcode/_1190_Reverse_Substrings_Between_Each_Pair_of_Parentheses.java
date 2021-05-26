package cn.leetcode;


/**
 * 1190. 反转每对括号间的子串
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 * <p>
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * <p>
 * 注意，您的结果中 不应 包含任何括号。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(abcd)"
 * 输出："dcba"
 * 示例 2：
 * <p>
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 * 示例 3：
 * <p>
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 * 示例 4：
 * <p>
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 * <p>
 * 题解： 使用递归会比使用栈简单很多，思路如下：
 * 1.定义process函数是从左括号开始到最近右括号结束返回的结果，如(ab) 返回 ba
 * 2.返回的结果定义为一个class,两个字段，一个是返回的逆序后的字符串，一个是当前结束的下标
 * 3.具体思路看代码中的注释
 */
public class _1190_Reverse_Substrings_Between_Each_Pair_of_Parentheses {
    public static void main(String[] args) {
        System.out.println(reverseParentheses("abcd"));
        System.out.println(reverseParentheses("(abcd)"));
        System.out.println(reverseParentheses("(u(love)i)"));
        System.out.println(reverseParentheses("(ed(et(oc))el)"));
        System.out.println(reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }

    public static String reverseParentheses(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        Info info = process(chars, 0);
        //因为没有判断是否有个最大的括号包住，这里需要再逆序一遍
        String ans = new StringBuilder(info.res).reverse().toString();
        return ans;
    }

    /**
     * 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
     *
     * @param str
     * @param i
     * @return
     */
    private static Info process(char[] str, int i) {
        // 当前的搜集结果
        StringBuilder curAns = new StringBuilder();
        while (i < str.length && str[i] != ')') {
            // 如果是左括号，递归调用
            if (str[i] == '(') {
                Info process = process(str, i + 1);
                curAns.append(process.res);
                i = process.index + 1;
            } else {
                // 这里就是一个字符串，所以添加到当前的结果中
                curAns.append(str[i++]);
            }
        }
        // 逆序返回结果
        String s = curAns.reverse().toString();
        Info info = new Info(s, i);
        return info;

    }

    /**
     * 封装结果的类
     */
    private static class Info {
        // 责的这一段的结果是多少
        String res;
        //负责的这一段计算到了哪个位置
        int index;

        public Info(String res, int index) {
            this.res = res;
            this.index = index;
        }
    }
}
