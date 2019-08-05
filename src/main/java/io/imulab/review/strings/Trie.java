package io.imulab.review.strings;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;

public class Trie<V> implements StringSymbolTable<V> {

    private static final int R = 256;
    private static final Function<Character, Integer> charToIndexFunc = c -> (int) c;
    private int size = 0;

    private Node root = new Node();

    @Override
    public void put(String key, V value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, V value, int d) {
        if (x == null)
            return new Node();

        if (d == key.length()) {
            if (x.value == null)
                size++;
            x.value = value;
            return x;
        }

        int nextIndex = charToIndexFunc.apply(key.charAt(d));
        assert nextIndex >= 0 && nextIndex < R;
        x.next[nextIndex] = put(x.next[nextIndex], key, value, d + 1);
        return x;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<V> get(String key) {
        Node n = get(root, key, 0);
        if (n == null || n.value == null)
            return Optional.empty();
        return Optional.of((V) n.value);
    }

    public Node get(Node x, String key, int d) {
        if (x == null)
            return null;

        if (d == key.length())
            return x;

        int nextIndex = charToIndexFunc.apply(key.charAt(d));
        assert nextIndex >= 0 && nextIndex < R;
        return get(x.next[nextIndex], key, d+1);
    }

    @Override
    public void delete(String key) {
        Node x = get(root, key, 0);
        if (x == null)
            return;

        if (x.value != null) {
            size--;
            x.value = null;
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

    private static class Node {
        Object value;
        Node[] next = new Node[R];
    }
}
