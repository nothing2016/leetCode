package cn.leetcode;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * <p>
 * 题解: 以当前的柱子a[i]为整个柱子的高度，那么只要找到左边最远一个大于等于a[i]的柱子l和右边最远一个大于等于a[i]的柱子r，这样 r-l * a[i]就是面积
 * 从左到右遍历一遍，就可以找到解。但是如何找到左(右)边最远一个大于等于a[i]的柱子需要通过单调栈来加速到O(1)的时间复杂度，整个时间复杂度才能到达O(N)
 *
 * @author oudaming
 * @date 2021-03-01 17:47
 */
public class _84_Largest_Rectangle_in_Histogram {
    public static void main(String[] args) {
        System.out.println(new _84_Largest_Rectangle_in_Histogram().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(new _84_Largest_Rectangle_in_Histogram().largestRectangleArea(new int[]{1, 1, 1}));
        System.out.println(new _84_Largest_Rectangle_in_Histogram().largestRectangleArea(new int[]{1, 8, 1}));
        System.out.println(new _84_Largest_Rectangle_in_Histogram().largestRectangleArea(new int[]{999, 999, 999, 999}));
    }

    public int largestRectangleArea(int[] heights) {
        int N = heights.length;
        Stack<Integer> leftStack = new Stack<>();
        int[] left = new int[N];
        for (int i = 0; i < N; i++) {
            // 出栈
            while (!leftStack.isEmpty() && heights[i] <= heights[leftStack.peek()]) {
                left[i] = leftStack.pop();
            }
            left[i] = leftStack.isEmpty() ? -1 : leftStack.peek();
            // 入栈
            leftStack.push(i);
        }

        Stack<Integer> rightStack = new Stack<>();
        int[] right = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            // 出栈
            while (!rightStack.isEmpty() && heights[i] <= heights[rightStack.peek()]) {
                right[i] = rightStack.pop();
            }
            right[i] = rightStack.isEmpty() ? N : rightStack.peek();
            // 入栈
            rightStack.push(i);
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, heights[i] * (right[i] - left[i] - 1));
        }
        return ans;
    }
}
