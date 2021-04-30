package cn.leetcode;

/**
 * 137. 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 * <p>
 * <p>
 * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 题解：每一位相加，然后每一位余3得到的就是结果
 *
 * @author oudaming
 * @date 2021-04-30 9:57
 */
public class _137_Single_Number_II {
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 3, 2}));
        System.out.println(singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }

    public static int singleNumber(int[] nums) {
        int ans = 0;
        int ci = 1;
        for (int i = 0; i < 32; i++) {
            int bit = 0;
            for (int j = 0; j < nums.length; j++) {
                // 获取每个数的最后一位
                bit += (nums[j] >> i & 1);
            }
            bit %= 3;

            // 将得到的位数变成10进制
            ans = ans + bit * ci;
            ci *= 2;
        }
        return ans;
    }
}
