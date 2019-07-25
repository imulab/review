package io.imulab.review.sort;

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
