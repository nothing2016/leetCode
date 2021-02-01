package cn.leetcode;

/**
 * 33. 搜索旋转排序数组
 * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为[4,5,6,7,0,1,2] ）。
 * 请你在数组中搜索target ，如果数组中存在这个目标值，则返回它的索引，否则返回-1
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * <p>
 * 题解：参考官方题解，使用二分搜索，就算是旋转过的，也是有规律的。
 * 我们将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。拿示例来看，我们从 6 这个位置分开以后数组变成了 [4, 5, 6] 和 [7, 0, 1, 2] 两个部分，
 * 其中左边 [4, 5, 6] 这个部分的数组是有序的，其他也是如此。
 * 因为旋转过，所以旋转的节点就在nums[0]上，如果nums[mid] < nums[0]  => 中点在旋转数组的右半部分   否在在左半部分
 *
 * @author oudaming
 * @date 2021-02-01 15:17
 */
public class _33SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(new _33SearchInRotatedSortedArray().searchForce(nums, 2));
        System.out.println(new _33SearchInRotatedSortedArray().search(nums, 2));
        System.out.println(new _33SearchInRotatedSortedArray().searchForce(nums, 4));
        System.out.println(new _33SearchInRotatedSortedArray().search(nums, 4));
        System.out.println(new _33SearchInRotatedSortedArray().searchForce(nums, 7));
        System.out.println(new _33SearchInRotatedSortedArray().search(nums, 7));
        System.out.println(new _33SearchInRotatedSortedArray().searchForce(nums, 0));
        System.out.println(new _33SearchInRotatedSortedArray().search(nums, 0));
        System.out.println(new _33SearchInRotatedSortedArray().searchForce(nums, 6));
        System.out.println(new _33SearchInRotatedSortedArray().search(nums, 6));
        System.out.println(new _33SearchInRotatedSortedArray().searchForce(nums, 90));
        System.out.println(new _33SearchInRotatedSortedArray().search(nums, 90));
    }

    public int searchForce(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // [l,r]取值包含
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            // 如果nums[mid] < nums[0]  => 中点在旋转数组的右半部分
            if (nums[mid] < nums[0]) {
                if (target >= nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                // 中点在旋转数组的左半部分
                if (target >= nums[l] && target <= nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return -1;
    }
}
