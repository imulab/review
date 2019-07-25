package io.imulab.review.sort;

public interface Sortable {

    /**
     * @return the total number of items in this sortable target.
     */
    int len();

    /**
     * Swap the item at one index with the item at another index. Both index shall
     * be within the boundary, or an {@link IndexOutOfBoundsException} will be thrown.
     * <p>
     * If both indexes are equal, this should be a no-op.
     *
     * @param i first index
     * @param j second index
     * @throws IndexOutOfBoundsException if one or both indexes is less than 0 or greater
     *                                   or equal to the total number of items.
     */
    void swap(int i, int j);

    /**
     * Check if one item at an index is less than another item at a second index. Both indexes
     * shall be within the boundary, or an {@link IndexOutOfBoundsException} will be thrown.
     *
     * @param i first index
     * @param j second index
     * @return true if item at first index is less than item at second index, false otherwise.
     * @throws IndexOutOfBoundsException if one or both indexes is less than 0 or greater
     *                                   or equal to the total number of items.
     */
    boolean less(int i, int j);

    /**
     * Check if one item at an index is greater than another item at a second index. Both indexes
     * shall be within the boundary, or an {@link IndexOutOfBoundsException} will be thrown.
     *
     * @param i first index
     * @param j second index
     * @return true if item at first index is greater than item at second index, false otherwise.
     * @throws IndexOutOfBoundsException if one or both indexes is less than 0 or greater
     *                                   or equal to the total number of items.
     */
    boolean greater(int i, int j);

    /**
     * Convenience method to check if this target is already sort.
     * @return true if is sorted
     */
    default boolean isSorted(Direction direction) {
        for (int i = 0; i < len() - 1; i++) {
            switch (direction) {
                case ASC:
                    if (greater(i, i+1)) {
                        return false;
                    }
                case DESC:
                    if (less(i, i+1)) {
                        return false;
                    }
            }
        }
        return true;
    }

    /**
     * Marker enumeration to indicate direction of sort.
     */
    enum Direction {
        ASC, DESC
    }
}
