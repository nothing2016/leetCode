package cn.leetcode;

/**
 * 416. 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 注意:
 * <p>
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 输入: [1, 5, 11, 5]
 * <p>
 * 输出: true
 * <p>
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * <p>
 * 思路：变成大小为arr[].length的背包，背包的容积为：累加和的一半
 *
 * @author oudaming
 * @date 2021-01-06 14:11
 */
public class _416PartitionEqualSubsetSum {

    public static void main(String[] args) {
        int[] nums = {14, 9, 8, 4, 3, 2};
        boolean a = new _416PartitionEqualSubsetSum().canPartition(nums);
        boolean b = new _416PartitionEqualSubsetSum().canPartition2(nums);
        boolean c = new _416PartitionEqualSubsetSum().canPartition3(nums);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        return process(nums, 0, sum / 2);
    }

    private boolean process(int[] nums, int index, int rest) {
        if (rest < 0 || index > nums.length) {
            return false;
        }
        if (rest == 0) {
            return index <= nums.length ? true : false;
        }
        if (index == nums.length) {
            return rest == 0 ? true : false;
        }
        // 不要当前的东西
        boolean p1 = process(nums, index + 1, rest);
        // 要当前的东西
        boolean p2 = process(nums, index + 1, rest - nums[index]);
        return p1 || p2;
    }

    /**
     * 由暴力递归改进，自顶向下
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int bag = sum / 2;

        boolean dp[][] = new boolean[nums.length + 1][bag + 1];

        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }
        for (int index = nums.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < dp[0].length; rest++) {

//                dp[index][rest] = rest - nums[index] >= 0 ? dp[index + 1][rest] || dp[index + 1][rest - nums[index]] : dp[index + 1][rest];

                if (rest >= nums[index]) {
                    dp[index][rest] = dp[index + 1][rest] || dp[index + 1][rest - nums[index]];
                } else {
                    dp[index][rest] = dp[index + 1][rest];
                }
            }
        }
//        printMatrix(dp);
        return dp[0][bag];
    }

    /**
     * 自下向上
     *
     * @param nums
     * @return
     */
    public boolean canPartition3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int bag = sum / 2;

        boolean dp[][] = new boolean[nums.length + 1][bag + 1];

        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 0; i < dp.length - 1; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j - nums[i] >= 0) {
                    dp[i + 1][j] = dp[i][j] || dp[i][j - nums[i]];
                }
            }
        }

//        printMatrix(dp);

        return dp[nums.length - 1][bag];
    }

    private void printMatrix(boolean[][] dp) {
        System.out.println("=========开始打印=========");
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + ",");
            }
            System.out.println("");
        }
        System.out.println("=========结束打印=========");
    }


}
