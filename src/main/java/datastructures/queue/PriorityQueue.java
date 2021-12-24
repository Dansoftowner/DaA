package datastructures.queue;

import java.util.Arrays;

public class PriorityQueue<T extends Comparable<T>> {

    private final Object[] data;
    private int size;
    private int front;

    public PriorityQueue(int capacity) {
        data = new Object[capacity];
    }

    public void enqueue(T offered) {
        if (isFull())
            throw new IllegalStateException();
        data[shiftItemsToInsert(offered)] = offered;
        size++;
    }

    private int shiftItemsToInsert(T offered) {
        int i;
        for (i = size - 1; i >= 0; i--) {
            @SuppressWarnings("unchecked")
            T element = (T) data[i];
            if (element.compareTo(offered) < 0)
                break;
            else
                data[i + 1] = data[i];
        }
        return i + 1;
    }

    public T dequeue() {
        if (isEmpty())
            throw new IllegalStateException();
        @SuppressWarnings("unchecked")
        var dequeued = (T) data[front];
        data[front++] = null;
        return dequeued;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return data.length == size;
    }

    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(data, front, size));
    }
}
