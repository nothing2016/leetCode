package cn.leetcode;


/**
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * <p>
 * 题解： 如  6^5 = 6^4 * 6^2   其实拿5变成二进制就是101 就是取为1的位数，使用二进制的位运算即可，但是要注意这里有负数，负数直接变成正数会溢出的
 *
 * @author oudaming
 * @date 2021-02-19 14:18
 */
public class _50_Pow_x_n_ {
    public static void main(String[] args) {
        System.out.println(new _50_Pow_x_n_().myPow(2, 5));
        System.out.println(new _50_Pow_x_n_().myPow(2, -2));
        System.out.println(new _50_Pow_x_n_().myPow(2.10000, 3));
        System.out.println(new _50_Pow_x_n_().myPow(2.00000, -2));
        System.out.println(new _50_Pow_x_n_().myPow(2.00000, -2147483648));
    }

    public double myPow(double x, int n) {
        long m = n;
        return m > 0 ? f(x, m) : 1 / f(x, -m);
    }

    private double f(double x, long n) {
        double ans = 1;
        while (n > 0) {
            // 取到最后一个1
            long lastBit = n % 2;
//            long lastBit = n & 1;
            if (lastBit == 1) {
                ans *= x;
            }
//            n = n >> 1;
            n /= 2;
            x *= x;
        }
        return ans;
    }

}
