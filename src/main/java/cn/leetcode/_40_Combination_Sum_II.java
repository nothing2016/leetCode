package cn.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 40. 组合总和 II
 * 给定一个数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * <p>
 * candidates中的每个数字在每个组合中只能使用一次。
 * <p>
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 *
 * @author oudaming
 * @date 2021-02-03 15:39
 */
public class _40_Combination_Sum_II {

    public static void main(String[] args) {
//        List<List<Integer>> lists = new _40_Combination_Sum_II().combinationSum2(new int[]{2, 3, 5}, 8);
        List<List<Integer>> lists = new _40_Combination_Sum_II().combinationSum2(new int[]{1, 1, 7}, 8);
//        List<List<Integer>> lists = new _40_Combination_Sum_II().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
//        List<List<Integer>> lists = new _40_Combination_Sum_II().combinationSum2(new int[]{48, 22, 49, 24, 26, 47, 33, 40, 37, 39, 31, 46, 36, 43, 45, 34, 28, 20, 29, 25, 41, 32, 23, 69}, 69);
//        List<List<Integer>> lists = new _40_Combination_Sum_II().combinationSum2(new int[]{48, 22, 49, 24, 26, 47, 33, 40, 37, 39, 31, 46, 36, 43, 45, 34, 28, 20, 29, 25, 41, 32, 23, 69}, 69);
        for (List<Integer> item : lists) {
            System.out.println(item);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        HashMap<String, Boolean> map = new HashMap<>();
        dfs(candidates, target, ans, combine, 0, map);
        return ans;
    }

    /**
     * 就是当前idx节点选和不选的情况
     *
     * @param candidates
     * @param target
     * @param ans
     * @param combine
     * @param idx
     */
    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx, HashMap<String, Boolean> map) {
        if (target == 0) {
            ArrayList<Integer> integers = new ArrayList<>(combine);
            integers.sort((a, b) -> a - b);
            String s = integers.toString();
            if (!map.containsKey(s)) {
                map.put(s, true);
                ans.add(new ArrayList<Integer>(integers));
            }
            return;
        }
        if (idx >= candidates.length) {
            return;
        }
        if (target < 0) {
            return;
        }


        // 1.选择当前数
        combine.add(candidates[idx]);
        dfs(candidates, target - candidates[idx], ans, combine, idx + 1, map);
        combine.remove(combine.size() - 1);

        // 2.直接跳过当前数
        dfs(candidates, target, ans, combine, idx + 1, map);
    }
}
