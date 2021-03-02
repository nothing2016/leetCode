package cn.leetcode;

/**
 * 98. 验证二叉搜索树
 * <p>
 * 输入：[5,4,6,null,null,3,7]
 * 输出：false
 *
 * @author oudaming
 * @date 2021-03-02 17:49
 */
public class _98_Validate_Binary_Search_Tree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        System.out.println(new _98_Validate_Binary_Search_Tree().isValidBST(node1));
//        System.out.println(new _98_Validate_Binary_Search_Tree().isValidBST(null));
    }

    public boolean isValidBST(TreeNode root) {
        Info info = process(root);
        return info.isOk;
    }


    public Info process(TreeNode root) {
        return null;
//        if (root == null) {
//            return ;
//        }
//        TreeNode left = root.left;
//        TreeNode right = root.right;
//        if (left != null && left.val >= root.val) {
//            return false;
//        }
//        if (right != null && right.val <= root.val) {
//            return false;
//        }
//        return isValidBST(root.left) && isValidBST(root.right);
    }

    private static class Info {
        int max;
        int min;
        boolean isOk;

        public void info(int max, int min, boolean isOk) {
            this.max = max;
            this.min = min;
            this.isOk = isOk;
        }
    }
}
