package io.imulab.review.tree;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class AVLTreeTests {

    @Test
    void testPutAndGetAndDelete() {
        List<Integer> elements = Arrays.asList(1, 10, 3, 44, 7, 11);
        SymbolTable<Integer, Integer> table = new AVLTree<>();

        for (int i = 0; i < elements.size(); i++) {
            Integer e = elements.get(i);
            table.put(e, e);
            Assertions.assertThat(table.get(e).isPresent()).isTrue();
            Assertions.assertThat(table.get(e).get()).isEqualTo(e);
            Assertions.assertThat(table.size()).isEqualTo(i + 1);
        }

        for (int i = 0; i < elements.size(); i++) {
            Integer e = elements.get(i);
            table.delete(e);
            Assertions.assertThat(table.get(e).isPresent()).isFalse();
            Assertions.assertThat(table.size()).isEqualTo(elements.size() - i - 1);
        }
    }
}
