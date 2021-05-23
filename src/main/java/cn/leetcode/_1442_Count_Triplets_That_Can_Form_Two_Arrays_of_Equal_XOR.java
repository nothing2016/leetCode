package cn.leetcode;


/**
 * 1442. 形成两个异或相等数组的三元组数目
 * 给你一个整数数组 arr 。
 * <p>
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * <p>
 * a 和 b 定义如下：
 * <p>
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 * <p>
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [2,3,1,6,7]
 * 输出：4
 * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
 * 示例 2：
 * <p>
 * 输入：arr = [1,1,1,1,1]
 * 输出：10
 * 示例 3：
 * <p>
 * 输入：arr = [2,3]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：arr = [1,3,5,7,9]
 * 输出：3
 * 示例 5：
 * <p>
 * 输入：arr = [7,11,12,9,5,2,7,17,22]
 * 输出：8
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 */
public class _1442_Count_Triplets_That_Can_Form_Two_Arrays_of_Equal_XOR {
    public static void main(String[] args) {
        System.out.println(countTriplets(new int[]{2, 3, 1, 6, 7}));
        System.out.println(countTriplets(new int[]{1, 1, 1, 1, 1}));
        System.out.println(countTriplets(new int[]{2, 3}));
        System.out.println(countTriplets(new int[]{3, 4, 2}));
        System.out.println(countTriplets(new int[]{1, 3, 5, 7, 9}));
        System.out.println(countTriplets(new int[]{7, 11, 12, 9, 5, 2, 7, 17, 22}));
        System.out.println(countTriplets(new int[]{342}));
        System.out.println(countTriplets(new int[]{}));
    }

    public static int countTriplets(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) {
            return 0;
        }
        int[] xorSum = initSum(arr);
        // 其中 (0 <= i < j <= k < arr.length) 。
        int N = arr.length;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j; k < N; k++) {
                    if (getXor(i, j - 1, xorSum) == getXor(j, k, xorSum)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 初始化xor异或数组和
     */
    private static int[] initSum(int[] arr) {
        int N = arr.length;
        int[] xorSum = new int[N];
        xorSum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            xorSum[i] = xorSum[i - 1] ^ arr[i];
        }
        return xorSum;
    }

    private static int getXor(int start, int end, int[] xorSum) {
        if (start == 0) {
            return xorSum[end];
        } else {
            return xorSum[start - 1] ^ xorSum[end];
        }
    }
}
