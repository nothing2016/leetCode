package cn.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 697. 数组的度
 * 给定一个非空且只包含非负数的整数数组nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在 nums 中找到与nums拥有相同大小的度的最短连续子数组，返回其长度。
 * <p>
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 *
 * @author oudaming
 * @date 2021-02-20 9:37
 */
public class _697_Degree_of_an_Array {
    public static void main(String[] args) {
//        System.out.println(new _697_Degree_of_an_Array().findShortestSubArray(new int[]{1, 2, 2, 3, 1}));
//        System.out.println(new _697_Degree_of_an_Array().findShortestSubArray(new int[]{1, 2, 2, 3, 1, 4, 2}));
        System.out.println(new _697_Degree_of_an_Array().findShortestSubArray(new int[]{1, 2, 3, 4}));
    }

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> firstPosition = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        int maxCount = 1;
        int len = Integer.MAX_VALUE;
        // 先找到这个度
        for (int i = 0; i < nums.length; i++) {
            if (!firstPosition.containsKey(nums[i])) {
                firstPosition.put(nums[i], i);
            }

            if (count.containsKey(nums[i])) {
                count.put(nums[i], count.get(nums[i]) + 1);
                if (count.get(nums[i]) + 1 > maxCount) {
                    maxCount = count.get(nums[i]);
                }
            } else {
                count.put(nums[i], 1);
            }
        }
        // 得到度之后，在找到度的最小长度
        count.clear();
        for (int i = 0; i < nums.length; i++) {
            if (count.containsKey(nums[i])) {
                count.put(nums[i], count.get(nums[i]) + 1);
            } else {
                count.put(nums[i], 1);
            }
            if (count.get(nums[i]) == maxCount) {
                len = Math.min(len, i - firstPosition.get(nums[i]) + 1);
            }
        }
        return len;
    }
}
