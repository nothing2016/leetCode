package cn.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字已经存在，则变更其数据值；
 * 如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶：你是否可以在O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 输入
 * ["LRUCache","put","put","get","put","get","put","get","get","get"]
 * [[2],[1,0],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
 * <p>
 * 输出：[null,null,null,0,null,-1,null,-1,3,4]
 *
 * @author oudaming
 * @date 2021-03-08 17:26
 */
public class _146_LRU_Cache {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 0);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));


    }

    private static void printLinkList(LinkedList<Node> queue) {
        while (!queue.isEmpty()) {
            Node info = queue.pollFirst();
            System.out.println(info.val);
        }
    }


}


/**
 * 这种写法很low,只能到达O(N)
 */
class LRUCache {
    private static class Node {
        Integer val;
        Integer key;

        public Node(Integer val) {
            this.val = val;
        }

        public Node(Integer val, Integer key) {
            this.val = val;
            this.key = key;
        }
    }

    // 缓存的大小
    private int capacity;
    private LinkedList<Node> queue;
    private Map<Integer, Node> map = new HashMap<>();
    private int size = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node != null) {
            queue.remove(node);
            queue.addFirst(node);
        }
        return node == null ? -1 : node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(value, key);
            queue.addFirst(node);
            map.put(key, node);
            size++;
            if (size > capacity) {
                Node node1 = queue.removeLast();
                map.remove(node1.key);
                size--;
            }
        } else {
            // 这里的删除时O(N)的，遍历删除，所以需要自己实现队列
            queue.remove(node);
            node.val = value;
            queue.addFirst(node);
            map.put(key, node);
        }
    }
}

/**
 * 这个才是正解 O(1)
 */
class LRUCache2 {

    private MyCache<Integer, Integer> cache;

    public LRUCache2(int capacity) {
        cache = new MyCache<>(capacity);
    }

    public int get(int key) {
        Integer ans = cache.get(key);
        return ans == null ? -1 : ans;
    }

    public void put(int key, int value) {
        cache.set(key, value);
    }

    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> last;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class NodeDoubleLinkedList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        public NodeDoubleLinkedList() {
            head = null;
            tail = null;
        }

        public void addNode(Node<K, V> newNode) {
            if (newNode == null) {
                return;
            }
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.last = tail;
                tail = newNode;
            }
        }

        public void moveNodeToTail(Node<K, V> node) {
            if (tail == node) {
                return;
            }
            // node 不是尾巴
            if (head == node) {
                head = node.next;
                head.last = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.last = tail;
            node.next = null;
            tail.next = node;
            tail = node;
        }

        public Node<K, V> removeHead() {
            if (head == null) {
                return null;
            }
            Node<K, V> res = head;
            if (head == tail) { // 链表中只有一个节点的时候
                head = null;
                tail = null;
            } else {
                head = res.next;
                res.next = null;
                head.last = null;
            }
            return res;
        }

    }

    public static class MyCache<K, V> {
        private HashMap<K, Node<K, V>> keyNodeMap;
        private NodeDoubleLinkedList<K, V> nodeList;
        private final int capacity;

        public MyCache(int cap) {
            if (cap < 1) {
                throw new RuntimeException("should be more than 0.");
            }
            keyNodeMap = new HashMap<K, Node<K, V>>();
            nodeList = new NodeDoubleLinkedList<K, V>();
            capacity = cap;
        }

        public V get(K key) {
            if (keyNodeMap.containsKey(key)) {
                Node<K, V> res = keyNodeMap.get(key);
                nodeList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        public void set(K key, V value) {
            if (keyNodeMap.containsKey(key)) {
                Node<K, V> node = keyNodeMap.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);
            } else {
                if (keyNodeMap.size() == capacity) {
                    removeMostUnusedCache();
                }
                Node<K, V> newNode = new Node<K, V>(key, value);
                keyNodeMap.put(key, newNode);
                nodeList.addNode(newNode);
            }
        }

        private void removeMostUnusedCache() {
            Node<K, V> removeNode = nodeList.removeHead();
            keyNodeMap.remove(removeNode.key);
        }

    }

}