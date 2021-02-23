package cn.leetcode;

/**
 * 1052. 爱生气的书店老板
 * 今天，书店老板有一家店打算试营业customers.length分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续X 分钟不生气，但却只能使用一次。
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 * <p>
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * <p>
 * 题解：先把确定满意的顾客加起来。然后滑动窗口计算可以额外增加的最大满意顾客数。
 * 注意：这里就是将窗口内把1变成0，窗口外相邻的是0或1，我们并不需要关心
 *
 * @author oudaming
 * @date 2021-02-23 10:20
 */
public class _1052_Grumpy_Bookstore_Owner {
    public static void main(String[] args) {
        System.out.println(maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3));
        System.out.println(maxSatisfied(new int[]{2, 0, 2, 2}, new int[]{1, 1, 1, 1}, 2));
        System.out.println(maxSatisfied(new int[]{5, 8}, new int[]{0, 1}, 1));
    }

    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int N = customers.length;
        // 1.先把确定满意的顾客加起来
        int total = 0;
        for (int i = 0; i < customers.length; i++) {
            total += (1 - grumpy[i]) * customers[i];
        }
        // 2. 找到滑动窗口内最大的不满意顾客
        // 滑动窗口内最大的累加不满意的值
        int maxUn = 0;
        for (int i = 0; i < X; i++) {
            // grumpy[i] == 1时是不满意的，0时满意的
            maxUn += grumpy[i] * customers[i];
        }
        // tmp记录每次窗口的值
        int tmp = maxUn;
        for (int i = X; i < N; i++) {
            // 1, 0, 1, 2, 1, 1, 7, 5
            // 0, 1, 0, 1, 0, 1, 0, 1
            // 窗口r右滑，只有grumpy[i] == 1才能拿到不满意的值
            tmp = tmp + (grumpy[i] * customers[i]);
            // 窗口l右滑，只有grumpy[i] == 1才能拿到不满意的值
            tmp = tmp - (grumpy[i - X] * customers[i - X]);

            // 结算
            maxUn = Math.max(maxUn, tmp);
        }
        return total + maxUn;
    }
}
