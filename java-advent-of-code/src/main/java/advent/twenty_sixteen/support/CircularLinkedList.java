package advent.twenty_sixteen.support;

import java.util.NoSuchElementException;

public class CircularLinkedList<T> {
    public int size() {
        return size;
    }

    static class Node<D> {
        D data;
        Node next;
        Node previous;
    }

    private Node<T> current = null;
    private Node<T> middle = null;

    private int size = 0;

    public T head() {
        if (current == null) {
            throw new NoSuchElementException("List is empty");
        }
        return current.data;
    }

    public T middle() {
        if (current == null) {
            throw new NoSuchElementException("List is empty");
        }
        return middle.data;
    }

    public void insertBeforeCurrent(T data) {
        if (current == null) {
            current = insert(data, null);
            middle = current;
        } else {
            insert(data, current.previous);
        }
        size++;

        if (size % 2 == 0) {
            middle = middle.next;
        }
    }

    private static <T> Node insert(T data, Node location) {
        Node newNode = new Node();
        newNode.data = data;

        if (location == null) {
            location = newNode;
            location.next = newNode;
            location.previous = newNode;
            return newNode;
        }

        Node nodeBefore = location;
        Node nodeAfter = location.next;

        nodeBefore.next = newNode;
        nodeAfter.previous = newNode;

        newNode.next = nodeAfter;
        newNode.previous = nodeBefore;

        return location;
    }

    public void step(int steps) {
        if (steps == 0) {
            return;
        }
        for (int i = 0; i != Math.abs(steps); i++) {
            if (steps > 0) {
                current = current.next;
                middle = middle.next;
            } else {
                current = current.previous;
                middle = middle.previous;
            }
        }
    }

    public void delete() {
        if (current == null) {
            throw new NoSuchElementException("List is empty");
        }
        delete(0);
    }

    public void delete(int steps) {
        if (current == null) {
            throw new NoSuchElementException("List is empty");
        }

        size--;
        if (size == 0) {
            current = null;
            middle = null;
        } else {
            Node temp = current;
            Node xxx = current.next;
            for (int i = 0; i != steps; i++) {
                temp = temp.next;
            }

            Node old = temp;
            old.next.previous = old.previous;
            old.previous.next = old.next;

            if (current == temp) {
                current = xxx;
            }

            if (size % 2 == 1) {
                middle = middle.previous;
            } else {
                middle = middle.next;
            }
        }
    }

    public void deleteMiddle() {
        middle.next.previous = middle.previous;
        middle.previous.next = middle.next;

        size--;

        if (size == 0) {
            middle = null;
            current = null;
        } else {
            if (size % 2 == 1) {
                middle = middle.previous;
            } else {
                middle = middle.next;
            }
        }
    }

    @Override
    public String toString() {
        if (current == null) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node t = current;
        do {
            builder.append(t.data);
            t = t.next;
            if (t != current) {
                builder.append(", ");
            }
        } while (t != current);
        builder.append("]");
        return builder.toString();
    }
}
