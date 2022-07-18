package datastructures.tree;

public class BinaryTree<T extends Comparable<T>> {

    private Node<T> root;

    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
            return;
        }
        var parent = findParent(value);
        if (parent.value.compareTo(value) < 0) parent.rightChild = new Node<>(value);
        else parent.leftChild = new Node<>(value);
    }

    private Node<T> findParent(T value) {
        Node<T> current = root;
        while (true) {
            int comparison = current.value.compareTo(value);
            Node<T> next = comparison < 0 ? current.rightChild : current.leftChild;
            if (next == null)
                return current;
            current = next;
        }
    }

    public boolean contains(T value) {
        Node<T> current = root;
        while (current != null) {
            if (current.value.equals(value))
                return true;

            if (current.value.compareTo(value) < 0)
                current = current.rightChild;
            else
                current = current.leftChild;
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
