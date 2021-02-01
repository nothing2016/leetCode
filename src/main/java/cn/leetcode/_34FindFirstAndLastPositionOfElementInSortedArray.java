package cn.leetcode;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 * <p>
 * 进阶：
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 题解： 使用二分搜索log(N)到下标的位置，然后通过当前位置上下几个位置找到第一个位置和最后一个位置(常数时间)
 *
 * @author oudaming
 * @date 2021-02-01 16:24
 */
public class _34FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
//        int[] nums = {5, 7, 7, 8, 8, 10};
//        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int[] nums = {7, 7, 7, 7, 7, 7, 7};
//        printArray(new _34FindFirstAndLastPositionOfElementInSortedArray().searchRange2(nums, 7));
        printArray(new _34FindFirstAndLastPositionOfElementInSortedArray().searchRange(nums, 7));
//        printArray(new _34FindFirstAndLastPositionOfElementInSortedArray().searchRange(nums, 7));
//        printArray(new _34FindFirstAndLastPositionOfElementInSortedArray().searchRange(nums, 5));
//        printArray(new _34FindFirstAndLastPositionOfElementInSortedArray().searchRange(nums, 10));

    }

    private static void printArray(int[] ints) {
        if (ints != null) {
            for (int i : ints) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }

    /**
     * 如果是[1,1,1,1,1,1,1,1]这种情况，会蜕变成O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int index = search(nums, target);
        if (index == -1) {
            return new int[]{-1, -1};
        }
        int l = index;
        int r = index;
        while (l >= 0) {
            if (l - 1 >= 0 && nums[l - 1] == target) {
                l--;
            } else {
                break;
            }
        }
        while (r <= nums.length - 1) {
            if (r + 1 <= nums.length - 1 && nums[r + 1] == target) {
                r++;
            } else {
                break;
            }
        }

        return new int[]{l, r};
    }

    private int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int mid = 0;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            if (target >= nums[l] && target <= nums[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 真正的log(n)级别，因为两次都是log(n),也就是2*log(n),忽略常数项，还是log(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearchEqFirst(nums, target);
        int rightIdx = binarySearchGreatFirst(nums, target) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    /**
     * 二分找到第一个等于target的下标
     * 从右边下标找过来，有一个就更新一个，一定就能找到第一个
     * 这里找到时不用直接返回，需要二分到自然越界
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearchEqFirst(int[] nums, int target) {
        int r = nums.length - 1;
        int rightIndex = nums.length;
        int l = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (target <= nums[mid]) {
                rightIndex = mid;
                r--;
            } else {
                l++;
            }
        }
        return rightIndex;
    }

    /**
     * 二分找到第一个大于target的下标
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
                r--;
            } else {
                l++;
            }
        }
        return rightIndex;
    }


}
