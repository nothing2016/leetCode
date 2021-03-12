package cn.leetcode;

/**
 * 331. 验证二叉树的前序序列化
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 * <p>
 * -  -    _9_
 * -  -   /   \
 * - -   3     2
 * - -  / \   / \
 * -- 4   1  #  6
 * -/ \ / \   / \
 * #  # # #   #   #
 * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 * <p>
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 * <p>
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 * <p>
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "1,#"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: "9,#,#,1"
 * 输出: false
 * <p>
 * 题解：前序遍历中，最后一个字符一定是整颗树的最后一个节点，将整个数变成一个图，有如下的规律
 * 1.根节点的出度为2，入度为0
 * 2.一般节点，入度+1，出度+2
 * 3.叶子节点，入度+2，出入+0
 * 4.只有遍历结束后，出度的总和 == 入度的总和，如果遍历的过程中“出度的总和 == 入度的总和”，那么
 * 一定是非法的二叉树（原因是一旦相等，证明就到了树的尾部，但是依然还有字符，证明是非法的）
 *
 * @author oudaming
 * @date 2021/3/12 0012 23:06
 */
public class _331_Verify_Preorder_Serialization_of_a_Binary_Tree {
    public static void main(String[] args) {
        System.out.println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println(isValidSerialization("1,#"));
        System.out.println(isValidSerialization("9,#,#,1"));
    }

    public static boolean isValidSerialization(String preorder) {
        String[] str = preorder.split(",");
        if (str.length == 1) {
            if ("#".equals(str[0])) {
                return true;
            } else {
                return false;
            }
        }
        // 长度大于2,第一个节点又是null,这颗树一定不合格
        if ("#".equals(str[0])) {
            return false;
        }
        int N = str.length;
        int in = 0;
        int out = 2;
        for (int i = 1; i < N; i++) {
            if ("#".equals(str[i])) {
                in++;
            } else {
                out += 2;
                in++;
            }
            if (i != N - 1 && out == in) {
                return false;
            }
        }
        return out == in;
    }
}
