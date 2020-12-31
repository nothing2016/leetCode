package cn.leetcode.dynamicPlanning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 386. 字典序排数
 * 给定一个整数n, 返回从1到n的字典顺序。
 * 例如，
 * 给定 n =13，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * <p>
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据小于等于5,000,000。
 * <p>
 * <p>
 * 我们可以把它看成有9棵树，每棵树的根节点的值分别是从1到9，并且每棵树都有10个子节点，并且每个子节点又会有10个子节点……
 * 可以看成下面的9棵树的 （根左右优先遍历），出口就是 当前节点的值 > n
 * <p>
 * <p>
 * ------ 1              2    3   4    5    6   7   8  9
 * 10  11  12  13
 *
 * @author oudaming
 * @date 2020-12-31 10:45
 */
public class _386LexicographicalNumbers {
    public static void main(String[] args) {
        List<Integer> integers = new _386LexicographicalNumbers().lexicalOrder2(24);
        integers.forEach(e -> System.out.print(e + ","));
    }

    public List<Integer> lexicalOrder2(int n) {
        List<Integer> result = new ArrayList<>();
        if (n == 0) {
            return Arrays.asList(0);
        }
        for (int i = 1; i <= 9; i++) {
            dfs(i, n, result);
        }
        return result;
    }

    /**
     * 类 根左右 深度优先遍历
     *
     * @param i      当前节点的值
     * @param n      节点的最大值
     * @param result 结果
     */
    private void dfs(int i, int n, List<Integer> result) {
        if (i > n) {
            return;
        }
        result.add(i);
        for (int j = 0; j <= 9; j++) {
            dfs(i * 10 + j, n, result);
        }
    }

    public static List<Integer> lexicalOrder(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return Arrays.asList(0);
        }
        for (int i = 1; i <= n; i++) {
            result.add(String.valueOf(i));
        }
        Collections.sort(result);
        List<Integer> collect = result.stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
        return collect;
    }
}
