package cn.leetcode;

/**
 * 1486. 数组异或操作
 * 给你两个整数，n 和 start 。
 * <p>
 * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
 * <p>
 * 请返回 nums 中所有元素按位异或（XOR）后得到的结果。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5, start = 0
 * 输出：8
 * 解释：数组 nums 为 [0, 2, 4, 6, 8]，其中 (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8 。
 * "^" 为按位异或 XOR 运算符。
 * 示例 2：
 * <p>
 * 输入：n = 4, start = 3
 * 输出：8
 * 解释：数组 nums 为 [3, 5, 7, 9]，其中 (3 ^ 5 ^ 7 ^ 9) = 8.
 * 示例 3：
 * <p>
 * 输入：n = 1, start = 7
 * 输出：7
 * 示例 4：
 * <p>
 * 输入：n = 10, start = 5
 * 输出：2
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 1000
 * 0 <= start <= 1000
 * n == nums.length
 *
 * @author oudaming
 * @date 2021-05-07 9:39
 */
public class _1486_XOR_Operation_in_an_Array {
    public static void main(String[] args) {
        System.out.println(xorOperation(5, 0));
        System.out.println(xorOperation(4, 3));
        System.out.println(xorOperation(1, 7));
        System.out.println(xorOperation(10, 5));
    }

    public static int xorOperation(int n, int start) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int item = start + 2 * i;
            ans ^= item;
        }
        return ans;
    }
}
