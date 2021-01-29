package cn.leetcode;

/**
 * 29. 两数相除
 * 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数dividend除以除数divisor得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * <p>
 * 太难了，没找到方法，放弃
 *
 * @author oudaming
 * @date 2021-01-29 16:26
 */
public class _29DivideTwoIntegers {
    public static void main(String[] args) {
//        int divide = new _29DivideTwoIntegers().divide(10, 3);
//        System.out.println(divide);
//        divide = new _29DivideTwoIntegers().divide(7, -3);
//        System.out.println(divide);
//        divide = new _29DivideTwoIntegers().divide(1, 1);
//        System.out.println(divide);
//        divide = new _29DivideTwoIntegers().divide(-2147483648, -1);
//        System.out.println(divide);
////        int divide = new _29DivideTwoIntegers().divide(2, 1);
//        divide = new _29DivideTwoIntegers().divide(2147483647, 1);
//        int divide = new _29DivideTwoIntegers().divide(-1, 1);
//        int divide = new _29DivideTwoIntegers().divide(-2147483648, 1);
        int divide = new _29DivideTwoIntegers().divide(-2147483648, 2);
        System.out.println(divide);
    }

    public int divide(int dividend, int divisor) {
        // 结果是否是负数
        boolean isNegative = ((dividend >>> 31 & 1) ^ (divisor >>> 31 & 1)) == 1 ? true : false;
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
        }

        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        if (divisor > dividend) {
            return 0;
        }
        int sum = divisor;
        int ans = 0;
        while (sum <= dividend) {
            if (sum > Integer.MAX_VALUE - divisor) {
                ans++;
                break;
            }
            ans++;
            sum += divisor;
        }
        return isNegative ? -ans : ans;
    }
}
