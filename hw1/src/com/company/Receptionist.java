package com.company;



import java.util.Scanner;


/**
 * Bu class kisaca sisteme Receptionist olarak girildiginizdeki yapabileceginiz seylerin methodunun bulundugu yer.
 * Extends from Guest because they have similar methods.
 */
public class Receptionist extends Guest implements User {
    final private int ONE_PERSON_ROOM_NUMBER = 20;
    final private int TOTAL_ROOM_NUMBER = 30;

    /**
     * Takes an input for a room number and use it for check in.
     * Throws exception if you give unbooked room number.
     * @param tmpRooms holds whole hotel records.
     */
    public void check_in(Room [] tmpRooms) throws Exception {
        System.out.println("Which room number do you want to checked in");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        //im doing this way because sometimes compareTo doesnt work well i dont know why still.
        int control1 = tmpRooms[input].get_avaiable().compareTo("no");
        int control2 = tmpRooms[input].getCheck_in().compareTo("no");
        if(control1 == 0 && control2 == 0 && input <ONE_PERSON_ROOM_NUMBER){
            tmpRooms[input].setCheck_in("yes");
            tmpRooms[input].setRoom_situtationet("is being used");
            System.out.printf("%d <--- This room checked in\n",input);
        }
        else if( input < TOTAL_ROOM_NUMBER){
            String [] tmpS = {"is being used","is being used","is being used","is being used6" +
                    ""};
            String [] tmpR = {"yes","yes","yes","yes"};
            tmpRooms[input].setCheck_ins(tmpR);
            tmpRooms[input].setRoom_situtations(tmpS);
        }
        else
            throw new Exception("Invalid Value"); ;
    }

    /**
     * This method only wrote for TESTS.
     * PLEASE DON'T USE THIS METHOD.
     */
    public boolean check_in(Room[] tmpRooms,int rooom_number){
        if( rooom_number > ONE_PERSON_ROOM_NUMBER || rooom_number < 0)
            return false;
        int rmbrnumbr = rooom_number;
        tmpRooms[rmbrnumbr].setCheck_in("yes");
        tmpRooms[rmbrnumbr].setRoom_situtationet("is being used");
        System.out.printf("%d <--- This room checked in\n",rmbrnumbr);
        return true;

    }

    /**
     * Takes an input for a room number and use it for check out.
     * Throws exception if you give unchecked room number.
     * @param tmpRooms holds whole hotel records.
     */
    public void check_out(Room [] tmpRooms)throws Exception {
        System.out.println("Which room number do you want to checked out ?");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int control1 = tmpRooms[input].get_avaiable().compareTo("no");
        int control2 = tmpRooms[input].getCheck_in().compareTo("yes");
        if(control1 == 0 && control2 == 0 && input <ONE_PERSON_ROOM_NUMBER){
            Room tmp = new Room();
            tmpRooms[input] = tmp;
            tmpRooms[input].set_rm(input);
            System.out.printf("%d <--- This room checked out\n",input);

        }
        else if (input < TOTAL_ROOM_NUMBER){
            String [] tmpR = {"","","",""};
            String [] tmpS = {"yes","yes","yes","yes"};
            tmpRooms[input].setCheck_ins(tmpR);
            tmpRooms[input].set_avaiables(tmpS);
        }
        else
            throw new Exception("Invalid Value");


    }

    /**
     * This method only wrote for TESTS.
     * PLEASE DON'T USE THIS METHOD.
     */
    public boolean check_out(Room [] tmpRooms,int rooom_number){
        if( rooom_number > ONE_PERSON_ROOM_NUMBER || rooom_number < 0)
            return false;
        int rm_number = rooom_number;
        Room tmp = new Room();
        tmp.set_rm(rm_number);
        tmpRooms[rm_number] = tmp;
        System.out.printf("%d <--- This room checked out\n",rm_number);
        return true;
    }

    /**
     *
     * @return Receptionist
     */
    @Override
    public String whoami(){
        return "Receptionist";
    }
}
