package io.imulab.review.graph;

import java.util.HashSet;
import java.util.Set;

public class EdgeWeightedGraph {

    private final Vertex[] graph;

    EdgeWeightedGraph(int V) {
        this.graph = new Vertex[V];
        for (int v = 0; v < V; v++) {
            this.graph[v] = new Vertex();
        }
    }

    /**
     * Add an edge to the graph.
     */
    void addEdge(Edge o) {
        int v = o.either();
        int w = o.other(v);

        checkIndex(v);
        checkIndex(w);

        graph[v].edges.add(o);
        graph[w].edges.add(o);
    }

    /**
     * Return all edges adjacent to this vertex.
     */
    Iterable<Edge> adjacent(int v) {
        checkIndex(v);
        return graph[v].edges;
    }

    /**
     * Return all edges in this graph.
     */
    Iterable<Edge> edges() {
        Set<Edge> edges = new HashSet<>();
        for (int v = 0; v < V(); v++) {
            edges.addAll(graph[v].edges);
        }
        return edges;
    }

    /**
     * Return the number of vertices in this graph.
     */
    int V() {
        return graph.length;
    }

    /**
     * Return the number of edges in this graph.
     */
    int E() {
        int count = 0;
        for (int v = 0; v < V(); v++) {
            count += graph[v].edges.size();
        }
        return count / 2;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= V())
            throw new IndexOutOfBoundsException(index + " is out of bounds");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("----- EdgeWeightedGraph -----");
        for (Edge edge : edges()) {
            sb.append("\t");
            sb.append(edge.toString());
            sb.append("\n");
        }
        sb.append("-----------------------------");

        return sb.toString();
    }

    private class Vertex {
        final Set<Edge> edges = new HashSet<>();
    }
}
