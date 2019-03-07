package com.company;

import java.io.*;
import java.util.*;

/**
 * This class reads "courses.csv" and lets you use some methods on it
 */
public class Part1 {
    private LinkedList<String> data = new LinkedList<>();
    private String filename = "courses.csv";
    public Part1(){
        readFile();
    }

    /**
     * This methods reads file from HW03 folder.
     */
    private void readFile(){
        /*Bu method yazilirken Stackoverflowdan yararlanilmistir*/
        try {
            String SEPARATOR = ";";
            BufferedReader reader = new BufferedReader(
                    new FileReader(filename));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(SEPARATOR, -1);
                for (int i = 0; i < fields.length; i++)
                    data.add(fields[i]);

            }
            /*for(int i =0;i<data.size();++i)
                System.out.println(i+" - "+data.get(i));
                */

        }
        catch (Exception e){
            System.out.println("Problem detected at readFile method.");
            System.exit(1);
        }
    }

    /**
     *
     * @param code Course code
     * @return full of data String about given Course code.
     */
    public String getByCode(String code){
        return data.get(data.indexOf(code)-1)+" "+
                data.get(data.indexOf(code))+" "+
                data.get(data.indexOf(code)+1)+" "+
                data.get(data.indexOf(code)+2)+" "+
                data.get(data.indexOf(code)+3)+" "+
                data.get(data.indexOf(code)+4);
    }

    /**
     *
     * @param semester listelemek istediginiz semesteri parametre olarak aliyor.
     * @return Icinde verilen parametreyle ayni semester olan tum derslerin bulundugu linkedlist
     */
    public LinkedList listSemesterCourses (int semester){

        LinkedList<String> result = new LinkedList<> ();
        for(int i =6;i<data.size(); i = i+6){
            try {
                int tmp = Integer.parseInt(data.get(i));
                if (tmp == semester) {
                    result.add(data.get(i) + " " + data.get(i + 1) + " " + data.get(i + 2) + " " +
                            data.get(i + 3) + " " + data.get(i + 4) + " " + data.get(i + 5));
                }
            }
            catch(Exception e){
            }
        }
        return result;

    }

    /**
     *
     * @param start_index startIndex
     * @param last_index lastIndex
     * @return LinkedList between startIndex-lastIndex
     */
    public LinkedList getByRange(int start_index, int last_index){
        LinkedList<String> result = new LinkedList<> ();
        for(int i =6*(start_index+1);i<last_index*6+1; i = i+6){
            try {
                int tmp = Integer.parseInt(data.get(i));
                result.add(data.get(i) + " " + data.get(i + 1) + " " + data.get(i + 2) + " " +
                        data.get(i + 3) + " " + data.get(i + 4) + " " + data.get(i + 5));

            }
            catch(Exception e){
            }
        }
        return result;
    }

    public LinkedList getData(){
        return data;
    }


}
