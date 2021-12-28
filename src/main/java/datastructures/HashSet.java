package datastructures;

import java.util.*;
import java.util.LinkedList;

public class HashSet<E> implements Iterable<E> {

    private final LinkedList<E>[] data;

    public HashSet(int capacity) {
        this.data = new LinkedList[capacity];
    }

    public void add(E item) {
       if (!contains(item)) addItem(item);
    }

    public boolean contains(E item) {
        var bucket = getBucket(item);
        return bucket != null && bucket.contains(item);
    }

    private void addItem(E item) {
        var bucket = getBucket(item);
        if (bucket != null)
            bucket.add(item);
        else
            data[hash(item)] = new LinkedList<>(List.of(item));
    }

    private LinkedList<E> getBucket(E item) {
        return data[hash(item)];
    }

    private int hash(E item) {
        return Math.abs(Objects.hashCode(item)) % data.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E e : this)
            sb.append(e).append(", ");
        if (!sb.isEmpty()) sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i;
            int k;
            Iterator<E> currentBucketIterator;

            @Override
            public boolean hasNext() {
                return i < data.length;
            }

            @Override
            public E next() {
                if (currentBucketIterator != null) {
                    if (currentBucketIterator.hasNext())
                        return currentBucketIterator.next();
                    currentBucketIterator = null;
                } else {
                    var bucket = data[i++];
                    if (bucket != null) {
                        currentBucketIterator = bucket.iterator();
                        if (currentBucketIterator.hasNext())
                            return currentBucketIterator.next();
                    }
                }
                if (hasNext())
                    return next();
                throw new IllegalStateException();
            }
        };
    }
}
