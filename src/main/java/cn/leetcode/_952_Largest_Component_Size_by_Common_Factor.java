package cn.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 952. 按公因数计算最大组件大小
 * <p>
 * 给定一个由不同正整数的组成的非空数组 A，考虑下面的图：
 * <p>
 * 有 A.length 个节点，按从 A[0] 到 A[A.length - 1] 标记；
 * 只有当 A[i] 和 A[j] 共用一个大于 1 的公因数时，A[i] 和 A[j] 之间才有一条边。
 * 返回图中最大连通组件的大小。
 *
 * @author oudaming
 * @date 2021/2/7 0007 22:16
 */
public class _952_Largest_Component_Size_by_Common_Factor {
    public static void main(String[] args) {
        System.out.println(new _952_Largest_Component_Size_by_Common_Factor().largestComponentSize(new int[]{4, 6, 15, 35}));
        System.out.println(new _952_Largest_Component_Size_by_Common_Factor().largestComponentSize(new int[]{20, 50, 9, 63}));
        System.out.println(new _952_Largest_Component_Size_by_Common_Factor().largestComponentSize(new int[]{2, 3, 6, 7, 4, 12, 21, 39}));
    }

    public int largestComponentSize(int[] arr) {
        int N = arr.length;
        List<Integer> init = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            init.add(i);
        }
        UnionSet unionFind = new UnionSet(init);
        // 因子为key的 对应下标为value的值
        HashMap<Integer, Integer> fatorsMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            int limit = (int) Math.sqrt(num);
            for (int j = 1; j <= limit; j++) {
                if (num % j == 0) {
                    if (j != 1) {
                        if (!fatorsMap.containsKey(j)) {
                            fatorsMap.put(j, i);
                        } else {
                            unionFind.union(fatorsMap.get(j), i);
                        }
                    }
                    int other = num / j;
                    if (other != 1) {
                        if (!fatorsMap.containsKey(other)) {
                            fatorsMap.put(other, i);
                        } else {
                            unionFind.union(fatorsMap.get(other), i);
                        }
                    }
                }
            }
        }
        return unionFind.maxSize();
    }


    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionSet<V> {
        // 当前值对应的包装类
        public HashMap<V, Node<V>> nodes = new HashMap<>();
        // 当前节点的父亲节点，优化后表示当前节点对应的集合跟节点
        public HashMap<Node<V>, Node<V>> parents = new HashMap<>();
        // 集合根节点对应的集合的大小
        public HashMap<Node<V>, Integer> sizeMap = new HashMap<>();

        public UnionSet(List<V> values) {
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 从点cur开始，一直往上找，找到不能再往上的代表点，返回
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // cur头节点
            while (!path.isEmpty()) {
                // 优化，将所有节点的父节点直接指向集合的跟节点
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        /**
         * 求最大一个连通区域的大小
         *
         * @return
         */
        public int maxSize() {
            int max = 0;
            for (Integer item : sizeMap.values()) {
                max = Math.max(max, item);
            }
            return max;
        }
    }
}
