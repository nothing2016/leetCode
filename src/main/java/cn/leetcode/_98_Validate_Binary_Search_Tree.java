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
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(8);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        TreeNode node = new TreeNode(2147483647);
        System.out.println(new _98_Validate_Binary_Search_Tree().isValidBST(node1));
//        System.out.println(new _98_Validate_Binary_Search_Tree().isValidBST(null));
    }

    public boolean isValidBST(TreeNode root) {
        Info info = process(root);
        return info.isOk;
    }


    public Info process(TreeNode root) {
        if (root == null) {
            return new Info(null, null, true);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        if (leftInfo.isOk && rightInfo.isOk && (leftInfo.max == null || root.val > leftInfo.max) && (rightInfo.min == null || root.val < rightInfo.min)) {
            int max = rightInfo.max == null ? root.val : rightInfo.max;
            int min = leftInfo.min == null ? root.val : leftInfo.min;
            return new Info(max, min, true);
        } else {
            return new Info(null, null, false);
        }
    }

    private static class Info {
        // 整个树上的最大值
        Integer max;
        // 整个树上的最小值
        Integer min;
        // 是否是搜索二叉树
        boolean isOk;

        public Info(Integer max, Integer min, boolean isOk) {
            this.max = max;
            this.min = min;
            this.isOk = isOk;
        }
    }

}

