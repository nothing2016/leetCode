package cn.leetcode;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * <p>
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有next 指针都被设置为 NULL。
 * <p>
 * 题解： 还是使用宽度优先遍历，但要定义一个自己的队列，使用有限的三个变量(head,tail,size),这里的方法非常的妙，
 * 通过每次poll弹出的时候，将当前层连接在一起，又通过size得到当前层的大小，这样两层之间就不会串在一起
 *
 * @author oudaming
 * @date 2021-03-05 9:44
 */
public class _116_Populating_Next_Right_Pointers_in_Each_Node {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        Node connect = connect(node1);
        System.out.println(connect.val);
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static class MyQueue {
        public Node head;
        public Node tail;
        public int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void offer(Node cur) {
            size++;
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
        }

        public Node poll() {
            size--;
            Node ans = head;
            head = head.next;
            ans.next = null;
            return ans;
        }

    }

    public static Node connect(Node root) {
        if (root == null) {
            return root;
        }
        MyQueue queue = new MyQueue();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 第一个弹出的节点
            Node pre = null;
            // size的大小也是当前遍历层的大小
            int size = queue.size;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (pre != null) {
                    // 这里的方法非常的妙，通过每次poll弹出的时候，将当前层连接在一起，又通过size得到当前层的大小，这样两层之间就不会串在一起
                    pre.next = cur;
                }
                pre = cur;
            }
        }
        return root;
    }
}
