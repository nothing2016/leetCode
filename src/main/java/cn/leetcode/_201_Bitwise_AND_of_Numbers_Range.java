package cn.leetcode;

/**
 * 201. 数字范围按位与
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：left = 5, right = 7
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：left = 0, right = 0
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：left = 1, right = 2147483647
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * 0 <= left <= right <= 2^31 - 1
 * <p>
 * 题解： 结果就是left和right的公共前缀，left~right中间的值是不需要考虑的
 */
public class _201_Bitwise_AND_of_Numbers_Range {
    public static void main(String[] args) {
//        System.out.println(rangeBitwiseAnd(5, 7));
//        System.out.println(rangeBitwiseAnd(0, 0));
        System.out.println(rangeBitwiseAnd(1, 2147483647));
    }

    public static int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        // 找到公共前缀
        while (left < right) {
            left >>= 1;
            right >>= 1;
            ++shift;
        }
        return right << shift;
    }
}
