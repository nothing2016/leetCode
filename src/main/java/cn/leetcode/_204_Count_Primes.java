package cn.leetcode;

import java.util.Arrays;

/**
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：0
 * <p>
 * 提示：
 * 0 <= n <= 5 * 10^6
 * <p>
 * 题解：如果一个数是质数，那么它的倍数一定不是质素，这样通过，2、3可以推导出所有不是质素的数，用数组储存即可
 * 时间复杂度O(N),空间复杂度O(N)
 *
 * @author oudaming
 * @date 2021-03-24 16:13
 */
public class _204_Count_Primes {
    public static void main(String[] args) {
        System.out.println(countPrimes2(10));
//        System.out.println(countPrimes(20));
//        System.out.println(countPrimes(1));
    }

    // 时间复杂度O(N spart(N)) N 根号N
    public static int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    public static boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 先把所有的数都当成素数，然后将素数的倍数变成非素数即可
     *
     * @param n
     * @return
     */
    public static int countPrimes2(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= n; ++i) {
            // 如果一个数是质数，那么它的倍数一定不是质素
            if (isPrime[i]) {
                int k = 2;
                while (k * i <= n) {
                    isPrime[i * k++] = false;
                }
            }
        }
        int ans = 0;
        for (int i = 2; i < n; i++) {
            ans += isPrime[i] ? 1 : 0;
        }
        return ans;
    }

}
