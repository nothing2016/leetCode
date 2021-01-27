package cn.leetcode;

/**
 * 12. 整数转罗马数字
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1到 3999 的范围内。
 *
 * @author oudaming
 * @date 2021-01-27 15:26
 */
public class _12IntegerToRoman {
    public static void main(String[] args) {
        System.out.println(new _12IntegerToRoman().intToRoman(3));
        System.out.println(new _12IntegerToRoman().intToRoman(4));
        System.out.println(new _12IntegerToRoman().intToRoman(9));
        System.out.println(new _12IntegerToRoman().intToRoman(58));
    }

    public String intToRoman(int num) {
        // c[0][...] -> 个位
        // c[1][...] -> 十位
        // c[2][...] -> 百位
        // c[3][...] -> 千位
        String[][] c = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}};
        StringBuilder temp = new StringBuilder();
        String ans = temp.append(c[3][num / 1000 % 10])
                .append(c[2][num / 100 % 10])
                .append(c[1][num / 10 % 10])
                .append(c[0][num % 10]).toString();
        return ans;
    }
}
