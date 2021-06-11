package cn.leetcode;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * <p>
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 * <p>
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^4
 * <p>
 * 题解：一个数学定理可以帮助解决本题：「四平方和定理」。
 * <p>
 * 四平方和定理证明了任意一个正整数都可以被表示为至多四个正整数的平方和。这给出了本题的答案的上界为4。答案为{1，2，3，4}中的一个
 * 同时四平方和定理包含了一个更强的结论：当且仅当 n = 4^k * (8m+7)，答案就是4
 * 当n != 4^k * (8m+7),答案就是1，2，3
 * 3比较难判断，但是1，2比较容易判断
 * <p>
 * a*a + b*b == n时，如果a和b都不等于0，那么答案就是2，否在就是1，其余的情况就是3
 */
public class _279_Perfect_Squares {
    // 暴力解
    public static int numSquares1(int n) {
        // 结果初始化为n, 最大的个数是n个1相加，1是完全平方和
        int res = n, num = 2;
        while (num * num <= n) {
            // a 个num的平方，余数是b
            int a = n / (num * num), b = n % (num * num);
            res = Math.min(res, a + numSquares1(b));
            num++;
        }
        return res;
    }

    // 1 : 1, 4, 9, 16, 25, 36, ...
    // 4 : 7, 15, 23, 28, 31, 39, 47, 55, 60, 63, 71, ...
    // 规律解
    // 规律一：个数不超过4
    // 规律二：出现1个的时候，显而易见
    // 规律三：任何数 % 8 == 7，一定是4个
    // 规律四：任何数消去4的因子之后，剩下rest，rest % 8 == 7，一定是4个
    public static int numSquares2(int n) {
        int rest = n;
        while (rest % 4 == 0) {
            rest /= 4;
        }
        if (rest % 8 == 7) {
            return 4;
        }
        int f = (int) Math.sqrt(n);
        if (f * f == n) {
            return 1;
        }
        for (int first = 1; first * first <= n; first++) {
            int second = (int) Math.sqrt(n - first * first);
            if (first * first + second * second == n) {
                return 2;
            }
        }
        return 3;
    }

    // 数学解
    // 1）四平方和定理
    // 2）任何数消掉4的因子，结论不变
    public static int numSquares3(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        if (n % 8 == 7) {
            return 4;
        }
        for (int a = 0; a * a <= n; ++a) {
            int b = (int) Math.sqrt(n - a * a);
            if (a * a + b * b == n) {
                return (a > 0 && b > 0) ? 2 : 1;
            }
        }
        return 3;
    }

    public static void main(String[] args) {

//        for (int i = 1; i < 1000; i++) {
//            System.out.println(i + " , " + numSquares1(i));
//        }
    }
}
