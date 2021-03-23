package cn.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 341. 扁平化嵌套列表迭代器
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * <p>
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 * 示例 2:
 * <p>
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 *
 * @author oudaming
 * @date 2021-03-23 9:40
 */
public class _341_Flatten_Nested_List_Iterator {

    public static void main(String[] args) {
        List<NestedInteger> nestedList = new ArrayList<>();
        List<NestedInteger> temp1 = new ArrayList<>();
        temp1.add(new NestedIntegerImp(new Integer(1)));
        temp1.add(new NestedIntegerImp(new Integer(1)));
        nestedList.add(new NestedIntegerImp(temp1));
        nestedList.add(new NestedIntegerImp(new Integer(2)));
        List<NestedInteger> temp2 = new ArrayList<>();
        temp2.add(new NestedIntegerImp(new Integer(1)));
        temp2.add(new NestedIntegerImp(new Integer(1)));
        nestedList.add(new NestedIntegerImp(temp2));

        NestedIterator nestedIterator = new NestedIterator(nestedList);
        while (nestedIterator.hasNext()) {
            System.out.println(nestedIterator.next());
        }
    }


}

class NestedIterator implements Iterator<Integer> {
    private List<Integer> list = new ArrayList<>();
    Iterator<Integer> iterator;

    public NestedIterator(List<NestedInteger> nestedList) {
        f(nestedList, list);
    }

    @Override
    public Integer next() {
        if (iterator == null) {
            iterator = list.listIterator();
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        if (iterator == null) {
            iterator = list.listIterator();
        }
        return iterator.hasNext();
    }

    /**
     * 深度遍历 nestedList 的数据到 list 中
     */
    private void f(List<NestedInteger> nestedList, List<Integer> list) {
        if (nestedList != null && nestedList.size() != 0) {
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    list.add(nestedInteger.getInteger());
                } else {
                    f(nestedInteger.getList(), list);
                }
            }
        }
    }
}

class NestedIntegerImp implements NestedInteger {

    private Object object;

    public NestedIntegerImp(Object object) {
        this.object = object;
    }

    @Override
    public boolean isInteger() {
        return object instanceof Integer;
    }

    @Override
    public Integer getInteger() {
        return (Integer) object;
    }

    @Override
    public List<NestedInteger> getList() {
        return (List<NestedInteger>) object;
    }
}

interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}