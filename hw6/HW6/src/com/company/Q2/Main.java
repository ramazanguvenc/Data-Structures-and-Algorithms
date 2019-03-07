package com.company.Q2;

public class Main {
    private static BTree<Integer> bt = new BTree<>(3);
    public static void add(){
        try{
            bt.add(35);
            bt.add(19);
            bt.add(16);
            bt.add(68);
            bt.add(37);
            bt.add(94);
            bt.add(7);
            bt.add(13);
            bt.add(66);
            bt.add(87);
        }catch (Exception e){
            bt = new BTree<>(3);
            System.out.println("--------------------");
            add();
        }
    }

    public static void main(String[] args) {
        add();
        System.out.println(bt);
    }

}
