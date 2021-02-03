package cn.leetcode;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水
 * <p>
 * 题解：将问题变成当前 i点能接多少雨水的问题，如果找到左边和右边的最大值，那么就有如小规律，
 * <p>
 * 雨水 = min(left_max,right_max) > a[i]? min(left_max,right_max)- a[i] : 0
 *
 * @author oudaming
 * @date 2021-02-03 16:54
 */
public class _42_Trapping_Rain_Water {
    public static void main(String[] args) {

        System.out.println(new _42_Trapping_Rain_Water().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new _42_Trapping_Rain_Water().trap(new int[]{1, 0, 2}));
        System.out.println(new _42_Trapping_Rain_Water().trap(new int[]{2}));
    }

    public int trap(int[] height) {
        // 如果没有三根柱子以上，是接不到水的
        if (height == null || height.length < 3) {
            return 0;
        }
        int N = height.length;
        // 先求当前位置右边的最大值
        int[] rightMax = new int[N];
        int max = -1;
        // rightMax[N-1] = 0
        for (int i = N - 2; i >= 0; i--) {
            max = Math.max(height[i + 1], max);
            rightMax[i] = max;
        }
        // 每个位置能接的水量
        // 我们知道，第一个位置和最后一个位置是接不到雨的
        int leftMax = height[0];
        int totalWater = 0;
        for (int i = 1; i <= N - 2; i++) {
            leftMax = Math.max(leftMax, height[i - 1]);
            int min = Math.min(leftMax, rightMax[i]);
            totalWater += (min > height[i] ? min - height[i] : 0);
        }
        return totalWater;
    }
}
