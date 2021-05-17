package cn.leetcode;

import java.util.Arrays;

/**
 * 1482. 制作 m 束花所需的最少天数
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 * <p>
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 * <p>
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 * <p>
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
 * 输出：3
 * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
 * 现在需要制作 3 束花，每束只需要 1 朵。
 * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
 * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
 * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
 * 示例 2：
 * <p>
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
 * 输出：-1
 * 解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
 * 示例 3：
 * <p>
 * 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * 输出：12
 * 解释：要制作 2 束花，每束需要 3 朵。
 * 花园在 7 天后和 12 天后的情况如下：
 * 7 天后：[x, x, x, x, _, x, x]
 * 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
 * 12 天后：[x, x, x, x, x, x, x]
 * 显然，我们可以用不同的方式制作两束花。
 * 示例 4：
 * <p>
 * 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
 * 输出：1000000000
 * 解释：需要等 1000000000 天才能采到花来制作花束
 * 示例 5：
 * <p>
 * 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
 * 输出：9
 * <p>
 * <p>
 * 提示：
 * <p>
 * bloomDay.length == n
 * 1 <= n <= 10^5
 * 1 <= bloomDay[i] <= 10^9
 * 1 <= m <= 10^6
 * 1 <= k <= n
 * <p>
 * 题解：
 * 一. 如果m * k > length (数组的长度)就一定没有值
 * 二. 否则就一定有值，并且答案一定在min(array[i]) 和 max(array[i])之间
 * 如果我们从min 枚举到 max, 一定可以找到值，但是我们发现，[min,max]的区间
 * 是单调性的，也就是 [false,false,....false,true.....true,true],从false到true的
 * 这样可以使用二分来查找
 * <p>
 * 时间复杂度为 O(N log (max - min))
 *
 * @author oudaming
 * @date 2021/5/9 0009 16:52
 */
public class _1482_Minimum_Number_of_Days_to_Make_m_Bouquets {

    public static void main(String[] args) {
        System.out.println(new _1482_Minimum_Number_of_Days_to_Make_m_Bouquets().minDays(new int[]{1, 10, 2, 9, 3, 8, 4, 7, 5, 6}, 4, 2));
    }

    public int minDays(int[] bloomDay, int m, int k) {

        if (m > bloomDay.length / k) return -1; // 此时无论如何都制作不出来m束花

        // 只要 m <= bloomDay.length / k, 成立，无论如何都能制作出来
        // 调用两次stream()方法求最值的效率较低，可以使用常规方法替代！
        int low = Arrays.stream(bloomDay).min().getAsInt(); // 花开的最小天数
        int high = Arrays.stream(bloomDay).max().getAsInt(); // 花开的最大天数

        // 如果可以制作m束花，天数一定在low和high之间，因此使用二分查找
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (make(bloomDay, m, k, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;

    }

    public boolean make(int[] bloomDay, int m, int k, int days) {
        int flowers = 0; // 代表可用的花的个数
        int makeFlowers = 0; // 代表当前天数days可以制作出的花的数量
        int n = bloomDay.length;
        for (int i = 0; i < n; i++) {
            if (bloomDay[i] <= days) {
                flowers++; // 只要开花所需天数小于等于days, 则说明当前花可用
                if (flowers == k) {   // 当前花的数量满足可以制作一束花的数量k时，则制作出的花的数量makeFlowers++;并重置makeFlowers
                    makeFlowers++;
                    flowers = 0;  // 重置当前可用花的数量
                }
            } else {
                flowers = 0; // 因为需要连续的k朵花，因此只要中间有一朵花没开, flowers就重置为0
            }

        }
        return makeFlowers >= m; // 只要 makeFlowers >= m 就说明可以满足要求
    }
}
