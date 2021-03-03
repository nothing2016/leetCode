package cn.leetcode;

/**
 * 338. 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 输入: 2
 * 输出: [0,1,1]
 * <p>
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 * @author oudaming
 * @date 2021-03-03 10:06
 */
public class _338_Counting_Bits {
    public static void main(String[] args) {
        int[] ints = new _338_Counting_Bits().countBits2(5);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    public int[] countBits(int num) {
        if (num == 0) {
            return new int[]{0};
        }
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            ans[i] = getOneBitCount(i);
        }
        return ans;
    }

    private int getOneBitCount(int num) {
        int count = 0;
        while (num != 0) {
            count += ((num & 1) == 1 ? 1 : 0);
            num = num >> 1;
        }
        return count;
    }

    /**
     * 动态规划的解法，如num == 7,那么需要求bits[0],bits[1],bits[2],bits[3]..bits[7]的值
     * 我们来找一下规律，7 的二进制 111, 也就是算 ’11‘ 有多少个一 再加上最后一位 ’1‘有多少个一
     * '11' 就等于第一位’1‘有多少一 再加上最后一位 '1'有多少个一即可，那么前面的’1‘可以通过右移动一位得到
     *
     * @param num
     * @return
     */
    public int[] countBits2(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }


}
