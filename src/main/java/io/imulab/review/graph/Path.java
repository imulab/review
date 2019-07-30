package io.imulab.review.graph;

public interface Path {

    /**
     * Get the source index.
     */
    int getSource();

    /**
     * Whether this has a path from source to the destination.
     *
     * @param v destination index.
     */
    boolean hasPathTo(int v);

    /**
     * Get an iterable collection containing paths from source to the destination.
     *
     * @param v destination index.
     */
    Iterable<Integer> pathTo(int v);
}
