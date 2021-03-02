package cn.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 90. 子集 II
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 输入: [1,2,2]
 * 输出:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 * <p>
 * 题解：从左到右的尝试模型，每个节点选择要和不要
 *
 * @author oudaming
 * @date 2021-03-01 11:31
 */
public class _90_Subsets_II {
    public static void main(String[] args) {
        List<List<Integer>> subsets = new _90_Subsets_II().subsetsWithDup(new int[]{1, 2, 2});
        for (List<Integer> a : subsets) {
            System.out.println(a);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        process(nums, 0, item, ans, set);
        return ans;
    }

    /**
     * 当前来到了i节点，选择要和不要
     *
     * @param nums
     * @param i
     * @param item 当前的选择
     * @param ans
     * @return
     */
    private void process(int[] nums, int i, List<Integer> item, List<List<Integer>> ans, HashSet<String> set) {
        if (i == nums.length) {
            ArrayList<Integer> integers = new ArrayList<>(item);
            integers.sort((a, b) -> a - b);
            if (!set.contains(integers.toString())) {
                ans.add(integers);
                set.add(integers.toString());
            }
            return;
        }

        // 要
        ArrayList<Integer> yes = new ArrayList<>(item);
        yes.add(nums[i]);
        process(nums, i + 1, yes, ans, set);

        // 不要
        ArrayList<Integer> no = new ArrayList<>(item);
        process(nums, i + 1, no, ans, set);

    }
}
