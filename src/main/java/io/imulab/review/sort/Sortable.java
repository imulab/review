package io.imulab.review.sort;

public interface Sortable<T extends Comparable<T>> {

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
     * Produces a range of the target identified by the indexes. Both indexes shall be within the
     * boundary, or an {@link IndexOutOfBoundsException} will be thrown.
     *
     * @param startInclusive start index of the range (inclusive)
     * @param endExclusive end index of the range (exclusive)
     * @return a new target with elements in the specified range
     */
    Sortable<T> subTarget(int startInclusive, int endExclusive);

    /**
     * Completely clone a target.
     *
     * @return cloned target with all elements inside.
     */
    Sortable<T> clonedTarget();

    /**
     * Arbitrarily assign an item to a position. Index shall remain in bound, or {@link IndexOutOfBoundsException}
     * will be thrown.
     *
     * @param item item
     * @param index index
     */
    void assign(T item, int index);

    /**
     * Get an item at an index. Index shall remain in bound, or {@link IndexOutOfBoundsException}.
     *
     * @param index index
     * @return item at index
     */
    T get(int index);

    /**
     * Convenience method to check if this target is already sort.
     * @return true if is sorted, false otherwise
     */
    default boolean isSorted(Direction direction) {
        for (int i = 0; i < len() - 1; i++) {
            switch (direction) {
                case ASC:
                    if (greater(i, i+1)) {
                        return false;
                    }
                    break;
                case DESC:
                    if (less(i, i+1)) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    /**
     * Convenience method to check if this target is already sort in the ascending direction.
     * @return true if is sorted, false otherwise
     */
    default boolean isSorted() {
        return isSorted(Direction.ASC);
    }

    /**
     * Marker enumeration to indicate direction of sort.
     */
    enum Direction {
        ASC, DESC
    }
}
