package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 401. 二进制手表
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 * <p>
 * 例如，下面的二进制手表读取 "3:25" 。
 * <p>
 * <p>
 * （图源：WikiMedia - Binary clock samui moon.jpg ，许可协议：Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0) ）
 * <p>
 * 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
 * <p>
 * 小时不会以零开头：
 * <p>
 * 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
 * 分钟必须由两位数组成，可能会以零开头：
 * <p>
 * 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：turnedOn = 1
 * 输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 * 示例 2：
 * <p>
 * 输入：turnedOn = 9
 * 输出：[]
 * <p>
 * <p>
 * 解释：
 * <p>
 * 0 <= turnedOn <= 10
 * <p>
 * 题解：由题意可知，小时由 4个比特表示，分钟由 6 个比特表示，比特位值为 0 表示灯灭，为 11 表示灯亮。
 * <p>
 * 我们可以枚举小时的所有可能值 [0,11]，以及分钟的所有可能值 [0,59]，并计算二者的二进制中 1 的个数之和，若为 turnedOn，则将其加入到答案中。
 *
 * @author oudaming
 * @create 2021-06-21 9:39
 **/
public class _401_Binary_Watch {
    public static void main(String[] args) {
        List<String> strings = readBinaryWatch(1);
        strings.forEach(e -> System.out.println(e));
    }

    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn) {
                    ans.add(i + ":" + (j < 10 ? "0" + j : j));
                }
            }
        }
        return ans;
    }
}
