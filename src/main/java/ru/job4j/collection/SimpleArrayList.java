package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    private int cursor = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            expandArray();
        }
        container[size++] = value;
        modCount++;
    }

    public void expandArray() {
        this.container = Arrays.copyOf(
                container, container.length * 2
        );
    }

    @Override
    public T set(int index, T newValue) {
        T rsl = container[index];
        Objects.checkIndex(index, size);
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T rsl = container[index];
        modCount++;
        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(
                    container, index + 1, container, index, newSize - index
            );
        }
        container[size = newSize] = null;
        return rsl;
    }

    @Override
    public T get(int index) {
        T rsl = container[index];
        Objects.checkIndex(index, size);
        return rsl;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        cursor = 0;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[cursor++];
            }

        };
    }
}