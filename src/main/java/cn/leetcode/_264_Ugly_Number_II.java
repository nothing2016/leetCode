package cn.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 264. 丑数 II
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * <p>
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10
 * 输出：12
 * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * 解释：1 通常被视为丑数。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 1690
 *
 * @author oudaming
 * @date 2021/4/11 0011 09:29
 */
public class _264_Ugly_Number_II {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
        System.out.println(nthUglyNumber(1690));
    }

    public static int nthUglyNumber2(int n) {
        // 这种方法会导致oom,无法申请那么大的内存
        int N = 100 - 1;
        boolean[] isUgly = new boolean[N + 1];
        isUgly[1] = true;
        for (int i = 1; i <= N; i++) {
            if (isUgly[i]) {
                if (i * 2 <= N) {
                    isUgly[i * 2] = true;
                }
                if (i * 3 <= N) {
                    isUgly[i * 3] = true;
                }
                if (i * 5 <= N) {
                    isUgly[i * 5] = true;
                }
            }
        }

        // 获取结果
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (isUgly[i]) {
                count++;
                if (count == n) {
                    return i;
                }
            }
        }
        return 0;
    }

    // 方法超时
    public static int nthUglyNumber3(int n) {
        int count = 0;
        for (int i = 1; i <= Integer.MAX_VALUE; i++) {
            if (isUgly(i)) {
                count++;
                if (count == n) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        int[] factories = new int[]{2, 3, 5};
        for (int fact : factories) {
            // 将n一直除上2,3,5,如果最后剩下1，那么就是丑数
            while (n % fact == 0) {
                n /= fact;
            }
        }
        return n == 1;
    }

    /**
     * 使用堆来求解
     *
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> set = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        set.add(1L);
        heap.add(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long cur = heap.poll();
            ugly = (int) cur;
            for (int factor : factors) {
                long next = cur * factor;
                if (!set.contains(next)) {
                    heap.add(next);
                    set.add(next);
                }
            }
        }
        return ugly;
    }
}
