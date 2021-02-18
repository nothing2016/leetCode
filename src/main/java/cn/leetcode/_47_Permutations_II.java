package cn.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 *
 * @author oudaming
 * @date 2021-02-18 13:53
 */
public class _47_Permutations_II {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        List<List<Integer>> permute = new _47_Permutations_II().permuteUnique(nums);
        for (List<Integer> a : permute) {
            System.out.println(a);
        }
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
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
        // 因为数据  -10 <= nums[i] <= 10
        boolean[] visit = new boolean[22];
        // 这里需要剪枝，如果nums[j]相同的值已经选择过，那么就排除掉
        for (int j = i; j < nums.size(); j++) {
            if (!visit[10 + nums.get(j)]) {
                visit[10 + nums.get(j)] = true;
                Collections.swap(nums, i, j);
                process(nums, i + 1, ans);
                Collections.swap(nums, i, j);
            }
        }
    }

}
