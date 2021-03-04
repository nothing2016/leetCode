package cn.leetcode;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * <p>
 * 输入: "race a car"
 * 输出: false
 *
 * @author oudaming
 * @date 2021-03-04 17:25
 */
public class _125_Valid_Palindrome {
    public static void main(String[] args) {
        System.out.println(new _125_Valid_Palindrome().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(new _125_Valid_Palindrome().isPalindrome("race a car"));
        System.out.println(new _125_Valid_Palindrome().isPalindrome("0P"));
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] chars = s.toCharArray();
        int l = 0;
        int r = chars.length - 1;
        while (l <= r) {
            if (!isLower(chars[l]) && !isUpper(chars[l]) && !isNumber(chars[l])) {
                l++;
                continue;
            }
            if (!isLower(chars[r]) && !isUpper(chars[r]) && !isNumber(chars[r])) {
                r--;
                continue;
            }
            if (!isEqualIgnoreCase(chars[l++], chars[r--])) {
                return false;
            }
        }
        return true;

    }

    public boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    public boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    public boolean isEqualIgnoreCase(char a, char b) {
        if (a == b) {
            return true;
        }
        if (isLower(a)) {
            return (a - 32) == b;
        }
        if (isUpper(a)) {
            return (a + 32) == b;
        }
        return false;
    }
}
