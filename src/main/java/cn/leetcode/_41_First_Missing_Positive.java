package cn.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
 * <p>
 * 输入：nums = [1,2,0]
 * 输出：3
 * <p>
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 *
 * @author oudaming
 * @date 2021-02-03 11:24
 */
public class _41_First_Missing_Positive {
    public static void main(String[] args) {
        System.out.println(new _41_First_Missing_Positive().firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(new _41_First_Missing_Positive().firstMissingPositive2(new int[]{3, 4, -1, 1}));
    }

    /**
     * 时间复杂度 O(n),空间复杂度 O(n)
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], true);
        }
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (!map.containsKey(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 官方解：时间复杂度 O(n),空间复杂度 O(1)
     * 实际上，对于一个长度为 N 的数组，其中没有出现的最小正整数只能在 [1, N+1] 中。这是因为如果 [1, N] 都出现了，那么答案是 N+1，
     * 否则答案是 [1, N] 中没有出现的最小正整数。这样一来，我们将所有在 [1, N] 范围内的数放入哈希表，也可以得到最终的答案。
     * 而给定的数组恰好长度为 N，这让我们有了一种将数组设计成哈希表的思路：
     * <p>
     * 我们对数组进行遍历，对于遍历到的数 xx，如果它在 [1, N][1,N] 的范围内，那么就将数组中的第 x-1 个位置（注意：数组下标从 00 开始）打上「标记」。
     * 在遍历结束之后，如果所有的位置都被打上了标记，那么答案是 N+1N+1，否则答案是最小的没有打上标记的位置加 11。
     * 注意：其主要的想法也是对hash的解法进行优化，就是使用一个数组怎么证明自己已经存在过
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
