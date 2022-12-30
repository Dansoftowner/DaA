package playground;

import datastructures.hashtable.HashTableLin;
import datastructures.tree.BinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import static java.lang.Math.max;

public class Playground {

    public static void main(String[] args) {
        var tree = new BinaryTree2();
       // var tree2 = new BinaryTree<Integer>();
        for (int i : new int[] { 7, 4, 9, 1,6, 8, 10/*10, 5, 15, 6, 1, 8, 12, 18, 17*/}) {
            tree.insert(i);
         //   tree2.insert(i);
        }

        System.out.println();
        //tree2.insert(2);
//
//        System.out.println(tree.getAncestors(4));
//        System.out.println(tree.areSiblings(10, 9));
//        System.out.println(tree.max());
//        System.out.println(tree.maxFast());
//        System.out.println(tree.countLeaves());
//        System.out.println(tree.size());
//        System.out.println(tree2.levelOrder());
//
//        tree.swapRoot();
//        System.out.println(tree.isSearchTree());
//        System.out.println(tree.equals(tree2));
//        System.out.println(tree.nodesAtKDistance(10));
        /*var tree = new BinaryTree<Integer>();
        tree.insert(1);
        System.out.println(tree.contains(1));

        tree.insert(2);
        tree.insert(7);
        tree.insert(19);
        tree.insert(-1);
        System.out.println(tree.contains(2));
        System.out.println(tree.contains(7));
        System.out.println(tree.contains(19));
        System.out.println(tree.contains(-1));
        System.out.println(tree.contains(1900));

        for (int i = 0; i < 32; i++)
            tree.insert(i);
        boolean containsAll = true;
        for (int i = 0; i<32; i++)
            containsAll = containsAll && tree.contains(i);
        System.out.println(containsAll);*/
    }


    public static void mainHashtable(String[] args) {
        Supplier<Object> fn = () -> new Object() {
            @Override
            public int hashCode() {
                return 1;
            }
        };
        var obj1 = fn.get();
        var obj2 = fn.get();

        var hs = new HashTableLin<>(10);
        hs.put(obj1, "Sanyi");
        hs.put(obj2, "Manyi");
        hs.put("obj2", "KOMÁM");
        System.out.println(hs.get(obj1));
        System.out.println(hs.get(obj2));
        System.out.println(hs.get("obj2"));
        System.out.println(hs.containsKey("asdf"));
        System.out.println(hs.containsKey(obj1));
        System.out.println(hs.containsKey(obj2));
        System.out.println(hs.containsKey("obj2"));
    }
}

class Hashtable {

    private final LinkedList<Entry>[] store;

    public Hashtable(int capacity) {
        this.store = new LinkedList[capacity];
    }

    public void put(Object key, Object value) {
        var bucket = getBucket(key);
        Entry found = find(bucket, key);
        if (found != null)
            bucket.remove(found);
        bucket.add(new Entry(key, value));
    }

    public Object get(Object key) {
        Entry entry = find(getBucket(key), key);
        return entry != null ? entry.value : null;
    }

    public boolean containsKey(Object key) {
        var bucket = getBucket(key);
        if (bucket == null || bucket.isEmpty()) return false;
        return find(bucket, key) != null;
    }

    private Entry find(LinkedList<Entry> bucket, Object key) {
        for (Entry entry : bucket) {
            if (entry.key.equals(key))
                return entry;
        }
        return null;
    }

    public LinkedList<Entry> getBucket(Object key) {
        int h = getHash(key);
        var bucket = store[h];
        if (bucket == null)
            bucket = (store[h] = new LinkedList<>());
        return bucket;
    }

    private int getHash(Object key) {
        return Math.abs(key.hashCode()) % store.length;
    }

    record Entry(Object key, Object value) {
    }

}

class BinaryTree2 {

    private Node root;

    void insert(int item) {
        var newNode = new Node(item);
        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        while (true) {
            if (current.value < item) {
                if (current.rightChild == null) {
                    current.rightChild = newNode;
                    break;
                }

                current = current.rightChild;

            } else {
                if (current.leftChild == null) {
                    current.leftChild = newNode;
                    break;
                }
                current = current.leftChild;
            }
        }
    }

    boolean find(int item) {
        // O(log n)
        Node current = root;
        while (current != null) {
            if (current.value == item)
                return true;
            else if (current.value > item)
                current = current.leftChild;
            else
                current = current.rightChild;
        }
        return false;
    }

    boolean contains(int item) {
        return contains(root, item);
    }

    private boolean contains(Node node, int item) {
        if (node == null)
            return false;
        if (node.value == item)
            return true;
        return contains(node.leftChild, item) || contains(node.rightChild, item);
    }

    List<Integer> traversePreOrder() {
        var list = new LinkedList<Integer>();
        traversePreOrder(root, list);
        return list;
    }

    private void traversePreOrder(Node node, LinkedList<Integer> list) {
        if (node == null)
            return;
        list.add(node.value);
        traversePreOrder(node.leftChild, list);
        traversePreOrder(node.rightChild, list);
    }


    List<Integer> traverseInOrder() {
        var list = new LinkedList<Integer>();
        traverseInOrder(root, list);
        return list;
     }

    private void traverseInOrder(Node node, LinkedList<Integer> list) {
        if (node == null)
            return;
        traverseInOrder(node.leftChild, list);
        list.add(node.value);
        traverseInOrder(node.rightChild, list);
    }

    List<Integer> traversePostOrder() {
        var list = new LinkedList<Integer>();
        traversePostOrder(root, list);
        return list;
    }

    private void traversePostOrder(Node node, LinkedList<Integer> list) {
        if (node == null)
            return;
        traversePostOrder(node.leftChild, list);
        traversePostOrder(node.rightChild, list);
        list.add(node.value);
    }

    List<Integer> traverseLevelOrder() {
        List<Integer> list = new LinkedList<>();
        int height = height();
        for (int i = 0; i <= height; i++) {
            list.addAll(nodesAtKDistance(i));
        }
        return list;
    }

    int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null)
            return -1;
        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    int min() {
        return min(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private int min(Node node, int min, int max) {
        if (node == null)
            return Integer.MAX_VALUE;
        if (isLeaf(node))
            return node.value;
        int minLeft = min(node.leftChild, min, node.value);
        int minRight = min(node.rightChild, node.value, max);
        return Math.min(node.value, Math.min(minLeft, minRight));
    }

    List<Integer> nodesAtKDistance(int distance) {
        var list = new LinkedList<Integer>();
        nodesAtKDistance(root, distance, list);
        return list;
    }

    private void nodesAtKDistance(Node node, int distance, LinkedList<Integer> list) {
        if (node == null)
            return;
        if (distance == 0) {
            list.add(node.value);
            return;
        }
        nodesAtKDistance(node.leftChild, distance -1, list);
        nodesAtKDistance(node.rightChild, distance -1, list);
    }

    private boolean isLeaf(Node node) {
        return node.leftChild == null && node.rightChild == null;
    }

    static class Node {
        int value;
        Node leftChild;
        Node rightChild;

        public Node(int value) {
            this.value = value;
        }
    }
}