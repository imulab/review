package io.imulab.review.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * An undirected graph
 */
public class Graph {

    private final Vertex[] graph;

    public Graph(int V) {
        this.graph = new Vertex[V];
        for (int v = 0; v < graph.length; v++) {
            this.graph[v] = new Vertex();
        }
    }

    /**
     * Add an edge between two vertices. An {@link IndexOutOfBoundsException} if any vertex index is out of bounds.
     *
     * @param v index of the first vertex
     * @param w index of the second vertex.
     */
    void addEdge(int v, int w) {
        checkIndex(v);
        checkIndex(w);

        // undirected graph add vertices both ways.
        graph[v].vertices.add(w);
        graph[w].vertices.add(v);
    }

    /**
     * Get all adjacent vertices of a vertex. An {@link IndexOutOfBoundsException} will be thrown if the vertex is
     * out of bounds.
     *
     * @param v index of the vertex
     * @return an iterable collection of all connected vertices.
     */
    Iterable<Integer> adjacent(int v) {
        checkIndex(v);
        return graph[v].vertices;
    }

    /**
     * Get the number of connected vertices to a vertex. An {@link IndexOutOfBoundsException} will be thrown if the
     * vertex is out of bounds.
     *
     * @param v index of the vertex
     * @return number of connected vertices.
     */
    int degree(int v) {
        checkIndex(v);
        return graph[v].vertices.size();
    }

    /**
     * Get the number of self loops in this graph.
     */
    int selfLoops() {
        int count = 0;
        for (int v = 0; v < V(); v++) {
            for (int w : adjacent(v)) {
                if (w == v) {
                    count++;
                }
            }
        }

        // divide by 2 because undirected graphs always count twice.
        return count / 2;
    }

    /**
     * Get the total number of vertices in this graph.
     */
    int V() {
        return graph.length;
    }

    /**
     * Get the total number of edges in this graph.
     */
    int E() {
        int count = 0;
        for (int v = 0; v < V(); v++) {
            count += degree(v);
        }

        // divide by 2 because undirected graphs always count twice.
        return count / 2;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= graph.length)
            throw new IndexOutOfBoundsException(index + " is out of bounds.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("----- Graph -----\n");
        for (int v = 0; v < this.V(); v++) {
            for (int w : adjacent(v)) {
                sb.append("\t");
                sb.append(v);
                sb.append(" - ");
                sb.append(w);
                sb.append("\n");
            }
        }
        sb.append("-----------------\n");

        return sb.toString();
    }

    /**
     * Wrapper class around generic array vertices because Java does not allow generic array creation.
     */
    private class Vertex {
        final Set<Integer> vertices = new HashSet<>();
    }

    public static Graph exampleGraph() {
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 5);
        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 2);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 2);
        g.addEdge(4, 3);
        g.addEdge(5, 3);
        g.addEdge(5, 0);
        return g;
    }
}
