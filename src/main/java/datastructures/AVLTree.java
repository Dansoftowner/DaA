package datastructures;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {

    private Node root;


    private boolean isEmpty() {
        return root == null;
    }

    public void insert(int item) {
        root = insert(root, item);
    }

    private Node insert(Node root, int item) {
        if (root == null)
            return new Node(item);

        if (root.item < item)
            root.right = insert(root.right, item);
        else
            root.left = insert(root.left, item);
        return root;
    }

    /*public void insert(int item) {
        if (isEmpty()) {
            root = new Node(item);
            return;
        }
        insert(root, item);
    }

    private void insert(Node root, int item) {
        if (root.item < item) {
            if (root.right == null) {
                root.right = new Node(item);
                return;
            }
            insert(root.right, item);
        } else {
            if (root.left == null) {
                root.left = new Node(item);
                return;
            }
            insert(root.left, item);
        }
    }*/

    public List<Integer> inOrderTraversal() {
        var list = new ArrayList<Integer>();
        inOrderTraversal(root, list);
        return list;
    }

    private void inOrderTraversal(Node root, ArrayList<Integer> list) {
        if (root == null)
            return;
        inOrderTraversal(root.left, list);
        list.add(root.item);
        inOrderTraversal(root.right, list);
    }

    public static void main(String[] args) {
        var tree = new AVLTree();

        for (int i : new int[]{2, -1, 21, 18, 10, 6}) {
            tree.insert(i);
        }

        System.out.println(tree.inOrderTraversal());
    }

    private class Node {
        int item;
        Node left;
        Node right;

        public Node(int item) {
            this.item = item;
        }
    }
}
