package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int modCount = 0;

    private int size = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (size >= capacity * LOAD_FACTOR) {
            expandArray();
        }
        int bucket = indexFor(hash(Objects.hashCode(key)));
        if (table[bucket] == null) {
            table[bucket] = new MapEntry<>(key, value);
            size++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expandArray() {
        capacity *= 2;
        MapEntry<K, V>[] tmp = new MapEntry[capacity];
        for (MapEntry<K, V> e : table) {
            if (e != null) {
                int bucket = indexFor(hash(Objects.hashCode(e.key)));
                tmp[bucket] = e;
            }
        }
        table = tmp;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int bucket = indexFor(hash(Objects.hashCode(key)));
        if (isEquals(key)) {
            rsl = table[bucket].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int bucket = indexFor(hash(Objects.hashCode(key)));
        if (isEquals(key)) {
            table[bucket] = null;
            size--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private boolean isEquals(K key) {
        boolean rsl = false;
        int bucket = indexFor(hash(Objects.hashCode(key)));
        MapEntry<K, V> e = table[bucket];
        if (e != null
                && Objects.hashCode(e.key) == Objects.hashCode(key)
                && (Objects.equals(e.key, key))) {
            rsl = true;
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
                if (modCount != lastMod) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < table.length
                        && table[cursor] == null) {
                    cursor++;
                }
                return cursor < table.length;
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