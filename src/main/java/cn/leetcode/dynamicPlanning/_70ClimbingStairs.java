package cn.leetcode.dynamicPlanning;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * @author oudaming
 * @date 2021/1/5 0005 22:57
 */
public class _70ClimbingStairs {
    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        // 两种跳法可以达到，1,下一个阶梯跳一步，和 下下个阶梯跳两边
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 动态规划解法
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(new _70ClimbingStairs().climbStairs(10));
        System.out.println(new _70ClimbingStairs().climbStairs2(10));
    }
}
