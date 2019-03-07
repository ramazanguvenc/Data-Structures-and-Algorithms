package com.company;

public interface SearchTree<E> {
    E find(E target);
    boolean contains(E target);
    boolean add(E item);
    boolean remove(E o);
    E delete(E o);

}
