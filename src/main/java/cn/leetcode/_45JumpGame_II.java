package cn.leetcode;

/**
 * 45. 跳跃游戏 II  力扣
 * leetCode Jump Game II
 *
 * @author oudaming
 * @date 2020/11/14 0014 09:31
 */
public class _45JumpGame_II {
    public int jump(int[] nums) {
        // 跳的步数
        int jump = 0;
        // 当前jump步数能跳的最大下标
        int currentJumpMaxIndex = 0;
        //  下一个步数 jump + 1 能跳的最大下标
        int nextJumpMaxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > currentJumpMaxIndex) {
                jump++;
                currentJumpMaxIndex = nextJumpMaxIndex;
            }
            nextJumpMaxIndex = Math.max(nextJumpMaxIndex, i + nums[i]);
        }
        return jump;
    }

    public static void main(String[] args) {
//        Input: nums = [2,3,1,1,4]
//        Output: 2

        System.out.println(new _45JumpGame_II().jump(new int[]{2, 3, 4, 3, 6, 1}));


    }
}
