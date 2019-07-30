package io.imulab.review.sort;

import io.imulab.review.graph.DiGraph;

import java.util.Stack;

/**
 * Topological sort determines the scheduling dependencies between vertices when each vertex represents a do-able or task
 * that has other dependencies.
 *
 * The sort performs a depth first search and maintains a reversed post order of the vertices visited.
 */
public class TopologicalSort {

    private final DiGraph graph;
    private final boolean[] marked;
    private final Stack<Integer> reversePost;

    public TopologicalSort(DiGraph graph) {
        this.graph = graph;
        this.marked = new boolean[graph.V()];
        this.reversePost = new Stack<>();
        for (int v = 0; v < graph.V(); v++) {
            if (!marked[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        marked[v] = true;
        for (int w : graph.adjacent(v)) {
            if (!marked[w]) {
                dfs(w);
            }
        }
        reversePost.push(v);
    }

    public static Iterable<Integer> sort(DiGraph graph) {
        TopologicalSort sort = new TopologicalSort(graph);

        // since the iterator implementation of Java's native stack data structure uses
        // the underlying vector order, we need to manually reverse the order here.
        Stack<Integer> reversed = new Stack<>();
        while (!sort.reversePost.isEmpty()) {
            reversed.push(sort.reversePost.pop());
        }

        return reversed;
    }
}
