package datastructures;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

    private final LinkedList<T> backend;

    public Stack() {
        backend = new LinkedList<>();
    }

    public T push(T item) {
        backend.addFirst(item);
        return item;
    }

    public T pop() {
        if (backend.isEmpty())
            throw new IllegalStateException();
        T peek = peek();
        backend.deleteFirst();
        return peek;
    }

    public T peek() {
        return backend.get(0);
    }

    public boolean isEmpty() {
        return backend.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return backend.iterator();
    }
}
