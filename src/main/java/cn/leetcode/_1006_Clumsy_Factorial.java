package cn.leetcode;

import java.util.LinkedList;

/**
 * 1006. 笨阶乘
 * <p>
 * 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
 * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。
 * <p>
 * 例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。
 * <p>
 * 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
 * <p>
 * 实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：4
 * 输出：7
 * 解释：7 = 4 * 3 / 2 + 1
 * 示例 2：
 * <p>
 * 输入：10
 * 输出：12
 * 解释：12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 10000
 * -2^31 <= answer <= 2^31 - 1  （答案保证符合 32 位整数。）
 * <p>
 * 题解：
 * 思路1：先拼接简单表达式，然后使用 227. 基本计算器 II的算法将结果计算出来
 * 思路2：直接使用双向队列来解决这个问题，定义一个队列queue，
 * 1.依次将N,N-1,N-2...2 的值放到队列里
 * 2.依次将 * / + - 放到队列里
 * 3.放数字和操作符的时候，是交替的，如N=5，先将5放到队列末尾，再放* 到队列末尾，再放4到末尾。。。。。。
 * 4.如果放 数字的时候，发现队列的末尾是 + - 的时候，将值直接入队列末尾
 * 5.如果放 数字的时候，发现队列的末尾是 * / 的时候，就将队列中最新的数字和当前数字计算一次，然后将计算后的值直接入队列末尾
 * 6.最后将1单独放到队列末尾
 * 7.现在队列中都是数字和 + - ，这样我们将队列中的值计算一遍就是最终的答案了
 *
 * @author oudaming
 * @date 2021-04-01 9:36
 */
public class _1006_Clumsy_Factorial {
    public static void main(String[] args) {
        System.out.println(clumsy(4));
        System.out.println(clumsy2(4));

        System.out.println(clumsy(1));
        System.out.println(clumsy2(1));

        System.out.println(clumsy(2));
        System.out.println(clumsy2(2));

        System.out.println(clumsy(10));
        System.out.println(clumsy2(10));
    }


    public static int clumsy2(int N) {
        int cur = 0;
        LinkedList<String> queue = new LinkedList<>();

        for (int i = N; i >= 2; i--) {
            cur = cur % 4;
            String op;
            if (cur == 0) {
                op = "*";
            } else if (cur == 1) {
                op = "/";
            } else if (cur == 2) {
                op = "+";
            } else {
                op = "-";
            }
            addNum(queue, i);
            queue.addLast(op);
            cur++;
        }
        addNum(queue, 1);
        int num = getNum(queue);
        return num;
    }

    /**
     * 使用表达式的解法，很复杂
     *
     * @param N
     * @return
     */
    public static int clumsy(int N) {
        int cur = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = N; i >= 2; i--) {
            cur = cur % 4;
            builder.append(i);
            if (cur == 0) {
                builder.append('*');
            } else if (cur == 1) {
                builder.append('/');
            } else if (cur == 2) {
                builder.append('+');
            } else {
                builder.append('-');
            }
            cur++;
        }
        builder.append(1);
        String s = builder.toString();
        return calculate(s);
    }

    public static int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        s = s.replaceAll(" ", "");
        char[] chars = s.toCharArray();
        int[] ans = process(chars, 0);
        return ans[0];
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    public static int[] process(char[] str, int i) {
        LinkedList<String> que = new LinkedList<String>();
        int cur = 0;
        int[] bra = null;
        // 从i出发，开始撸串
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 遇到的是运算符号
                addNum(que, cur);
                que.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else { // 遇到左括号了
                bra = process(str, i + 1);
                cur = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(que, cur);
        return new int[]{getNum(que), i};
    }

    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top);
            } else {
                cur = Integer.valueOf(que.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        que.addLast(String.valueOf(num));
    }

    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }
}
