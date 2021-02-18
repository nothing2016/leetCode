package cn.leetcode;

/**
 * 66. 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * @author oudaming
 * @date 2021-02-18 11:38
 */
public class _66_Plus_One {
    public static void main(String[] args) {
        printDigits(new _66_Plus_One().plusOne(new int[]{2, 3, 4}));
        printDigits(new _66_Plus_One().plusOne(new int[]{9, 9, 9}));
        printDigits(new _66_Plus_One().plusOne(new int[]{2, 0, 0}));
    }

    private static void printDigits(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
    }

    public int[] plusOne(int[] digits) {
        // 有可能要得到一个进位
        int first = 0;
        int N = digits.length;
        for (int i = N - 1; i >= 0; i--) {
            if (i == N - 1) {
                digits[i]++;
            }
            int sum = digits[i] + first;
            first = sum / 10;
            digits[i] = sum % 10;
        }
        if (first != 0) {
            int[] ans = new int[N + 1];
            ans[0] = first;
            for (int i = 0; i < N; i++) {
                ans[i + 1] = digits[i];
            }
            return ans;
        }
        return digits;
    }
}
