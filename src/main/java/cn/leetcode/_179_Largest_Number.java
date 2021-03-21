package cn.leetcode;

import java.util.Arrays;

/**
 * 179. 最大数
 * 给定一组非负整数 nums，重新排列它们每个数字的顺序（每个数字不可拆分）使之组成一个最大的整数。
 * <p>
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 * <p>
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出："1"
 * 示例 4：
 * <p>
 * 输入：nums = [10]
 * 输出："10"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 *
 * @author oudaming
 * @date 2021/3/21 0021 09:14
 */
public class _179_Largest_Number {
    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{1, 0, 0, 2}));
        System.out.println(largestNumber(new int[]{0, 0, 100}));
    }

    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder builder = new StringBuilder();
        for (String str : strs) {
            builder.append(str);
        }
        String ans = builder.toString();
//        return ans;
        // 防止都是0的情况，如[0,0,0] 现在的结果是000，所以需要去掉前导0
        char[] str = ans.toCharArray();
        int index = -1;
        for (int i = 0; i < str.length; i++) {
            if (str[i] != '0') {
                index = i;
                break;
            }
        }
        return index == -1 ? "0" : ans.substring(index);
    }

}
