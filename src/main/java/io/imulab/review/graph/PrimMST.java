package io.imulab.review.graph;

import io.imulab.review.tree.Heap;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The Prim algorithm starts by adding the minimum edge of the first vertex onto the tree. Then adding the minimum of
 * all edges that is connected to one vertex on the tree to the tree (of course, the added edge must have a vertex that
 * is not on the tree, thus not effectively creating a loop)
 */
public class PrimMST implements MinimumSpanningTree {

    /**
     * A boolean array to keep track of all vertices on the tree.
     */
    private boolean[] marked;

    /**
     * A queue to collect minimum spanning tree edges
     */
    private Queue<Edge> mst = new LinkedList<>();

    /**
     * A minimum heap to obtain the minimum to all candidate edges.
     */
    private Heap<Edge> minPQ = Heap.minHeap();

    public PrimMST(EdgeWeightedGraph graph) {
        marked = new boolean[graph.V()];

        // collect edges connected to vertex 0 as candidates
        candidate(graph, 0);

        while (!minPQ.isEmpty() && mst.size() < graph.V() - 1) {
            Edge e = minPQ.removeTop();
            int v = e.either();
            int w = e.other(v);

            // this edge is already in the tree
            if (marked[v] && marked[w])
                continue;

            // add edge to the tree
            mst.offer(e);

            // offer the newly joined vertex from the new edge to be considered.
            if (marked[v]) {
                candidate(graph, w);
            }
            if (marked[w]) {
                candidate(graph, v);
            }
        }
    }

    private void candidate(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge edge : graph.adjacent(v)) {
            // don't consider edges that is already on the tree (both vertices marked)
            if (!marked[edge.other(v)])
                minPQ.insert(edge);
        }

    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        double count = 0d;
        for (Edge e : edges()) {
            count += e.weight();
        }
        return count;
    }
}
