package io.imulab.review.sort;

public class Array<T extends Comparable<T>> implements Sortable<T> {

    private final T[] items;

    public Array(T[] items) {
        this.items = items;
    }

    public T[] getItems() {
        return items;
    }

    @Override
    public int len() {
        return items.length;
    }

    @Override
    public void swap(int i, int j) {
        checkBoundary(i);
        checkBoundary(j);

        if (i == j)
            return;

        T temp = this.items[i];
        this.items[i] = this.items[j];
        this.items[j] = temp;
    }

    @Override
    public boolean less(int i, int j) {
        checkBoundary(i);
        checkBoundary(j);

        if (i == j)
            return false;

        return this.items[i].compareTo(this.items[j]) < 0;
    }

    @Override
    public boolean greater(int i, int j) {
        checkBoundary(i);
        checkBoundary(j);

        if (i == j)
            return false;

        return this.items[i].compareTo(this.items[j]) > 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Sortable<T> subTarget(int startInclusive, int endExclusive) {
        checkBoundary(startInclusive);
        checkBoundary(endExclusive - 1);

        if (endExclusive <= startInclusive)
            throw new IllegalArgumentException("end less or equal to start index");

        Comparable[] subItems = new Comparable[endExclusive - startInclusive];
        System.arraycopy(this.items, startInclusive, subItems, 0, endExclusive - startInclusive);

        return new Array(subItems);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Sortable<T> clonedTarget() {
        Comparable[] clonedItems = new Comparable[len()];
        if (len() >= 0) {
            System.arraycopy(this.items, 0, clonedItems, 0, len());
        }
        return new Array(clonedItems);
    }

    @Override
    public void assign(T item, int index) {
        checkBoundary(index);
        this.items[index] = item;
    }

    @Override
    public T get(int index) {
        checkBoundary(index);
        return this.items[index];
    }

    private void checkBoundary(int index) {
        if (index < 0 || index >= this.items.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (len() > 0) {
            for (int i = 0; i < len() - 1; i++) {
                sb.append(this.items[i].toString());
                sb.append(",");
                sb.append(" ");
            }

            sb.append(this.items[len() - 1]);
        }
        sb.append("]");

        return sb.toString();
    }
}
