package cn.leetcode;

/**
 * 11. 盛最多水的容器
 * <p>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。找出其中的两条线，使得它们与x轴共同构
 * 成的容器可以容纳最多的水。
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * @author oudaming
 * @date 2021-01-27 14:49
 */
public class _11ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new _11ContainerWithMostWater().maxArea(height));
    }

    /**
     * 暴力枚举
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        int max = 0;
        int len = height.length;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                max = Math.max(max, (i - j) * Math.min(height[i], height[j]));
            }
        }
        return max;
    }

    /**
     * 加速版的，只要O(N)的时间复杂度
     * 我们思考一个问题，那就是：桶的长度越长，桶越高，那么他就越能装水
     * 现在两个指针从左右两边一起往中间缩小，在当前的情况下，如果左边height[l]高度小于右边高度height[r]，那么当前的桶以height[l]为左,height[r]为最大右边
     * 的情况肯定是最优的了，因为r左边的无论多高，整体桶的高度都只能是height[l],所以当前一定是局部最优值，想要找到下一个值的话，就l++
     *
     * @param height
     * @return
     */
    public static int maxArea2(int[] height) {
        int max = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return max;
    }
}
