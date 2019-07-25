package io.imulab.review.sort;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Quick sort tries to find a pivot element where elements left to the pivot is less and elements right to the pivot
 * is greater.
 *
 * It does so adopting two cursors in a range. The pivot is the first element within that range. The bottom cursor
 * grows until it is greater than the pivot, and the top cursor decreases until it is less than the pivot. At that
 * moment, swap the two cursors. Keep doing this until the two cursor crosses. Finally, swap the pivot (first element)
 * within the top cursor (which had now went under the bottom cursor). This ensures our invariant.
 *
 * Recursively perform this step for the left and right partitions around the pivot.
 *
 * The performance highly depends on the performance of the shuffle.
 *
 * Time complexity (best) O(NlgN), recursion is O(lgN), partition is O(N)
 * Time complexity (worst) O(N^2) sorting a reversely sorted target, leading to only one partition doing all the work.
 *
 * Space complexity O(N), does not require extra space
 */
public class QuickSort {

    public static void sort(Sortable target) {
        sort(target, Sortable.Direction.ASC);
    }

    public static void sort(Sortable target, Sortable.Direction direction) {
        System.out.println("Before: " + target);

        shuffle(target);
        sort(target, 0, target.len() - 1, direction);

        System.out.println("After: " + target);
    }

    private static void sort(Sortable target, int lowInclusive, int highInclusive, Sortable.Direction direction) {
        if (highInclusive <= lowInclusive)
            return;

        int k = partition(target, lowInclusive, highInclusive, direction);

        sort(target, lowInclusive, k - 1, direction);
        sort(target, k + 1, highInclusive, direction);
    }

    private static int partition(Sortable target, int lowInclusive, int highInclusive, Sortable.Direction direction) {
        int i = lowInclusive, j = highInclusive + 1;

        while (true) {
            // uses ++i/--j because we need to swap them AFTER the cursor movement.
            switch (direction) {
                // in the ascending case:
                // i keeps moving right as long as it is less than the pivot at lowInclusive
                // j keeps moving left as long as it is greater than the pivot at lowInclusive
                case ASC:
                    while (target.greater(lowInclusive, ++i)) {
                        if (i >= highInclusive)
                            break;
                    }
                    while (target.less(lowInclusive, --j)) {
                        if (j <= lowInclusive)
                            break;
                    }
                    break;

                // in the descending case:
                // i keeps moving right as long as it is greater than the pivot at lowInclusive
                // j keeps moving left as long as it is less than the pivot at lowInclusive
                case DESC:
                    while (target.less(lowInclusive, ++i)) {
                        if (i >= highInclusive)
                            break;
                    }
                    while (target.greater(lowInclusive, --j)) {
                        if (j <= lowInclusive)
                            break;
                    }
                    break;
            }

            if (j <= i)
                break;

            // swap the out-of-place items
            target.swap(i, j);
        }

        // swap the pivot into place
        target.swap(lowInclusive, j);

        return j;
    }

    private static void shuffle(Sortable target) {
        for (int i = 0; i < target.len() - 1; i++) {
            int j = ThreadLocalRandom.current().nextInt(i + 1, target.len());
            target.swap(i, j);
        }
    }
}
