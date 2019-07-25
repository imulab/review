package io.imulab.review.sort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayTests {

    @Test
    void testLength() {
        Sortable target = new Array<>(new Integer[]{1, 2, 3, 4, 5});
        Assertions.assertThat(target.len()).isEqualTo(5);
    }

    @Test
    void testLessThan() {
        Sortable target = new Array<>(new Integer[]{1, 2, 2, 4, 5});
        Assertions.assertThat(target.less(1, 3)).isTrue();
        Assertions.assertThat(target.less(4, 0)).isFalse();
        Assertions.assertThat(target.less(1, 2)).isFalse();
        Assertions.assertThatThrownBy(() -> target.less(100, 100))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testGreaterThan() {
        Sortable target = new Array<>(new Integer[]{1, 2, 2, 4, 5});
        Assertions.assertThat(target.greater(1, 3)).isFalse();
        Assertions.assertThat(target.greater(4, 0)).isTrue();
        Assertions.assertThat(target.greater(1, 2)).isFalse();
        Assertions.assertThatThrownBy(() -> target.greater(100, 100))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSwap() {
        Array target = new Array<>(new Integer[]{1, 2, 3, 4, 5});
        Assertions.assertThat(target.getItems()[1]).isEqualTo(2);
        Assertions.assertThat(target.getItems()[3]).isEqualTo(4);
        target.swap(1, 3);
        Assertions.assertThat(target.getItems()[1]).isEqualTo(4);
        Assertions.assertThat(target.getItems()[3]).isEqualTo(2);
    }

    @Test
    void testSubTarget() {
        Sortable target = new Array<>(new Integer[]{1, 2, 3, 4, 5});

        Sortable s0 = target.subTarget(1, 3);
        Assertions.assertThat("[2, 3]").isEqualTo(s0.toString());

        Sortable s1 = target.subTarget(0, 5);
        Assertions.assertThat("[1, 2, 3, 4, 5]").isEqualTo(s1.toString());
    }

    @Test
    void testClonedTarget() {
        Sortable target = new Array<>(new Integer[]{1, 2, 3, 4, 5});
        Sortable cloned = target.clonedTarget();

        Assertions.assertThat("[1, 2, 3, 4, 5]").isEqualTo(cloned.toString());
        Assertions.assertThat(target).isNotSameAs(cloned);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAssign() {
        Array target = new Array<>(new Integer[]{1, 2, 3, 4, 5});

        target.assign(100, 0);
        target.assign(200, 4);
        target.assign(300, 2);

        Assertions.assertThat(target.getItems()[0]).isEqualTo(100);
        Assertions.assertThat(target.getItems()[1]).isEqualTo(2);
        Assertions.assertThat(target.getItems()[2]).isEqualTo(300);
        Assertions.assertThat(target.getItems()[3]).isEqualTo(4);
        Assertions.assertThat(target.getItems()[4]).isEqualTo(200);
    }

    @Test
    void testToString() {
        Array t1 = new Array<>(new Integer[]{1, 2, 3, 4, 5});
        Assertions.assertThat(t1.toString()).isEqualTo("[1, 2, 3, 4, 5]");

        Array t2 = new Array<>(new Integer[]{});
        Assertions.assertThat(t2.toString()).isEqualTo("[]");

        Array t3 = new Array<>(new Integer[]{1});
        Assertions.assertThat(t3.toString()).isEqualTo("[1]");
    }
}
