package cn.leetcode.dynamicPlanning;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 输入: 121
 * 输出: true
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * @author oudaming
 * @date 2020-12-31 10:00
 */
public class _9PalindromeNumber {
    public static void main(String[] args) {
        System.out.println(new _9PalindromeNumber().isPalindrome(-121));
    }

    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        String reverse = new StringBuilder(s).reverse().toString();
        return s.equals(reverse);
    }
}
