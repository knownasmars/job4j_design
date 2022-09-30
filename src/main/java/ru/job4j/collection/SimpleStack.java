package ru.job4j.collection;

public class SimpleStack<T> {

//    private Node<T> head;

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public SimpleStack() {
    }

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public boolean isEmpty() {
        return !linked.iterator().hasNext();
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}