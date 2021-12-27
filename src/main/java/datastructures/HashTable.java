package datastructures;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HashTable<K, V> {

    private final LinkedList<Entry>[] data;

    public HashTable(int capacity) {
        data = new LinkedList[capacity];
    }

    public void put(K key, V value) {
        Entry found = findEntry(key);
        if (found != null) found.value = value;
        else addEntry(key, new Entry(key, value));
    }

    public V get(K key) {
        Entry found = findEntry(key);
        return found != null ? found.value : null;
    }

    public void remove(K key) {
        LinkedList<Entry> bucket = getBucket(key);
        if (bucket != null) bucket.removeIf(entry -> entry.key == key);
    }

    private Entry findEntry(K key) {
        LinkedList<Entry> bucket = getBucket(key);
        if (bucket != null)
            for (Entry e : bucket)
                if (Objects.equals(e.key, key))
                    return e;
        return null;
    }

    private void addEntry(K key, Entry entry) {
        var bucket = getBucket(key);
        if (bucket != null)
            bucket.add(entry);
        else
            data[hash(key)] = new LinkedList<>(List.of(entry));
    }

    private LinkedList<Entry> getBucket(K key) {
        return data[hash(key)];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % data.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var bucket : data)
            if (bucket != null)
                for (var entry : bucket)
                    sb.append(String.format("%s=%s%n", entry.key, entry.value));
        return sb.toString();
    }

    private class Entry {
        private final K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
