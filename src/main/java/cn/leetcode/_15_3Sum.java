package cn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 题解：三数之和可以拆分成当前值加上两数之和即可，如果三数之和要求=10，当前值为2，那么只要 2 + 两数之和为8的即可，如(2,7,1)
 *
 * @author oudaming
 * @date 2021-01-27 16:37
 */
public class _15_3Sum {

    public static void main(String[] args) {
//        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {0, 0, 0, 0, 0};
        List<List<Integer>> lists = new _15_3Sum().threeSum(nums);
        for (List<Integer> item : lists) {
            System.out.println(String.join(",", item.toString()));
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] ints = Arrays.stream(nums).sorted().toArray();
        if (ints.length < 3) {
            return new ArrayList<>();
        }
        for (int i = 0; i < ints.length - 2; i++) {
            if (i != 0 && ints[i] == ints[i - 1]) {
                continue;
            }
            List<List<Integer>> lists = twoSum(ints, i + 1, -ints[i]);
            if (lists.size() > 0) {
                for (List<Integer> two : lists) {
                    two.add(ints[i]);
                    ans.add(two);
                }

            }

        }
        return ans;
    }

    /**
     * 求数组nums之后的两数之和，使用双指针
     *
     * @param nums  已经排好序的值
     * @param start 需要找到二元组的开始点
     * @return
     */
    public List<List<Integer>> twoSum(int[] nums, int start, int twoSum) {
        int l = start;
        int r = nums.length - 1;
        List<List<Integer>> ans = new ArrayList<>();
        while (l != r) {
            if (nums[l] + nums[r] == twoSum) {
                // 1)第一位收集 2)不是第一位，并且和前一位不相等时也收集
                if (l == start || (l != start && nums[l] != nums[l - 1])) {
                    // 收集答案   [1,1,2,2,3]  比如收集两数== 2的情况，1就是不能重复收集的
                    ArrayList<Integer> item = new ArrayList<Integer>();
                    item.add(nums[l]);
                    item.add(nums[r]);
                    ans.add(item);
                }
                l++;
            } else if (nums[l] + nums[r] < twoSum) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }
}
