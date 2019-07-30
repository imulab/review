package io.imulab.review.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Breath first search algorithm. This implementation inherits from depth first search algorithm to avoid re-implementing
 * much of the setup and display logic, it only override the core traversal algorithm.
 *
 * Breath first search algorithm computes the shortest path because it prioritizes the consideration of adjacent vertices.
 */
public class BreathFirstSearch extends DepthFirstSearch {

    public BreathFirstSearch(Graph graph, int source) {
        super(graph, source);
    }

    @Override
    protected void traverse(int source) {
        bfs(source);
    }

    private void bfs(int source) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        marked[source] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : graph.adjacent(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.offer(w);
                }
            }
        }
    }
}
