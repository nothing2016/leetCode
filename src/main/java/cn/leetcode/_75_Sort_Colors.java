package cn.leetcode;

import static cn.leetcode._88_Merge_Sorted_Array.printArray;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * <p>
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * <p>
 * 输入：nums = [0]
 * 输出：[0]
 *
 * @author oudaming
 * @date 2021-02-26 14:46
 */
public class _75_Sort_Colors {
    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        printArray(nums);
    }

    public static void sortColors(int[] nums) {
        netherlandsFlag(nums, 0, nums.length - 1, 1);
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    //  <arr[R]  ==arr[R]  > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R, int partitionVal) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1; // < 区 左边界
        int more = R + 1;     // > 区 右边界
        int index = L;
        while (index < more) {
            if (arr[index] == partitionVal) {
                index++;
            } else if (arr[index] < partitionVal) {
                swap(arr, index++, ++less);
            } else { // >
                swap(arr, index, --more);
            }
        }
//        System.out.println((less + 1) + "," + (more - 1));
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
