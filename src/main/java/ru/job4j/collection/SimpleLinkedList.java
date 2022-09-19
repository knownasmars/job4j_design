package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int modCount = 0;

    private int size = 0;

    private Node<E> head;

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        modCount++;
        size++;
        if (head == null) {
            head = new Node<>(value, null);
            return;
        }
        Node<E> currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = new Node<>(value, null);
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.element;
    }

    public E node(int index) {
        Node<E> current = head;
        E element = null;
        if (index >= size - 1) {
            return head.element;
        }
        for (int j = 0; j < index; j++) {
            current = current.next;
            element = current.element;
        }
        return element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> current = head;
            final int expectedMod = modCount;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }
}