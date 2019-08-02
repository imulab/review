package io.imulab.review.strings;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Sorts faster than MSD radix sort. Not stable, but faster.
 */
public class ThreeWayRadixQuickSortTests {

    @Test
    void testSort() {
        String[] target = new String[]{
                "abcde",
                "abc",
                "a",
                "abcd",
                "ab"
        };
        String[] expect = new String[]{
                "a",
                "ab",
                "abc",
                "abcd",
                "abcde"
        };

        ThreeWayRadixQuickSort.sort(target, c -> {
            switch (c) {
                case 'a':
                    return 0;
                case 'b':
                    return 1;
                case 'c':
                    return 2;
                case 'd':
                    return 3;
                case 'e':
                    return 4;
                default:
                    throw new RuntimeException("unexpected character");
            }
        });

        for (int i = 0; i < target.length; i++) {
            Assertions.assertThat(target[i]).isEqualTo(expect[i]);
        }
    }
}
