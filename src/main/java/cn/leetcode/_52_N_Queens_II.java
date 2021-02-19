package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 52. N皇后 II
 * n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 * @author oudaming
 * @date 2021-02-19 14:48
 */
public class _52_N_Queens_II {
    public static void main(String[] args) {
        System.out.println(new _52_N_Queens_II().totalNQueens(4));
    }

    public int totalNQueens(int n) {
        return num1(n);
    }

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        // record[0] ?  record[1]  ?  record[2]
        int[] record = new int[n]; // record[i] -> i行的皇后，放在了第几列
        List<List<String>> ans = new ArrayList<>();
        int count = process1(0, record, n, ans);
        return count;
    }

    // 潜台词：record[0..i-1]的皇后，任何两个皇后一定都不共行、不共列，不共斜线
    // 目前来到了第i行
    // record[0..i-1]表示之前的行，放了的皇后位置
    // n代表整体一共有多少行  0~n-1行
    // 返回值是，摆完所有的皇后，合理的摆法有多少种
    public static int process1(int i, int[] record, int n, List<List<String>> ans) {
        if (i == n) { // 终止行
            // 收集所有的值
            List<String> item = new ArrayList<>();
            for (int k = 0; k < n; k++) {
                char[] chars = new char[n];
                for (int h = 0; h < n; h++) {
                    chars[h] = record[k] == h ? 'Q' : '.';
                }
                item.add(String.valueOf(chars));
            }
            ans.add(item);
            return 1;
        }
        // 没有到终止位置，还有皇后要摆
        // 结果一定要在for循环这前定义，因为要搜集所有列摆放的结果
        int res = 0;
        for (int j = 0; j < n; j++) { // 当前行在i行，尝试i行所有的列  -> j
            // 当前i行的皇后，放在j列，会不会和之前(0..i-1)的皇后，不共行共列或者共斜线，
            // 如果是，认为有效
            // 如果不是，认为无效
            if (isValid(record, i, j)) {
                record[i] = j;
                // 加法的意思就是： 当皇后放在i行j列这种情况时，i+1，i+2...n行一共有多少摆法
                // 所以一定是加法，因为，i行的j是一个for循环，需要每一列都尝试一下，已经是乘法的意思了
                res += process1(i + 1, record, n, ans);
                // 这里的回溯是多余的，因为后面的那个值会覆盖掉前面的值
                record[i] = 0;
            }
        }
        return res;
    }

    // record[0..i-1]你需要看，record[i...]不需要看
    // 返回i行皇后，放在了j列，是否有效
    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) { // 之前的某个k行的皇后
            // k, record[k]   i, j
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }
}
