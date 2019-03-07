package com.company;

/**
 * Interface for normal searchTree
 * @param <E> should be comparable.
 */
public interface searchTree<E> {
    boolean add(E item);
    boolean contains(E target);
    E find(E target);
    E delete(E target);
    boolean remove(E target);
}
