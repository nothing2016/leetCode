package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的数字可以无限制重复被选取。
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 *
 * @author oudaming
 * @date 2021-02-03 13:52
 */
public class _39_Combination_Sum {
    public static void main(String[] args) {
//        List<List<Integer>> lists = new _39_Combination_Sum().combinationSum(new int[]{2, 3, 5}, 8);
//        List<List<Integer>> lists = new _39_Combination_Sum().combinationSum(new int[]{48, 22, 49, 24, 26, 47, 33, 40, 37, 39, 31, 46, 36, 43, 45, 34, 28, 20, 29, 25, 41, 32, 23, 69}, 69);
        List<List<Integer>> lists = new _39_Combination_Sum().combinationSum(new int[]{48, 22, 49, 24, 26, 47, 33, 40, 37, 39, 31, 46, 36, 43, 45, 34, 28, 20, 29, 25, 41, 32, 23, 69}, 69);
        for (List<Integer> item : lists) {
            System.out.println(item);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    /**
     * 不超时，通过
     *
     * @param candidates
     * @param target
     * @param ans
     * @param combine
     * @param idx
     */
    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target < 0) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }


        // 1.选择当前数
        combine.add(candidates[idx]);
        dfs(candidates, target - candidates[idx], ans, combine, idx);
        // 回溯
        combine.remove(combine.size() - 1);

        // 2.直接跳过当前数
        dfs(candidates, target, ans, combine, idx + 1);

    }

}
