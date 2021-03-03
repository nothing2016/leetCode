package cn.leetcode;

/**
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * 题解：典型的卡特兰数，符合公式：f(n)= f(0)*f(n-1)+f(1)*f(n-2) + ... + f(n-1)*f(0) (n≥2)
 *
 * @author oudaming
 * @date 2021-03-03 10:40
 */
public class _96_Unique_Binary_Search_Trees {
    public static void main(String[] args) {
        System.out.println(new _96_Unique_Binary_Search_Trees().numTrees(10));
    }

    public int numTrees(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 1;
        ans[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= i - 1; j++) {
                ans[i] += ans[j] * ans[i - 1 - j];
            }
        }
//        for (int i = 0; i <= n; i++) {
//            System.out.print(ans[i] + ",");
//        }
//        System.out.println("=========");
        return ans[n];
    }
}
