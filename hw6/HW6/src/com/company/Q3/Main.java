package com.company.Q3;

import com.company.BinarySearchTree;

public class Main {

    // AVLTREE kodu eksik methodlari tarafimdan YAZILMAMISTIR.
    // AVLTree yi kitabin verdigi 2. kaynaktan kopyaladim cunku yoksa add methodu duzgun calismiyordu
    // Bu yuzden AVLTree yi kontrol edemicektim.
    // Bu arada constructora bos Tree gonderince method calismayabilir garantisini veremiyorum.
    public static void main(String[] args) {
        AVLTree<Integer> av = new AVLTree<>();
        for(int i =0; i < 10 ; i ++)
            av.add(i);
        AVLTree<Integer> k = new AVLTree<>(av);
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for(int i =0 ; i < 10 ; i ++)
            bst.add(i);
        AVLTree<Integer> t = new AVLTree<>(bst);
    }
}
