package datastructures.tree;

public class BinaryTree<T extends Comparable<T>> {

    private Node<T> root;

    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
            return;
        }
        Node<T> current = root;
        while (current != null) {
            if (current.value.compareTo(value) < 0)
                current = insertRight(current, value);
            else
                current = insertLeft(current, value);
        }
    }

    private Node<T> insertRight(Node<T> base, T value) {
        if (base.rightChild != null) {
            return base.rightChild;
        } else {
            base.rightChild = new Node<>(value);
            return null;
        }
    }

    private Node<T> insertLeft(Node<T> base, T value) {
        if (base.leftChild != null) {
            return base.leftChild;
        } else {
            base.leftChild = new Node<>(value);
            return null;
        }
    }

    public boolean contains(T value) {
        Node<T> current = root;
        while (current != null) {
            if (current.value.equals(value))
                return true;

            if (current.value.compareTo(value) < 0) {
                current = current.rightChild;
            } else {
                current = current.leftChild;
            }
        }
        return false;
    }


    private static class Node<T> {

        Node<T> leftChild;
        Node<T> rightChild;

        T value;

        public Node(T value) {
            this.value = value;
        }
    }
}
