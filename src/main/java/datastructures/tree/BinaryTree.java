package datastructures.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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


  /*  public List<T> levelOrder() {
        return new LinkedList<>(levelOrder(root)) {{ addFirst(root.value); }};
    }

    private List<T> levelOrder(Node<T> base) {
        List<T> list = new LinkedList<>();
        if (base.leftChild != null)
            list.add(base.leftChild.value);
        if (base.rightChild != null)
            list.add(base.rightChild.value);

        if (base.leftChild != null)
            list.addAll(levelOrder(base.leftChild));
        if (base.rightChild != null)
            list.addAll(levelOrder(base.rightChild));
        return list;
    }
*/
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
