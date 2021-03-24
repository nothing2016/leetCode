package cn.leetcode;

import java.util.HashSet;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：
 * <p>
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为  1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 2^31 - 1
 *
 * @author oudaming
 * @date 2021-03-24 15:58
 */
public class _202_Happy_Number {
    public static void main(String[] args) {
//        System.out.println(isHappy(19));
//        System.out.println(isHappy(2));
//        System.out.println(isHappy(1));
        System.out.println(isHappy(7));// true
    }

    public static boolean isHappy(int n) {
        long m = (long) n;
        HashSet<Long> set = new HashSet<>();
        set.add(m);
        while (m != 1) {
            m = getPower(m);
            if (set.contains(m)) {
                return false;
            }
            set.add(m);
        }
        return true;
    }

    public static long getPower(long num) {
        long sum = 0;
        while (num != 0) {
            long k = num % 10;
            sum += k * k;
            num /= 10;
        }
        return sum;
    }
}
