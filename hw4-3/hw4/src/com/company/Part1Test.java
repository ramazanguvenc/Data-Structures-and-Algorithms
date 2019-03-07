package com.company;

import static org.junit.Assert.*;

public class Part1Test {
    @org.junit.Test
    public void add() {
        Part1<Integer> test = new Part1<>();
        assertEquals(true,test.add(null,5));
    }

    @org.junit.Test
    public void postOrderSearch() {
        Part1<Integer> test = new Part1<>();
        test.add(null,5);
        assertNotNull(test.postOrderSearch(5));
    }

    @org.junit.Test
    public void levelOrderSearch() {
        Part1<Integer> test = new Part1<>();
        test.add(null,5);
        assertNotNull(test.postOrderSearch(5));
    }
}