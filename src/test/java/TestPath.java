

import com.Edge;

import com.Graph;
import com.ShortestPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class TestPath {

    Edge<Integer> node1, node2, node3, node4, node5, node6;
    Graph grph;

    @Before
    public void assembleGraph() {
        grph = new Graph();
        node1 = new Edge<Integer>(1, 0);
        node2 = new Edge<Integer>(2, 0);
        node3 = new Edge<Integer>(3, 0);
        node4 = new Edge<Integer>(4, 0);
        node5 = new Edge<Integer>(5, 0);
        node6 = new Edge<Integer>(6, 0);

        grph.addEdge(node1);
        grph.addEdge(node2);
        grph.addEdge(node3);
        grph.addEdge(node4);
        grph.addEdge(node5);
        grph.addEdge(node6);

        node1.connectTo(node2, 7);

        node1.connectTo(node3, 9);
        node1.connectTo(node6, 14);

        node3.connectTo(node2, 11);
        node2.connectTo(node3, 10);
        node2.connectTo(node4, 15);
        node4.connectTo(node2, 11);

        node3.connectTo(node6, 2);
        node3.connectTo(node4, 11);

        node4.connectTo(node5, 6);
        node6.connectTo(node5, 9);

    }

    @Test
    public void testDistance() {
        ShortestPath test = new ShortestPath(grph);
        Assert.assertEquals(20, test.heapPath(node1, node4));
    }

    @Test
    public void testIncorrectDistance() {
        ShortestPath test = new ShortestPath(grph);
        Assert.assertNotEquals(22, test.heapPath(node1, node4));
    }

    @Test
    public void testPath() {
        ShortestPath test = new ShortestPath(grph);
        Assert.assertEquals("134", test.getPath(node1, node4));
    }

    @Test
    public void testUnreachablePath() {
        ShortestPath test = new ShortestPath(grph);
        Assert.assertEquals("target Unreachable", test.getPath(node5, node1).trim());
    }

}