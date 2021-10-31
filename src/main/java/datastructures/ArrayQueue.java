package datastructures;

import java.util.Arrays;


public class ArrayQueue<T> {

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

    @Override
    public String toString() {

        // [60, null, 30, 40, 50]
        //   R        F

        /*StringBuilder value = new StringBuilder();
        for (int i = size, f = front, r = rear-1; i > 0; i--)
            if (f < array.length)
                value.append(value.isEmpty() ? "" : ", ").append(array[f++]);
            else
                value.append(value.isEmpty() ? "" : ", ").append(array[r++]);

        value.insert(0, "[").append("]");
        return value.toString();*/
        return Arrays.toString(array);
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
