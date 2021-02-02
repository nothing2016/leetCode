package cn.leetcode;

/**
 * 35.搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * <p>
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * <p>
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * <p>
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * <p>
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * @author oudaming
 * @date 2021-02-02 16:31
 */
public class _35SearchInsertPosition {
    public static void main(String[] args) {
        System.out.println(new _35SearchInsertPosition().searchInsert(new int[]{1, 3, 5, 6}, 5));
        System.out.println(new _35SearchInsertPosition().searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(new _35SearchInsertPosition().searchInsert(new int[]{1, 3, 5, 6}, 7));
        System.out.println(new _35SearchInsertPosition().searchInsert(new int[]{1, 3, 5, 6}, 0));

    }

    /**
     * 1)二分找到第一个大于target的下标rightIndex
     * 2)判断前一个是否等于target,等于就直接返回rightIndex-1，否则返回rightIndex
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int rightIndex = binarySearchGreatFirst(nums, target);
        if (rightIndex != 0 && target == nums[rightIndex - 1]) {
            return rightIndex - 1;
        }
        return rightIndex;

    }

    /**
     * 1)二分找到第一个大于target的下标rightIndex
     * 从右边下标找过来，有一个就更新一个，一定就能找到第一个
     * 这里找到时不用直接返回，需要二分到自然越界
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearchGreatFirst(int[] nums, int target) {
        int r = nums.length - 1;
        // 这里赋值有点巧妙，比如[1,2,3,4,5,6,7],想要找到大于7的第一个值，就是下标为7(nums.length)的虚拟值
        int rightIndex = nums.length;
        int l = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (target < nums[mid]) {
                rightIndex = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return rightIndex;
    }
}
