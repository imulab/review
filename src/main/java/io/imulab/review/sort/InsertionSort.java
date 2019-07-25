package io.imulab.review.sort;

/**
 * For a {@link Sortable} target of size N, start from index 0 and gradually go to N - 1. In each iteration, denote
 * the item at this moving index 'p'. Keep swapping 'p' with the item before it 'q' if 'q' is greater than 'p' (in the
 * case of ascending order) or 'q' is less than 'p' (in the case of descending order).
 *
 * What we get is a target, in each iteration, items before 'p' is sorted.
 *
 * Time complexity: O(N^2)
 * Space complexity: O(N)
 */
public class InsertionSort {

    public static void sort(Sortable target) {
        sort(target, Sortable.Direction.ASC);
    }

    public static void sort(Sortable target, Sortable.Direction direction) {
        System.out.println("Before: " + target.toString());

        for (int i = 0; i < target.len(); i++) {
            for (int j = i; j > 0; j--) {
                switch (direction) {
                    case ASC:
                        if (target.greater(j - 1, j)) {
                            target.swap(j - 1 , j);
                        }
                        break;
                    case DESC:
                        if (target.less(j - 1, j)) {
                            target.swap(j - 1 , j);
                        }
                        break;
                }
            }
        }

        System.out.println("After: " + target.toString());
    }
}
