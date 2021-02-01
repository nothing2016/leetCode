package cn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 * <p>
 * 给定一个包含n 个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 题解：想求四数之和，先求三数之和，想求三数之和，先求两数之和。两数之和很简单，数组排序后，使用两头双指针解即可
 *
 * @author oudaming
 * @date 2021-02-01 14:49
 */
public class _18_4Sum {
    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
//        int[] nums = {0, 0, 0, 0, 0, 0};
        List<List<Integer>> lists = new _18_4Sum().fourSum(nums, 0);
        for (List<Integer> item : lists) {
            System.out.println(item.toString());
        }
    }

    /**
     * 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        nums = Arrays.stream(nums).sorted().toArray();
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }
        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> lists = threeSum(nums, i + 1, target - nums[i]);
            if (lists.size() > 0) {
                for (List<Integer> three : lists) {
                    three.add(nums[i]);
                    ans.add(three);
                }
            }
        }
        return ans;
    }

    /**
     * 三数之和
     *
     * @param nums
     * @param start
     * @param target
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums, int start, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] ints = nums;
        if (ints.length - start < 3) {
            return new ArrayList<>();
        }
        for (int i = start; i < ints.length - 2; i++) {
            if (i != start && ints[i] == ints[i - 1]) {
                continue;
            }
            List<List<Integer>> lists = twoSum(ints, i + 1, target - ints[i]);
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
