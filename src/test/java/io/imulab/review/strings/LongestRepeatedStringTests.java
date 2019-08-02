package io.imulab.review.strings;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestRepeatedStringTests {

    @Test
    void testCompute() {
        String target = "longest repeated string tests test the longest repeated string";
        String lrs = LongestRepeatedString.compute(target);
        Assertions.assertThat(lrs).isEqualTo("longest repeated string");
    }
}
