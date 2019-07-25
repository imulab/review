package io.imulab.review.sort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SelectionSortTests {

    @Test
    void testSortAscending() {
        Sortable target = new Array<>(TestUtility.randomIntegerArray(0, 100, 20));
        SelectionSort.sort(target, Sortable.Direction.ASC);
        Assertions.assertThat(target.isSorted(Sortable.Direction.ASC)).isTrue();
    }
}
