package io.imulab.review.graph;

/**
 * The algorithm that answers the question: "is the two vertices connected".
 */
public class ConnectedComponents {

    private final Graph graph;
    private final boolean[] marked;
    private final int[] cc;
    private int id;

    ConnectedComponents(Graph g) {
        this.graph = g;
        this.marked = new boolean[g.V()];
        this.cc = new int[g.V()];
        this.id = 0;

        traverse();
    }

    private void traverse() {
        for (int v = 0; v < graph.V(); v++) {
            if (marked[v]) {
                continue;
            }
            dfs(v);
            id++;
        }
    }

    private void dfs(int v) {
        marked[v] = true;
        cc[v] = id;
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
        return cc[v] == cc[w];
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
        return cc[v];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= graph.V())
            throw new IndexOutOfBoundsException(index + " is out of bounds");
    }
}
