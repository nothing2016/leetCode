package cn.leetcode;

import java.util.Arrays;

/**
 * 403. 青蛙过河
 * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
 * <p>
 * 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
 * <p>
 * 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
 * <p>
 * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：stones = [0,1,3,5,6,8,12,17]
 * 输出：true
 * 解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子,
 * 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子,
 * 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
 * 示例 2：
 * <p>
 * 输入：stones = [0,1,2,3,4,8,9,11]
 * 输出：false
 * 解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= stones.length <= 2000
 * 0 <= stones[i] <= 2^31 - 1
 * stones[0] == 0
 *
 * @author oudaming
 * @date 2021-04-29 10:19
 */
public class _403_Frog_Jump {

    public static void main(String[] args) {
        System.out.println(canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
        System.out.println(canCross(new int[]{0, 1, 2, 3, 4, 8, 9, 11}));
    }

    public static boolean canCross(int[] stones) {
        int n = stones.length;
        // 记忆化搜索记录的结果
        Boolean[][] visited = new Boolean[n][n];
        return dfs(stones, 0, 0, visited);
    }

    /**
     * 当前来到了第i个石头，返回是否能从第i个石头跳到岸上
     *
     * @param stones
     * @param i       当前来到了哪个石头
     * @param lastDis 上次跳的距离
     * @return
     */
    private static boolean dfs(int[] stones, int i, int lastDis, Boolean[][] visited) {
        if (i == stones.length - 1) {
            return true;
        }
        if (visited[i][lastDis] != null) {
            return visited[i][lastDis];
        }

        // curDis 当前能跳的距离为[lastDis -1, lastDis + 1]，所有要全部尝试跳一遍
        for (int curDis = lastDis - 1; curDis <= lastDis + 1; curDis++) {
            // 距离大于0就可以跳
            if (curDis > 0) {
                // 二分查找 值为curDis + stones[i]的下标 j
                int j = Arrays.binarySearch(stones, i + 1, stones.length, curDis + stones[i]);
                // 如果j >= 0 表示存在， 并且j之后也能跳上案，就返回true
                if (j >= 0 && dfs(stones, j, curDis, visited)) {
                    return visited[i][lastDis] = true;
                }
            }
        }
        // 如果都尝试过了, 没有返回true，那就返回false
        return visited[i][lastDis] = false;
    }

}
