package cn.leetcode;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * <p>
 * 题解: 以当前的柱子a[i]为整个柱子的高度，那么只要找到左边最远一个大于等于a[i]的柱子l和右边最远一个大于等于a[i]的柱子r，这样 r-l * a[i]就是面积
 * 从左到右遍历一遍，就可以找到解。但是如何找到左(右)边远一个大于等于a[i]的柱子需要通过单调栈来加速到O(1)的时间复杂度，整个时间复杂度才能到达O(N)
 *
 * @author oudaming
 * @date 2021-03-01 17:47
 */
public class _84_Largest_Rectangle_in_Histogram {
    public int largestRectangleArea(int[] heights) {
        return 0;
    }
}
