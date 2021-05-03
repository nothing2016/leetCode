package cn.leetcode;

/**
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围[[−2^31,  2^31 − 1]] ，就返回 0。
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 * <p>
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 * <p>
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 * <p>
 * 输入：x = 0
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * -2^31 <= x <= 2^31 - 1
 *
 * @author oudaming
 * @date 2021-01-27 13:55
 */
public class _7ReverseInteger {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(new _7ReverseInteger().reverse(Integer.MIN_VALUE));
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
        if (x < 0) {
            // 如果转换之后还是小于0，那么就可能是Max_min,直接返回0
            return 0;
        }
        int ans = 0;
        while (x > 0) {
            // 溢出时输出0
            // ans * 10 + x % 10 > Integer.MAX_VALUE转化而来
            if (ans > (Integer.MAX_VALUE - x % 10) / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;

        }
        return isNegative ? -ans : ans;
    }
}