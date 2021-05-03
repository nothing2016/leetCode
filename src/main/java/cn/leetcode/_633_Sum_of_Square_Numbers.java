package cn.leetcode;

/**
 * 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * 示例 2：
 * <p>
 * 输入：c = 3
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：c = 4
 * 输出：true
 * 示例 4：
 * <p>
 * 输入：c = 2
 * 输出：true
 * 示例 5：
 * <p>
 * 输入：c = 1
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= c <= 2^31 - 1
 *
 * @author oudaming
 * @date 2021-04-28 9:29
 */
public class _633_Sum_of_Square_Numbers {
    public static void main(String[] args) {
        for (int c = 0; c <= 100; c++) {
            System.out.println(c + "," + judgeSquareSum(c));
        }
    }

    /**
     * 时间复杂度 O(sqrt(c))
     *
     * @param c
     * @return
     */
    public static boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }


    /**
     * 双指针的解法   O(sqrt(c))
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum2(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

}
