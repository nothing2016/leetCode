package cn.leetcode;

/**
 * 80. 删除有序数组中的重复项 II
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * <p>
 * <p>
 * 说明：
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢？
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 * <p>
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 3 * 10^4
 * -104 <= nums[i] <= 10^4
 * nums 已按升序排列
 *
 * @author oudaming
 * @date 2021-04-06 9:27
 */
public class _80_Remove_Duplicates_from_Sorted_Array_II {
    public static void main(String[] args) {
//        int[] nums1 = {1, 1, 1, 2, 2, 3};
//        int size1 = removeDuplicates(nums1);
//        printArray(nums1, size1);

        int[] nums2 = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int size2 = removeDuplicates(nums2);
        printArray(nums2, size2);

    }

    private static void printArray(int[] nums, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 2) {
            return nums.length;
        }
        int cur = nums[0];
        int curIndex = 0;
        int count = 1;
        int N = nums.length;
        for (int i = 1; i < N; i++) {
            if (nums[i] == cur) {
                count++;
                if (count <= 2) {
                    nums[++curIndex] = nums[i];
                }
            } else {
                nums[++curIndex] = nums[i];
                count = 1;
                cur = nums[i];
            }
        }
        return curIndex + 1;
    }
}
