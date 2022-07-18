package datastructures.queue;

import datastructures.linkedlist.LinkedList;

public class LinkedListQueue<T> {

    private final LinkedList<T> backend;

    public LinkedListQueue() {
        backend = new LinkedList<>();
    }

    public void enqueue(T item) {
        backend.addLast(item);
    }

    public T dequeue() {
        var removed = peek();
        backend.deleteFirst();
        return removed;
    }

    public T peek() {
        return backend.get(0);
    }

    public int size() {
        return backend.size();
    }

    public boolean isEmpty() {
        return backend.isEmpty();
    }

    @Override
    public String toString() {
        return backend.toString();
    }
}
