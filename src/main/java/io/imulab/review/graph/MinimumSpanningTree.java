package io.imulab.review.graph;

/**
 * Minimum spanning tree is a sub graph to a edge weighted graph that connects all vertices, has no loops and has the
 * minimum total weight.
 */
public interface MinimumSpanningTree {

    /**
     * Return the edges in this MST
     */
    Iterable<Edge> edges();

    /**
     * Return the total weight of the MST
     */
    double weight();
}
