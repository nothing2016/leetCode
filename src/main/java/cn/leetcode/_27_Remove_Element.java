package cn.leetcode;

/**
 * 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 *
 * @author oudaming
 * @date 2021/3/7 0007 17:07
 */
public class _27_Remove_Element {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
        int count = removeElement(nums, 0);
        System.out.println(count);
        print(nums);
    }

    private static void print(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println("=============");
    }

    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] == val) {
                count++;
            } else {
                nums[i - count] = nums[i];
            }
        }
        return N - count;
    }
}
