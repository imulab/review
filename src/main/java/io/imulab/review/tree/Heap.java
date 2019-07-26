package io.imulab.review.tree;

import io.imulab.review.sort.List;

public class Heap<T extends Comparable<T>> extends List<T> {

    private final Type type;

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> Heap<T> maxHeap() {
        return new Heap(Type.MAX);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> Heap<T> minHeap() {
        return new Heap(Type.MIN);
    }

    public Heap(Type type) {
        super();
        this.type = type;
    }

    public void insert(T item) {
        add(item);
        promote(len() - 1);
        assert isValid();
    }

    public T removeTop() {
        T top = get(0);
        swap(0, len() - 1);
        remove(len() - 1);
        demote(0);
        assert isValid();
        return top;
    }

    private int parentIndexOf(int index) {
        checkBoundary(index);
        return (index - 1) / 2;
    }

    private int leftChildIndexOf(int index) {
        checkBoundary(index);
        return index * 2 + 1;
    }

    private int rightChildIndexOf(int index) {
        checkBoundary(index);
        return index * 2 + 2;
    }

    private int rightSiblingOf(int index) {
        checkBoundary(index);
        return index + 1;
    }

    private void promote(int index) {
        checkBoundary(index);

        switch (this.type) {
            case MAX:
                while (index > 0 && greater(index, parentIndexOf(index))) {
                    swap(index, parentIndexOf(index));
                    index = parentIndexOf(index);
                }
                break;
            case MIN:
                while (index > 0 && less(index, parentIndexOf(index))) {
                    swap(index, parentIndexOf(index));
                    index = parentIndexOf(index);
                }
                break;
        }
    }

    private void demote(int index) {
        checkBoundary(index);

        switch (this.type) {
            case MAX:
                while (leftChildIndexOf(index) < len()) {
                    int k = leftChildIndexOf(index);

                    if (rightSiblingOf(k) < len() && greater(rightSiblingOf(k), k)) {
                        k = rightChildIndexOf(index);
                    }

                    if (!less(index, k)) {
                        break;
                    }

                    swap(index, k);
                    index = k;
                }
                break;

            case MIN:
                while(leftChildIndexOf(index) < len()) {
                    int k = leftChildIndexOf(index);

                    if (rightSiblingOf(k) < len() && less(rightSiblingOf(k), k)) {
                        k = rightChildIndexOf(index);
                    }

                    if (!greater(index, k)) {
                        break;
                    }

                    swap(index, k);
                    index = k;
                }
                break;
        }
    }

    public boolean isValid() {
        for (int i = 0; i < len(); i++) {
            int left = leftChildIndexOf(i);
            int right = rightChildIndexOf(i);
            switch (type) {
                case MAX:
                    if (left < len() && greater(left, i))
                        return false;
                    if (right < len() && greater(right, i))
                        return false;
                    break;
                case MIN:
                    if (left < len() && less(left, i))
                        return false;
                    if (right < len() && less(right, i))
                        return false;
                    break;
            }
        }
        return true;
    }

    public enum Type {
        /**
         * Max heap is where parent is larger than its children
         */
        MAX,
        /**
         * Min heap is where parent is smaller than its children
         */
        MIN
    }
}
