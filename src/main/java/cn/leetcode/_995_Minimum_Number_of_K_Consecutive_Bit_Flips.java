package cn.leetcode;

/**
 * 995. K 连续位的最小翻转次数
 * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
 * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
 * <p>
 * 输入：A = [0,0,0,1,0,1,1,0], K = 3
 * 输出：3
 * 解释：
 * 翻转 A[0],A[1],A[2]:A变成 [1,1,1,1,0,1,1,0]
 * 翻转 A[4],A[5],A[6]:A变成 [1,1,1,1,1,0,0,0]
 * 翻转 A[5],A[6],A[7]:A变成 [1,1,1,1,1,1,1,1]
 * <p>
 * 题解：由于对同一个子数组执行两次翻转操作不会改变该子数组，所以对每个长度为 K 的子数组，应至多执行一次翻转操作
 * 对于若干个 K 位翻转操作，改变先后顺序并不影响最终翻转的结果。不妨从 A[0] 开始考虑，若 A[0]=0，则必定要翻转从位置 0 开始的子数组；
 * 若 A[0]=1，则不翻转从位置 0 开始的子数组。
 * 按照这一策略，我们从左到右地执行这些翻转操作。由于翻转操作是唯一的，若最终数组元素均为 1，则执行的翻转次数就是最小的。
 * <p>
 * 用 N 表示数组 A 的长度。若直接模拟上述过程，复杂度将会是 O(NK) 的。如何优化呢？使用差分数组
 * 这启发我们用差分数组的思想来计算当前数字需要翻转的次数。我们可以维护一个差分数组diff，其中 diff[i] 表示两个相邻元素 A[i−1] 和 A[i] 的翻转次数的差(即[i]位置反转次数 - [i-1]反转次数的值)
 * 对于区间 [l,r]，将其元素全部加 1，只会影响到 l 和 r+1 处的差分值，故 diff[l] 增加 1，diff[r+1] 减少 1。
 * <p>
 * 通过累加差分数组可以得到当前位置需要翻转的次数，我们用变量 revCnt 来表示这一累加值。因为diff[r + 1]的值会是负数，所以反转次数一定是对的
 * 遍历到 A[i] 时，若 A[i]+revCnt 是偶数，则说明当前元素的实际值为 0，需要翻转区间 [i,i+K-1]，我们可以直接将 revCnt 增加 1，diff[i+K] 减少 1。
 * <p>
 * 注意到若 i+K>n 则无法执行翻转操作，此时应返回 -1。
 *
 * @author oudaming
 * @date 2021-02-18 10:09
 */
public class _995_Minimum_Number_of_K_Consecutive_Bit_Flips {
    public static void main(String[] args) {
        int[] A = {0, 0, 0, 1, 0, 1, 1, 0};
//        System.out.println(new _995_Minimum_Number_of_K_Consecutive_Bit_Flips().minKBitFlips(A, 1));
//        System.out.println(new _995_Minimum_Number_of_K_Consecutive_Bit_Flips().minKBitFlips(A, 2));
        System.out.println(new _995_Minimum_Number_of_K_Consecutive_Bit_Flips().minKBitFlips(A, 3));
//        System.out.println(new _995_Minimum_Number_of_K_Consecutive_Bit_Flips().minKBitFlips(A, 4));
    }

    public int minKBitFlips(int[] A, int K) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int N = A.length;
        // 差分数组
        int[] diff = new int[N + 1];
        int revCnt = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            // 通过累加差分数组可以得到当前位置需要翻转的次数
            revCnt += diff[i];
            if ((revCnt + A[i]) % 2 == 0) {
                // 需要反转
                if (i + K > N) {
                    // 但是已经反转不了了
                    return -1;
                }
                ans++;
                // 这里需要++，因为只有满足条件的才能进入到这里来，不是每次for循环都会++
                revCnt++;
                diff[i]++;
                diff[i + K]--;
            }

        }
        return ans;
    }
}
