package org.kl.bean;

public final class Edge<T> {
    private final T first;
    private final T last;

    public Edge(T first, T last) {
        this.first = first;
        this.last  = last;
    }

    public T getFirst() { return first; }
    public T getLast()  { return last;  }
}
