package io.imulab.review.sort;

public class Array<T extends Comparable<T>> implements Sortable {

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

        T temp = this.items[i];
        this.items[i] = this.items[j];
        this.items[j] = temp;
    }

    @Override
    public boolean less(int i, int j) {
        checkBoundary(i);
        checkBoundary(j);

        return this.items[i].compareTo(this.items[j]) < 0;
    }

    @Override
    public boolean greater(int i, int j) {
        checkBoundary(i);
        checkBoundary(j);

        return this.items[i].compareTo(this.items[j]) > 0;
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
