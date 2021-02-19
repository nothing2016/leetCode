package cn.leetcode;

/**
 * 1004. 最大连续1的个数 III
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * <p>
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * <p>
 * <p>
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * <p>
 * 题解：滑动窗口求解,滑动窗口内保持有三个0在之中就可以了
 *
 * @author oudaming
 * @date 2021-02-19 9:43
 */
public class _1004_Max_Consecutive_Ones_III {
    public static void main(String[] args) {
//        int i = new _1004_Max_Consecutive_Ones_III().longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3);
//        int i = new _1004_Max_Consecutive_Ones_III().longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2);
//        int i = new _1004_Max_Consecutive_Ones_III().longestOnes(new int[]{1, 0, 0, 1, 1, 0}, 2);
        int i = new _1004_Max_Consecutive_Ones_III().longestOnes(new int[]{0, 0, 0, 1}, 4);
        System.out.println(i);
    }

    // 区间表示[l,r]
    public int longestOnes(int[] A, int K) {
        int l = 0, r = 0;
        int len = 0;
        int sum = 0;
        while (r < A.length) {
            if (sum <= K) {

                // r往右边滑动
                if (A[r] == 0) {
                    sum++;
                    // 如果是0，那么+1之后没有大于K也结算
                    if (sum <= K) {
                        len = Math.max(len, r - l + 1);
                    }
                } else {
                    // 如果是1，必须结算一次
                    len = Math.max(len, r - l + 1);
                }
                r++;
            }
            while (sum > K) {
                // l 往右滑动
                if (A[l] == 0) {
                    sum--;
                }
                l++;
            }
        }
        return len;
    }
}
