package datastructures.tree;

public class BinaryTree<T extends Comparable<T>> {

    private Node<T> root;

    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
            return;
        }
        var current = root;
        while (true) {
            if (current.value.compareTo(value) < 0) {
                if (current.rightChild == null) {
                    current.rightChild = new Node<>(value);
                    break;
                }
                current = current.rightChild;
            } else {
                if (current.leftChild == null) {
                    current.leftChild = new Node<>(value);
                    break;
                }
                current = current.leftChild;
            }
        }
    }

    public boolean contains(T value) {
        Node<T> current = root;
        while (current != null) {
            int comparison = current.value.compareTo(value);
            if (comparison < 0) current = current.rightChild;
            else if (comparison > 0) current = current.leftChild;
            else return true;
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

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
