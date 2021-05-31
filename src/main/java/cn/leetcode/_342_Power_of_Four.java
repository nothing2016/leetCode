package cn.leetcode;

/**
 * 342. 4的幂
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4^x
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 16
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：n = 5
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * -2^31 <= n <= 2^31 - 1
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你能不使用循环或者递归来完成本题吗？
 */
public class _342_Power_of_Four {
    public static void main(String[] args) {
        System.out.println(isPowerOfFour(-64));
        System.out.println(isPowerOfFour(0));
        System.out.println(isPowerOfFour(14));
        System.out.println(isPowerOfFour(5));
        System.out.println(isPowerOfFour(8));
        System.out.println(isPowerOfFour(4));
        System.out.println(isPowerOfFour(16));
        System.out.println(isPowerOfFour(64));
        System.out.println(isPowerOfFour(256));
        System.out.println(isPowerOfFour(164));
    }

    // 这种方法不是很好，耗时多
    public static boolean isPowerOfFour(int n) {
        if (n <= 0) return false;
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }

    /**
     * n > 0 && ((n & (n - 1)) == 0) 判断是否是2的幂，如果4的幂，那么就一定是2的幂， 2的幂是前提
     * 0xAAAAAAAA 是mask 掩码，如果是4的幂的话，n & 0xAAAAAAAA) == 0  一定成立
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfFour2(int n) {
        return n > 0 && ((n & (n - 1)) == 0) && ((n & 0xAAAAAAAA) == 0);
    }

}
