package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Part2Test {

    @Test
    void disable() {
        System.out.println("Unit test started for disable method\n");
        Part2<String> test = new Part2<>();
        test.add("Ramazan");
        try {
            assertEquals(true,test.disable(0));
        } catch (Exception e) {
            System.out.println("Something went wrong with disable test");
        }
        System.out.println("\nUnit test successfully finished disable method\n");
    }

    @Test
    void enable() {
        System.out.println("Unit test started for enable method\n");
        Part2<String> test = new Part2<>();
        test.add("Ramazan");
        try {
            test.disable(0);
            assertEquals(true,test.enable(0));
        } catch (Exception e) {
            System.out.println("Something went wrong with enable test");
        }
        System.out.println("\nUnit test successfully finished enable method\n");
    }
}