package cn.leetcode;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，
 * 所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * <p>
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * <p>
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 *
 * @author oudaming
 * @date 2021/3/11 0011 22:37
 */
public class _167_Two_Sum_II_Input_array_is_sorted {
    public static void main(String[] args) {

//        int[] ints = new _167_Two_Sum_II_Input_array_is_sorted().twoSum(new int[]{2, 7, 11, 15}, 9);
//        System.out.println(ints[0] + "," + ints[1]);
//        int[] ints1 = new _167_Two_Sum_II_Input_array_is_sorted().twoSum(new int[]{2, 3, 4}, 6);
//        System.out.println(ints1[0] + "," + ints1[1]);
//        int[] ints2 = new _167_Two_Sum_II_Input_array_is_sorted().twoSum(new int[]{-1, 0}, -1);
//        System.out.println(ints2[0] + "," + ints2[1]);

        int[] ints3 = new _167_Two_Sum_II_Input_array_is_sorted().twoSum(new int[]{5, 25, 75}, 100);
        System.out.println(ints3[0] + "," + ints3[1]);
    }

    public int[] twoSum(int[] numbers, int target) {
        int N = numbers.length;
        int l = 0;
        int r = N - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                break;
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{l + 1, r + 1};
    }
}
