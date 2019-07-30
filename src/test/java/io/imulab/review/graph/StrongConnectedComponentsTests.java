package io.imulab.review.graph;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class StrongConnectedComponentsTests {

    @Test
    void testConnectivity() {
        DiGraph g = exampleGraph();
        StrongConnectedComponents scc = new StrongConnectedComponents(g);

        Assertions.assertThat(scc.count()).isEqualTo(5);

        // 1 is not strongly connected to any others
        for (int i = 0; i < g.V(); i++) {
            if (i != 1) {
                Assertions.assertThat(scc.isConnected(1, i)).isFalse();
            }
        }

        // 7 is not strongly connected to any others
        for (int i = 0; i < g.V(); i++) {
            if (i != 7) {
                Assertions.assertThat(scc.isConnected(7, i)).isFalse();
            }
        }

        // 6-8 is strongly connected, but disconnected with the rest
        Assertions.assertThat(scc.isConnected(6, 8)).isTrue();
        for (int i = 0; i < g.V(); i++) {
            if (i != 6 && i != 8) {
                Assertions.assertThat(scc.isConnected(6, i)).isFalse();
                Assertions.assertThat(scc.isConnected(8, i)).isFalse();
            }
        }

        // 9-10-11-12 is strongly connected, but disconnected with the rest
        for (int i = 9; i <= 12; i++) {
            for (int j = 9; j <= 12; j++) {
                if (i != j) {
                    Assertions.assertThat(scc.isConnected(i, j)).isTrue();
                }
            }
        }
        for (int i = 0; i < g.V(); i++) {
            if (i != 9 && i != 10 && i != 11 && i != 12) {
                Assertions.assertThat(scc.isConnected(9, i)).isFalse();
                Assertions.assertThat(scc.isConnected(10, i)).isFalse();
                Assertions.assertThat(scc.isConnected(11, i)).isFalse();
                Assertions.assertThat(scc.isConnected(12, i)).isFalse();
            }
        }

        // 0-2-3-4-5 is strongly connected, but disconnected with the rest
        for (int i : Arrays.asList(0, 2, 3, 4, 5)) {
            for (int j : Arrays.asList(0, 2, 3, 4, 5)) {
                if (i != j) {
                    Assertions.assertThat(scc.isConnected(i, j)).isTrue();
                }
            }
        }
        for (int i = 0; i < g.V(); i++) {
            if (!Arrays.asList(0, 2, 3, 4, 5).contains(i)) {
                Assertions.assertThat(scc.isConnected(0, i)).isFalse();
                Assertions.assertThat(scc.isConnected(2, i)).isFalse();
                Assertions.assertThat(scc.isConnected(3, i)).isFalse();
                Assertions.assertThat(scc.isConnected(4, i)).isFalse();
                Assertions.assertThat(scc.isConnected(5, i)).isFalse();
            }
        }
    }

    private DiGraph exampleGraph() {
        DiGraph g = new DiGraph(13);

        // vertex 0
        g.addEdge(0, 1);
        g.addEdge(0, 5);

        // vertex 2
        g.addEdge(2, 0);
        g.addEdge(2, 3);

        // vertex 3
        g.addEdge(3, 2);
        g.addEdge(3, 5);

        // vertex 4
        g.addEdge(4, 2);
        g.addEdge(4, 3);

        // vertex 5
        g.addEdge(5, 4);

        // vertex 6
        g.addEdge(6, 0);
        g.addEdge(6, 4);
        g.addEdge(6, 8);
        g.addEdge(6, 9);

        // vertex 7
        g.addEdge(7, 6);
        g.addEdge(7, 9);

        // vertex 8
        g.addEdge(8, 6);

        // vertex 9
        g.addEdge(9, 10);
        g.addEdge(9, 11);

        // vertex 10
        g.addEdge(10, 12);

        // vertex 11
        g.addEdge(11, 4);
        g.addEdge(11, 12);

        // vertex 12
        g.addEdge(12, 9);

        return g;
    }
}
