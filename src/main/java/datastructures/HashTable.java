package datastructures;

import java.util.LinkedList;

public class HashTable {

    private final LinkedList<Entry>[] data;

    public HashTable(int capacity) {
        data = new LinkedList[capacity];
    }

    public void put(int key, String value) {
        int hash = hash(key);
        LinkedList<Entry> l = data[hash];
        if (l != null) {
            for (Entry e : l)
                if (e.key == key) {
                    e.value = value;
                    return;
                }
            l.add(new Entry(key, value));
            return;
        }
        var newLinkedList = new LinkedList<Entry>();
        newLinkedList.add(new Entry(key, value));
        data[hash] = newLinkedList;
    }

    public String get(int key) {
        int hash = hash(key);
        LinkedList<Entry> values = data[hash];
        if (values != null)
            for (Entry e : values)
                if (e.key == key)
                    return e.value;
        return null;
    }

    public void remove(int key) {
        int hash = hash(key);
        LinkedList<Entry> values = data[hash];
        if (values != null) values.removeIf(e -> e.key == key);
    }

    private Entry findEntry(int key) {
        int hash = hash(key);
        LinkedList<Entry> values = data[hash];
        if (values != null)
            for (Entry e : values)
                if (e.key == key) {
                    return e;
                }
        return null;
    }

    private int hash(int key) {
        return key % data.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var values : data)
            if (values != null)
                for (var entry : values)
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
