package com.company;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class Part3Test {

    @Test
    void remove() {
        Part1 tmp = new Part1();
        Part3<String> tmp1 = new Part3<>();
        for(int i = 0 ; i < 8;i++){
            LinkedList<String> a = tmp.listSemesterCourses(i);
            for(int j=0;j<a.size();j++)
                tmp1.add(a.get(j));
        }
        tmp1.next();
        assertEquals(true,tmp1.remove(tmp1.next()));
    }

    @Test
    void add() {
        Part1 tmp = new Part1();
        Part3<String> tmp1 = new Part3<>();
        LinkedList<String> a = tmp.listSemesterCourses(1);
        assertEquals(true,tmp1.add(a.get(0)));
    }

    @Test
    void hasNext() {
        Part1 tmp = new Part1();
        Part3<String> tmp1 = new Part3<>();
        for(int i = 0 ; i < 8;i++){
            LinkedList<String> a = tmp.listSemesterCourses(i);
            for(int j=0;j<a.size();j++)
                tmp1.add(a.get(j));
        }
        while(tmp1.hasNext())
            tmp1.next();
    }

    @Test
    void next() {
        Part1 tmp = new Part1();
        Part3<String> tmp1 = new Part3<>();
        for(int i = 0 ; i < 8;i++){
            LinkedList<String> a = tmp.listSemesterCourses(i);
            for(int j=0;j<a.size();j++)
                tmp1.add(a.get(j));
        }
        while(tmp1.hasNext())
            tmp1.next();
    }
}