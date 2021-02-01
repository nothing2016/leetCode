package cn.leetcode;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * 给定一个包括n 个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * <p>
 * 题解： 和三数之和是一样的逻辑，使用双指针即可
 *
 * @author oudaming
 * @date 2021-02-01 13:48
 */
public class _16_3SumClosest {
    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        System.out.println(new _16_3SumClosest().threeSumClosest(nums, -5));
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int ans = 0;
        int min = Integer.MAX_VALUE;
        nums = Arrays.stream(nums).sorted().toArray();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l != r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(target - sum) < min) {
                    min = Math.abs(target - sum);
                    ans = sum;
                }
                if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return ans;
    }
}
