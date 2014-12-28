package com;

import java.util.*;

public class ShortestPath {

    private Graph graph;

    private PriorityQueue<Link> heap;

    Map<Edge, Edge> loopedPath;
    String path;
    Boolean isPathValid;
    Integer distance;

    private static final int INITIAL_CAPACITY = 10;


    /**
     * @param graph: The Graph to traverse
     *
     *        Constructor - initializes each Node's distance to infinity,used for initial comparison
     * */

    public ShortestPath(Graph graph) {
        this.graph = graph;
        loopedPath = new HashMap<Edge, Edge>();
        resetGraph();
        setIsPathValid(false);
        distance = new Integer(0);

        heap = new PriorityQueue<Link>(INITIAL_CAPACITY, new Comparator<Link>() {
            public int compare(Link source, Link sink) {
                return (source.getDistance() + source.getSource().getDistance())
                        - (sink.getDistance() + sink.getSource().getDistance());
            }
        });
    }

    // initializes the Graph Nodes to infinity
    public void resetGraph() {
        for (int i = 0; i < this.graph.count(); i++) {
            this.graph.get(i).setDistance(Integer.MAX_VALUE);
            this.graph.get(i);
        }
    }



    public int heapPath(Edge start, Edge end) {
        start.setDistance(0); // initialize the start distance to 0

        // the Edge to evaluate. We evaluate the start Node first
        Edge evaluate = start;

        // as long as we have elements in the Graph to traverse
        do {
            LinkedList<Link> links = evaluate.getConnections();
            Iterator<Link> iterate = links.iterator();

            while (iterate.hasNext()) {
                Link conn = iterate.next();
                heap.add(conn);
            }

            // then poll the PriorityQueue to determine
            // which Node to visit next
            Link temp = null;
            if (!heap.isEmpty()) {
                temp = heap.poll();
                if (loopedPath.containsKey(temp.getSource()) && loopedPath.containsKey(temp.gettarget())
                        || temp.gettarget().getElem().equals(start.getElem())) {

                    temp = heap.poll();
                }

            } else {
                break;
            }

            evaluate = temp.gettarget();

            // and update that Node's distance if a shorter path is found
            int distance = evaluate.getDistance();
            int newDist = temp.getSource().getDistance() + temp.getDistance();

            // set the distance and also make an entry in the map of the form <current node,parent>

            if (newDist < distance) {
                evaluate.setDistance(newDist);
                if (!loopedPath.containsKey(temp.gettarget())) {
                    loopedPath.put(temp.gettarget(), temp.getSource());
                    System.out.println("Learned" + temp.gettarget());
                } else {
                    System.out.println("Inside else");
                    break;
                }
            }

        } while (!heap.isEmpty());

        return end.getDistance();
    }

    public String getPath(Edge start, Edge end) {
        distance = heapPath(start, end);
        path = plotPath(start, end, graph.count());
        return path;
    }

    public String plotPath(Edge start, Edge end, int length) {

        StringBuffer sb = new StringBuffer();
        Edge sink = end;
        int i = 0;
        // break the loop when you can successfully traverse from the sink to the source or you exceed
        // the number of edges assembled
        while (loopedPath.get(end) != start && i++ < length) {
            sb.append(loopedPath.get(end));
            end = loopedPath.get(end);
        }

        if (loopedPath.get(end) == start && loopedPath.get(start) != end) {
            setIsPathValid(true);

            // we have the order in which vertices need to be visited from the sink to source,so we
            // reverse them
            sb.reverse();

            // adding start and end vertices for the sake of intuitiveness & completeness
            sb.insert(0, start);
            sb.append(sink);
            return sb.toString();
        } else {
            sb = new StringBuffer();
            return sb.append("Target Unreachable").toString();
        }

    }

    public Map<Edge, Edge> getexactPath() {
        return loopedPath;
    }


    

    public void setIsPathValid(Boolean isPathValid) {
        this.isPathValid = isPathValid;
    }

}