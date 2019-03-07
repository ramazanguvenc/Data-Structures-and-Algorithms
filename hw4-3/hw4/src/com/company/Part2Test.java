package com.company;

import org.junit.Test;

import static org.junit.Assert.*;

public class Part2Test {

    @Test
    public void add() throws Exception {
        Part2<Integer> a = new Part2<>(1);
        assertEquals(true,a.add(5));
    }

    @Test
    public void contains() throws Exception {
        Part2<Integer> a = new Part2<>(1);
        a.add(5);
        assertEquals(true,a.contains(5));
    }

    @Test
    public void find() throws Exception {
        Part2<Integer> a = new Part2<>(1);
        a.add(5);
        assertNotNull(a.find(5));
    }

    @Test
    public void delete() throws Exception {
        Part2<Integer> a = new Part2<>(1);
        a.add(5);
        assertNotNull(a.delete(5));
    }

    @Test
    public void remove() throws Exception {
        Part2<Integer> a = new Part2<>(1);
        a.add(5);
        assertEquals(true,a.remove(5));
    }
}