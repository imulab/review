package io.imulab.review.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Directed graph
 */
@SuppressWarnings("Duplicates")
public class DiGraph {

    private final Vertex[] graph;

    DiGraph(int V) {
        this.graph = new Vertex[V];
        for (int v = 0; v < graph.length; v++) {
            this.graph[v] = new Vertex();
        }
    }

    void addEdge(int v, int w) {
        checkIndex(v);
        checkIndex(w);

        graph[v].vertices.add(w);
    }

    Iterable<Integer> adjacent(int v) {
        checkIndex(v);
        return graph[v].vertices;
    }

    int degree(int v) {
        checkIndex(v);
        return graph[v].vertices.size();
    }

    int V() {
        return graph.length;
    }

    int E() {
        int count = 0;
        for (int v = 0; v < V(); v++) {
            count += degree(v);
        }
        return count;
    }

    DiGraph reverse() {
        DiGraph reversed = new DiGraph(V());
        for (int v = 0; v < V(); v++) {
            for (int w : adjacent(v)) {
                reversed.addEdge(w, v);
            }
        }
        return reversed;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= graph.length)
            throw new IndexOutOfBoundsException(index + " is out of bounds.");
    }

    /**
     * Wrapper class around generic array vertices because Java does not allow generic array creation.
     */
    private class Vertex {
        final Set<Integer> vertices = new HashSet<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("----- DiGraph -----\n");
        for (int v = 0; v < this.V(); v++) {
            for (int w : adjacent(v)) {
                sb.append("\t");
                sb.append(v);
                sb.append(" -> ");
                sb.append(w);
                sb.append("\n");
            }
        }
        sb.append("-------------------\n");

        return sb.toString();
    }
}
