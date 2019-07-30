package io.imulab.review.graph;

import io.imulab.review.sort.TopologicalSort;

/**
 * The algorithm that answers the question: "is the two vertices inter-connected?". Vertex v and Vertex w are considered
 * strongly connected if and only if v has a path to w and w also has a path to v.
 * <p>
 * First, compute the reversed post order of the reversed graph G' (this is done by doing a topological sort on G'). Second,
 * run DFS on the reversed post order, similar to when computing connected components.
 */
@SuppressWarnings("Duplicates")
public class StrongConnectedComponents {

    private final DiGraph graph;
    private final boolean[] marked;
    private final int[] scc;
    private int id;

    StrongConnectedComponents(DiGraph g) {
        this.graph = g;
        this.marked = new boolean[g.V()];
        this.scc = new int[g.V()];
        this.id = 0;

        Iterable<Integer> vertices = TopologicalSort.sort(this.graph.reverse());
        for (int v : vertices) {
            if (!marked[v]) {
                dfs(v);
                id++;
            }
        }
    }

    private void dfs(int v) {
        marked[v] = true;
        scc[v] = id;
        for (int w : graph.adjacent(v)) {
            if (!marked[w]) {
                dfs(w);
            }
        }
    }

    /**
     * Whether the vertices are connected in the graph.
     */
    boolean isConnected(int v, int w) {
        checkIndex(v);
        checkIndex(w);
        return scc[v] == scc[w];
    }

    /**
     * The number of connected components in a graph.
     */
    int count() {
        return id;
    }

    /**
     * The identifier for the component the vertex is in.
     */
    int id(int v) {
        checkIndex(v);
        return scc[v];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= graph.V())
            throw new IndexOutOfBoundsException(index + " is out of bounds");
    }
}
