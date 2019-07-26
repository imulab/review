package io.imulab.review.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class List<T extends Comparable<T>> implements Sortable<T> {

    private final ArrayList<T> list;

    public List() {
        this.list = new ArrayList<>();
    }

    public List(T... items) {
        this();
        this.list.addAll(Arrays.asList(items));
    }

    @Override
    public int len() {
        return this.list.size();
    }

    @Override
    public void swap(int i, int j) {
        checkBoundary(i);
        checkBoundary(j);

        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    @Override
    public boolean less(int i, int j) {
        checkBoundary(i);
        checkBoundary(j);

        return list.get(i).compareTo(list.get(j)) < 0;
    }

    @Override
    public boolean greater(int i, int j) {
        checkBoundary(i);
        checkBoundary(j);

        return list.get(i).compareTo(list.get(j)) > 0;
    }

    @Override
    public Sortable<T> subTarget(int startInclusive, int endExclusive) {
        checkBoundary(startInclusive);
        checkBoundary(endExclusive - 1);

        List<T> newList = new List<>();
        newList.list.addAll(list.subList(startInclusive, endExclusive));
        return newList;
    }

    @Override
    public Sortable<T> clonedTarget() {
        List<T> newList = new List<>();
        newList.list.addAll(list);
        return newList;
    }

    @Override
    public void assign(T item, int index) {
        checkBoundary(index);
        list.set(index, item);
    }

    @Override
    public T get(int index) {
        checkBoundary(index);
        return list.get(index);
    }

    protected void add(T item) {
        list.add(item);
    }

    protected void remove(int index) {
        checkBoundary(index);

        ArrayList<T> wip = new ArrayList<>();
        ListIterator<T> itr = list.listIterator();
        while (itr.hasNext()) {
            if (itr.nextIndex() == index) {
                itr.next();
            }
            // double check because we may have skipped one
            if (itr.hasNext()) {
                wip.add(itr.next());
            }
        }

        list.clear();
        list.addAll(wip);
    }

    protected void checkBoundary(int index) {
        if (index < 0 || index >= this.list.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.nextIndex() < len()) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }
}
