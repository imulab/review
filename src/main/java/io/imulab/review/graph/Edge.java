package io.imulab.review.graph;

/**
 * Explicit edge with weights.
 *
 * Edges in undirected graphs and directed graphs are really implicit and entails only the concept of connection. Edges
 * in a more generic graph (e.g. used in minimum spanning tree calculation) must entail weights in addition to
 * connectivity.
 */
public class Edge implements Comparable<Edge> {

    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double wieght) {
        if (v == w)
            throw new IllegalArgumentException("self loop edge not allowed");
        this.v = v;
        this.w = w;
        this.weight = wieght;
    }

    /**
     * Returns either vertex.
     */
    int either() {
        return v;
    }

    /**
     * Returns the vertex other than the provided vertex.
     */
    int other(int v) {
        if (v == this.v)
            return this.w;
        else if (v == this.w)
            return this.v;
        else
            throw new IllegalArgumentException(v + " is not on the edge");
    }

    /**
     * Return the weight of the edge.
     */
    double weight() {
        return this.weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight < o.weight)
            return -1;
        else if (this.weight > o.weight)
            return 1;
        else
            return 0;
    }

    @Override
    public int hashCode() {
        return Math.min(v, w) * 31 + Math.max(v, w);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Edge))
            return false;

        Edge other = (Edge) obj;
        return (v == other.v && w == other.w) || (v == other.w && w == other.v);
    }

    @Override
    public String toString() {
        return v + " -- " + w;
    }
}
