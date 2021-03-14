package cn.leetcode;

/**
 * 171. Excel表列序号
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 * <p>
 * 例如，
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 示例 1:
 * <p>
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 * <p>
 * 输入: "ZY"
 * 输出: 701
 *
 * @author oudaming
 * @date 2021/3/14 0014 18:36
 */
public class _171_Excel_Sheet_Column_Number {
    public static void main(String[] args) {
        System.out.println(titleToNumber("A"));
        System.out.println(titleToNumber("AB"));
        System.out.println(titleToNumber("ZY"));
    }

    public static int titleToNumber(String columnTitle) {
        if (columnTitle == null || columnTitle.length() == 0) {
            return 0;
        }
        char[] chars = columnTitle.toCharArray();
        int N = chars.length;
        int ans = 0;
        int s = 1;
        for (int i = N - 1; i >= 0; i--) {
            int num = chars[i] - 'A' + 1;
            ans = ans + s * num;
            s *= 26;
        }
        return ans;
    }
}
