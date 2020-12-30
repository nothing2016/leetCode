package cn.leetcode;

import java.util.*;

/**
 * 429  N 叉树的层序遍历
 *
 * @author oudaming
 * @date 2020/12/30 0030 21:59
 */
public class _429NAryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        Node root = new Node();
        root.val = 1;
        List<Node> children = new ArrayList<>();
        Node one = new Node();
        one.val = 2;
        Node two = new Node();
        two.val = 3;
        children.add(one);
        children.add(two);
        root.children = children;
        List<List<Integer>> lists = new _429NAryTreeLevelOrderTraversal().levelOrder2(root);
        System.out.println(lists);
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        // 当前节点在哪一层
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(root, 1);
        // 记录每一层的结果
        LinkedHashMap<Integer, List<Integer>> map = new LinkedHashMap<>();
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            List<Integer> integers = map.getOrDefault(curNodeLevel, new ArrayList<>());
            integers.add(cur.val);
            map.put(curNodeLevel, integers);
//            System.out.println(cur.val + "," + curNodeLevel);
            List<Node> children = cur.children;
            if (children != null && children.size() != 0) {
                for (Node child : children) {
                    queue.add(child);
                    levelMap.put(child, curNodeLevel + 1);
                }
            }
        }
        for (List<Integer> integers : map.values()) {
            result.add(integers);
        }
        return result;
    }

    /**
     * 目前还有错误
     * [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * 输出：
     * [[1],[2,3,4,5],[6,7],[8],[9,10],[11],[12],[13],[14]]
     * 预期：
     * [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        result.add(Arrays.asList(root.val));
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            List<Node> children = cur.children;
            if (children != null && children.size() != 0) {
                List<Integer> list = new ArrayList<>();
                for (Node child : children) {
                    list.add(child.val);
                    queue.add(child);
                }
                result.add(list);
            }
        }
        return result;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};