package io.imulab.review.sort;

/**
 * Merge sort works by dividing the target into a left portion and a right portion. Assuming both portions are also
 * sorted, they can be merged together to create a also sorted target. In order to achieve such assumption, the division
 * is done recursively until the left and right is only one element each.
 *
 * Note the merge process requires extra space, we need to copy both portions out and then merge them back in to the
 * original target.
 *
 * Time complexity: O(NlgN), Merge process is O(N), Recursive process is O(lgN)
 * Space complexity: O(N), Actually requiring O(2N), but this is still O(N)
 */
public class MergeSort {

    public static void sort(Sortable target) {
        sort(target, Sortable.Direction.ASC);
    }

    public static void sort(Sortable target, Sortable.Direction direction) {
        System.out.println("Before: " + target.toString());

        sort(target, 0, target.len() - 1, direction);

        System.out.println("After: " + target.toString());
    }

    private static void sort(Sortable target, int lowInclusive, int highInclusive, Sortable.Direction direction) {
        if (highInclusive <= lowInclusive)
            return;

        int mid = (lowInclusive + highInclusive) / 2;

        sort(target, lowInclusive, mid, direction);
        sort(target, mid + 1, highInclusive, direction);

        merge(target, lowInclusive, mid, highInclusive, direction);
    }

    @SuppressWarnings("unchecked")
    private static void merge(Sortable target, int lowInclusive, int mid, int highInclusive, Sortable.Direction direction) {
        assert lowInclusive <= mid && mid <= highInclusive;

        Sortable first = target.subTarget(lowInclusive, mid + 1);
        Sortable second = target.subTarget(mid + 1, highInclusive + 1);

        assert first.isSorted(direction);
        assert second.isSorted(direction);

        int i = 0, j = 0;

        for (int k = lowInclusive; k <= highInclusive; k++) {

            // first is exhausted, unload the remaining of the second
            if (i >= first.len()) {
                target.assign(second.get(j++), k);
                continue;
            }

            // second is exhausted, unload the remaining of the first
            if (j >= second.len()) {
                target.assign(first.get(i++), k);
                continue;
            }

            // when ascending, add the lesser one first
            // when descending, add the greater one first
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

    @SuppressWarnings("unchecked")
    private static boolean lessThan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    @SuppressWarnings("unchecked")
    private static boolean greaterThan(Comparable a, Comparable b) {
        return a.compareTo(b) > 0;
    }
}
