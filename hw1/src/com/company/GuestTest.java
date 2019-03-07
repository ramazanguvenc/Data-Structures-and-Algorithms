package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    @Test
    void book_a_room() {
        System.out.println("Unit test started for book_a_room method\n");
        Guest test = new Guest();
        try{
            Hotel input = new Hotel(1);
            int room_number = 2;
            String name = "Ramazan";
            String surname = "Guvenc";
            assertEquals(true,test.book_a_room(input.get_rooms(),room_number,name,surname),"check_in Unit test failed");
            System.out.println("\nUnit test successfully finished for book_a_room method\n");
        } catch (Exception e) {
            System.out.println("Exception caught while unit test");
        }

    }

    @Test
    void cancel_reservation() {
        System.out.println("Unit test started for book_a_room method\n");
        Guest test = new Guest();
        try{
            Hotel input = new Hotel(1);
            int room_number = 2;
            String name = "Ramazan";
            String surname = "Guvenc";
            assertEquals(true,test.cancel_reservation(input.get_rooms(),room_number),"check_in Unit test failed");
            System.out.println("\nUnit test successfully finished for book_a_room method\n");
        } catch (Exception e) {
            System.out.println("Exception caught while unit test");
        }


    }
}