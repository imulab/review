package io.imulab.review.graph;

import io.imulab.review.tree.Heap;

import java.util.Stack;

/**
 * Dijkstra's Algorithm computes a shortest path tree in any edge weighted graph with non-negative weights.
 *
 * The algorithm starts from the source and computes a number of edges which are ranked by their tentative distance
 * to its "from" vertex. The edge that provides the minimum distance is evaluated to see it can contribute to decrease
 * the total minimum distance to the source. If it can, then continue to compute the adjacent edges to its "to" vertex.
 */
public class DijkstraSP implements ShortestPath {

    /**
     * An array marks the shortest path to an indexed vertex.
     */
    private DirectedEdge[] edgeTo;

    /**
     * An array marks the shortest total distance from the source to the indexed vertex. For example, if
     * distanceTo[3] = 50, then the distance from the source to vertex #4 is 50.
     */
    private double[] distanceTo;

    /**
     * A min priority queue used to get the smallest weighted edge.
     */
    private Heap<VertexWrapper> pq = Heap.minHeap();

    public DijkstraSP(EdgeWeightedDiGraph graph, int source) {
        if (source < 0 || source >= graph.V())
            throw new IllegalArgumentException("source is out of bounds");

        edgeTo = new DirectedEdge[graph.V()];
        distanceTo = new double[graph.V()];

        // initialize unvisited distance to positive infinity
        for (int v = 0; v < graph.V(); v++) {
            distanceTo[v] = Double.POSITIVE_INFINITY;
        }
        distanceTo[source] = 0d;

        // start from source, relax each edge
        pq.insert(new VertexWrapper(source, 0d));
        while (!pq.isEmpty()) {
            int v = pq.removeTop().vertex;
            for (DirectedEdge e : graph.adjacent(v)) {
                relax(e);
            }
        }
    }

    /**
     * Relaxation checks if a given edge would contribute a shorter path to the "to" vertex.
     */
    private void relax(DirectedEdge edge) {
        int v = edge.from();
        int w = edge.to();

        double tentativeDistance = distanceTo[v] + edge.weight();

        if (distanceTo[w] > tentativeDistance) {
            // update edge and tentative distance
            distanceTo[w] = tentativeDistance;
            edgeTo[w] = edge;

            VertexWrapper t = new VertexWrapper(w, tentativeDistance);
            if (pq.contains(t)) {
                pq.update(t);
            } else {
                pq.insert(t);
            }
        }
    }

    @Override
    public double distanceTo(int v) {
        return distanceTo[v];
    }

    @Override
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v))
            return null;

        Stack<DirectedEdge> paths = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            paths.push(e);
        }

        // due to the implementation of Java's stack iterator, we need to reverse it again
        Stack<DirectedEdge> reverse = new Stack<>();
        while (!paths.isEmpty()) {
            reverse.push(paths.pop());
        }

        return reverse;
    }

    public boolean hasPathTo(int v) {
        return distanceTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * A wrapper around an indexed vertex so that it can be compared through its tentative distance and still
     * maintains the equality rule based on the vertex's index.
     *
     * We do this so that we don't have to implement a separate IndexedKeyPriorityQueue
     */
    private class VertexWrapper implements Comparable<VertexWrapper> {

        final int vertex;
        final double distanceToMe;

        public VertexWrapper(int vertex, double key) {
            this.vertex = vertex;
            this.distanceToMe = key;
        }

        @Override
        public int compareTo(VertexWrapper o) {
            if (distanceToMe < o.distanceToMe)
                return -1;
            else if (distanceToMe > o.distanceToMe)
                return 1;
            else
                return 0;
        }

        @Override
        public int hashCode() {
            return 31 * vertex;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (!(obj instanceof VertexWrapper))
                return false;

            VertexWrapper that = (VertexWrapper) obj;
            return this.vertex == that.vertex;
        }
    }
}
