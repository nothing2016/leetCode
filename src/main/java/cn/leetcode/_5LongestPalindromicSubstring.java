package cn.leetcode;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * @author oudaming
 * @date 2021/1/9 0009 11:35
 */
public class _5LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(new _5LongestPalindromicSubstring().longestPalindrome(s));
        System.out.println(new _5LongestPalindromicSubstring().manacher(s));
        System.out.println(new _5LongestPalindromicSubstring().longestPalindrome("1"));
        System.out.println(new _5LongestPalindromicSubstring().manacher("1"));
        System.out.println(new _5LongestPalindromicSubstring().longestPalindrome("123456543217"));
        System.out.println(new _5LongestPalindromicSubstring().manacher("123456543217"));
    }

    public String longestPalindrome(String s) {
        return process(s);
    }

    /**
     * 暴力方法，O(N的平方)
     * 1.将str全部补充#，使得回文的长度全部变成奇数,如12214 -> #1#2#2#1#4#
     * 这样求12214的最长回文子串就是求#1#2#2#1#4#的最长回文子串，得到的结果将#去掉即可
     * 2.每个位置，左右扩，找到最长的即可
     *
     * @param s
     * @return
     */
    private String process(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] str = change(s);
        String result = "";
        int index = -1;
        int maxRadius = -1;

        for (int i = 0; i < str.length; i++) {
            int left = i;
            int right = i;
            // 回文半径
            int radius = 0;
            while (left >= 0 && right < str.length) {
                if (str[left] == str[right]) {
                    radius++;
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            if (radius > maxRadius) {
                maxRadius = radius;
                index = i;
            }
        }
        for (int i = index - (maxRadius - 1); i < index + maxRadius; i++) {
            result += (str[i] == '#' ? "" : str[i]);
        }
        return result;
    }

    private char[] change(String s) {
        char[] charArr = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }


    /**
     * O(N)
     * manacher算法: 找到最大的回文子串的长度
     * pArr[]: 记录每个位置回文半径长度
     * R: 代表记录过的回文最大的右边界，R只会一直增大
     * C: 和R 配套使用，代表对应R的回文中心点
     * <p>
     * 四种情况：
     * 1) i 不在R的范围内，那么只能向i的两边扩充
     * 2）i 在R的范围内，分为三种情况
     * a).i 的对称点i` 的回文半径刚好在[L,R]的范围内，pArr[i] = pArr[i`]
     * b).i 的对称点i` 的回文半径超过了[L,R]的范围内，pArr[i] = R - i
     * c).i 的对称点i` 的回文半径刚好在[L,R]的边界上，需要两边扩充
     * <p>
     * 注意：这里的中心点C一定是在i的左边的
     */
    public String manacher(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        // "12132" -> "#1#2#1#3#2#"  补充#，让任意一个字符串都是奇数的回文
        char[] str = change(s);
        // 回文半径的大小，不是半径的下标，如果一个当前一个数没有回文，只有自己，那么值就为1
        int[] pArr = new int[str.length];
        int C = -1;
        // 讲述中：R代表最右的扩成功的位置。coding：最右的扩成功位置的，再下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            // R第一个违规的位置，i>= R
            // i位置扩出来的答案，i位置扩的区域，至少是多大。
            // 先求当前点i的回文半径，这里就是在R范围内a.b的情况找最小值作为当前的回文半径
            // 1情况的话，就是1作为自己的回文半径
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;

            // 只要扩充还在有效的范围内，继续
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                // 如果相等，扩充（就是1，和c的情况）
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    // a,b 情况会直接break
                    break;
                }
            }
            // 拿到了i的回文半径，查看是否需要更新
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }

        String result = "";
        int index = -1;
        int maxRadius = -1;
        for (int i = 0; i < pArr.length; i++) {
            if (pArr[i] == max) {
                index = i;
                maxRadius = max;
            }
        }
        for (int i = index - (maxRadius - 1); i < index + maxRadius; i++) {
            result += (str[i] == '#' ? "" : str[i]);
        }
        return result;
    }
}
