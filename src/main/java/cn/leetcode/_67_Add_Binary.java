package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 67. 二进制求和
 * <p>
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 * @author oudaming
 * @date 2021-02-24 10:00
 */
public class _67_Add_Binary {
    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("1010", "1011"));
        System.out.println(addBinary("0", "0"));
        System.out.println(addBinary("0", "1"));
    }

    public static String addBinary(String a, String b) {
        char[] chars1 = a.toCharArray();
        char[] chars2 = b.toCharArray();
        List<Integer> ans = new ArrayList<>();
        int N1 = chars1.length;
        int N2 = chars2.length;
        int i = N1 - 1;
        int j = N2 - 1;

        int after = 0;
        while (i >= 0 && j >= 0) {
            ans.add(((chars1[i] - '0') + (chars2[j] - '0') + after) % 2);
            if ((chars1[i] - '0') + (chars2[j] - '0') + after >= 2) {
                after = 1;
            } else {
                after = 0;
            }
            i--;
            j--;
        }
        while (i >= 0) {
            ans.add((chars1[i] - '0') ^ after);
            if ((chars1[i] - '0') + after >= 2) {
                after = 1;
            } else {
                after = 0;
            }
            i--;
        }
        while (j >= 0) {
            ans.add((chars2[j] - '0') ^ after);
            if ((chars2[j] - '0') + after >= 2) {
                after = 1;
            } else {
                after = 0;
            }
            j--;
        }
        if (after == 1) {
            ans.add(1);
        }
        StringBuilder ret = new StringBuilder();
        for (int k = ans.size() - 1; k >= 0; k--) {
            ret.append(ans.get(k));
        }
        return ret.toString();
    }
}
