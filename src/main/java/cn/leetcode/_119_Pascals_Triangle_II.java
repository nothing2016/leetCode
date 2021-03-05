package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. 杨辉三角 II
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * <p>
 * 输入: 3
 * 输出: [1,3,3,1]
 *
 * @author oudaming
 * @date 2021-03-05 11:26
 */
public class _119_Pascals_Triangle_II {
    public static void main(String[] args) {
        System.out.println(new _119_Pascals_Triangle_II().getRow(0));
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> integers = generate(rowIndex + 1).get(rowIndex);
        return integers;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ans.add(new ArrayList<>());
            ans.get(i).add(1);
        }
        for (int i = 1; i < numRows; i++) {
            for (int j = 1; j < i; j++) {
                ans.get(i).add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
            }
            ans.get(i).add(1);
        }
        return ans;
    }

}
