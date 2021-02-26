package cn.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 1178. 猜字谜
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 * <p>
 * 字谜的迷面puzzle 按字符串形式给出，如果一个单词word符合下面两个条件，那么它就可以算作谜底：
 * <p>
 * 单词word中包含谜面puzzle的第一个字母。
 * 单词word中的每一个字母都可以在谜面puzzle中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及"based"（其中的 "s" 没有出现在谜面中）。
 * 返回一个答案数组answer，数组中的每个元素answer[i]是在给出的单词列表 words 中可以作为字谜迷面puzzles[i]所对应的谜底的单词数目。
 * <p>
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"],
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 * <p>
 * 输入：
 * ["apple","pleas","please"]
 * ["aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"]
 * 输出：[0,1,3,2,0]
 * <p>
 * ["aebd","cbdjegehgfcefbgceifdcjcbhdbbhhdi","bbcaehdgdghgaaghdbdg","fhgjegdagiadgdhaeicgjgifabadjdfe","gcadacg","efhjdffcaih","cgfjcdddabcdafjhcafieiadgebdeicbjjifgjbaf","cbfhbdaiajhdefgjefjibefjaahgdachhfge","cjejijcibgigceefidhcgbbdgg","jaedgdggbajbbibifadjeddbff","chdihgafjgfeaeefdigfeifjaihcg","giicgbjeah","bagcfechdabicgbidbceggjfedaabfibhcieefjhj","gbagbjhdjjdgifgaciehfjabi","ehheaajfbjdhabbjafeid","biajeeagdecjigefgidc","fejfdfeghbbdfc","bfbfffe","digd","bciabjhf"]
 * ["axtniqf","xsdlyik","ldsimca","ptvdamy","djihgak","xencovd","rbwpugz","xvsbpmj","epfhmxs","fshonmc"]
 * 解释：
 * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
 * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * 2 个单词可以作为"absoryz" 的谜底 : "aaaa", "asas"
 * 4 个单词可以作为"actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * 没有单词可以作为"gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 * <p>
 * 提示：
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j]都是小写英文字母。
 * 每个puzzles[i]所包含的字符都不重复。
 * <p>
 * 题解：
 * 给你两组 words 和 puzzles，有两条规则：
 * word 包含 puzzle 的首字符
 * word 中的所有字符都存在于 puzzle 中
 * 满足条件的 word 和 puzzle，前者是后者的谜底，求：每个 puzzle 的谜底的个数。
 * <p>
 * 这道题只会出现小写字母，种类不多最多26种，而且单词的字符只要在 puzzle 里出现了就行。即对每个字符来说，就两种状态：是否出现过，可以用 0/1 来代表这种相对的状态。
 * 出现过的记为1，没出现过的为0，比如 abc：111，aacc：101，zab:1000…0011（26位）
 * 遍历单词数组，找出单词对应的二进制数，存入map，并统计对应的次数，因为有些单词对应同一个二进制数，比如 abc 和 aaabbc 都是 111。
 * puzzle 的首字符必须要存在于单词中，我们找出所有包含puzzle首字母的puzzle字母组合。比如 aboveyz，满足的组合有：a，ab，ao，av，ae，ay，az，abo，abv，abe……都对应有二进制数。
 * 而每个单词都对应一个二进制数，如果在其中，则这个单词就是 puzzle 的谜底。
 * 所以，对于 puzzle 的这些二进制数，即它的组合，我们去查看 map 中是否有对应的值 c，如果有，意味着有 c 个单词是这样的字母组合，是这个puzzle的谜底。
 * 把所有的组合在map中对应的值累加起来，就是当前 puzzle 的谜底单词个数。
 *
 * @author oudaming
 * @date 2021-02-26 10:19
 */
public class _1178_Number_of_Valid_Words_for_Each_Puzzle {
    public static void main(String[] args) {
//        List<Integer> numOfValidWords = findNumOfValidWords(new String[]{"aaaa", "asas", "able", "ability", "actt", "actor", "access"}, new String[]{"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"});
//        List<Integer> numOfValidWords = findNumOfValidWords(new String[]{"raaa"}, new String[]{"aboveyz"});
        List<Integer> numOfValidWords = findNumOfValidWords(new String[]{"apple", "pleas", "please"}, new String[]{"aelwxyz", "aelpxyz", "aelpsxy", "saelpxy", "xaelpsy"});
//        List<Integer> numOfValidWords = findNumOfValidWords(new String[]{"aebd", "cbdjegehgfcefbgceifdcjcbhdbbhhdi", "bbcaehdgdghgaaghdbdg", "fhgjegdagiadgdhaeicgjgifabadjdfe", "gcadacg", "efhjdffcaih", "cgfjcdddabcdafjhcafieiadgebdeicbjjifgjbaf", "cbfhbdaiajhdefgjefjibefjaahgdachhfge", "cjejijcibgigceefidhcgbbdgg", "jaedgdggbajbbibifadjeddbff", "chdihgafjgfeaeefdigfeifjaihcg", "giicgbjeah", "bagcfechdabicgbidbceggjfedaabfibhcieefjhj", "gbagbjhdjjdgifgaciehfjabi", "ehheaajfbjdhabbjafeid", "biajeeagdecjigefgidc", "fejfdfeghbbdfc", "bfbfffe", "digd", "bciabjhf"}, new String[]{"axtniqf", "xsdlyik", "ldsimca", "ptvdamy", "djihgak", "xencovd", "rbwpugz", "xvsbpmj", "epfhmxs", "fshonmc"});
        for (Integer a : numOfValidWords) {
            System.out.println(a);
        }
    }

    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        // 1. 将words对应的二进制数变成词频表
        for (String word : words) {
            Integer binaryBit = getBinaryBitInt(word);
            map.put(binaryBit, map.getOrDefault(binaryBit, 0) + 1);
        }

        // 2.计算每个puzzles的结果
        for (String puzzle : puzzles) {
            int itemResult = 0;
            int binaryBit = getBinaryBitInt(puzzle);
            int mast = binaryBit;
            int firstBit = 1 << (puzzle.charAt(0) - 'a');
            while (binaryBit != 0) {

                // 如果子集包含第一位，才能成为结果
                if ((binaryBit & firstBit) == firstBit) {
                    itemResult += map.getOrDefault(binaryBit, 0);
                }
                // binaryBit所有子集的求法
                binaryBit = (binaryBit - 1) & mast;
            }
            ans.add(itemResult);
        }
        return ans;
    }

    /**
     * 将单词转换成二进制的整数
     * 即对每个字符来说，就两种状态：是否出现过，可以用 0/1 来代表这种相对的状态
     *
     * @param word
     * @return
     */
    private static Integer getBinaryBitInt(String word) {
        char[] chars = word.toCharArray();
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            ans |= (1 << (chars[i] - 'a'));
        }
        return ans;
    }
}
