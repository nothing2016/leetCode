package cn.leetcode;

/**
 * 69. x 的平方根
 * 实现int sqrt(int x)函数。
 * 计算并返回x的平方根，其中x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * @author oudaming
 * @date 2021-02-18 11:53
 */
public class _69_Sqrt_x_ {
    public static void main(String[] args) {
        System.out.println(new _69_Sqrt_x_().mySqrt(4));
        System.out.println(new _69_Sqrt_x_().mySqrt(1));
        System.out.println(new _69_Sqrt_x_().mySqrt(90));
        System.out.println(new _69_Sqrt_x_().mySqrt(100));
        System.out.println(new _69_Sqrt_x_().mySqrt(2147395600));
    }

    public int mySqrt(int x) {
        for (long i = 0; i <= x; i++) {
            if (i * i == x) {
                return (int) (i);
            }

            if (i * i > x) {
                return (int) (i - 1);
            }
        }
        return -1;
    }
}
