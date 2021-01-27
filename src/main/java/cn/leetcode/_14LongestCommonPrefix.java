package cn.leetcode;

/**
 * 14. 最长公共前缀
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * @author oudaming
 * @date 2021-01-27 16:07
 */
public class _14LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(new _14LongestCommonPrefix().longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(new _14LongestCommonPrefix().longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(new _14LongestCommonPrefix().longestCommonPrefix(new String[]{"dog"}));

    }

    public String longestCommonPrefix(String[] strs) {
        StringBuilder commonPrefix = new StringBuilder();
        int max_row = strs.length;
        if (max_row == 0) {
            return "";
        }
        if (max_row == 1) {
            return strs[0];
        }
        int col = 0;
        boolean found = false;
        while (!isOut(col, strs) && !found) {
            // 第几列的值
            char colValue = strs[0].charAt(col);
            for (int row = 1; row < max_row; row++) {
                if (strs[row].charAt(col) != colValue) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                commonPrefix.append(colValue);
                col++;
            }
        }
        return commonPrefix.toString();
    }

    private boolean isOut(int col, String[] strs) {
        int row = strs.length;
        for (int i = 0; i < row; i++) {
            if (col >= strs[i].length()) {
                return true;
            }
        }
        return false;
    }


}
