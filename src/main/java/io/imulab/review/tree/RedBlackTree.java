package io.imulab.review.tree;

import java.util.Iterator;
import java.util.Optional;

@SuppressWarnings("Duplicates")
public class RedBlackTree<K extends Comparable<K>, V> implements SymbolTable<K, V> {

    private static final Boolean RED = true;
    private static final Boolean BLACK = false;

    private Node root;
    private int size;

    // Case 1: Insert into one node
    // - If new node is inserted to the left, done
    // - If new node is inserted to the right, we are right leaning, do left rotation
    //
    //   b (insert a)  ->   b  ->  done
    //                    //
    //                    a
    //
    //   a (insert b)  ->  a   --(left rotate)-->  b
    //                     \\                     //
    //                      b                     a
    //
    // Case 2: Insert into a two node
    //
    //   b  --(insert c)-->  b  --(color flip)-->  b
    //  //                 // \\                  / \
    //  a                  a   c                 a  c
    //
    //   c  --(insert a)-->  c  --(right rotate)-->  b  --(color flip)-->  b
    //  //                  //                      //\\                  / \
    //  b                   b                      a   c                 a   c
    //                     //
    //                    a
    //
    //   c  --(insert b)-->  c  --(left rotate a)-->  c  --(right rotate c)-->  b  --(color flip)-->  b
    //  //                  //                       //                       // \\                  / \
    //  a                  a                        b                        a    c                 a  c
    //                      \\                     //
    //                       b                     a
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node h, K key, V value) {
        if (h == null) {
            size++;
            return new Node(key, value, RED);
        }


        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);
        } else {
            h.value = value;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = leftRotate(h);
        }

        if (isRed(h.left) && isRed(h.left.left)) {
            h = rightRotate(h);
        }

        if (isRed(h.left) && isRed(h.right)) {
            flipColor(h);
        }

        return h;
    }

    @Override
    public Optional<V> get(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return Optional.of(x.value);
        }
        return Optional.empty();
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
            size--;

            // one child or no child
            if (x.left == null)
                return x.right;
            if (x.right == null)
                return x.left;

            Node x0 = x;
            x = getMin(x0.right);
            x.right = deleteMin(x0.right);
            x.left = x0.left;
        }

        return x;
    }

    private Node getMin(Node x) {
        Node t = x;
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    // Left rotation happens when the
    // red link is right leaning:
    //
    // Before:
    //     h
    //    / \\
    //   1   x
    //      / \
    //     2  3
    //
    // After:
    //        x
    //      // \
    //     h   3
    //    / \
    //   1  2
    private Node leftRotate(Node h) {
        assert h.right.color == RED;

        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;

        return x;
    }

    // right rotation is a temporary operation,
    // which can be rectified by other rotations.
    //
    // Before:
    //     h
    //   // \
    //   x   3
    //  / \
    // 1  2
    //
    // After:
    //        x
    //       / \\
    //      1   h
    //         / \
    //        2   3
    private Node rightRotate(Node h) {
        assert h.left.color == RED;

        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;

        return x;
    }

    // color flip happens when both left and right link is red.
    //
    // Before:
    //      1
    //      |
    //      h
    //    // \\
    //   2    3
    //
    // After:
    //      1
    //      ||
    //      h
    //     / \
    //    2   3
    private void flipColor(Node h) {
        assert h.left.color == RED && h.right.color == RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<K> keyIterator() {
        throw new UnsupportedOperationException("not implemented");
    }

    private class Node {

        private final K key;
        private V value;
        private Node left;
        private Node right;
        private boolean color;

        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }
}
