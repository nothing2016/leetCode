package cn.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. 连续的子数组和
 * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 * <p>
 * 子数组大小 至少为 2 ，且
 * 子数组元素总和为 k 的倍数。
 * 如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [23,2,4,6,7], k = 6
 * 输出：true
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [23,2,6,4,7], k = 6
 * 输出：true
 * 解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
 * 42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
 * 示例 3：
 * <p>
 * 输入：nums = [23,2,6,4,7], k = 13
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 * <p>
 * 题解：
 * 1.求连续子数组的和，前缀和是最容易想到的，如果只用前缀和，那么时间复杂的只能到达O(N^2)，依然超时
 * 2.优化，定义sum[i]为从小标0到i的前缀和，那么如果sum[j] - sum[i] = n * k，则推导出  sum[j] % k  == sum[i] % k ,如果我们知道  j -i >=2,那么能直接返回true了
 * 3.所以我们使用map<余数，小标>来记录余数出现的位置，经过一次遍历就能得到结果，详细过程看代码
 */
public class _523_Continuous_Subarray_Sum {
    public static void main(String[] args) {
        System.out.println(checkSubarraySum2(new int[]{1, 2, 3}, 5));
//        System.out.println(checkSubarraySum(new int[]{6}, 6));
//        System.out.println(checkSubarraySum2(new int[]{6}, 6));
//        System.out.println(checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6));
//        System.out.println(checkSubarraySum2(new int[]{23, 2, 4, 6, 7}, 6));
//        System.out.println(checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 13));
//        System.out.println(checkSubarraySum2(new int[]{23, 2, 6, 4, 7}, 13));
//        System.out.println(checkSubarraySum(new int[]{5, 0, 0, 0}, 3));
//        System.out.println(checkSubarraySum2(new int[]{5, 0, 0, 0}, 3));
    }

    /**
     * 没有优化，超时
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int N = nums.length;
        int[] sum = new int[N];
        sum[0] = nums[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int temp = sum[j] - sum[i] + nums[i];
                if (temp % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkSubarraySum2(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int N = nums.length;
        // key: 前缀和与k的余数   sum[i]%k
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // 提前将余数为零的值放到map中，下标为-1
        map.put(0, -1);

        int[] sum = new int[N];
        sum[0] = nums[0];
        // 提前求出前缀和
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        for (int i = 0; i < N; i++) {
            // 余数
            int remainder = sum[i] % k;
            if (map.containsKey(remainder)) {
                if (i - map.get(remainder) >= 2) {
                    return true;
                }
            } else {
                // 保存第一次的余数的位置
                map.put(remainder, i);
            }
        }
        return false;
    }

}
