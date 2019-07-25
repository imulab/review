package io.imulab.review.sort;

/**
 * For a {@link Sortable} target of size N, start from index 0. Pick the minimum (or maximum, depending on
 * the sort direction) item in the range of [0, N) and swap it with the current item.
 *
 * Time complexity O(N^2)
 * Space complexity O(N)
 */
public class SelectionSort {

    public static void sort(Sortable target, Sortable.Direction direction) {
        System.out.println("Before: " + target.toString());

        for (int i = 0; i < target.len() - 1; i++) {
            switch (direction) {
                case ASC:
                    target.swap(i, getMinimumIndex(target, i));
                    break;
                case DESC:
                    target.swap(i, getMaximumIndex(target, i));
                    break;
            }
        }

        System.out.println("After: " + target.toString());
    }

    public static void sort(Sortable target) {
        sort(target, Sortable.Direction.ASC);
    }

    private static int getMinimumIndex(Sortable target, int startInclusive) {
        int minIndex = startInclusive;
        for (int i = startInclusive; i < target.len(); i++) {
            if (target.less(i, minIndex)) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static int getMaximumIndex(Sortable target, int startInclusive) {
        int maxIndex = startInclusive;
        for (int i = startInclusive; i < target.len(); i++) {
            if (target.greater(i, maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
