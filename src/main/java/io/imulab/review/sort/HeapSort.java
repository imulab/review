package io.imulab.review.sort;

import io.imulab.review.tree.Heap;

/**
 * Heap sort treats a sortable target as a binary heap. Normally, it is separated into two stages.
 *
 * In this first stage, we work our way up from half of the target and try demote every element. The result is that
 * the target is rearranged as a binary heap.
 *
 * In the second stage, we remove the top element and put it into place to form the final sorted target.
 *
 * This implementation simplifies this process by inserting directly to a separate heap so stage one is not necessary.
 * It then copies every top element back so maintaining a capacity cursor to limit the demotion process during the top
 * removal operation is not necessary.
 *
 * Time Complexity O(2NlgN), because each stage takes O(NlgN); in the best case (already sorted), the first stage is a
 * no-op, hence the best time complexity becomes O(NlgN)
 * Space Complexity, normally O(N) because it can be performed in place; this implementation does not reflect that.
 */
public class HeapSort {

    public static <T extends Comparable<T>> void sort(Sortable<T> target) {
        sort(target, Sortable.Direction.ASC);
    }

    public static <T extends Comparable<T>> void sort(Sortable<T> target, Sortable.Direction direction) {
        System.out.println("Before: " + target);

        Heap<T> heap = null;
        switch (direction) {
            case ASC:
                heap = Heap.minHeap();
                break;
            case DESC:
                heap = Heap.maxHeap();
                break;
        }

        for (int i = 0; i < target.len(); i++) {
            heap.insert(target.get(i));
        }

        for (int i = 0; i < target.len(); i++) {
            target.assign(heap.removeTop(), i);
        }

        System.out.println("After: " + target);
    }
}
