package cn.leetcode;

/**
 * 525. 连续数组
 * 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。
 * <p>
 * 输入: [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 * <p>
 * 输入: [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 * <p>
 * 输入:[0,0,1,0,0,0,1,1]
 * 输出: 6
 * <p>
 * 这道题目有个特点，就是数组里的数字全都是0和1，我们可以把所有为0的数字都替换成-1，
 * 定义一个变量sum = 0，然后我们让这个变量与数组中的每个元素逐个相加，
 * 用map记录每次相加的结果及其下标，如果在后面得到某个结果(记该结果的下标为b)
 * 是map里面已经存在的值（记该值的下标为a），那么证明sum在a~b这个范围内相加的结果为0，
 * 也就是这个范围中0与1的数量相同，
 * 然后通过下标相减得到这个范围长度。接下来我们不断更新这种范围长度的最大值便可。
 *
 * @author oudaming
 * @date 2020/12/30 0030 23:21
 */
public class _525ContiguousArray {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0};
        System.out.println(new _525ContiguousArray().findMaxLength(nums));
    }

    public int findMaxLength(int[] nums) {
        return 0;
    }
}
