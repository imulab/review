package io.imulab.review.strings;

import java.util.function.Function;

/**
 * Key indexed counting decouples order from the sorted elements. It relies on an explicit or implicit mapping from the
 * elements to be sorted to a range of index from 0 to R, where R is the number of unique elements.
 *
 * With the order determined, we can calculate a frequency map to determine where the elements should go in the final
 * order. With this domain knowledge, we can decrease the time complexity to almost linear.
 *
 * Time complexity: O(N+R)
 * Space complexity: O(N+R)
 */
public class KeyIndexedCounting {

    /**
     * Perform key indexed sorting
     * @param target            the target array to be sorted
     * @param indexConversion   function to convert a string in the target array to an 0 based index. The smallest
     *                          item must resolve to 0 and next item to 1 and so on. The biggest item must resolve
     *                          to R - 1
     * @param R                 number of unique items in the target array
     */
    public static void sort(String[] target, Function<String, Integer> indexConversion, int R) {
        // count array has R+1 space because it is used as a cumulative
        // frequency array, so we leave one extra space for 0.
        //
        // the count array stands for "how many items are before this element",
        // which can be used to determine the appropriate sorted index for the element.
        //
        // For example, if count[1] = 3, this means there are 3 items with converted index 0.
        // Assuming this element is a, then should have target[0] = a, target[1] = a, target[2] = a.
        // This is how count[0] is used. count[0] = 0, it can be used to increment to suite this need.
        int[] count = new int[R+1];

        // calculate how frequent each element appears
        for (int i = 0; i < target.length; i++) {
            int countArrayIndex = indexConversion.apply(target[i]);
            assert countArrayIndex >= 0 && countArrayIndex < R;
            count[countArrayIndex+1]++;
        }

        // construct the cumulative count
        for (int i = 0; i < R; i++) {
            count[i+1] += count[i];
        }

        // copy items to aux array
        String[] aux = new String[target.length];
        for (int i = 0; i < target.length; i++) {
            int countArrayIndex = indexConversion.apply(target[i]);
            assert countArrayIndex >= 0 && countArrayIndex < R;

            // find the appropriate sorted index using the count array index and assign to
            // position. Increment the sorted index by 1 to prepare for additional same element.
            aux[count[countArrayIndex]++] = target[i];
        }

        // copy it back
        for (int i = 0; i < target.length; i++) {
            target[i] = aux[i];
        }
    }
}
