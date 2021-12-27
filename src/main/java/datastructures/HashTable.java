package datastructures;

import java.util.LinkedList;
import java.util.List;

public class HashTable {

    private final LinkedList<Entry>[] data;

    public HashTable(int capacity) {
        data = new LinkedList[capacity];
    }

    public void put(int key, String value) {
        Entry found = findEntry(key);
        if (found != null) found.value = value;
        else addEntry(key, new Entry(key, value));
    }

    public String get(int key) {
        Entry found = findEntry(key);
        return found != null ? found.value : null;
    }

    public void remove(int key) {
        LinkedList<Entry> bucket = getBucket(key);
        if (bucket != null) bucket.removeIf(entry -> entry.key == key);
    }

    private Entry findEntry(int key) {
        LinkedList<Entry> bucket = getBucket(key);
        if (bucket != null)
            for (Entry e : bucket)
                if (e.key == key) {
                    return e;
                }
        return null;
    }

    private void addEntry(int key, Entry entry) {
        var bucket = getBucket(key);
        if (bucket != null)
            bucket.add(entry);
        else
            data[hash(key)] = new LinkedList<>(List.of(entry));
    }

    private LinkedList<Entry> getBucket(int key) {
        return data[hash(key)];
    }

    private int hash(int key) {
        return Math.abs(key) % data.length;
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
        private int key;
        private String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
