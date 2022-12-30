package datastructures.hashtable;

/**
 * Hash table with linear probing
 */
public class HashTableLin<K, V> {

    private final Entry<K, V>[] store;

    public HashTableLin(int capacity) {
        this.store = new Entry[capacity];
    }

    public void put(K key, V value) {
        int i = hash(key);
        var entry = store[i];
        while (entry != null && !entry.key.equals(key)) {
            entry = store[++i];
        }
        if (entry == null) store[i] = new Entry(key, value);
        else entry.value = value;
    }

    public V get(K key) {
        int i = hash(key);
        var entry = store[i];
        try {
            while (entry != null && !entry.key.equals(key))
                entry = store[++i];
        } catch (ArrayIndexOutOfBoundsException e) {
            entry = null;
        }
        return getValue(entry);
    }

    public boolean containsKey(K key) {
        int i = hash(key);
        while (i < store.length && getKey(store[i]) != null && !getKey(store[i]).equals(key))
            i++;
        return i != store.length && store[i] != null;
    }

    /*private Entry findSlot(K key) {
    }*/

    private K getKey(Entry<K, V> entry) {
        if (entry == null) return null;
        return entry.key;
    }

    private V getValue(Entry<K, V> entry) {
        if (entry == null) return null;
        return entry.value;
    }

    private int hash(K key) {
        return key.hashCode() % store.length;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
