package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        float threshold = (float) (count / table.length);
        if (threshold >= LOAD_FACTOR) {
            expandArray();
        }
        int bucket = indexFor(hash(key == null ? 0 : key.hashCode()));
        if (table[bucket] == null) {
            table[bucket] = new MapEntry<>(key, value);
            count++;
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
        table = Arrays.copyOf(table, 16);
    }

    @Override
    public V get(K key) {
        int bucket = indexFor(hash(key == null ? 0 : key.hashCode()));
        for (MapEntry<K, V> e : table) {
            if (key == table[bucket].key) {
                return e.value;
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int bucket = indexFor(hash(key == null ? 0 : key.hashCode()));
        if (key != null) {
            table[bucket] = null;
            rsl = true;
            count--;
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
                return cursor < count;
            }

            @Override
            public K next() {
                if (modCount != lastMod) {
                    throw new ConcurrentModificationException();
                }
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