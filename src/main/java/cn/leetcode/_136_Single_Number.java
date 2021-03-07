package cn.leetcode;

/**
 * 136. 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * @author oudaming
 * @date 2021/3/7 0007 12:32
 */
public class _136_Single_Number {
    public static void main(String[] args) {
        System.out.println(new _136_Single_Number().singleNumber(new int[]{2, 2, 1}));
        System.out.println(new _136_Single_Number().singleNumber(new int[]{4, 1, 2, 1, 2}));
    }

    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int eor = 0;
        for (int i = 0; i < nums.length; i++) {
            eor ^= nums[i];
        }
        return eor;
    }
}
