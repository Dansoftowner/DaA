package datastructures.queue;

import datastructures.Stack;

public class StackQueue<T> {

    private final Stack<T> stack;
    private final Stack<T> reversedStack;

    public StackQueue() {
        this.stack = new Stack<>();
        this.reversedStack = new Stack<>();
    }

    public void enqueue(T item) {
        stack.push(item);
    }

    public T dequeue() {
        if (isEmpty())
            throw new IllegalStateException();

      //  moveStackElements(reversedStack, stack);
        moveStackElements(stack, reversedStack);
        return reversedStack.pop();
    }

    public T peek() {
        moveStackElements(stack, reversedStack);
        return reversedStack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty() && reversedStack.isEmpty();
    }

    private void moveStackElements(Stack<T> from, Stack<T> to) {
        while (!from.isEmpty())
            to.push(from.pop());
    }

    @Override
    public String toString() {
        return "First stack: " + stack + "\nSecond (rev) stack: " + reversedStack;
    }
}

/*public class StackQueue<T> {

    private final Stack<T> stack;

    public StackQueue() {
        this.stack = new Stack<>();
    }

    public void enqueue(T item) {
        stack.push(item);
    }

    public T dequeue() {
        Stack<T> reversed = new Stack<>();
        moveStackElements(stack, reversed);

        var peek = reversed.pop();
        moveStackElements(reversed, stack);

        return peek;
    }

    public T peek() {
        Stack<T> reversed = new Stack<>();
        moveStackElements(stack, reversed);

        T peek = reversed.peek();

        moveStackElements(reversed, stack);

        return peek;
    }

    private void moveStackElements(Stack<T> from, Stack<T> to) {
        while (!from.isEmpty())
            to.push(from.pop());
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}*/
