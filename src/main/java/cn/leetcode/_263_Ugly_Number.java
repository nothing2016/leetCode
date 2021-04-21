package cn.leetcode;

/**
 * 263. 丑数
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 6
 * 输出：true
 * 解释：6 = 2 × 3
 * 示例 2：
 * <p>
 * 输入：n = 8
 * 输出：true
 * 解释：8 = 2 × 2 × 2
 * 示例 3：
 * <p>
 * 输入：n = 14
 * 输出：false
 * 解释：14 不是丑数，因为它包含了另外一个质因数 7 。
 * 示例 4：
 * <p>
 * 输入：n = 1
 * 输出：true
 * 解释：1 通常被视为丑数。
 * <p>
 * <p>
 * 提示：
 * <p>
 * -2^31 <= n <= 2^31 - 1
 *
 * @author oudaming
 * @date 2021/4/10 0010 22:46
 */
public class _263_Ugly_Number {
    public static void main(String[] args) {
        System.out.println(isUgly(4));
        System.out.println(isUgly(8));
        System.out.println(isUgly(14));
        System.out.println(isUgly(1));
    }

    public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        int[] factories = new int[]{2, 3, 5};
        for (int fact : factories) {
            // 将n一直除上2,3,5,如果最后剩下1，那么就是丑数
            while (n % fact == 0) {
                n /= fact;
            }
        }
        return n == 1;
    }
}
