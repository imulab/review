package io.imulab.review.sort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeapSortTests {

    @Test
    @SuppressWarnings("unchecked")
    void testSortAscending() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Run: " + (i+1));
            Sortable target = new Array<>(TestUtility.randomIntegerArray(0, 100, 20));
            HeapSort.sort(target);
            Assertions.assertThat(target.isSorted()).isTrue();
            System.out.println();
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSortDescending() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Run: " + (i+1));
            Sortable target = new Array<>(TestUtility.randomIntegerArray(0, 100, 20));
            HeapSort.sort(target, Sortable.Direction.DESC);
            Assertions.assertThat(target.isSorted(Sortable.Direction.DESC)).isTrue();
            System.out.println();
        }
    }
}
