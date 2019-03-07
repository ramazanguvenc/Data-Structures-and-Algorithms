package com.company;

import java.util.Scanner;

/**
 * Bu class kisaca sisteme Guest olarak girildiginizdeki yapabileceginiz seylerin methodunun bulundugu yer.
 */
public class Guest implements  User {
    private int room_number;
    private String name,surname;
    private String [] names,surnames,avaiables,checkins,roomsitutations;
    final private int TOTAL_ROOM_NUMBER = 30;
    final private int ONE_PERSON_ROOM_NUMBER = 20;


    /**
     * Bu fonksiyon oda ayirtmaya yariyor.
     * Kisaca kiralamak istediginiz odanin sayisina bakiyor ve ona gore 4 kisilik mi 1 kisilik mi oda istediginizi anliyor.
     * Ona gore sizden input girmenizi istiyor.
     * @param tmpRooms is Room Array which i hold whole of my rooms. Its the same thing HOTEL_RECORDS.csv in reality.
     */
    @Override
    public void book_a_room(Room [] tmpRooms) {
        while(true){
            System.out.println("Which room do you want to book ?");
            Scanner scanner = new Scanner(System.in);
            room_number = scanner.nextInt();
            //Checking for if he wants 1 person room or 4 people room.
            if(room_number <= ONE_PERSON_ROOM_NUMBER){
                if(tmpRooms[room_number].get_avaiable().compareTo("yes") == 0){
                    System.out.println("Please enter your name");
                    name = scanner.next();
                    System.out.println("Please enter your surname");
                    surname = scanner.next();
                    tmpRooms[room_number].set_room_things("no",name,surname,"no","booked");
                    System.out.printf("%d room booked.\n",room_number);
                    return;
                }
            }
            //Checking for if he wants 1 person room or 4 people room.
            if(room_number > ONE_PERSON_ROOM_NUMBER && room_number < TOTAL_ROOM_NUMBER){
                if(tmpRooms[room_number].get_avaiable().compareTo("yes") == 0){
                    names = new String[4];
                    surnames = new String[4];
                    avaiables = new String[4];
                    checkins = new String[4];
                    roomsitutations = new String[4];
                    for(int j=0 ; j<4 ; ++j){
                        System.out.println("Please enter the name");
                        names[j] = scanner.next();
                        System.out.println("Please enter the surname");
                        surnames[j] = scanner.next();
                        avaiables[j] = "no";
                        checkins[j] = "no";
                        roomsitutations[j] ="booked";

                    }
                    tmpRooms[room_number].multip_set_room_things(avaiables,names,surnames,checkins,roomsitutations);
                    return;

                }
            }
        }
    }

    /**
     * This function only wrote for tests. Please dont use !!
     */
    public boolean book_a_room(Room [] tmpRooms,int tmpRoomnumber,String tmpName,String tmpSurname){
        if(tmpRoomnumber > ONE_PERSON_ROOM_NUMBER || tmpRoomnumber < 0)
            return false;
        tmpRooms[tmpRoomnumber].set_room_things("no",tmpName,tmpSurname,"no","booked");
        System.out.printf("%d room booked.\n",tmpRoomnumber);
        return true;

    }

    /**
     * This function is canceling your reservation.
     * If you try to use not booked rooms it will throw Exception.
     * @param tmpRooms is Room Array which i hold whole of my rooms. Its the same thing HOTEL_RECORDS.csv in reality.
     */
    @Override
    public void cancel_reservation(Room[] tmpRooms) throws Exception {
        int i = 0;
        while(true){
            i++;
            System.out.println("Which room do you want to cancel your reservation ?");
            Scanner scanner = new Scanner(System.in);
            room_number = scanner.nextInt();
            if(room_number <= 20){
                if(tmpRooms[room_number].getRoomsitutation().compareTo("booked") == 0){
                    tmpRooms[room_number].set_room_things("yes","","","","");
                    System.out.printf("%d room number your reservation canceled.\n",room_number);
                    return;
                }
                if(i == 5) {
                    throw new Exception("Invalid Value");
                }
            }
            if(room_number > ONE_PERSON_ROOM_NUMBER && room_number < TOTAL_ROOM_NUMBER){

                tmpRooms[room_number].multip_set_room_things(new String[]{"", "", "", ""},new String[]{"", "", "", ""},new String[]{"", "", "", ""},new String[]{"", "", "", ""},new String[]{"", "", "", ""});
                System.out.printf("%d room number your reservation canceled.\n",room_number);
                return;

            }
        }

    }
    /**
     *This function only wrote for tests. Please dont use !!
     */
    public boolean cancel_reservation(Room[] tmpRooms,int rm){
        if(rm > ONE_PERSON_ROOM_NUMBER || rm < 0)
            return false;
        tmpRooms[rm].set_room_things("yes","","","","");
        System.out.printf("%d room number your reservation canceled.\n",rm);
        return true;
    }


    /**
     *
     * @return Guest
     */
    public String whoami(){
        return "Guest";
    }



}
