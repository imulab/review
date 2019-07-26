package io.imulab.review.tree;

import java.util.Iterator;
import java.util.Optional;

public interface SymbolTable<K, V> {

    /**
     * Put a key corresponding to a value into the table.
     */
    void put(K key, V value);

    /**
     * Get the value corresponding to a key.
     */
    Optional<V> get(K key);

    /**
     * Remove the key from the table.
     */
    void delete(K key);

    /**
     * Return the size of the table.
     */
    int size();

    /**
     * Return the iterator over all keys in the table.
     */
    Iterator<K> keyIterator();
}
