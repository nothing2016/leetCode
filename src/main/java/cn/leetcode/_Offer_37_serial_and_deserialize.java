package cn.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * <p>
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * <p>
 * <p>
 * 题解： 使用前序遍历来实现，注意，后续遍历是无法实现的
 *
 * @author oudaming
 * @create 2021-06-30 9:40
 **/
public class _Offer_37_serial_and_deserialize {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//
//        node1.left = node2;
//        node1.right = node3;
        Codec codec = new Codec();
        TreeNode deserialize = codec.deserialize(codec.serialize(null));
        System.out.println(deserialize);
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder ans = new StringBuilder();
        if (root == null) {
            return "";
        }
        pre(root, ans);
        return ans.toString().substring(1);

    }

    private void pre(TreeNode root, StringBuilder ans) {
        if (root == null) {
            ans.append("_" + "#");
            return;
        }
        ans.append("_" + root.val);
        pre(root.left, ans);
        pre(root.right, ans);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }
        String[] s = data.split("_");
        Queue<String> queue = new LinkedList<>();
        for (String i : s) {
            queue.add(i);
        }
        TreeNode root = buildByPre(queue);
        return root;
    }

    private TreeNode buildByPre(Queue<String> queue) {
        String poll = queue.poll();
        if ("#".equals(poll)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.valueOf(poll));
        node.left = buildByPre(queue);
        node.right = buildByPre(queue);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));