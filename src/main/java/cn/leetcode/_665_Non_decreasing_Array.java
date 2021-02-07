package cn.leetcode;

/**
 * 665. 非递减数列
 * <p>
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * <p>
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * <p>
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 * <p>
 * 题解： 对应[4,2,3]数组，4 > 2,要使得[4,2]非递减，那么将4->2,要么将2->4，再检查非递减的性质即可
 * 所以步骤是：将num[i-1]变成num[i]，检查num[i-1]和num[i-2]的合法性，再i++继续
 *
 * @author oudaming
 * @date 2021/2/7 0007 20:19
 */
public class _665_Non_decreasing_Array {
    public static void main(String[] args) {
        System.out.println(new _665_Non_decreasing_Array().checkPossibility(new int[]{4, 2, 3}));
        System.out.println(new _665_Non_decreasing_Array().checkPossibility(new int[]{4, 2, 1}));
        System.out.println(new _665_Non_decreasing_Array().checkPossibility(new int[]{6, 7, 8, 2, 4}));
    }

    public boolean checkPossibility(int[] nums) {
        if (nums.length == 1 || nums.length == 2) {
            return true;
        }
        int dec = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                dec++;
                // 如果违反了两次，直接返回false
                if (dec > 1) {
                    return false;
                }
                // 1.先将nums[i-1] -> nums[i]
                int x = nums[i - 1];
                nums[i - 1] = nums[i];
                // 2.如果变化之后还是违反定义，那么就将nums[i]->nums[i-1]
                if (i - 2 >= 0 && nums[i - 1] < nums[i - 2]) {
                    nums[i - 1] = x;
                    nums[i] = x;
                }
                // 变化之后，再去看nums[i+1]的值
            }
        }
        return true;
    }
}
