package datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class LinkedList<T> implements Iterable<T> {

    private Node head;
    private Node tail;
    private int size;

    public void addFirst(T item) {
        var node = new Node(item);
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    public void addLast(T item) {
        var node = new Node(item);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void deleteFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        else if (isSingle())
            head = tail = null;
        else
            head = head.next;
        size--;
    }

    public void deleteLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        else if (isSingle()) {
            head = tail = null;
        } else {
            Node beforeTail = head;
            while (beforeTail.next != tail)
                beforeTail = beforeTail.next;
            beforeTail.next = null;
            tail = beforeTail;
        }
        size--;
    }

    public boolean contains(T item) {
        for (T t : this)
            if (t != null && t.equals(item))
                return true;
        return false;

        /*Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.value.equals(item))
                return true;
            currentNode = currentNode.next;
        }*/
    }

    public int indexOf(T item) {
        int i = 0;
        for (T t : this)
            if (t == null || !t.equals(item))
                i++;
            else
                return i;
        return -1;
        /*int i = 0;
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.value.equals(item))
                return i;
            currentNode = currentNode.next;
            i++;
        }
        return -1;*/
    }

    public T get(int index) {
        if (index < 0)
            throw new IllegalArgumentException();
        int i = 0;
        Node currentNode = head;
        while (currentNode != null) {
            if (i == index) return currentNode.value;
            currentNode = currentNode.next;
            i++;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (T t : this)
            array[i++] = t;
        return (T[]) array;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private boolean isSingle() {
        return head == tail;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    @Override
    public String toString() {
        var converted = new DynamicArray<String>();
        for (T item : this) converted.insert(item.toString());
        return String.format(
                "head=%s, tail=%s, items: [%s]",
                Optional.ofNullable(head).map(it -> (Object) it.value).orElse("-"),
                Optional.ofNullable(tail).map(it -> (Object) it.value).orElse("-"),
                String.join(", ", converted)
        );
    }

    private class Node {
        private final T value;
        private Node next;

        Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
}
