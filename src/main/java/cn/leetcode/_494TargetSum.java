package cn.leetcode;

/**
 * 494. 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号+和-。对于数组中的任意一个整数，你都可以从+或-中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 * <p>
 * 思路：转换成背包问题的要和不要
 *
 * @author oudaming
 * @date 2021-01-06 18:19
 */
public class _494TargetSum {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        System.out.println(new _494TargetSum().findTargetSumWays(nums, S));
    }

    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return process(nums, 0, S);
    }

    private int process(int[] nums, int index, int rest) {
        if (index == nums.length) {
            // 到了最后一位，如果剩余的空间还不为零，那么就没有解，为零就有解
            return rest == 0 ? 1 : 0;
        }
        // 当前的index的数使用+ 号
        int p1 = process(nums, index + 1, rest + nums[index]);
        int p2 = process(nums, index + 1, rest - nums[index]);
        return p1 + p2;
    }
}
