package cn.leetcode;

/**
 * 1035. 不相交的线
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 * <p>
 * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
 * <p>
 * nums1[i] == nums2[j]
 * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 * <p>
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums1 = [1,4,2], nums2 = [1,2,4]
 * 输出：2
 * 解释：可以画出两条不交叉的线，如上图所示。
 * 但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
 * 示例 2：
 * <p>
 * 输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 * 输出：2
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length <= 500
 * 1 <= nums2.length <= 500
 * 1 <= nums1[i], nums2[i] <= 2000
 * <p>
 * 题解： 使用动态规划
 * 1.N 为nums1 的长度，i为nums1的下标，M为nums2的长度，j为nums2的下标
 * 2.定义dp[i][j] 为从nums1[i]开始到nums1[N-1] 和 从nums2[j]开始到nums2[M-1] 的不相交的线段数量
 * 3.if(nums1[i] == nums[j]) => 1 + dp[i+1][j+1]
 * if(nums1[i] == nums[j]) => max{ dp[i+1][j], dp[i][j+1] }
 * 4.依赖顺序是从下到上，从右到左的过程
 */
public class _1035_Uncrossed_Lines {
    public static void main(String[] args) {
        System.out.println(maxUncrossedLines(new int[]{1, 4, 2}, new int[]{1, 2, 4}));
        System.out.println(maxUncrossedLines(new int[]{2, 5, 1, 2, 5}, new int[]{10, 5, 2, 1, 5, 2}));
        System.out.println(maxUncrossedLines(new int[]{1, 3, 7, 1, 7, 5}, new int[]{1, 9, 2, 5, 1}));
    }

    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return 0;
        }
        int N = nums1.length;
        int M = nums2.length;
        // 使用M+1为了使越界时无需进行特殊处理
        int[][] dp = new int[N][M + 1];
        // 先填充最后一行
        for (int j = M - 1; j >= 0; j--) {
            dp[N - 1][j] = nums1[N - 1] == nums2[j] ? 1 : dp[N - 1][j + 1];
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }
}
