package io.imulab.review.graph;

import org.junit.jupiter.api.Test;

public class DepthFirstSearchTests {

    @Test
    void testSearch() {
        Graph g = UnDiGraph.exampleGraph();
        DepthFirstSearch dfs = new DepthFirstSearch(g, 0);
        for (int w = 1; w < g.V(); w++) {
            System.out.println("From 0 to " + w);
            System.out.println("\t" + dfs.displayPathTo(w));
        }
    }
}
