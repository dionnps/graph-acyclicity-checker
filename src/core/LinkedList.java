package core;

import java.util.ArrayList;
import java.util.List;

public class LinkedList {
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;

    public void add(int value) {
        if (!contains(value)) {
            Node newNode = new Node(value);

            if (head == null) {
                head = newNode;
                return;
            }

            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public boolean contains(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void remove(int value) {
        if (head == null) {
            return;
        }

        if (head.data == value) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data == value) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public List<Integer> toList() {
        List<Integer> list = new ArrayList<>();
        Node current = head;

        while (current != null) {
            list.add(current.data);
            current = current.next;
        }

        return list;
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}
