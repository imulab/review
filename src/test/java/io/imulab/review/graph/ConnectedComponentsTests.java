package io.imulab.review.graph;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("Duplicates")
public class ConnectedComponentsTests {

    @Test
    void testConnectivity() {
        ConnectedComponents cc = new ConnectedComponents(exampleGraph());

        Assertions.assertThat(cc.count()).isEqualTo(3);

        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                if (i != j) {
                    Assertions.assertThat(cc.isConnected(i, j)).isTrue();
                }
            }
        }

        for (int i = 7; i <= 8; i++) {
            for (int j = 7; j <= 8; j++) {
                if (i != j) {
                    Assertions.assertThat(cc.isConnected(i, j)).isTrue();
                }
            }
        }

        for (int i = 9; i <= 12; i++) {
            for (int j = 9; j <= 12; j++) {
                if (i != j) {
                    Assertions.assertThat(cc.isConnected(i, j)).isTrue();
                }
            }
        }

        for (int i = 0; i <= 6; i++) {
            for (int j = 7; j <= 8; j++) {
                Assertions.assertThat(cc.isConnected(i, j)).isFalse();
            }
            for (int j = 9; j <= 12; j++) {
                Assertions.assertThat(cc.isConnected(i, j)).isFalse();
            }
        }
    }

    private Graph exampleGraph() {
        Graph g = new Graph(13);

        // component 0-1-2-3-4-5-6
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 5);
        g.addEdge(0, 6);
        g.addEdge(1, 0);
        g.addEdge(2, 0);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 3);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(5, 0);
        g.addEdge(5, 3);
        g.addEdge(5, 4);
        g.addEdge(6, 0);
        g.addEdge(6, 4);

        // component 7-8
        g.addEdge(7, 8);
        g.addEdge(8, 7);

        // component 9-10-11-12
        g.addEdge(9, 10);
        g.addEdge(9, 11);
        g.addEdge(9, 12);
        g.addEdge(10, 9);
        g.addEdge(11, 9);
        g.addEdge(11, 12);
        g.addEdge(12, 9);
        g.addEdge(12, 11);

        return g;
    }
}
