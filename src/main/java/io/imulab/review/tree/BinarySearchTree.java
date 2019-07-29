package io.imulab.review.tree;

import java.util.*;

@SuppressWarnings("Duplicates")
public class BinarySearchTree<K extends Comparable<K>, V> implements SymbolTable<K, V> {

    private Node root;

    public BinarySearchTree() {
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node root, K key, V value) {
        if (root == null) {
            return new Node(key, value);
        }

        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = put(root.left, key, value);
        } else if (cmp > 0) {
            root.right = put(root.right, key, value);
        } else {
            root.value = value;
        }

        root.updateSize();
        return root;
    }

    @Override
    public Optional<V> get(K key) {
        Node cursor = root;

        while (cursor != null) {
            int cmp = key.compareTo(cursor.key);
            if (cmp == 0) {
                return Optional.of(cursor.value);
            } else if (cmp < 0) {
                cursor = cursor.left;
            } else {
                cursor = cursor.right;
            }
        }

        return Optional.empty();
    }

    @Override
    public int size() {
        if (root == null)
            return 0;
        return root.size;
    }

    @Override
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            // one child or no child
            if (x.left == null)
                return x.right;
            if (x.right == null)
                return x.left;

            // two child
            // Replace this node with the smallest node in the right sub-tree
            // The smallest node in the right sub-tree is guaranteed to be
            // 1. bigger than the left sub-tree, because it is from the right sub-tree
            // 2. smaller than the all of the right sub-tree, because it is the min from the original right sub-tree.
            Node t = x;
            x = getMin(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        x.updateSize();
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.updateSize();
        return x;
    }

    private Node getMin(Node x) {
        Node t = x;
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    @Override
    public Iterator<K> keyIterator() {
        Collection<Node> nodes = new ArrayList<>();
        traverseAndCollect(root, nodes);
        return nodes.stream().map(node -> node.key).iterator();
    }

    private void traverseAndCollect(Node x, Collection<Node> nodes) {
        if (x == null)
            return;
        traverseAndCollect(x.left, nodes);
        nodes.add(x);
        traverseAndCollect(x.right, nodes);
    }

    /**
     * A node in the tree.
     */
    private class Node {

        private final K key;
        private V value;
        private Node left;
        private Node right;
        private int size;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.updateSize();
        }

        void updateSize() {
            this.size = 1 + ((left == null) ? 0 : left.size) + ((right == null) ? 0 : right.size);
        }
    }
}
