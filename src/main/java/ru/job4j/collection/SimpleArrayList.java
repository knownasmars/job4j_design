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

    private void expandArray() {
        int newLength = size == 0 ? 10 : container.length * 2;
        this.container = Arrays.copyOf(
                container, newLength
        );
    }

    @Override
    public T set(int index, T newValue) {
        container[index] = newValue;
        return get(index);
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T rsl = container[index];
        modCount++;
        final int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(
                    container, index + 1, container, index, newSize - index
            );
        }
        size = newSize;
        container[newSize] = null;
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