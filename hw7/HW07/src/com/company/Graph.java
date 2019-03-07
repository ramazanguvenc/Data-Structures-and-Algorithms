package com.company;

import java.util.Iterator;

public interface Graph {
    int getNumV();
    Iterator< Edge > edgeIterator(int source);
}
