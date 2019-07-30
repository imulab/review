package io.imulab.review.graph;

import org.junit.jupiter.api.Test;

public class BreathFirstSearchTests {

    @Test
    void testSearch() {
        Graph g = UnDiGraph.exampleGraph();
        BreathFirstSearch bfs = new BreathFirstSearch(g, 0);
        for (int w = 1; w < g.V(); w++) {
            System.out.println("From 0 to " + w);
            System.out.println("\t" + bfs.displayPathTo(w));
        }
    }
}
