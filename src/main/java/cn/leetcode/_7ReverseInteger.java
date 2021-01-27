package cn.leetcode;

/**
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围[[−231,  231 − 1]] ，就返回 0。
 *
 * @author oudaming
 * @date 2021-01-27 13:55
 */
public class _7ReverseInteger {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(new _7ReverseInteger().reverse(-90));
        System.out.println(new _7ReverseInteger().reverse(123));
        System.out.println(new _7ReverseInteger().reverse(0));
        System.out.println(new _7ReverseInteger().reverse(-1));
        System.out.println(new _7ReverseInteger().reverse(1534236469));
    }

    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        // 是否是负数
        boolean isNegative = (x >>> 31 & 1) == 1;
        // 统一变成正数处理
        x = isNegative ? -x : x;
        int ans = 0;
        while (x > 0) {
            // 溢出时输出0
            if (ans > (Integer.MAX_VALUE - x % 10) / 10) {
                return 0;
            } else {
                ans = ans * 10 + x % 10;
                x = x / 10;
            }
        }
        return isNegative ? -ans : ans;
    }
}