package cn.leetcode;

import java.util.Arrays;

/**
 * 354. 俄罗斯套娃信封问题
 * <p>
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式(w, h)出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 说明:
 * 不允许旋转信封。
 * <p>
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 * @author oudaming
 * @date 2021-03-04 10:18
 */
public class _354_Russian_Doll_Envelopes {
    public static void main(String[] args) {
//        System.out.println(maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
//        System.out.println(maxEnvelopes(new int[][]{{1, 2}, {2, 3}}));
//        System.out.println(maxEnvelopes(new int[][]{{1, 1}}));
//        System.out.println(maxEnvelopes(new int[][]{{2, 100}, {3, 200}, {4, 300}, {5, 500}, {5, 400}, {5, 250}, {6, 370}, {6, 360}, {7, 380}}));// 5
        System.out.println(maxEnvelopes(new int[][]{{}}));// 5
//        System.out.println(maxEnvelopes(new int[][]{{17, 15}, {17, 18}, {2, 8}, {7, 2}, {17, 2}, {17, 8}, {6, 15}})); // 3
    }

    public static class Envelope {
        public int w;
        public int h;

        public Envelope(int weight, int hight) {
            w = weight;
            h = hight;
        }
    }

    public static Envelope[] getSortedEnvelopes(int[][] envelopes) {
        Envelope[] res = new Envelope[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            res[i] = new Envelope(envelopes[i][0], envelopes[i][1]);
        }
        // 如果宽度相等，高度大的排列在前面
        Arrays.sort(res, (o1, o2) -> o1.w != o2.w ? o1.w - o2.w : o2.h - o1.h);

        return res;
    }

    public static int maxEnvelopes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        Envelope[] envelopes = getSortedEnvelopes(matrix);
        // ends数组放的值是高度，从小到大，能加速最长递增子序列的求解
        int[] ends = new int[matrix.length];
        ends[0] = envelopes[0].h;
        int right = 0;// 有效值的最右范围 right]
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < envelopes.length; i++) {
            l = 0;
            r = right;
            // 找到大于envelopes[i].h的下一个位置
            while (l <= r) {
                m = (l + r) / 2;
                if (envelopes[i].h > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            // height[] 中扩展的最右的位置，l有可能是right范围之内的值
            right = Math.max(right, l);
            ends[l] = envelopes[i].h;
        }
        // right是下标，所以要返回 right + 1
        return right + 1;
    }
}
