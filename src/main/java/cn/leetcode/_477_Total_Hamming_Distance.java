package cn.leetcode;

/**
 * 477. 汉明距离总和
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * <p>
 * 计算一个数组中，任意两个数之间汉明距离的总和。
 * <p>
 * 示例:
 * <p>
 * 输入: 4, 14, 2
 * <p>
 * 输出: 6
 * <p>
 * 解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
 * 所以答案为：
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * 注意:
 * <p>
 * 数组中元素的范围为从 0到 10^9。
 * 数组的长度不超过 10^4。
 * <p>
 * 题解： 若长度为 nn 的数组 nums 的所有元素二进制的第 i 位共有 c 个 1，n−c 个 0，
 * 则些元素在二进制的第 i 位上的汉明距离之和为
 * c⋅(n−c)
 * 所以总值为所有位数的结果累加即可
 */
public class _477_Total_Hamming_Distance {
    public static void main(String[] args) {
        System.out.println(totalHammingDistance(new int[]{4, 14, 2}));
        System.out.println(totalHammingDistance(new int[]{4}));
    }

    public static int totalHammingDistance(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int ans = 0, n = nums.length;
        for (int i = 0; i < 30; ++i) {
            int c = 0;
            for (int val : nums) {
                c += (val >> i) & 1;
            }
            ans += c * (n - c);
        }
        return ans;
    }
}
