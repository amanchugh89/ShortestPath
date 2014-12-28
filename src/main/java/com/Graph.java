
/**
 * Created by aman on 27/12/14.
 */
package com;

import java.util.ArrayList;


public class Graph {

    // ArrayList to hold edges for random access
    private ArrayList<Edge> edges;

    public Graph() {
        edges = new ArrayList<Edge>();
    }

    // add Edge to the List
    public boolean addEdge(Edge vertex) {
        if (edges.contains(vertex))
            return false;
        edges.add(vertex);
        return true;
    }

    public boolean contains(Edge vertex) {
        return edges.contains(vertex);
    }

    public Edge get(int index) {
        return edges.get(index);
    }

    // returns number of Edges in Graph
    public int count() {
        return edges.size();
    }

    public boolean equals(Graph other) {

        if (other.edges.size() != edges.size())
            return false;

        // store all of Edges of larger Graph
        ArrayList<Edge> temp = new ArrayList<Edge>(other.edges);

        // Graphs are equal only if temp is unchanged
        return temp.retainAll(edges);
    }
}