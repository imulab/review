package io.imulab.review.misc;

public class UnionFind {

    /**
     * The parent array records the index of the parent for each indexed element. Such
     * chain of indexes form a ancestral tree that can be traced back to its root. If
     * two elements have the same root, they are connected.
     *
     * The parent of a root is always its self.
     */
    private final int[] parent;

    /**
     * The rank record is maintained so that when two distinct root needs to be connected, we
     * can decide who would connect to whom.
     */
    private final int[] rank;

    /**
     * Total number of distinct groups. Starts off as the total number of elements and decreases as things
     * are connected together.
     */
    private int count;

    public UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        this.count = n;
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.rank[i] = 0;
        }
    }

    public int find(int p) {
        checkIndex(p);

        while (p != parent[p]) {
            // path compression as we don't really need to maintain a long trace
            parent[p] = parent[parent[p]];
            // trace upwards to root
            p = parent[p];
        }

        return p;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ)
            return;

        // connect the smaller rank to the larger rank
        // if rank is the same, arbitrarily connect Q to P and increase P rank
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }

        // one less component as two componets are connected
        count--;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException(index + " is out of bounds.");
    }
}
