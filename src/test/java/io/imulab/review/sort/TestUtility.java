package io.imulab.review.sort;

import java.util.concurrent.ThreadLocalRandom;

public class TestUtility {

    public static Integer[] randomIntegerArray(int rangeLow, int rangeHigh, int count) {
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        Integer[] target = new Integer[count];
        for (int i = 0; i < count; i++) {
            target[i] = tlr.nextInt(rangeLow, rangeHigh);
        }
        return target;
    }
}
