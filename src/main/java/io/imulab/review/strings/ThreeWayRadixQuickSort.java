package io.imulab.review.strings;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ThreeWayRadixQuickSort {

    public static void sort(String[] target, Function<Character, Integer> charToIndexFunc) {
        sort(target, 0, target.length - 1, 0, (str, d) -> {
            if (d < str.length())
                return charToIndexFunc.apply(str.charAt(d));
            return -1;
        });
    }

    private static void sort(String[] target, int low, int high, int d, BiFunction<String, Integer, Integer> charAtFunc) {
        if (high <= low)
            return;

        // partition so that:
        // low to lt-1 is less than pivot
        // lt to gt is equal to pivot
        // gt+1 to high is greater than pivot
        int v = charAtFunc.apply(target[low], d);   // pivot

        int lt = low, gt = high;
        int i = low + 1;
        while (i <= gt) {
            int w = charAtFunc.apply(target[i], d); // cursor

            // the cursor lt moves the pivot to the right until it is grouped with elements = pivot
            // the cursor i moves to the right, one ahead of the pivot cursor lt until it hits element = pivot, then
            // it gradually moves right and becomes the first of the region elements > pivot
            // gt gradually moves left to meet with i and terminates the loop
            if (v > w) {
                swap(target, lt++, i++);
            } else if (v < w) {
                swap(target, i, gt--);
            } else {
                i++;
            }
        }

        sort(target, low, lt-1, d, charAtFunc);
        if (v >= 0) {
            sort(target, lt, gt, d+1, charAtFunc);
        }
        sort(target, gt+1, high, d, charAtFunc);
    }

    private static void swap(String[] target, int i, int j) {
        String temp = target[i];
        target[i] = target[j];
        target[j] = temp;
    }
}
