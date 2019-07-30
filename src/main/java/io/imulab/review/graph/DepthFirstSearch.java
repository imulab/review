package io.imulab.review.graph;

import java.util.Iterator;
import java.util.Stack;

public class DepthFirstSearch implements Path {

    protected final Graph graph;
    protected final int source;

    // an array keep track of which vertex is visited
    // so we don't visit the same vertex twice during a search.
    protected boolean[] marked;

    // an array keep track of the path during a search.
    // for example, if edgeTo[1] = 0, then we came to vertex 1
    // from vertex 0. This is useful during the query phase when
    // we need to roll back the track.
    protected int[] edgeTo;

    public DepthFirstSearch(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        if (source < 0 || source >= graph.V())
            throw new IllegalArgumentException("source out of bounds");

        this.marked = new boolean[this.graph.V()];
        this.edgeTo = new int[this.graph.V()];

        traverse(this.source);
    }

    protected void traverse(int source) {
        dfs(source);
    }

    // depth first search uses the call stack as its data structure, which is why
    // we don't see an explicit stack data structure here.
    private void dfs(int v) {
        marked[v] = true;
        for (int w : graph.adjacent(v)) {
            if (!marked[w]) {
                dfs(w);
                edgeTo[w] = v;
            }
        }
    }

    @Override
    public int getSource() {
        return this.source;
    }

    @Override
    public boolean hasPathTo(int v) {
        checkIndex(v);
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;

        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        while (v != source) {
            v = edgeTo[v];
            stack.push(v);
        }

        Stack<Integer> reversed = new Stack<>();
        while (!stack.isEmpty()) {
            reversed.push(stack.pop());
        }

        return reversed;
    }

    public String displayPathTo(int v) {
        StringBuilder sb = new StringBuilder();

        Iterable<Integer> iterable = pathTo(v);
        if (iterable == null) {
            sb.append("<no path>");
        } else {
            Iterator<Integer> itr = iterable.iterator();
            if (itr.hasNext()) {
                sb.append(itr.next());
            }
            while (itr.hasNext()) {
                sb.append(" -> ");
                sb.append(itr.next());
            }
        }

        return sb.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= graph.V())
            throw new IndexOutOfBoundsException(index + " is out of bounds.");
    }
}
