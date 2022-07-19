package datastructures.tree;

import java.util.*;

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

    public List<T> inOrder() {
        return inOrder(root);
    }

    private List<T> inOrder(Node<T> base) {
        if (base == null)
            return Collections.emptyList();
        return new LinkedList<>() {{
            addAll(inOrder(base.leftChild));
            add(base.value);
            addAll(inOrder(base.rightChild));
        }};
    }

    public List<T> preOrder() {
        return preOrder(root);
    }

    private List<T> preOrder(Node<T> base) {
        if (base == null)
            return Collections.emptyList();
        return new LinkedList<>() {{
            add(base.value);
            addAll(preOrder(base.leftChild));
            addAll(preOrder(base.rightChild));
        }};
    }

    public List<T> postOrder() {
        return postOrder(root);
    }

    private List<T> postOrder(Node<T> base) {
        if (base == null)
            return Collections.emptyList();
        return new LinkedList<>() {{
            addAll(postOrder(base.leftChild));
            addAll(postOrder(base.rightChild));
            add(base.value);
        }};
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> base) {
        if (base == null)
            return -1;
        return 1 + Math.max(height(base.leftChild), height(base.rightChild));
    }

    /**
     * O(n)
     */
    public T min() {
        return min(root);
    }

    private T min(Node<T> base) {
        if (isLeaf(base))
            return base.value;
        T minLeft = min(base.leftChild);
        T minRight = min(base.rightChild);
        return min(base.value, min(minLeft, minRight));
    }

    /**
     * Only with binary SEARCH trees O(log n)
     */
    public T minFast() {
        if (root == null) throw new IllegalStateException();
        return minFast(root);
    }

    private T minFast(Node<T> node) {
        if (node.leftChild == null)
            return node.value;
        return minFast(node.leftChild);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTree<T> that = (BinaryTree<T>) o;
        return equalsAll(root, that.root);
    }

    private boolean equalsAll(Node<T> treeOne, Node<T> treeTwo) {
        if (isLeaf(treeOne) || isLeaf(treeTwo))
            return equals(treeOne, treeTwo);
        if (equals(treeOne, treeTwo))
            return equalsAll(treeOne.leftChild, treeTwo.leftChild) &&
                    equalsAll(treeOne.rightChild, treeTwo.rightChild);
        return false;
    }

    private boolean equals(Node<T> treeOne, Node<T> treeTwo) {
        return Objects.equals(treeOne, treeTwo) &&
                Objects.equals(treeOne.leftChild, treeTwo.leftChild) &&
                 Objects.equals(treeOne.rightChild, treeTwo.rightChild);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

    private boolean isLeaf(Node<T> node) {
        return node.rightChild == null && node.leftChild == null;
    }

    private T min(T o1, T o2) {
        return o1.compareTo(o2) < 0 ? o1 : o2;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return value.equals(node.value);
        }
    }
}
