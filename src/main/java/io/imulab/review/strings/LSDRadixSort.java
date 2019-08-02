package io.imulab.review.strings;

import java.util.function.Function;

@SuppressWarnings("Duplicates")
public class LSDRadixSort {

    public static void sort(String[] target, Function<Character, Integer> indexFunc) {
        if (target == null || target.length < 1)
            return;

        int R = 256;    // java char is 2 byte, so 2^16 possibilities
        int W = widthCheck(target);

        // move from least significant digit (LSD)
        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R+1];

            // calculate frequency
            for (int i = 0; i < target.length; i++) {
                int countIndex = indexFunc.apply(target[i].charAt(d));
                assert countIndex >= 0 && countIndex < R;
                count[countIndex+1]++;
            }

            // build cumulative frequency
            for (int i = 0; i < R; i++) {
                count[i+1] += count[i];
            }

            // put elements into place
            String[] aux = new String[target.length];
            for (int i = 0; i < target.length; i++) {
                int countIndex = indexFunc.apply(target[i].charAt(d));
                assert countIndex >= 0 && countIndex < R;
                aux[count[countIndex]++] = target[i];
            }

            // copy elements back
            for (int i = 0; i < target.length; i++) {
                target[i] = aux[i];
            }
        }
    }

    // check the input target has uniform width
    private static int widthCheck(String[] target) {
        int w = target[0].length();
        for (int i = 1; i < target.length; i++) {
            if (target[i].length() != w)
                throw new IllegalArgumentException("non-uniform length");
        }
        return w;
    }
}
