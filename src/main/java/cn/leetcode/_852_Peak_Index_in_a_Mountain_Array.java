package cn.leetcode;

/**
 * 852. 山脉数组的峰顶索引
 * <p>
 * 符合下列属性的数组 arr 称为 山脉数组 ：
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [0,1,0]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：arr = [0,2,1,0]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：arr = [0,10,5,2]
 * 输出：1
 * 示例 4：
 * <p>
 * 输入：arr = [3,4,5,1]
 * 输出：2
 * 示例 5：
 * <p>
 * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
 * 输出：2
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= arr.length <= 104
 * 0 <= arr[i] <= 106
 * 题目数据保证 arr 是一个山脉数组
 * <p>
 * 题解：使用二分方法查找，每次都往山顶走即可
 */
public class _852_Peak_Index_in_a_Mountain_Array {
    public static void main(String[] args) {
//        System.out.println(peakIndexInMountainArray(new int[]{0, 1, 0}));
//        System.out.println(peakIndexInMountainArray(new int[]{0, 2, 1, 0}));
//        System.out.println(peakIndexInMountainArray(new int[]{0, 10, 5, 2}));
//        System.out.println(peakIndexInMountainArray(new int[]{3, 4, 5, 1}));
//        System.out.println(peakIndexInMountainArray(new int[]{24, 69, 100, 99, 79, 78, 67, 36, 26, 19}));
//        System.out.println(peakIndexInMountainArray(new int[]{40, 48, 61, 75, 100, 99, 98, 39, 30, 10}));
        System.out.println(peakIndexInMountainArray(new int[]{18, 29, 38, 59, 98, 100, 99, 98, 90}));
    }

    public static int peakIndexInMountainArray(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        return process(arr);
    }

    /**
     * 二分找到山峰的山顶
     *
     * @param arr
     * @return
     */
    private static int process(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        int mid = l + (r - l) / 2;
        while (l <= r) {
            mid = l + (r - l) / 2;
            // 山顶在右边，往右边走
            if (arr[mid] < arr[mid + 1]) {
                l = mid + 1;
                // 山顶在左边，往左边走
            } else if (arr[mid] < arr[mid - 1]) {
                r = mid - 1;
            } else {
                // 现在就是山顶，直接返回
                return mid;
            }
        }
        return mid;
    }
}
