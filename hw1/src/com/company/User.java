package com.company;

/**
 * Basic interface and wrote for Guest and Receptionist classes.
 */
public interface User {
    void book_a_room(Room [] tmp);
    void cancel_reservation(Room[] tmp) throws Exception;
}
