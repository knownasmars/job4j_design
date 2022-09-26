package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleStack<T> implements Iterable<T> {

    private Node<T> first;

    private Node<T> last;

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public void addFirst(T value) {
        Node<T> node = new Node<>(null, value, null);
        if (first == null) {
            first = node;
            return;
        }
        while (node.prev != null) {
            first = first.prev;
        }
        first.prev = node;
        node.next = first;
    }

    public T pop() {
        if (first == null || last == null) {
            throw new NoSuchElementException();
        }
        T rsl = last.value;
        Node<T> tmp = last;
        last = last.prev;
        tmp.prev = null;
        tmp.value = null;
        return rsl;
    }

    public void push(T value) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(Node<T> prev, T value, Node<T> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}