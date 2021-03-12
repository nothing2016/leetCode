package cn.leetcode;

/**
 * 168. Excel表列名称
 * <p>
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 * <p>
 * 例如，
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ...
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: "A"
 * 示例 2:
 * <p>
 * 输入: 28
 * 输出: "AB"
 * 示例 3:
 * <p>
 * 输入: 701
 * 输出: "ZY"
 * 题解：本身不难，其实就是10进制变成26进制，一般都是从0开始的，但是这个是从A(1)开始，
 * 所以增加了难度。
 * 实际上我们会发现，只要每次计算时将n先自减1（n -= 1）然后再取余数，
 * 除以26就刚好可以了
 *
 * @author oudaming
 * @date 2021/3/12 0012 23:35
 */
public class _168_Excel_Sheet_Column_Title {
    public static void main(String[] args) {
        System.out.println(convertToTitle(901));
//        System.out.println(convertToTitle(1));
    }

    public static String convertToTitle(int columnNumber) {
        int n = columnNumber;
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            int c = n % 26;
            sb.insert(0, (char) ('A' + c));
            n /= 26;
        }
        return sb.toString();

    }
}
