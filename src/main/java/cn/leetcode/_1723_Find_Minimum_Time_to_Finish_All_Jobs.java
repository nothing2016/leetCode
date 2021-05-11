package cn.leetcode;

/**
 * 1723. 完成所有工作的最短时间
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 * <p>
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
 * <p>
 * 返回分配方案中尽可能 最小 的 最大工作时间 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：jobs = [3,2,3], k = 3
 * 输出：3
 * 解释：给每位工人分配一项工作，最大工作时间是 3 。
 * 示例 2：
 * <p>
 * 输入：jobs = [1,2,4,7,8], k = 2
 * 输出：11
 * 解释：按下述方式分配工作：
 * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
 * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
 * 最大工作时间是 11 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 10^7
 * <p>
 * 题解： 递归 + 回溯 + 剪纸
 * 剪纸策略：
 * 1.尝试过程中发现出现更大的结果，直接return
 * 2.不允许"前面一个工人没有分配工作，后面一个有分配工作"的情况
 *
 * @author oudaming
 * @date 2021-05-08 10:03
 */
public class _1723_Find_Minimum_Time_to_Finish_All_Jobs {
    public static void main(String[] args) {
        System.out.println(minimumTimeRequired(new int[]{1, 2, 4, 7, 8}, 2));
    }

    private static long ans = Long.MAX_VALUE;

    public static int minimumTimeRequired(int[] jobs, int k) {
        ans = Long.MAX_VALUE;
        // 每个工人的时间数组
        long[] times = new long[k];
        dfs(jobs, times, 0, 0L);
        return (int) ans;
    }

    /**
     * @param jobs
     * @param times   每个工人的时间数组
     * @param index   现在分配第index项工作
     * @param maxTime 所有工人的最大时间，我们要求这个时间尽可能小
     */
    private static void dfs(int[] jobs, long[] times, int index, long maxTime) {
        if (index == jobs.length) {
            ans = Math.min(maxTime, ans);
            return;
        }
        for (int i = 0; i < times.length; i++) {
            // 因为分配时要求每个工人必须有工作，所以我们要求从左到右给工人分配的时候，不允许"前面一个没有值，后面一个有值"的情况
            // 为什么可以这样规定？因为工人的顺序是对结果没有影响的，也就是顺序可以调换
            if (i != 0 && times[i - 1] == 0L) {
                return;
            }
            // 将时间尝试分配给每个工人
            times[i] += (long) jobs[index];
            long tmpMax = Math.max(maxTime, times[i]);
            // 如果当前的时间已经大于之前算过的结果，抛弃掉，只保留更小的时间
            if (tmpMax < ans) {
                dfs(jobs, times, index + 1, tmpMax);
            }
            // 回溯，将时间退还出来，这样就可以给下一个工人
            times[i] -= (long) jobs[index];
        }
    }
}
