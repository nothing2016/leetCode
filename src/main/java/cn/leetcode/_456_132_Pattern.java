package cn.leetcode;

import java.util.Stack;
import java.util.TreeMap;

/**
 * 456. 132模式
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，
 * 并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * <p>
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 * <p>
 * 进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * 示例 2：
 * <p>
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * 示例 3：
 * <p>
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * 题解：1.使用预处理leftMin 和 有序表(treeMap)处理这个问题
 * 2.使用单调栈解决
 *
 * @author oudaming
 * @date 2021-03-24 14:10
 */
public class _456_132_Pattern {
    public static void main(String[] args) {
//        System.out.println(find132pattern(new int[]{1, 2, 3, 4}));
//        System.out.println(find132pattern2(new int[]{1, 2, 3, 4}));
//
//        System.out.println(find132pattern(new int[]{3, 1, 4, 2}));
//        System.out.println(find132pattern2(new int[]{3, 1, 4, 2}));
//
//        System.out.println(find132pattern(new int[]{-1, 3, 2, 0}));
//        System.out.println(find132pattern2(new int[]{-1, 3, 2, 0}));
//
//        System.out.println(find132pattern(new int[]{-1, 1, 3, 1, 1}));
//        System.out.println(find132pattern2(new int[]{-1, 1, 3, 1, 1}));
//
//        System.out.println(find132pattern(new int[]{3, 5, 0, 3, 4}));
        System.out.println(find132pattern2(new int[]{3, 5, 0, 3, 4}));
    }

    /**
     * 时间复杂度 O(nlogn)
     *
     * @param nums
     * @return
     */
    public static boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }

        // 左侧最小值
        int leftMin = nums[0];
        // 右侧所有元素
        TreeMap<Integer, Integer> rightAll = new TreeMap<Integer, Integer>();

        for (int k = 2; k < n; ++k) {
            rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
        }

        for (int j = 1; j < n - 1; ++j) {
            if (leftMin < nums[j]) {
                Integer next = rightAll.ceilingKey(leftMin + 1);
                if (next != null && next < nums[j]) {
                    return true;
                }
            }
            // 没有找到，j往右移动，更新leftMin
            leftMin = Math.min(leftMin, nums[j]);
            // rightAll的值要减去一个
            rightAll.put(nums[j + 1], rightAll.get(nums[j + 1]) - 1);
            if (rightAll.get(nums[j + 1]) == 0) {
                rightAll.remove(nums[j + 1]);
            }
        }

        return false;
    }


    /**
     * O(N)
     * 若：a[k] < a[j]
     * 假设a[i]<a[k] 一定得到 a[i] < a[k] < a[j]
     * <p>
     * 3, 5, 0, 3, 4
     *
     * @param nums
     * @return
     */
    public static boolean find132pattern2(int[] nums) {
        int n = nums.length;
        Stack<Integer> candidateK = new Stack<>();
        // 栈：顶到底 严格小到大，需要的值放到栈底，因为现在需要找到j 右边小a[j]的最大值
        candidateK.push(nums[n - 1]);
        // 从右往左，小于a[j]的最大a[k]值
        int maxK = Integer.MIN_VALUE;

        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < maxK) {
                return true;
            }
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                maxK = candidateK.pop();
            }
            candidateK.push(nums[i]);
        }
        return false;
    }

}
