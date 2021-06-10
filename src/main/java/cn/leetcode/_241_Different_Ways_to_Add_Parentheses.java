package cn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 241. 为运算表达式设计优先级
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。
 * 你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2:
 * <p>
 * 输入: "2*3-4*5"
 * 输出: [-34, -14, -10, -10, 10]
 * 解释:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * <p>
 * 题解： 本题解 采用了 分治 的思想：
 * <p>
 * 1.遍历 字符串
 * 2.遇到操作符，就将左右两边的字符串，分别当作 两个表达式
 * <p>
 * 对于一个形如 x op y（op 为运算符，x 和 y 为数） 的算式而言，它的结果组合取决于 x 和 y 的结果组合数，而 x 和 y 又可以写成形如 x op y 的算式。
 * 因此，该问题的子问题就是 x op y 中的 x 和 y：以运算符分隔的左右两侧算式解。
 * 然后我们来进行 分治算法三步走：
 * <p>
 * 分解：按运算符分成左右两部分，分别求解
 * 解决：实现一个递归函数，输入算式，返回算式解
 * 合并：根据运算符合并左右两部分的解，得出最终解
 */
public class _241_Different_Ways_to_Add_Parentheses {
    public static void main(String[] args) {
        List<Integer> integers = diffWaysToCompute("1+2-1+3");
        for (Integer i : integers) {
            System.out.print(i + ",");
        }
    }

    public static List<Integer> diffWaysToCompute(String input) {
        if (input == null || input.length() <= 0) {
            return new ArrayList<Integer>();
        }

        // 当前表达式的结果
        ArrayList<Integer> curRes = new ArrayList<Integer>();
        int length = input.length();
        char[] charArray = input.toCharArray();
        for (int i = 0; i < length; i++) {
            char aChar = charArray[i];
            if (aChar == '+' || aChar == '-' || aChar == '*') { // 当前字符为 操作符
                List<Integer> leftList = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightList = diffWaysToCompute(input.substring(i + 1));
                for (int leftNum : leftList) {
                    for (int rightNum : rightList) {
                        // 这里是结果的组合
                        if (aChar == '+') {
                            curRes.add(leftNum + rightNum);
                        } else if (aChar == '-') {
                            curRes.add(leftNum - rightNum);
                        } else {
                            curRes.add(leftNum * rightNum);
                        }
                    }
                }

            }
        }
        // 如果出现 一个数比如 123，这种情况，没有运算符了，必须要处理的
        // 如 1 + 3 - 123  ，这个123是一定被分治成(1 + 3) - (123)两个部分的，所以必须要处理一下
        if (curRes.isEmpty()) {
            curRes.add(Integer.valueOf(input));
        }
        return curRes;
    }

}
