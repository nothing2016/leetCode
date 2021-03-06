package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案
 * <p>
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 *
 * @author oudaming
 * @date 2021/3/6 0006 09:29
 */
public class _131_Palindrome_Partitioning {
    public static void main(String[] args) {
        System.out.println(new _131_Palindrome_Partitioning().partition(""));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        List<String> item = new ArrayList<>();
        process(0, item, s, ans);
        return ans;
    }

    /**
     * [0,i-1]的位置已经选择好了，到i这个位置去分割了
     *
     * @param i
     * @param s
     * @param ans
     */
    private void process(int i, List<String> item, String s, List<List<String>> ans) {
        int N = s.length();
        if (i == N) {
            ans.add(item);
            return;
        }
        for (int j = i + 1; j <= N; j++) {
            String pre = s.substring(i, j);
            if (isPalindrome(pre)) {
                List<String> temp = new ArrayList<>(item);
                temp.add(pre);
                process(i + pre.length(), temp, s, ans);
            }
        }
    }

    /**
     * 是否是回文
     *
     * @param s
     * @return
     */
    private boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}
