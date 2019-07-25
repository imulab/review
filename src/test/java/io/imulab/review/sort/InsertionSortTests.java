package io.imulab.review.sort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class InsertionSortTests {

    @Test
    void testSortAscending() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Run: " + (i+1));
            Sortable target = new Array<>(TestUtility.randomIntegerArray(0, 100, 20));
            InsertionSort.sort(target);
            Assertions.assertThat(target.isSorted()).isTrue();
            System.out.println();
        }
    }

    @Test
    void testSortDescending() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Run: " + (i+1));
            Sortable target = new Array<>(TestUtility.randomIntegerArray(0, 100, 20));
            InsertionSort.sort(target, Sortable.Direction.DESC);
            Assertions.assertThat(target.isSorted(Sortable.Direction.DESC)).isTrue();
            System.out.println();
        }
    }
}
