package cn.leetcode;

import java.util.TreeSet;

/**
 * 220. 存在重复元素 III
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * <p>
 * 如果存在则返回 true，不存在返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1], k = 3, t = 0
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：nums = [1,0,1,1], k = 1, t = 2
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 2 * 104
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^4
 * 0 <= t <= 2^31 - 1
 * <p>
 * 题解： 滑动窗口 + 有序表   时间复杂度O(N * log(min(N , K)))
 *
 * @author oudaming
 * @date 2021/4/17 0017 21:24
 */
public class _220_Contains_Duplicate_III {
    public static void main(String[] args) {
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1}, 1, 2));
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 数组长度为0 或 k==0 时，就不会有两个不同下标，所以返回false
        if (nums == null || nums.length == 0 || k == 0) {
            return false;
        }
        TreeSet<Long> set = new TreeSet<>();
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            // 当达到i时，i已经属于逻辑窗口的一员了，所以只要包含，就返回成功
            if (set.contains((long) nums[i])) {
                return true;
            }
            // 在滑动窗口内找到以nums[i]为中心点，大于等于nums[i]-t 的第一个值
            Long ceiling = set.ceiling((long) nums[i] - t);
            // 如果有值，那么就直接返回成功
            if (ceiling != null && Math.abs(ceiling - nums[i]) <= t) {
                return true;
            }
            set.add((long) nums[i]);
            // 假设这里 k == 2, 那么当i== 2时，tree里已经有三个值了，就是下标为0,1,2
            // 的数nums[0],nums[1],nums[2],我们必须将nums[0]去掉，这样num[3]才能在下一次
            // 加入进来
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}
