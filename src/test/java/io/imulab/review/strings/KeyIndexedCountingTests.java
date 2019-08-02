package io.imulab.review.strings;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class KeyIndexedCountingTests {

    @Test
    void testKeyIndexedCountingSort() {
        String[] target = new String[]{"d", "a", "c", "f", "f", "b", "d", "b", "f", "b", "e", "a"};
        String[] expect = new String[]{"a", "a", "b", "b", "b", "c", "d", "d", "e", "f", "f", "f"};
        int R = 6;
        Function<String, Integer> convertFunc = s -> {
            switch (s) {
                case "a":
                    return 0;
                case "b":
                    return 1;
                case "c":
                    return 2;
                case "d":
                    return 3;
                case "e":
                    return 4;
                case "f":
                    return 5;
                default:
                    throw new RuntimeException("unexpected element");
            }
        };

        KeyIndexedCounting.sort(target, convertFunc, R);

        for (int i = 0; i < target.length; i++) {
            Assertions.assertThat(target[i]).isEqualTo(expect[i]);
        }
    }
}
