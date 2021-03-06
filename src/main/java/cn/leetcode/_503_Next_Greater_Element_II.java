package cn.leetcode;

/**
 * 503. 下一个更大元素 II
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 *
 * @author oudaming
 * @date 2021/3/6 0006 08:36
 */
public class _503_Next_Greater_Element_II {
    public static void main(String[] args) {
//        int[] ints = new _503_Next_Greater_Element_II().nextGreaterElements(new int[]{1, 8, 9, 1});
        int[] ints = new _503_Next_Greater_Element_II().nextGreaterElements(new int[]{-1, 0});
        printArray(ints);

    }

    public static void printArray(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + ",");
        }
        System.out.println("=============");
    }

    public int[] nextGreaterElements(int[] nums) {
        int N = nums.length;
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        if (nums.length == 1) {
            return new int[]{-1};
        }
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            int j = (i + 1) % N;
            boolean isFound = false;
            while (j != i) {
                if (nums[j] > nums[i]) {
                    ans[i] = nums[j];
                    isFound = true;
                    break;
                }
                j = (j + 1) % N;
            }
            if (!isFound) {
                ans[i] = -1;
            }
        }
        return ans;
    }

}
