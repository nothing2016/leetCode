package cn.leetcode;

/**
 * 461. 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * <p>
 * 注意：
 * 0 ≤ x, y < 231.
 * <p>
 * 示例:
 * <p>
 * 输入: x = 1, y = 4
 * <p>
 * 输出: 2
 * <p>
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ------↑   ↑
 * <p>
 * 上面的箭头指出了对应二进制位不同的位置。
 */
public class _461_Hamming_Distance {
    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
        System.out.println(hammingDistance(0, 0));
    }

    public static int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int ans = 0;
        while (xor > 0) {
            ans += (xor & 1);
            xor = xor >> 1;
        }
        return ans;
    }
}
