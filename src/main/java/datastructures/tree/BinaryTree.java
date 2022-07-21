package datastructures.tree;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        return minNode(base.value, minNode(minLeft, minRight));
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

    public void swapRoot() {
        var temp = root.leftChild;
        root.leftChild = root.rightChild;
        root.rightChild = temp;
    }

    public boolean isSearchTree() {
        Node<T> previousLeft = root;
        Node<T> previousRight = root;
        Node<T> currentLeft = root.leftChild;
        Node<T> currentRight = root.rightChild;
        while (currentLeft != null || currentRight != null) {
            if (currentLeft != null) {
                if (currentLeft.value.compareTo(previousLeft.value) > 0)
                    return false;
                currentLeft = currentLeft.leftChild;
                previousLeft = previousLeft.leftChild;
            }
            if (currentRight != null) {
                if (currentRight.value.compareTo(previousRight.value) < 0)
                    return false;
                currentRight = currentRight.rightChild;
                previousRight = previousRight.rightChild;
            }

        }
        return true;
    }

    public List<T> nodesAtKDistance(int dist) {
        return nodesAtKDistance(root, dist);
    }

    private List<T> nodesAtKDistance(Node<T> node, int dist) {
        if (node == null)
            return Collections.emptyList();
        if (dist == 0)
            return List.of(node.value);
        return Stream.of(
                nodesAtKDistance(node.leftChild, dist - 1),
                nodesAtKDistance(node.rightChild, dist - 1)
                )
                .flatMap(Collection::stream)
                .toList();
    }

    public List<T> levelOrder() {
        return IntStream.rangeClosed(0, height())
                .mapToObj(this::nodesAtKDistance)
                .flatMap(Collection::stream)
                .toList();
    }

    public int size() {
        return size(root);
    }

    private int size(Node<T> node) {
        if (node == null)
            return 0;
        return 1 + size(node.leftChild) + size(node.rightChild);
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node<T> node) {
        if (node == null)
            return 0;
        if (isLeaf(node))
            return 1;
        return countLeaves(node.leftChild) + countLeaves(node.rightChild);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTree<T> that = (BinaryTree<T>) o;
        return equalsAll(root, that.root);
    }

    private boolean equalsAll(Node<T> treeOne, Node<T> treeTwo) {
        if (treeOne == null && treeTwo == null)
            return true;
        if (treeOne != null && treeTwo != null)
            return treeOne.equals(treeTwo) &&
                    equalsAll(treeOne.leftChild, treeTwo.leftChild) &&
                    equalsAll(treeOne.rightChild, treeTwo.rightChild);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

    private boolean isLeaf(Node<T> node) {
        return node.rightChild == null && node.leftChild == null;
    }

    private T minNode(T o1, T o2) {
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
