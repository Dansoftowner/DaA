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

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        if (isLeftHeavy(root))
            System.out.printf("%s is left heavy! %n", root.item);
        else if (isRightHeavy(root))
            System.out.printf("%s is right heavy! %n", root.item);

        return root;
    }

    private int height(Node node) {
        return node == null ? -1 : node.height;
    }

    private boolean isLeftHeavy(Node node) {
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(Node node) {
        return balanceFactor(node) < -1;
    }

    private int balanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

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

        int height;

        public Node(int item) {
            this.item = item;
        }
    }
}
