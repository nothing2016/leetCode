package cn.leetcode;

/**
 * 55. 跳跃游戏
 * leetCode Jump Game II
 *
 * @author oudaming
 * @date 2020/11/14 0014 09:31
 */
public class _55JumpGame {
    public boolean canJump(int[] nums) {
        // 跳的步数
        int jump = 0;
        // 当前jump步数能跳的最大下标
        int currentJumpMaxIndex = 0;
        //  下一个步数 jump + 1 能跳的最大下标
        int nextJumpMaxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > currentJumpMaxIndex) {
                jump++;
                if (currentJumpMaxIndex != nextJumpMaxIndex) {
                    currentJumpMaxIndex = nextJumpMaxIndex;
                } else {
                    return false;
                }
            }
            nextJumpMaxIndex = Math.max(nextJumpMaxIndex, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
//        Input: nums = [2,3,1,1,4]
//        Output: true
//        Input: nums = [3,2,1,0,4]
//        Output: false

        System.out.println(new _55JumpGame().canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(new _55JumpGame().canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(new _55JumpGame().canJump(new int[]{3, 2, 1, 0}));


    }
}
