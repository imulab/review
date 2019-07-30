package io.imulab.review.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * An undirected graph
 */
public class UnDiGraph implements Graph {

    private final Vertex[] graph;

    public UnDiGraph(int V) {
        this.graph = new Vertex[V];
        for (int v = 0; v < graph.length; v++) {
            this.graph[v] = new Vertex();
        }
    }

    @Override
    public void addEdge(int v, int w) {
        checkIndex(v);
        checkIndex(w);

        // undirected graph add vertices both ways.
        graph[v].vertices.add(w);
        graph[w].vertices.add(v);
    }

    @Override
    public Iterable<Integer> adjacent(int v) {
        checkIndex(v);
        return graph[v].vertices;
    }

    @Override
    public int degree(int v) {
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

    @Override
    public int V() {
        return graph.length;
    }

    @Override
    public int E() {
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

    public static UnDiGraph exampleGraph() {
        UnDiGraph g = new UnDiGraph(6);
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
