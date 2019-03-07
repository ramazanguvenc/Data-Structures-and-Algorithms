package com.company;

import static org.junit.jupiter.api.Assertions.*;

class ReceptionistTest {

    /**
     * This test will fail if you change room_number to bigger than 19
     */
    @org.junit.jupiter.api.Test
    void check_in() {
        System.out.println("Unit test started for check_in method\n");
        try{
            Hotel input = new Hotel(1);
            Receptionist test = new Receptionist();
            int room_number = 2;
            assertEquals(true,test.check_in(input.get_rooms(),room_number),"check_in Unit test failed");
            System.out.println("\nUnit test successfully finished for check_in method\n");
        } catch (Exception e) {
            System.out.println("Exception caught while unit test");
        }


    }

    @org.junit.jupiter.api.Test
    void check_out() {
        System.out.println("Unit test started for check_out method\n");
        try{
            Hotel input = new Hotel(1);
            Receptionist test = new Receptionist();
            int room_number = 2;
            assertEquals(true,test.check_out(input.get_rooms(),room_number),"check_out Unit test failed");
            System.out.println("\nUnit test successfully finished for check_out method\n");
        } catch (Exception e) {
            System.out.println("Exception caught while unit test");
        }

    }
}