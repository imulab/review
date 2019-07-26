package io.imulab.review.sort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListTests {

    @Test
    void testLength() {
        Sortable target = new List<>(1, 2, 3, 4, 5);
        Assertions.assertThat(target.len()).isEqualTo(5);
    }

    @Test
    void testLessThan() {
        Sortable target = new List<>(1, 2, 2, 4, 5);
        Assertions.assertThat(target.less(1, 3)).isTrue();
        Assertions.assertThat(target.less(4, 0)).isFalse();
        Assertions.assertThat(target.less(1, 2)).isFalse();
        Assertions.assertThatThrownBy(() -> target.less(100, 100))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testGreaterThan() {
        Sortable target = new List<>(1, 2, 2, 4, 5);
        Assertions.assertThat(target.greater(1, 3)).isFalse();
        Assertions.assertThat(target.greater(4, 0)).isTrue();
        Assertions.assertThat(target.greater(1, 2)).isFalse();
        Assertions.assertThatThrownBy(() -> target.greater(100, 100))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testSwap() {
        Sortable target = new List<>(1, 2, 3, 4, 5);
        Assertions.assertThat(target.get(1)).isEqualTo(2);
        Assertions.assertThat(target.get(3)).isEqualTo(4);
        target.swap(1, 3);
        Assertions.assertThat(target.get(1)).isEqualTo(4);
        Assertions.assertThat(target.get(3)).isEqualTo(2);
    }

    @Test
    void testSubTarget() {
        Sortable target = new List<>(1, 2, 3, 4, 5);

        Sortable s0 = target.subTarget(1, 3);
        Assertions.assertThat("[2, 3]").isEqualTo(s0.toString());

        Sortable s1 = target.subTarget(0, 5);
        Assertions.assertThat("[1, 2, 3, 4, 5]").isEqualTo(s1.toString());
    }

    @Test
    void testClonedTarget() {
        Sortable target = new List<>(1, 2, 3, 4, 5);
        Sortable cloned = target.clonedTarget();

        Assertions.assertThat("[1, 2, 3, 4, 5]").isEqualTo(cloned.toString());
        Assertions.assertThat(target).isNotSameAs(cloned);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testAssign() {
        Sortable target = new List<>(1, 2, 3, 4, 5);

        target.assign(100, 0);
        target.assign(200, 4);
        target.assign(300, 2);

        Assertions.assertThat(target.get(0)).isEqualTo(100);
        Assertions.assertThat(target.get(1)).isEqualTo(2);
        Assertions.assertThat(target.get(2)).isEqualTo(300);
        Assertions.assertThat(target.get(3)).isEqualTo(4);
        Assertions.assertThat(target.get(4)).isEqualTo(200);
    }

    @Test
    void testToString() {
        Sortable t1 = new List<>(1, 2, 3, 4, 5);
        Assertions.assertThat(t1.toString()).isEqualTo("[1, 2, 3, 4, 5]");

        Sortable t2 = new List<>();
        Assertions.assertThat(t2.toString()).isEqualTo("[]");

        Sortable t3 = new List<>(1);
        Assertions.assertThat(t3.toString()).isEqualTo("[1]");
    }
}
