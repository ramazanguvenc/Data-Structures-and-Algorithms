package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Burasi ana sistemimizin bulundugu constructorlarindan test veya normal sekilde girebileceginiz classimiz.
 */
public class Hotel {
    final private int TOTAL_ROOM_NUMBER = 30;
    final private int ONE_PERSON_ROOM_NUMBER = 20;
    protected Room[] rooms = new Room[TOTAL_ROOM_NUMBER];
    String whoami, answer;
    int answer1;

    /**
     * Test constructor.
     *
     * @param x is useless only put there for overload.
     */
    public Hotel(int x) throws Exception {
        for (int i = 0; i < TOTAL_ROOM_NUMBER; ++i) {
            rooms[i] = new Room();
            rooms[i].set_rm(i);
        }

    }

    /**
     * This class basically wants inputs from user. If you want to test this system not use it please use other constructor.
     */
    public Hotel() throws Exception {
        for (int i = 0; i < TOTAL_ROOM_NUMBER; ++i) {
            rooms[i] = new Room();
            rooms[i].set_rm(i);
        }
        start();
    }


    /**
     * prints available rooms
     * checks Room[]rooms for that.
     */
    private void print_avaiable_rooms() {
        System.out.println("TEK KISILIK ODALAR");
        for (int i = 0; i < ONE_PERSON_ROOM_NUMBER - 1; ++i) {
            if (rooms[i].get_avaiable().compareTo("yes") == 0)
                System.out.printf("%d , ", rooms[i].get_rm());
        }
        System.out.printf("%d \n", rooms[ONE_PERSON_ROOM_NUMBER - 1].get_rm());
        System.out.println("4 KISILIK ODALAR");
        String[] x;
        for (int i = ONE_PERSON_ROOM_NUMBER; i < TOTAL_ROOM_NUMBER - 1; ++i) {
            x = rooms[i].get_avaiables();
            if (x[0].compareTo("yes") == 0)
                System.out.printf("%d , ", rooms[i].get_rm());
        }
        System.out.printf("%d \n", rooms[TOTAL_ROOM_NUMBER - 1].get_rm());


    }

    /**
     * This method creates HOTEL_RECORD.csv
     * start() uses this method if you say yes to "Do you enter for first time" question.
     * Handle his own errors.
     */
    private void create_Record() {
        try (FileWriter writer = new FileWriter("HOTEL_RECORD.csv")) {
            //If its first time.
            writer.append("ROOM NUMBERS");
            writer.append(';');
            writer.append("NAMES");
            writer.append(';');
            writer.append("SURNAMES");
            writer.append(';');
            // writer.append("IDs");
            // writer.append(',');
            writer.append("AVAIABLE");
            writer.append(';');
            writer.append("CHECK CONTROL");
            writer.append(';');
            writer.append("ROOM SITUATION");
            writer.append('\n');
            for (Integer i = 0; i < ONE_PERSON_ROOM_NUMBER; ++i) {
                rooms[i].set_rm(i);
                writer.append(i.toString());
                writer.append(';');
                writer.append('\n');
            }
            for (Integer i = ONE_PERSON_ROOM_NUMBER; i < TOTAL_ROOM_NUMBER; ++i) {
                for (int j = 0; j < 4; ++j) {
                    rooms[i].set_rm(i);
                    writer.append(i.toString());
                    writer.append(';');
                    writer.append('\n');
                }
            }
            //writer.append(',');


            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong. Please try again");
        }
    }

    /**
     * This method updates HOTEL_RECORD.csv for latest changes.
     * Handle his own errors.
     */
    private void updateRecord() {
        try (FileWriter writer = new FileWriter("HOTEL_RECORD.csv")) {
            writer.append("ROOM NUMBERS");
            writer.append(';');
            writer.append("NAMES");
            writer.append(';');
            writer.append("SURNAMES");
            writer.append(';');
            // writer.append("IDs");
            // writer.append(',');
            writer.append("AVAIABLE");
            writer.append(';');
            writer.append("CHECK CONTROL");
            writer.append(';');
            writer.append("ROOM SITUATION");
            writer.append('\n');
            for (Integer i = 0; i < ONE_PERSON_ROOM_NUMBER; ++i) {
                writer.append(i.toString());
                writer.append(';');
                writer.append(rooms[i].get_name());
                writer.append(';');
                writer.append(rooms[i].get_surname());
                writer.append(';');
                writer.append(rooms[i].get_avaiable());
                writer.append(';');
                writer.append(rooms[i].getCheck_in());
                writer.append(';');
                writer.append(rooms[i].getRoomsitutation());
                writer.append(';');
                writer.append('\n');

            }
            for (Integer i = ONE_PERSON_ROOM_NUMBER; i < TOTAL_ROOM_NUMBER; ++i) {

                String[] tmpNames = rooms[i].get_names().clone();
                String[] tmpSurnames = rooms[i].get_surnames().clone();
                String[] tmpAvaiables = rooms[i].get_avaiables().clone();
                String[] tmpCheckins = rooms[i].get_check_ins().clone();
                String[] tmpRoomSitutations = rooms[i].get_room_situtations().clone();
                for (int j = 0; j < 4; ++j) {
                    writer.append(i.toString());
                    writer.append(';');
                    writer.append(tmpNames[j]);
                    writer.append(';');
                    writer.append(tmpSurnames[j]);
                    writer.append(';');
                    writer.append(tmpAvaiables[j]);
                    writer.append(';');
                    writer.append(tmpCheckins[j]);
                    writer.append(';');
                    writer.append(tmpRoomSitutations[j]);
                    writer.append(';');
                    writer.append('\n');
                }


            }


        } catch (IOException e) {
            System.out.println("Something went wrong. Please try again");
        }


    }

