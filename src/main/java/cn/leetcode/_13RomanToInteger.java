package cn.leetcode;

/**
 * 13. 罗马数字转整数
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * @author oudaming
 * @date 2021-01-27 15:26
 */
public class _13RomanToInteger {
    public static void main(String[] args) {
        System.out.println(new _13RomanToInteger().romanToInt("III"));
        System.out.println(new _13RomanToInteger().romanToInt("IV"));
        System.out.println(new _13RomanToInteger().romanToInt("IX"));
        System.out.println(new _13RomanToInteger().romanToInt("LVIII"));
        System.out.println(new _13RomanToInteger().romanToInt("MCMXCIV"));
    }

    public int romanToInt(String s) {
        // c[0][...] -> 个位
        // c[1][...] -> 十位
        // c[2][...] -> 百位
        // c[3][...] -> 千位
        String[][] c = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}};
        int ans = 0;
        for (int i = 3; i >= 0; i--) {
            int len = c[i].length;
            for (int j = len - 1; j > 0; j--) {
                if (s.startsWith(c[i][j])) {
                    if (i == 3) {
                        ans = ans + j * 1000;
                    } else if (i == 2) {
                        ans = ans + j * 100;
                    } else if (i == 1) {
                        ans = ans + j * 10;
                    } else {
                        ans = ans + j;
                    }
                    s = s.substring(c[i][j].length());
                }
            }
        }
        return ans;
    }
}
