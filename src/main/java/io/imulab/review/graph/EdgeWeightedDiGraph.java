package io.imulab.review.graph;

import java.util.HashSet;
import java.util.Set;

public class EdgeWeightedDiGraph {

    private final Vertex[] vertices;

    public EdgeWeightedDiGraph(int V) {
        this.vertices = new Vertex[V];
        for (int v = 0; v < V; v++) {
            this.vertices[v] = new Vertex();
        }
    }

    public int V() {
        return vertices.length;
    }

    public int E() {
        int count = 0;
        for (int v = 0; v < V(); v++) {
            count += vertices[v].edges.size();
        }
        return count;
    }

    public void addEdge(DirectedEdge edge) {
        checkIndex(edge.from());
        vertices[edge.from()].edges.add(edge);
    }

    Iterable<DirectedEdge> adjacent(int v) {
        checkIndex(v);
        return vertices[v].edges;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= V())
            throw new IndexOutOfBoundsException(index + " is out of bounds");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("----- EdgeWeightedDiGraph -----");
        for (int v = 0; v < V(); v++)
        for (DirectedEdge edge : adjacent(v)) {
            sb.append("\t");
            sb.append(edge.toString());
            sb.append("\n");
        }
        sb.append("-----------------------------");

        return sb.toString();
    }

    private class Vertex {
        Set<DirectedEdge> edges = new HashSet<>();
    }
}
