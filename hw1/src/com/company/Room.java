package com.company;

/**
 * This class holds names,surnames,room situation etc.
 */
public class Room {
    private int room_number;
    private String avaiable;
    private String name;
    private String surname;
    private String check_in;
    private String room_situtation;
    private String [] names;
    private String [] surnames;
    private String [] avaiables;
    private String [] check_ins;
    private String [] room_situtations;

    /**
     * Normal constructor,
     * changes available to yes and other variables to.
     */
    public Room() {
        avaiable = "yes";
        name = "";
        surname = "";
        check_in ="";
        room_situtation="";
        names = new String[]{"", "", "", ""};
        surnames = new String[]{"", "", "", ""};
        avaiables = new String[]{"yes", "yes", "yes", "yes"};
        check_ins = new String[]{"", "", "", ""};
        room_situtations = new String[]{"", "", "", ""};


    }
    /**
     * @return returns String array which name is avaiables
     * Yes my english is garbage.
     */
    public String [] get_avaiables(){
        return avaiables;
    }
    /**
     * @return returns String array which name is check_ins
     */
    public String [] get_check_ins(){
        return check_ins;
    }
    /**
     * @return returns String array which name is room_room_situtations
     */
    public String [] get_room_situtations(){
        return room_situtations;
    }
    /**
     * @return returns String array which name is names
     */
    public String [] get_names(){
        return names;
    }
    /**
     * @return returns String array which name is surnames
     */
    public String [] get_surnames(){
        return surnames;
    }

    /**
     * Setter for 4 KISILIK ODALAR
     */
    public void multip_set_room_things(String [] tmpAvaiable,String [] tmpNames,String [] tmpSurnames,String [] tmpCheckins, String [] tmpRoomSitutations){
        avaiables = tmpAvaiable.clone();
        names = tmpNames.clone();
        surnames = tmpSurnames.clone();
        check_ins = tmpCheckins.clone();
        room_situtations = tmpRoomSitutations.clone();
    }

    /**
     * Constructor if you want to make Room already booked,is being used or whatever
     */
    public Room(String tmpAvaiable, String tmpName, String tmpSurname, int tmpRM, String tmpCheck_in, String tmpRoom_situtation){
        room_number = tmpRM;
        avaiable = tmpAvaiable;
        name = tmpName;
        surname = tmpSurname;
        check_in = tmpCheck_in;
        room_situtation = tmpRoom_situtation;
    }

    /**
     * Setter for 1 KISILIK ODALAR
     */
    public void set_room_things(String tmpAvaiable, String tmpName, String tmpSurname, String tmpCheck_in, String tmpRoom_situtation){
        avaiable = tmpAvaiable;
        name = tmpName;
        surname = tmpSurname;
        check_in = tmpCheck_in;
        room_situtation = tmpRoom_situtation;
    }

    /**
     * Getter for avaiable which is string variable.
     */
    public String get_avaiable(){
        return avaiable;
    }
    /**
     * Getter for name which is string variable.
     */
    public String get_name(){
        return name;
    }
    /**
     * Getter for surnam which is string variable.
     */
    public String get_surname(){
        return surname;
    }
    /**
     * Setter for room_number which is integer variable obviously.
     */
    public void set_rm(int tmp){
        room_number = tmp;
    }
    /**
     * Getter for room_number which is integer variable obviously.
     */
    public int get_rm(){
        return room_number;
    }

    /**
     * Getter for check_in which is string variable.
     */
    public String getCheck_in(){
        return check_in;
    }
    /**
     * Setter for check_in which is string variable.
     */
    public void setCheck_in(String tmp){
        check_in = tmp;
    }
    /**
     * Getter for room_situtation which is string variable.
     */
    public String getRoomsitutation(){
        return room_situtation;
    }
    /**
     * Setter for avaiable which is string variable.
     */
    public void setAvaiable(String tmp){
        avaiable = tmp;
    }
    /**
     * stter for room_situtation which is string variable.
     */
    public void setRoom_situtationet(String tmp){
        room_situtation = tmp;
    }

    public  void set_avaiables(String [] tmp){
        avaiables = tmp.clone();
    }

    public void setRoom_situtations(String [] tmp){
        room_situtations = tmp.clone();
    }
    public void setCheck_ins(String [] tmp){
        check_ins = tmp.clone();
    }

}
