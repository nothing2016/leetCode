package cn.leetcode;

/**
 * 231. 2 的幂
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 1
 * 输出：true
 * 解释：20 = 1
 * 示例 2：
 * <p>
 * 输入：n = 16
 * 输出：true
 * 解释：24 = 16
 * 示例 3：
 * <p>
 * 输入：n = 3
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：n = 4
 * 输出：true
 * 示例 5：
 * <p>
 * 输入：n = 5
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * -2^31 <= n <= 2^31 - 1
 * 进阶：你能够不使用循环/递归解决此问题吗？
 * <p>
 * 题解： 如果是2的次方的话，那么n的二进制表示中，只有一个1，而且负数一定不符合条件，
 * <p>
 * 00100    n = 4
 * 00011    n = (4 -1) = 3
 * 00000   与的结果为0就表示n是2的次方
 */
public class _231_Power_of_Two {

    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }
}
