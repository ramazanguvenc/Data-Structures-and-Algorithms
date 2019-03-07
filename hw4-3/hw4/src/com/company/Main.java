package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
            Part1<String> a = new Part1<>();
            a.add(null,"ramazan");
            a.add("ramazan","ali");
            a.add("ramazan","mehmet");
            a.add("ramazan","ahmet");
            a.add("ali","kemal");
            a.add("ali","ayse");
            a.add("mehmet","yagiz");
             a.add("yagiz","saadet");
            a.add("kemal","bilal");
            a.add("kemal","huseyin");
            System.out.println("Agac bastiriliyor\n-----------------\n"+a);
            System.out.println("\n-----------------\n");
            System.out.println("huseyin levelorderSearche gore araniyor\n");
            a.levelOrderSearch("huseyin");
            System.out.println("\n-----------------\n");
            System.out.println("ramazan postOrderSearche gore araniyor\n");
            a.postOrderSearch("ramazan");
            System.out.println("\n-----------------\n");

           System.out.println("\n-------------------\n");
           System.out.println("Part2 mainTest 1 basliyor");
           Part2<Integer> ab = new Part2<>();
           ab.add(28,30,42);
           ab.add(4,5,6);
           ab.add(12,13,14);
           ab.add(3,20,5);
           ab.add(40,40,50);
           System.out.println("Degerlerimiz eklendi simdi agacimiz  basiliyor");
           //toString baya garip calisiyor silme durumlarinda falan herhangi bir order sozu veremem.
           System.out.println(ab);
           System.out.println("4,5,6 siliniyor");
           ab.delete(4,5,6);
           System.out.println("4,5,6 silindi tekrar agacimiz bastiriliyor");
           System.out.println(ab);
           System.out.println("Part2 mainTest 1 bitti");
           System.out.println("\n-------------------\n");
           System.out.println("\n-------------------\n");
           System.out.println("Part2 mainTest 2 basliyor");
           Part2<Integer> b = new Part2<>(2);
           b.add(40,45);
           b.add(15,70);
           b.add(70,10);
           b.add(69,50);
           b.add(66,85);
           b.add(85,90);
           System.out.println("Degerlerimiz eklendi simdi agacimiz  basiliyor");
           System.out.println(b);
           //delete fonksiyonu 70,10 ve 15,70 i arkada ayni yerde tuttugundan kafasi karisabiliyor.
           System.out.println("66,85 siliniyor");
           b.delete(66,85);
           System.out.println(b);
           System.out.println("Part2 mainTest2 bitti");
           System.out.println("\n-------------------\n");


    }
}
