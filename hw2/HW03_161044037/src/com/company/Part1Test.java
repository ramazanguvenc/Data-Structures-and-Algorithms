package com.company;

import static org.junit.jupiter.api.Assertions.*;

class Part1Test {

    @org.junit.jupiter.api.Test
    void getByCode() {
        System.out.println("Unit test started for getByCode method\n");
        Part1 test = new Part1();
        assertEquals("1 CSE 101 Introduction To Computer Engineering 8 3 3+0+0",test.getByCode("CSE 101"));
        System.out.println("\nUnit test successfully finished getByCode method\n");

    }

    @org.junit.jupiter.api.Test
    void listSemesterCourses() {
        System.out.println("Unit test started for listSemesterCourses method\n");
        Part1 test = new Part1();
        assertEquals("1 CSE 101 Introduction To Computer Engineering 8 3 3+0+0",test.listSemesterCourses(1).get(1));
        System.out.println("\nUnit test successfully finished listSemesterCourses method\n");
    }

    @org.junit.jupiter.api.Test
    void getByRange() {
        System.out.println("Unit test started for getByRange method\n");
        Part1 test = new Part1();
        assertEquals("1 CSE 101 Introduction To Computer Engineering 8 3 3+0+0",test.getByRange(1,2).get(0));
        System.out.println("\nUnit test successfully finished getByRange method\n");
    }
}