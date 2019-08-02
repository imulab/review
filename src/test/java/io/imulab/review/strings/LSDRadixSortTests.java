package io.imulab.review.strings;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class LSDRadixSortTests {

    @Test
    void testSort() {
        String[] target = new String[]{
                "dab",
                "cab",
                "fad",
                "bad",
                "dad",
                "ebb",
                "ace",
                "add",
                "fed",
                "bed",
                "fee",
                "bee",
        };
        String[] expect = new String[]{
                "ace",
                "add",
                "bad",
                "bed",
                "bee",
                "cab",
                "dab",
                "dad",
                "ebb",
                "fad",
                "fed",
                "fee"
        };
        Function<Character, Integer> convertFunc = c -> {
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
                case 'f':
                    return 5;
                default:
                    throw new RuntimeException("unexpected character");
            }
        };

        LSDRadixSort.sort(target, convertFunc);

        for (int i = 0; i < target.length; i++) {
            Assertions.assertThat(target[i]).isEqualTo(expect[i]);
        }
    }
}
