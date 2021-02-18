package cn.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * @author oudaming
 * @date 2021-02-18 13:53
 */
public class _46_Permutations {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = new _46_Permutations().permute(nums);
        for (List<Integer> a : permute) {
            System.out.println(a);
        }
    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> numsList = new ArrayList<>();
        for (int index = 0; index < nums.length; index++) {
            numsList.add(nums[index]);
        }
        process(numsList, 0, ans);
        return ans;
    }

    /**
     * 对于位置i,任何数字都会在这里出现
     * 所以定义函数为：[0,i-1]的位置已经做好了选择，只需要i位置选需要的值，i位置可以选择[i,length -1]
     *
     * @param nums
     * @param i
     * @param ans
     */
    private void process(List<Integer> nums, int i, List<List<Integer>> ans) {
        if (i == nums.size()) {
            ans.add(new ArrayList<>(nums));
        }
        for (int j = i; j < nums.size(); j++) {
            Collections.swap(nums, i, j);
            process(nums, i + 1, ans);
            Collections.swap(nums, i, j);
        }
    }

}
