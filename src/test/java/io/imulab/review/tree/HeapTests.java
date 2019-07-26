package io.imulab.review.tree;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeapTests {

    @Test
    void testInsert() {
        Heap<String> heap = Heap.maxHeap();
        heap.insert("A");
        heap.insert("E");
        heap.insert("G");
        heap.insert("I");
        heap.insert("N");
        heap.insert("H");
        heap.insert("O");
        heap.insert("T");
        heap.insert("P");
        heap.insert("R");
        Assertions.assertThat(heap.isValid()).isTrue();
    }

    @Test
    void testRemove() {
        Heap<String> heap = Heap.maxHeap();
        heap.insert("A");
        heap.insert("E");
        heap.insert("G");
        heap.insert("I");
        heap.insert("N");
        heap.insert("H");
        heap.insert("O");
        heap.insert("T");
        heap.insert("P");
        heap.insert("R");
        Assertions.assertThat(heap.removeTop()).isEqualTo("T");
        Assertions.assertThat(heap.isValid()).isTrue();
    }
}
