package datastructures.queue;

import java.util.Iterator;

public class ArrayQueue<T> implements Iterable<T> {

    private final Object[] array;
    private int front;
    private int rear;
    private int size;

    public ArrayQueue() {
        this(10);
    }

    public ArrayQueue(int capacity) {
        this.array = new Object[capacity];
    }

    public void enqueue(T item) {
        if (isFull())
            throw new IllegalStateException();

        array[rear] = item;
        rear = (rear + 1) % array.length;
        size++;
    }

    public T dequeue() {
        T peek = peek();
        array[front] = null;
        front = (front + 1) % array.length;
        size--;
        return peek;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException();
        return (T) array[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (T item : this)
            sb.append(item).append(", ");
        /*for (int i = front; i < (front + size); i++)
            sb.append(array[i % array.length]).append(", ");*/
        if (sb.length() != 1) sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int i = front;

            @Override
            public boolean hasNext() {
                return i < (front + size);
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                return (T) array[i++ % array.length];
            }
        };
    }
}

/*public class ArrayQueue<T> {

    private final Object[] array;
    private int first;
    private int size;

    public ArrayQueue() {
        this(10);
    }

    public ArrayQueue(int capacity) {
        this.array = new Object[capacity];
    }

    public void enqueue(T item) {
        if (isFull())
            throw new IllegalStateException();

        // [null, null, 30, 0, 0]

        array[size++] = item;
    }

    public T dequeue() {
        T peek = peek();
        array[first++] = null;
        size--;
        return peek;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException();
        return (T) array[first];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(array, first, first + size));
    }
}*/
