package io.imulab.review.graph;

public interface Graph {

    /**
     * Add an edge between two vertices. An {@link IndexOutOfBoundsException} if any vertex index is out of bounds.
     *
     * @param v index of the first vertex
     * @param w index of the second vertex.
     */
    void addEdge(int v, int w);

    /**
     * Get all adjacent vertices of a vertex. An {@link IndexOutOfBoundsException} will be thrown if the vertex is
     * out of bounds.
     *
     * @param v index of the vertex
     * @return an iterable collection of all connected vertices.
     */
    Iterable<Integer> adjacent(int v);

    /**
     * Get the number of connected vertices to a vertex. An {@link IndexOutOfBoundsException} will be thrown if the
     * vertex is out of bounds.
     *
     * @param v index of the vertex
     * @return number of connected vertices.
     */
    int degree(int v);

    /**
     * Get the total number of vertices in this graph.
     */
    int V();

    /**
     * Get the total number of edges in this graph.
     */
    int E();
}
