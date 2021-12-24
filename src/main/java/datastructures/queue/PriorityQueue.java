package datastructures.queue;

public class PriorityQueue<T extends Comparable<T>> {

    private final Object[] data;
    private int size;
    private int rear;
    private int front;

    public PriorityQueue(int capacity) {
        data = new Object[capacity];
    }

    public void enqueue(T item) {
        if (size == data.length)
            throw new IllegalStateException();

        // [1]
        // [1, 5]
        //

        int i = rear -1;
        while(i >= 0 && ((T) data[i]).compareTo(item) > 0) {
            data[i + 1] = data[i];
            i--;
        }

        data[i + 1] = item;
        rear = (rear + 1) % data.length;
        size++;
    }

    public T dequeue() {
        var element = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return (T) element;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = front; i < (front + size); i++)
            sb.append(data[i % data.length]).append(", ");
        if (sb.length() != 1) sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