    /**
     * This method is for the starting Scanner(input) mode.
     * Asking you few questions and want you to write in command line.
     * Can throw Exception and excepts you should handle with that exception.
     * Of course if you do everything write and this method works perfectly.
     */
    private void start() throws Exception {
        System.out.println("Welcome to our hotel reservation system !");
        System.out.println("Who are you ? (Receptionist or guest other answers are invalid ");
        Scanner scanner = new Scanner(System.in);
        whoami = scanner.nextLine();
        int control1 = whoami.compareTo("Receptionist");
        int tmp123 = 0;
        if (control1 == 0) {
            call_Receptionist();
            tmp123 = 1;
        }

        control1 = whoami.compareTo("guest");
        if (control1 == 0 && tmp123 == 0)
            call_guest();


    }

    /**
     * start() uses this method only if you say "Receptionist" to his question to "Who are you ?".
     * Creates Receptionist object and you can use his methods if you input as excepted.
     * If you want to Exit just write 6 to console and it will call System.exit(0).
     * Can throw Exception and excepts you should handle with that exception.
     * Uses create_record() if its first time to enter this system otherwise it will call read_ex_file()
     */
    private void call_Receptionist() throws Exception {
        Receptionist rcp = new Receptionist();
        System.out.println("You entered as a Receptionist");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is this your first time to enter this system ? (yes or no other answers are invalid");
        answer = scanner.nextLine();
        int control1 = answer.compareTo("yes");
        if (control1 == 0) {
            create_Record();
            System.out.println("Records created as HOTEL_RECORD.csv");
        }
        else
            read_ex_file();

        while (true) {
            System.out.println("What do you want to do ?");
            System.out.println("1- Print avaiable rooms.(write 1 for this option\n" +
                    "2- Book a room\n" +
                    "3- Cancel a reservation\n" +
                    "4- Check in\n" +
                    "5- Check out\n6- Exit");
            answer1 = scanner.nextInt();
            if (!(answer1 == 1 || answer1 == 2 || answer1 == 3 || answer1 == 4 || answer1 == 5 || answer1 == 6))
                throw new Exception("Invalid Value");


            switch (answer1) {
                case 1:
                    print_avaiable_rooms();
                    break;
                case 2:
                    rcp.book_a_room(rooms);
                    updateRecord();
                    break;
                case 3:
                    rcp.cancel_reservation(rooms);
                    updateRecord();
                    break;
                case 4:
                    rcp.check_in(rooms);
                    updateRecord();
                    break;
                case 5:
                    rcp.check_out(rooms);
                    updateRecord();
                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }



    /**
     * start() uses this method only if you say "guest" to his question to "Who are you ?".
     * Creates Guest object and you can use his methods if you input as excepted.
     * If you want to Exit just write 4 to console and it will call System.exit(0).
     * Can throw Exception and excepts you should handle with that exception.
     *  Uses create_record() if its first time to enter this system otherwise it will call read_ex_file()
     */
    private void call_guest() throws Exception {
        Guest gst = new Guest();
        System.out.println("You entered as a GUEST");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Is this your first time to enter this system ? (yes or no other answers are invalid");
        answer = scanner.nextLine();
        int control1 = answer.compareTo("yes");
        if (control1 == 0) {
            create_Record();
            System.out.println("Records created as HOTEL_RECORD.csv");
        }
        else{
            read_ex_file();
            System.out.println("Everything works fine");
        }


        System.out.println("What do you want to do ?");
        while (true) {
            System.out.println("1- Print avaiable rooms.(write 1 for this option\n" +
                    "2- Book a room\n" +
                    "3- Cancel a reservation\n" +
                    "4- Exit");
            answer1 = scanner.nextInt();
            if (!(answer1 == 1 || answer1 == 2 || answer1 == 3 || answer1 == 4))
                throw new Exception("Invalid Value");
            switch (answer1) {
                case 1:
                    print_avaiable_rooms();
                    break;
                case 2:
                    gst.book_a_room(rooms);
                    updateRecord();
                    break;
                case 3:
                    gst.cancel_reservation(rooms);
                    updateRecord();
                    break;
                case 4:
                    System.exit(0);
                    break;

            }


        }

    }


    /**
     * Test method for our children.
     * I guess this is called "Main Test"
     * This method tries every useful Receptionist and Guest object methods..
     * Doesn't throw any exceptions because this method has perfect inputs.
     * If you have problem with how to use this system you can always check this method and how this method used other methods.
     */
    public void test1() {
        create_Record();
        System.out.println("Receptionist olarak giris yapiliyor");
        Receptionist rcp = new Receptionist();
        System.out.println("Available odalar bastiriliyor");
        print_avaiable_rooms(1);
        System.out.println("Oda 10 Ramazan Guvenc adina book yapiliyor");
        rcp.book_a_room(rooms, 10, "Ramazan", "Guvenc");
        updateRecord();
        System.out.println("Tekrar available odalar bastiriliyor");
        print_avaiable_rooms(1);
        System.out.println("Reservation cancel ediliyor");
        rcp.cancel_reservation(rooms, 10);
        System.out.println("Tekrar available odalar bastiriliyor");
        print_avaiable_rooms(1);
        System.out.println("Oda 5 Ramazan Guvenc adina book edilip check in yapiliyor");
        rcp.book_a_room(rooms,5,"Ramazan","Guvenc");
        rcp.check_in(rooms,5);
        updateRecord();
        System.out.println("Simdi Oda 5 in durumu bastiriliyor.\nRoom 5 "+rooms[5].getRoomsitutation());
        System.out.println("Oda 5 ten check out yapiliyor");
        rcp.check_out(rooms,5);
        updateRecord();
        System.out.println("Guest olarak giris yapiliyor");
        Guest gst = new Guest();
        System.out.println("Oda 8 Huseyin Guvenc adina ve Oda 2 Tarik Kilic adina rezerve ediliyor");
        gst.book_a_room(rooms,8,"Huseyin","Guvenc");
        gst.book_a_room(rooms,2,"Tarik","Kilic");
        updateRecord();
        System.out.println("Available odalar bastiriliyor");
        print_avaiable_rooms(1);
        System.out.println("Oda 8 Huseyin guvencin rezervasyonu iptal ettiriliyor");
        gst.cancel_reservation(rooms,8);
        updateRecord();
        System.out.println("Main testimiz bitmistir csv file inda sadece Oda 2 Tarik kilic adina rezerv olmasi gerekiyor lutfen orayi kontrol ediniz");


    }

    public void test2() {
        read_ex_file();
        updateRecord();
        System.out.println("Recordlariniz HOTEL_RECORD.csv files dan okunmustur");
        System.out.println("Available odalar bastiriliyor");
        print_avaiable_rooms(1);
        System.out.println("As you can see there is no room number 2 as a available");

    }

    /**
     * This method opens HOTEL_RECORD.csv
     * call_Receptionist() or call_guest() use this method if you say no to "Do you enter for first time" question.
     * Handle his own errors.
     */
    private void read_ex_file() {
        String fileName= "HOTEL_RECORD.csv";
        File file= new File(fileName);
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(";");
                // this adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //int lineNo = 1;
        for(List<String> line: lines) {
            int columnNo = 1,i=0;
            String [] tmp = new String[20];
            for (String value: line) {
                //System.out.println(value);
                tmp[i] = value;
                ++i;
                if((columnNo == 6) && ((value.compareTo("booked") == 0) || value.compareTo("is being used") ==0 )){
                    System.out.println(tmp[0]+" "+tmp[1]+" "+tmp[2]+" "+tmp[3]+" "+tmp[4]+" "+tmp[5]);
                    rooms[Integer.parseInt(tmp[0])].set_rm(Integer.parseInt(tmp[0]));
                    rooms[Integer.parseInt(tmp[0])].set_room_things(tmp[3],tmp[1],tmp[2],tmp[4],tmp[5]);

                }

                columnNo++;
            }
            //lineNo++;
        }

    }

    public Room [] get_rooms(){
        return rooms;
    }

    private void  print_avaiable_rooms(int x){
        System.out.println("AVAILABLE ROOMS");
        for (int i = 0; i < ONE_PERSON_ROOM_NUMBER - 1; ++i) {
            if (rooms[i].get_avaiable().compareTo("yes") == 0)
                System.out.printf("%d , ", rooms[i].get_rm());
        }
        System.out.printf("%d \n", rooms[ONE_PERSON_ROOM_NUMBER - 1].get_rm());

    }
}
