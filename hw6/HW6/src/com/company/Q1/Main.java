package com.company.Q1;

public class Main {

    /**
     * Tersten sorted array RedBlackTree ye yerlestirildi.
     * @param args args
     */
    public static void main(String[] args) {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        for(int i = 22; i > 0 ; i--)
            rbt.add(i);
        System.out.println(rbt);
    }
}
