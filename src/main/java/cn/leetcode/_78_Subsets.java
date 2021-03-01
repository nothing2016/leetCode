package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 题解：从左到右的尝试模型，每个节点选择要和不要
 *
 * @author oudaming
 * @date 2021-03-01 11:31
 */
public class _78_Subsets {
    public static void main(String[] args) {
        List<List<Integer>> subsets = new _78_Subsets().subsets(new int[]{1, 2, 3});
        for (List<Integer> a : subsets) {
            System.out.println(a);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        process(nums, 0, item, ans);
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
    private void process(int[] nums, int i, List<Integer> item, List<List<Integer>> ans) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(item));
            return;
        }

        // 要
        ArrayList<Integer> yes = new ArrayList<>(item);
        yes.add(nums[i]);
        process(nums, i + 1, yes, ans);

        // 不要
        ArrayList<Integer> no = new ArrayList<>(item);
        process(nums, i + 1, no, ans);

    }
}
