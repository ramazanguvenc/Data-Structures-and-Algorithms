package com.company;

/**
 * Main class, if you want to test this class create Hotel tmp = new Hotel(1); and call test() method.
 * Test2 HOTEl_RECORDS.csv den okumaya calisir.
 * Eger gercekten okuyup okumadigimi test etmek istiyorsaniz,  tmp.test1() ile beraber 1 kez calistirip sonra tmp.test1() i comment icine alirsaniz,
 * sonrasinda csv file in da degisiklik yaparsaniz available files da degistirdiginiz odalarin da artik bastirilmadigini gorebilirsiniz
 * read_ex_file() methodum bu isi yapiyor fakat bu methoddan sonra updateRecord() methoduda kullanilmali
 */
public class Main {

    public static void main(String[] args) throws Exception {




        //FOR MAIN TEST !!

        Hotel tmp = new Hotel(1);
        System.out.println("Test 1 basliyor\n\n");
        tmp.test1();
        System.out.println("\n\nTest 1 bitmistir\n\n");
        System.out.println("Test 2 basliyor\n\n");
        tmp.test2();









        //FOR CONSOLE INPUTS

       // Hotel tmp3 = new Hotel();





    }
}
