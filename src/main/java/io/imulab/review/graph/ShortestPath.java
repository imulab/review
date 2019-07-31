package io.imulab.review.graph;

/**
 * Interface for single source shortest path problem.
 */
public interface ShortestPath {

    /**
     * Return the distance from the source to the destination vertex.
     */
    double distanceTo(int v);

    /**
     * Return an iterable collection of edges, marking the path from source to the destination vertex.
     */
    Iterable<DirectedEdge> pathTo(int v);
}
