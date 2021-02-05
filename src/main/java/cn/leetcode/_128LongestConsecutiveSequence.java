package cn.leetcode;

import java.util.HashMap;

/**
 * leetCode
 * 128. 最长连续序列
 *
 * @author oudaming
 * @date 2020/11/14 0014 16:29
 */
public class _128LongestConsecutiveSequence {
    /**
     * key - value 表示 key 有几个连续的value,如 2-3 ，表示2的连续区间有3个
     * 有可能是[0,1,2],也有可能是[2,3,4]，但就是不可能是[1,2,3],因为map的值
     * 是用来去重的，如果是[1,2,3],那么1,3在map中就已经存在了，在加入map时就
     * 添加不进来了,这样每添加一个value，就能知道这个value区间的最大值了
     * <p>
     * 此解到达O(n) 时间复杂度
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> numMap = new HashMap<Integer, Integer>();
        // 最大连续区间的len
        int maxLen = 0;
        for (int num : nums) {
            if (!numMap.containsKey(num)) {
                numMap.put(num, 1);
                // 得到[..num-1]区间的长度
                int headLen = numMap.get(num - 1) == null ? 0 : numMap.get(num - 1);
                // 得到[num+1...]区间的长度
                int tailLen = numMap.get(num + 1) == null ? 0 : numMap.get(num + 1);
                // 整个新区间的总长度
                int allLean = headLen + tailLen + 1;
                // 一旦找到，只在乎头尾的值，其他的都没有意义，不需要更新，因为是脏数据了
                numMap.put(num - headLen, allLean);
                numMap.put(num + tailLen, allLean);
                maxLen = Math.max(maxLen, allLean);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
//        输入：nums = [100,4,200,1,3,2]
//        输出：4
//        解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
        System.out.println(new _128LongestConsecutiveSequence().longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }
}
