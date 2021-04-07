package cn.leetcode;

/**
 * 81. 搜索旋转排序数组 II
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 * <p>
 * 示例1：
 * <p>
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 * 示例2：
 * <p>
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 * <p>
 * 思路：
 * 对于数组中有重复元素的情况，二分查找时可能会有 a[l]=a[mid]=a[r]，此时无法判断区间 [l,mid] 和区间 [mid+1,r] 哪个是有序的。
 * 例如 nums=[3,1,2,3,3,3,3]，target=2，首次二分时无法判断区间[0,3] 和区间 [4,6] 哪个是有序的。
 * 对于这种情况，我们只能将当前二分区间的左边界加一，右边界减一，然后在新区间上继续二分查找。
 * <p>
 * 使用二分搜索，就算是旋转过的，也是有规律的。
 * 我们将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。拿示例来看，我们从 6 这个位置分开以后数组变成了 [4, 5, 6] 和 [7, 0, 1, 2] 两个部分，
 * 其中左边 [4, 5, 6] 这个部分的数组是有序的，其他也是如此。
 * 因为旋转过，所以旋转的节点就在nums[0]上，如果nums[mid] < nums[0]  => 中点在旋转数组的右半部分   否在在左半部分
 *
 * @author oudaming
 * @date 2021-04-07 9:44
 */
public class _81_Search_in_Rotated_Sorted_Array_II {
    public static void main(String[] args) {
        int[] nums = {2, 5, 6, 0, 0, 1, 2};
        System.out.println(searchForce(nums, 0));
        System.out.println(search(nums, 0));
        int[] nums2 = {2, 5, 6, 0, 0, 1, 2};
        System.out.println(searchForce(nums2, 3));
        System.out.println(search(nums2, 3));

        int[] nums3 = {1, 0, 1, 1, 1};
        System.out.println(searchForce(nums3, 0));
        System.out.println(search(nums3, 0));
    }

    public static boolean searchForce(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return nums[0] == target;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return true;
            }
            // 这种情况无法判断哪一部分有序，只能将相等的同时去掉
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                ++l;
                --r;
            } else if (nums[mid] < nums[l]) { // 中点在旋转数组的右半部分, [mid,r]有序
                if (target >= nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else { // 中点在旋转数组的左半部分, [l,mid]有序
                if (target >= nums[l] && target <= nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return false;
    }
}
