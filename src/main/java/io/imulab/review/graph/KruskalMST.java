package io.imulab.review.graph;

import io.imulab.review.misc.UnionFind;
import io.imulab.review.tree.Heap;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Sort the edges by their weight and add them to the list of edges as long as they don't form a cycle. Do this until we
 * form a minimum spanning tree.
 */
public class KruskalMST implements MinimumSpanningTree {

    private Queue<Edge> mst = new LinkedList<>();

    public KruskalMST(EdgeWeightedGraph graph) {
        Heap<Edge> minPQ = Heap.minHeap();
        for (Edge e : graph.edges()) {
            minPQ.insert(e);
        }

        // union find is used to detect loops
        UnionFind uf = new UnionFind(graph.V());
        while (!minPQ.isEmpty() && mst.size() < graph.V() - 1) {
            Edge e = minPQ.removeTop();
            int v = e.either();
            int w = e.other(v);

            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.offer(e);
            }
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
