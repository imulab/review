package io.imulab.review.sort;

/**
 * The bottom up merge sort views the {@link Sortable} of size N as N numbers of already sorted {@link Sortable} of
 * size 1. Based on that, we will just merge every 2 elements, then every 4 elements, and so on... The sequence of
 * 1, 2, 4, 8... reflects the maximum size of both sub targets that will be merged.
 *
 * Time complexity: O(NlgN), the stepping iteration is O(lgN), merge is still O(N)
 * Space complexity: O(N), only needs space equal to an extra auxiliary array
 */
public class BottomUpMergeSort {

    public static void sort(Sortable target) {
        sort(target, Sortable.Direction.ASC);
    }

    public static void sort(Sortable target, Sortable.Direction direction) {
        System.out.println("Before: " + target);

        for (int N = 1; N < target.len(); N*=2) {
            sort(target, N, direction);
        }

        System.out.println("After: " + target);
    }

    private static void sort(Sortable target, int step, Sortable.Direction direction) {
        for (int low = 0 ; low < target.len() - step; low += step * 2) {
            int mid = low + step - 1;   // the mid index needs to reflect the size of step, always!
            int high = Math.min(low + step + step - 1, target.len() - 1);   // the high index needs to be capped!
            merge(target, low, mid, high, direction);
        }
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    private static void merge(Sortable target, int lowInclusive, int mid, int highInclusive, Sortable.Direction direction) {
        Sortable first = target.subTarget(lowInclusive, mid + 1);
        Sortable second = target.subTarget(mid + 1, highInclusive + 1);

        assert first.isSorted(direction);
        assert second.isSorted(direction);

        int i = 0, j = 0;

        for (int k = lowInclusive; k <= highInclusive; k++) {
            if (i >= first.len()) {
                target.assign(second.get(j++), k);
                continue;
            }

            if (j >= second.len()) {
                target.assign(first.get(i++), k);
                continue;
            }

            switch (direction) {
                case ASC:
                    if (lessThan(first.get(i), second.get(j))) {
                        target.assign(first.get(i++), k);
                    } else {
                        target.assign(second.get(j++), k);
                    }
                    break;

                case DESC:
                    if (greaterThan(first.get(i), second.get(j))) {
                        target.assign(first.get(i++), k);
                    } else {
                        target.assign(second.get(j++), k);
                    }
                    break;
            }
        }
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    private static boolean lessThan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    @SuppressWarnings({"unchecked", "Duplicates"})
    private static boolean greaterThan(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }
}
