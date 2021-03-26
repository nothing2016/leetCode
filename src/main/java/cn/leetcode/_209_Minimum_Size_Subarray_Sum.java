package cn.leetcode;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * <p>
 * 题解：
 * 1.滑动窗口找到最优值，如果 sum >= target, left 右滑，如果sum<target ,right 右滑动
 * 2.变量min记录最小的长度即可
 * <p>
 * 时间复杂度 O(N)
 *
 * @author oudaming
 * @date 2021-03-26 10:01
 */
public class _209_Minimum_Size_Subarray_Sum {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(minSubArrayLen(4, new int[]{1, 4, 4}));
        System.out.println(minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0 || target == 0) {
            return 0;
        }
        int N = nums.length;
        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        int tmpSum = nums[0];
        while (right < N) {
            if (tmpSum >= target) {

                min = Math.min(min, right - left + 1);
                tmpSum -= nums[left++];
            } else {
                if (right + 1 == N) {
                    break;
                }
                tmpSum += nums[++right];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
