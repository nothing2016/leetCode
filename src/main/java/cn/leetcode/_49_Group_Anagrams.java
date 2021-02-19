package cn.leetcode;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * <p>
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * <p>
 * 输入： ["cab","tin","pew","duh","may","ill","buy","bar","max","doc"]
 * 输出：[["max"],["buy"],["doc"],["may"],["ill"],["duh"],["tin"],["bar"],["pew"],["cab"]]
 *
 * @author oudaming
 * @date 2021-02-18 15:31
 */
public class _49_Group_Anagrams {
    public static void main(String[] args) {
//        List<List<String>> lists = new _49_Group_Anagrams().groupAnagrams(new String[]{"cab", "tin", "pew", "duh", "may", "ill", "buy", "bar", "max", "doc"});
        List<List<String>> lists = new _49_Group_Anagrams().groupAnagrams(new String[]{"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"});
        for (List<String> item : lists) {
            System.out.println(item);
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);

            if (map.containsKey(key)) {
                map.get(key).add(s);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(s);
                map.put(key, list);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> item : map.values()) {
            ans.add(item);
        }
        return ans;
    }
}
