package cn.leetcode;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * @author oudaming
 * @date 2021-04-02 9:35
 */
public class _215_Kth_Largest_Element_in_an_Array {
    public int findKthLargest(int[] nums, int k) {
        // 直接找到第几小的元素即可
        return minKth(nums, nums.length + 1 - k);
    }

    /**
     * 数组中找到第几小的元素，快排改写，时间复杂度O(N)
     *
     * @param arr
     * @param k
     * @return
     */
    public static int minKth(int[] arr, int k) {
        return process(arr, 0, arr.length - 1, k - 1);
    }

    public static int process(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        // 在[L,R]中随机找到一个值作为轴点
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process(arr, L, range[0] - 1, index);
        } else {
            return process(arr, range[1] + 1, R, index);
        }
    }

    /**
     * 荷兰国旗分色问题
     * 将arr分成三段
     * 1. < pivot的
     * 2. == pivot的
     * 3. > pivot的
     * 返回的int[0] 是第一个 == pivot的下标
     * 返回的int[1] 是第最后一个 == pivot的下标
     *
     * @param arr
     * @param L
     * @param R
     * @param pivot
     * @return
     */
    public static int[] partition(int[] arr, int L, int R, int pivot) {
        // 小于pivot的最后一个小标
        int less = L - 1;
        // 大于pivot的第一个小标
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                // cur++ 的原因是因为arr[less + 1]的值是 == pivot的，所以cur需要++
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                // cur不变的原因是，arr[more -1]的值不知道是什么，需要判断一次
                swap(arr, cur, --more);
            } else {
                // arr[cur] == pivot的情况
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }
}
