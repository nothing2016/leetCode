package cn.leetcode;

/**
 * 26. 删除排序数组中的重复项
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 给定数组 nums = [1,1,2],
 * <p>
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 题解：将一个数组成两个数组来使用，使用两个指针，一个是新数组需要填的位置，一个表示老数据当前的数据是否要填充到新数组中即可
 *
 * @author oudaming
 * @date 2021-01-29 15:20
 */
public class _26RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
//        int[] nums = {1, 1, 2};
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int len = new _26RemoveDuplicatesFromSortedArray().removeDuplicates(nums);
        System.out.println("新长度为：" + len);
        for (int item : nums) {
            System.out.println(item);
        }
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 当前的cur是需要copy还是跳过？
        int cur = 1;
        // 新数组的长度
        int newLen = 1;
        while (cur < nums.length) {
            if (nums[cur] == nums[newLen - 1]) {
                cur++;
            } else {
                nums[newLen++] = nums[cur++];
            }
        }
        return newLen;
    }
}
