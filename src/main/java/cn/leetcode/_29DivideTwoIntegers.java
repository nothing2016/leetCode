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
        int divide = new _29DivideTwoIntegers().divide(10, 3);
        System.out.println(divide);
        divide = new _29DivideTwoIntegers().divide(7, -3);
        System.out.println(divide);
        divide = new _29DivideTwoIntegers().divide(1, 1);
        System.out.println(divide);
        divide = new _29DivideTwoIntegers().divide(-2147483648, -1);
        System.out.println(divide);
        divide = new _29DivideTwoIntegers().divide(2, 1);
        System.out.println(divide);
        divide = new _29DivideTwoIntegers().divide(2147483647, 1);
        System.out.println(divide);
        divide = new _29DivideTwoIntegers().divide(-1, 1);
        System.out.println(divide);
        divide = new _29DivideTwoIntegers().divide(-2147483648, 1);
        System.out.println(divide);
        divide = new _29DivideTwoIntegers().divide(-2147483648, 2);
        System.out.println(divide);
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        // 结果是否是负数
        boolean isNegative = ((dividend >>> 31 & 1) ^ (divisor >>> 31 & 1)) == 1 ? true : false;
        // 负数相除溢出的情况
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // 整数的范围在[-2^31 , 2^31 -1],所以全部变成负数来除就不会溢出
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;
        int ans = process(dividend, divisor);

        return isNegative ? -ans : ans;
    }

    /**
     * a % b
     *
     * @param a 负数
     * @param b 负数
     * @return
     */
    private int process(int a, int b) {
        // 如果abs(a) < abs(b),结果肯定是0
        if (a > b) {
            return 0;
        }
        int ans = 1;
        int tmpSum = b;
        // 负数 + 负数 如果溢出，值就是正数，这里需要判断一下溢出
        while (tmpSum + tmpSum >= a && tmpSum + tmpSum < 0) {
            // 这里直接翻倍是为了加速
            ans += ans;
            tmpSum += tmpSum;
        }
        // 剩下不能翻倍的递归计算一下即可
        return ans + process(a - tmpSum, b);

    }


}
