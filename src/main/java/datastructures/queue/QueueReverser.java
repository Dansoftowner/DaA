package datastructures.queue;

import datastructures.stack.Stack;


public class QueueReverser {
    public <T> ArrayQueue<T> reverse(ArrayQueue<T> queue, int k) {
        queue = copy(queue);
        int baseSize = queue.size();

        Stack<T> reversedElements = new Stack<T>();
        int i = 0;
        while (i < k) {
            reversedElements.push(queue.dequeue());
            i++;
        }

        ArrayQueue<T> result = new ArrayQueue<>();
        while(!reversedElements.isEmpty())
            result.enqueue(reversedElements.pop());

        i = k;
        while (i < baseSize) {
            result.enqueue(queue.dequeue());
            i++;
        }

        return result;

        // Q: [10, 20, 30, 40, 50], K = 3
        // S: [30, 20, 10]
        // Q + S : [40, 50, 30, 20, 10]
        // [30, 20, 10, 40, 50]
    }

    private static <T> ArrayQueue<T> copy(ArrayQueue<T> q) {
        var copy = new ArrayQueue<T>();
        for (T item : q)
            copy.enqueue(item);
        return copy;
    }
}
