package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int size = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        float threshold = (float) (1.0 * size) / table.length;
        if (threshold >= LOAD_FACTOR) {
            expandArray();
        }
        int bucket = indexFor(hash(key == null ? 0 : key.hashCode()));
        if (table[bucket] == null) {
            table[bucket] = new MapEntry<>(key, value);
            size++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> table.length);
    }

    private int indexFor(int hash) {
        return (table.length - 1) & hash;
    }

    private void expandArray() {
        MapEntry<K, V>[] tmp = new MapEntry[table.length * 2];
        for (MapEntry<K, V> entry : table) {
            int bucket = indexFor(hash(entry.key == null ? 0 : entry.key.hashCode()));
            tmp[bucket] = new MapEntry<>(entry.key, entry.value);
        }
        table = tmp;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        for (MapEntry<K, V> e : table) {
            if (key != null && e != null && e.key != null && e.key.hashCode() == key.hashCode()
                    && e.key.equals(key)) {
                rsl = e.value;
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int bucket = indexFor(hash(key == null ? 0 : key.hashCode()));
        MapEntry<K, V> e = table[bucket];
        if (key != null && e != null && e.key != null && e.key.hashCode() == key.hashCode()
                && e.key.equals(key)) {
            table[bucket] = null;
            rsl = true;
            size--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        int lastMod = modCount;
        return new Iterator<>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                boolean rsl = false;
                if (modCount != lastMod) {
                    throw new ConcurrentModificationException();
                }
                while (cursor <= size + 1) {
                    if (table[cursor] != null) {
                        rsl = true;
                        break;
                    }
                    cursor++;
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}