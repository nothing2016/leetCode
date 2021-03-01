package cn.leetcode;

/**
 * 303. 区域和检索 - 数组不可变
 * <p>
 * 给定一个整数数组 nums，求出数组从索引i到j（i≤j）范围内元素的总和，包含i、j两点。
 * <p>
 * 实现 NumArray 类：
 * <p>
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 从索引i到j（i≤j）范围内元素的总和，包含i、j两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
 *
 * @author oudaming
 * @date 2021-03-01 10:29
 */
public class _303_Range_Sum_Query_Immutable {
}

class NumArray {
    private final int[] nums;
    private int[] sum;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.sum = new int[nums.length];
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            sum[i] = total;
        }
    }

    public int sumRange(int i, int j) {
        return sum[j] - sum[i] + nums[i];
    }
}