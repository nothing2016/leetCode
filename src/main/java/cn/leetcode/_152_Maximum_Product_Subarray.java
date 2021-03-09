package cn.leetcode;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 题解：这个题的思路不能跟累积保持一样，因为一旦遇到0,后面的累积都为0
 * 所以我们可以考虑如下思路：
 * 1.必须以[i]位置结尾的情况
 * a.前面以[i-1]结尾的最大值是个负数，[i]是个正数，那么num[i]就是我们要找的值
 * b.前面以[i-1]结尾的最大值是个正数，[i]是个正数，那么那么num[i] * max[0,i-1]就是我们要找的值
 * c.前面以[i-1]结尾的最小值是个负数，[i]是个负数，那么那么num[i]* min[0,i-1]就是我们要找的值
 * <p>
 * 这样我们就知道，每一个[i]结尾的最大值，都要依赖[i-1]的最大值和最小值，以及num[i]本身
 *
 * @author oudaming
 * @date 2021-03-09 14:58
 */
public class _152_Maximum_Product_Subarray {
    public static void main(String[] args) {
        System.out.println(new _152_Maximum_Product_Subarray().maxProduct(new int[]{2, 3, -2}));
        System.out.println(new _152_Maximum_Product_Subarray().maxProduct(new int[]{2, 3, -2, 4, 0}));
        System.out.println(new _152_Maximum_Product_Subarray().maxProduct(new int[]{-2, 0, -1, -9}));
    }

    public int maxProduct(int[] nums) {
        int N = nums.length;
        // dpMax[i] 表示，必须以i结尾的子数组中，最大的累积是多少
        int[] dpMax = new int[N];
        // dpMax[i] 表示，必须以i结尾的子数组中，最小的累积是多少
        int[] dpMin = new int[N];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        for (int i = 1; i < N; i++) {
            dpMax[i] = Math.max(nums[i], Math.max(nums[i] * dpMax[i - 1], nums[i] * dpMin[i - 1]));
            dpMin[i] = Math.min(nums[i], Math.min(nums[i] * dpMax[i - 1], nums[i] * dpMin[i - 1]));
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, dpMax[i]);
        }
        return ans;
    }
}
