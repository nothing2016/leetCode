package cn.leetcode;

/**
 * 38. 外观数列
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 * <p>
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 第一项是数字 1
 * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
 * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
 * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
 * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
 * <p>
 * 输入：n = 4
 * 输出："1211"
 *
 * @author oudaming
 * @date 2021-02-03 9:30
 */
public class _38CountAndSay {
    public static void main(String[] args) {
        System.out.println(new _38CountAndSay().countAndSay(1));
    }

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        if (n == 2) {
            return "11";
        }
        String pre = "11";
        for (int k = 3; k <= n; k++) {
            int len = pre.length();
            int count = 1;
            StringBuilder ans = new StringBuilder("");
            for (int i = 1; i < len; i++) {
                if (pre.charAt(i) == pre.charAt(i - 1)) {
                    count++;
                } else {
                    // 结算，并清空count的值
                    ans.append("" + count + pre.charAt(i - 1));
                    count = 1;
                }
            }
            // 在结束的时候记得结算一次
            ans.append("" + count + pre.charAt(pre.length() - 1));

            pre = ans.toString();
            if (n == k) {
                return pre;
            }
        }
        return "";
    }
}
