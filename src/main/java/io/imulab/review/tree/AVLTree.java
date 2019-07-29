package io.imulab.review.tree;

import java.util.Iterator;
import java.util.Optional;

@SuppressWarnings("Duplicates")
public class AVLTree<K extends Comparable<K>, V> implements SymbolTable<K, V> {

    private int size;
    private Node root;

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            size++;
            return new Node(key, value);
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0 ) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        updateHeight(x);

        int balance = balanceOf(x);

        // left - left
        if (balance > 1 && key.compareTo(x.left.key) < 0) {
            return rightRotate(x);
        }

        // left - right
        if (balance > 1 && key.compareTo(x.left.key) > 0) {
            x.left = leftRotate(x.left);
            return rightRotate(x);
        }

        // right - right
        if (balance < -1 && key.compareTo(x.right.key) > 0) {
            return leftRotate(x);
        }

        // right - left
        if (balance < -1 && key.compareTo(x.right.key) < 0) {
            x.right = rightRotate(x.right);
            return leftRotate(x);
        }

        return x;
    }

    private Node leftRotate(Node h) {
        Node x = h.right;

        h.right = x.left;
        x.left = h;

        updateHeight(h);
        updateHeight(x);

        return x;
    }

    private Node rightRotate(Node h) {
        Node x = h.left;

        h.left = x.right;
        x.right = h;

        updateHeight(h);
        updateHeight(x);

        return x;
    }

    @Override
    public Optional<V> get(K key) {
        Node c = root;

        while (c != null) {
            int cmp = key.compareTo(c.key);
            if (cmp < 0) {
                c = c.left;
            } else if (cmp > 0) {
                c = c.right;
            } else {
                return Optional.of(c.value);
            }
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
        } else if (cmp > 0 ) {
            x.right = delete(x.right, key);
        } else {
            size--;

            if (x.left == null) {
                x = x.right;
            } else if (x.right == null) {
                x = x.left;
            } else {
                Node t = x;
                x = getMin(t.right);
                x.right = deleteMin(t.right);
                x.left = t.left;
            }
        }

        if (x == null)
            return null;

        updateHeight(x);

        int balance = balanceOf(x);

        // left - left
        if (balance > 1 && balanceOf(x.left) >= 0) {
            return rightRotate(x);
        }

        // left - right
        if (balance > 1 && balanceOf(x.left) < 0) {
            x.left = leftRotate(x.left);
            return rightRotate(x);
        }

        // right - right
        if (balance < -1 && balanceOf(x.right) <= 0) {
            return leftRotate(x);
        }

        // right - left
        if (balance < -1 && balanceOf(x.right) > 0) {
            x.right = rightRotate(x.right);
            return leftRotate(x);
        }

        return x;
    }

    private Node getMin(Node x) {
        assert x != null;
        Node c = x;
        while (c.left != null) {
            c = c.left;
        }

        return c;
    }

    private Node deleteMin(Node x) {
        assert x != null;
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<K> keyIterator() {
        throw new UnsupportedOperationException("not implemented");
    }

    private int balanceOf(Node x) {
        return heightOf(x.left) - heightOf(x.right);
    }

    private int heightOf(Node x) {
        return x == null ? 0 : x.height;
    }

    private void updateHeight(Node x) {
        if (x != null) {
            x.height = 1 + Math.max(heightOf(x.left), heightOf(x.right));
        }
    }

    private class Node {
        private final K key;
        private V value;
        private Node left;
        private Node right;
        private int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }
}
