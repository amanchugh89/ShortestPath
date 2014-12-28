package com;

/**
 * Created by aman on 27/12/14.
 */


public class Link<E> implements Comparable<Link<E>> {

    private Edge<E> source, target;
    private int distance;


    public Link(Edge<E> source, Edge<E> target) {
        this(source, target, 0);
    }

    public Link(Edge<E> source, Edge<E> target, int distance) {
        this.source = source;
        this.target = target;
        this.distance = distance;
    }


    public Edge<E> getSource() {
        return source;
    }

    public Edge<E> gettarget() {
        return target;
    }

    public int getDistance() {
        return distance;
    }


    public boolean equals(Link<E> other) {
        return source.equals(other.getSource()) && target.equals(other.gettarget())
                && distance == other.getDistance();
    }

    @Override
    public String toString() {
        return "Link [source=" + source + ", target=" + target + ", distance=" + distance + "]";
    }

    public int compareTo(Link<E> other) {
        return this.distance - other.distance;
    }
}