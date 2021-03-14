package cn.leetcode;

/**
 * 172. 阶乘后的零
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 * <p>
 * 题解：要计算阶乘后有几个零，只有计算有多少对2 * 5，容易证明n!中，2一定比5多
 * 所以只要求n!中因子有多少个5即可，比如25，存在5的位置是 1*5,2*5,3*5,4*5,5*5
 * 一定是有5个，后面要加上5*5中额外的5，所以就是 5 * 1 = 6个，下面代码比较巧，仔细琢磨
 *
 * @author oudaming
 * @date 2021/3/14 0014 23:37
 */
public class _172_Factorial_Trailing_Zeroes {
    public static int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
