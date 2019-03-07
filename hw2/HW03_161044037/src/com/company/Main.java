package com.company;

import java.util.LinkedList;
import java.util.ListIterator;

public class Main {

    public static void printLinkedList(LinkedList e){
        ListIterator itr = e.listIterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
    public static void printPart3(Part3 e){
        while(e.hasNext()){
            System.out.println(e.next());
        }
        //asagidaki method index=0 yapiyor cunku iterator claSS i yazmadigim icin direk next methodu yazdigim icin boyle bir sey gerekli
        e.indexZero();
    }
    public static void Part1_Test(){
        System.out.println("Part1 Test basladi.");
        Part1 tmp = new Part1();
        System.out.println("-------------------------------");
        System.out.println("Simdi getByCode methodudu CSE 101 de calisiyor mu diye test edilecek\n");
        String a = tmp.getByCode("CSE 101");
        System.out.println(a);
        System.out.println("-------------------------------");
        System.out.println("Simdi getBySemeter methodudu 5. semester icin test edecegim\n");
        printLinkedList(tmp.listSemesterCourses(5));
        System.out.println("-------------------------------");
        System.out.println("Simdi getByRange methodudu 2-13 index arasi icin test edecegim\n");
        printLinkedList(tmp.getByRange(2,13));
        System.out.println("-------------------------------");
        System.out.println("Test1 bitti");
    }

    public static void Part2_Test(){
        System.out.println("Part2 Test basladi.");
        System.out.println("-------------------------------");
        Part1 tmp = new Part1();
        Part2<String> tmp1 = new Part2<>();
        for(int i = 0 ; i < 8;i++){
            LinkedList<String> a = tmp.listSemesterCourses(i);
            for(int j=0;j<a.size();j++)
                tmp1.add(a.get(j));
        }
        printLinkedList(tmp1);
        System.out.println("\ncourse.csv deki urunler yeni linkedlistimize aktarildi ve bastirildi" +
                "\nBastirilirken iterator kullanildi(printLinkedList fonksiyonu iterator kullaniyor)");
        System.out.println("-------------------------------\n");
        try {
            tmp1.disable(0);
            tmp1.disable(1);
        } catch (Exception e) {
            System.out.println("Something went wrong with disable method");
        }
        System.out.println("0 ve 1. indexteki veriler disable edildi.\n" +
                "Liste tekrardan basiliyor");
        printLinkedList(tmp1);
        System.out.println("-------------------------------\n");
        try {
            tmp1.enable(0);
        } catch (Exception e) {
            System.out.println("Something went wrong with enable method");
        }
        System.out.println("Ilk elemanimiz 1 XXX XXX ile baslayan tekrar enable edildi");
        System.out.println("Liste tekrar basiliyor.\n");
        printLinkedList(tmp1);
        System.out.println("\n-------------------------------\n");
        System.out.println("1. indexteki elemanimiz hala disabled.get fonksiyonunu kullanmayi deneyecegim ve method duzgun calisiyorsa null basmasi gerekicek");
        System.out.println(tmp1.get(1));
        System.out.println("-------------------------------");
        System.out.println("Test2 bitti");

    }

    public static void Part3_Test(){
        System.out.println("Part3 Test basladi.");
        System.out.println("\n-------------------------------\n");
        System.out.println("add methodu denenerek course.csv deki bilgiler aktarilacak");
        Part1 tmp = new Part1();
        Part3<String> tmp1 = new Part3<>();
        for(int i = 0 ; i < 8;i++){
            LinkedList<String> a = tmp.listSemesterCourses(i);
            for(int j=0;j<a.size();j++)
                tmp1.add(a.get(j));
        }
        System.out.println("\n-------------------------------\n");
        System.out.println("next() ve hasNext() methodu kullanilarak listin icindeki bilgiler bastirilacak");
        //Asagidaki method next() methodunu ve hasNext() methodunu kullanir
        printPart3(tmp1);
        System.out.println("\n-------------------------------\n");
        System.out.println("remove kullanilarak 1. indexteki obje cikartilacak ve liste tekrar basilacak. Listedeki 1 CSE 101 ile baslayanin silinmesi gerek.\n");
        tmp1.next();
        tmp1.remove(tmp1.next());
        tmp1.indexZero();
        printPart3(tmp1);
        System.out.println("\n-------------------------------\n");
        System.out.println("nextInSemester kullanilarak bastirilacak.\nListenin tamami bastirildiktan sonra 3 adim daha devam edilecek circular oldugunu gostermek icin\n");
        //Sistemimim dizaynindan dolayi headide bastiriyor hasNext() dediginizde en sona gelindiginde.
        while(tmp1.hasNext())
            System.out.println(tmp1.nextInSemester());
        System.out.println(tmp1.nextInSemester());
        System.out.println(tmp1.nextInSemester());
        System.out.println("\n-------------------------------\n");
        System.out.println("Semesterin digerleri icinde dogru calistigini gostermek icin 4. semesterlarida ayni sekilde circular bastiracagim\n");
        tmp1.indexZero();
        for(int i =0;i<22;i++)
            tmp1.next();
        while(tmp1.hasNext())
            System.out.println(tmp1.nextInSemester());
        System.out.println(tmp1.nextInSemester());
        System.out.println(tmp1.nextInSemester());
        System.out.println(tmp1.nextInSemester());
        System.out.println("\n-------------------------------\n");
        System.out.println("Test3 bitti");

    }

    public static void main(String[] args) throws Exception {




        Part1_Test();
        Part2_Test();
        Part3_Test();



    }
}
