package cn.leetcode;

/**
 * 91. 解码方法
 * 一条包含字母A-Z 的消息通过以下映射进行了 编码 ：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。
 * 例如，"111" 可以将 "1" 中的每个 "1" 映射为 "A" ，从而得到 "AAA" ，或者可以将 "11" 和 "1"（分别为 "K" 和 "A" ）映射为 "KA" 。
 * 注意，"06" 不能映射为 "F" ，因为 "6" 和 "06" 不同。
 * 给你一个只含数字的 非空 字符串 num ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * <p>
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 不能映射到 "F" ，因为字符串开头的 0 无法指向一个有效的字符。
 *
 * @author oudaming
 * @date 2021-03-02 14:04
 */
public class _91_Decode_Ways {
    public static void main(String[] args) {
        System.out.println(new _91_Decode_Ways().numDecodings("12"));
        System.out.println(new _91_Decode_Ways().numDecodings2("12"));
        System.out.println(new _91_Decode_Ways().numDecodings("226"));
        System.out.println(new _91_Decode_Ways().numDecodings2("226"));
        System.out.println(new _91_Decode_Ways().numDecodings("27"));
        System.out.println(new _91_Decode_Ways().numDecodings2("27"));
        System.out.println(new _91_Decode_Ways().numDecodings("111111111111111111111111111111111111111111111"));
        System.out.println(new _91_Decode_Ways().numDecodings2("111111111111111111111111111111111111111111111"));
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        return process(chars, 0);
    }

    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        return dpWays(chars);
    }

    /**
     * 当前来到i位置
     *
     * @param str
     * @param index
     * @return
     */
    // 潜台词：str[0...index-1]已经转化完了，不用操心了
    // str[index....] 能转出多少有效的，返回方法数
    public static int process(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }
        // index作为第一位时，不能为0
        if (str[index] == '0') {
            return 0;
        }
        // index还有字符, 又不是‘0’
        // 1) （index 1 ~ 9）
        int ways = process(str, index + 1);
        // 2) 如果 index到了最后一位，就没有index作为10位，index + 1作为个位的情况了，直接返回
        if (index + 1 == str.length) {
            return ways;
        }
        // (index index + 1) "23" -> 23 "17" -> 17
        int num = (str[index] - '0') * 10 + str[index + 1] - '0';
        // num > 26，才是有效的转换
        if (num <= 26) {
            ways += process(str, index + 2);
        }
        return ways;
    }

    public static int dpWays(char[] str) {
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] == '0') {
                dp[i] = 0;
                continue;
            }
            dp[i] = dp[i + 1];
            if (i + 1 == N) {
                continue;
            } else {
                int num = (str[i] - '0') * 10 + str[i + 1] - '0';
                if (num <= 26) {
                    dp[i] = dp[i + 1] + dp[i + 2];
                }
            }
        }
        return dp[0];
    }


}
