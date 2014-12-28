package com;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by aman on 27/12/14.
 */

public class Edge<E> {

    private static int ID = 0;


    private E element;


    private int id;

    private int weight;


    private LinkedList<Link> pointers;


    public Edge() {
        // invoke constructor to initialize elem to null pointer
        this(null, Integer.MAX_VALUE);
    }

    public Edge(E el, int distance) {
        this.element = el;
        id = ID++;
        pointers = new LinkedList<Link>();
        this.weight = distance;
    }


    public E getElem() {
        return element;
    }


    public int getDistance() {
        return weight;
    }

    public void setDistance(int dist) {
        weight = dist;
    }


    public void connectTo(Edge other) {
        Link c = new Link(this, other);

        // check for duplicates
        if (!pointers.contains(c))
            pointers.add(c);

        // reference Connector in other Edge as well
        LinkedList<Link> conn = other.getConnections();
        if (!conn.contains(c))
            conn.add(c);
    }

    public void connectTo(Edge other, int distance) {
        Link c = new Link(this, other, distance);
        if (!pointers.contains(c))
            pointers.add(c);
    }

    public LinkedList<Link> getConnections() {
        return pointers;
    }


    public boolean equals(Edge other) {

        if (other.pointers.size() != pointers.size())
            return false;

        LinkedList<Link> temp = new LinkedList<Link>(pointers);


        return element.equals(other.getElem()) && temp.retainAll(other.pointers);
    }

    public String toString() {
        return this.element.toString();
    }
}