package cn.leetcode;

import java.util.HashMap;
import java.util.Objects;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * <p>
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * <p>
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 * <p>
 * 输入：nums = [1], target = 1
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 100
 */
public class _494_Target_Sum {
    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(findTargetSumWays(new int[]{1}, 1));
    }

    public static int findTargetSumWays(int[] nums, int target) {
        return process2(0, 0, nums, target, new HashMap<>());
    }

    /**
     * 到了index节点，但是还没有做出选择
     * 从左到右的尝试模型，每一个选择 + 或 -
     *
     * @param index
     * @param sum    表示之前的累加和
     * @param nums
     * @param target 目标
     * @return 返回的方法数
     */
    private static int process(int index, int sum, int[] nums, int target) {
        if (index == nums.length) {
            return sum == target ? 1 : 0;
        }
        return process(index + 1, sum + nums[index], nums, target)
                + process(index + 1, sum - nums[index], nums, target);
    }

    /**
     * 动态规划的求解方法
     *
     * @param index
     * @param sum
     * @param nums
     * @param target
     * @return
     */
    private static int process2(int index, int sum, int[] nums, int target, HashMap<Node, Integer> map) {

        if (map.containsKey(new Node(index, sum))) {
            return map.get(new Node(index, sum));
        }
        if (index == nums.length) {
            int ans = sum == target ? 1 : 0;
            map.put(new Node(index, sum), ans);
            return ans;
        }
        int ans = process(index + 1, sum + nums[index], nums, target)
                + process(index + 1, sum - nums[index], nums, target);
        map.put(new Node(index, sum), ans);
        return ans;
    }

    private static class Node {
        int index;
        int sum;

        public Node(int index, int sum) {
            this.index = index;
            this.sum = sum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return index == node.index &&
                    sum == node.sum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, sum);
        }
    }
}
