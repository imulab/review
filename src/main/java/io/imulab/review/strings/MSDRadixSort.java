package io.imulab.review.strings;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * The most significant digit radix sort solves the problem in LSD that all elements must have same length.
 *
 * MSD solves this by introducing the -1 index. The character index computing function can return -1 index when the
 * string is shorter that the indexed length. To collaborate with this change, the count array is now R+2 (R being the
 * size of the domain), one for the extra cumulative space and one to compensate for the -1 index as all indexes are
 * plus one when stored in the count array.
 *
 * In general, MSD works by sorting a range of the array based on the d-th index, then recursively sort each segment,
 * based on the count array, for the d+1-th index.
 *
 * It is somewhat deficient when sorting small ranges of sub-arrays as the count array must cover the entire domain. In
 * practice, when hi-lo is too small, it is sometimes substituted with insertion sort.
 *
 * Time complexity: O(N*W) N is the target size, W is the average width
 * Space complexity: N+DR   D is the average depth of recursion, R is the size of the domain
 */
public class MSDRadixSort {

    public static void sort(String[] target, Function<Character, Integer> charToIndexFunc) {
        BiFunction<String, Integer, Integer> charAtFunc = (str, d) -> {
            if (d < str.length())
                return charToIndexFunc.apply(str.charAt(d));
            return -1;
        };

        String[] aux = new String[target.length];
        sort(target, aux, 0, target.length - 1, 0, charAtFunc);
    }

    /**
     * Sort a range of the target array, with respect to the d-th index.
     *
     * @param target        target array to be sorted
     * @param aux           auxiliary array to copy work-in-progress data to
     * @param low           low boundary of the range to be sorted (inclusive)
     * @param high          high boundary of the range to be sorted (inclusive)
     * @param d             character index of each word in the target array to compare
     * @param charAtFunc    a function to calculate the sort index from the word and a character index
     */
    private static void sort(String[] target, String[] aux, int low, int high, int d, BiFunction<String, Integer, Integer> charAtFunc) {
        if (high <= low)
            return;

        int R = 256;
        // R+2 together with charAtFunc solves the issue where some string
        // is shorted than others. When a string is depleted at index d, we
        // just return -1 to have it ranked before the least item (which would
        // have index 0). Naturally, we will need to plus one when indexing them
        // in the count array. This requires one extra space, hence R+2.
        int[] count = new int[R + 2];

        // calculate frequency
        for (int i = low; i <= high; i++) {
            int countIndex = charAtFunc.apply(target[i], d);
            assert countIndex >= -1 && countIndex < R;
            // plus one for cumulative, plus two for -1 index
            count[countIndex + 2]++;
        }

        // build cumulative
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        // assign position
        for (int i = low; i <= high; i++) {
            int countIndex = charAtFunc.apply(target[i], d);
            assert countIndex >= -1 && countIndex < R;
            aux[count[countIndex + 1]++] = target[i];
        }

        // copy back
        for (int i = low; i <= high; i++) {
            target[i] = aux[i - low];
        }

        // recurse to every segment within the d-th sorted array, for the d+1-th character
        for (int r = 0; r < R; r++) {
            sort(target, aux, low + count[r], low + count[r + 1] - 1, d + 1, charAtFunc);
        }
    }
}
