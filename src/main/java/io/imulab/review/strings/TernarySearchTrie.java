package io.imulab.review.strings;

import java.util.Iterator;
import java.util.Optional;

public class TernarySearchTrie<V> implements StringSymbolTable<V> {

    private Node root;
    private int size;

    @Override
    public void put(String key, V value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, V value, int d) {
        assert d >= 0 && d < key.length();
        char c = key.charAt(d);

        if (x == null)
            x = new Node(c);

        if (c < x.c) {
            x.left = put(x.left, key, value, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, value, d);
        } else {
            if (d == key.length() - 1) {
                x.value = value;
                size++;
            } else {
                x.mid = put(x.mid, key, value, d + 1);
            }
        }

        return x;
    }

    @Override
    public Optional<V> get(String key) {
        Node n = get(root, key, 0);
        return (n == null) ? Optional.empty() : Optional.ofNullable(n.value);
    }

    private Node get(Node x, String key, int d) {
        assert d >= 0 && d < key.length();
        char c = key.charAt(d);

        if (x == null)
            return null;

        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c) {
            return get(x.right, key, d);
        } else {
            if (d == key.length() - 1) {
                return x;
            } else {
                return get(x.mid, key, d + 1);
            }
        }
    }

    @Override
    public void delete(String key) {
        Node n = get(root, key, 0);
        if (n.value != null) {
            n.value = null;
            size--;
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<String> keyIterator() {
        throw new UnsupportedOperationException("not implemented");
    }

    private class Node {
        private char c;
        private V value;
        private Node left, mid, right;

        Node(char c) {
            this.c = c;
        }
    }
}
