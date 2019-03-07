package com.company;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Bu class normal link liste enable ve disable ozellikleri getirilerek ozellestirilmis halidir.
 * @param <E>
 */
public class  Part2<E>  extends LinkedList<E> {
    List<Pair<E,Integer>> disabled = new ArrayList<>();
    LinkedList<E> withoutDisabled = new LinkedList<E>();

    private class Pair<E,K>{
        private E key;
        private K value;
        Pair(E e,K k){
            this.key = e;
            this.value = k;
        }
        E getKey(){
            return key;
        }
        K getValue(){
            return value;
        }
    }

    /**
     * Bu method verilen indexteki veriyi linkedlist methodlarini kullanmasini engeller.
     * @param index index
     * @return
     * @throws Exception if index is invalid
     */
    public boolean disable(int index) throws Exception {
        disabled.add(new Pair(super.get(index),index));

        withoutDisabled.remove(disabled.get(findInDisabled(index)).getKey());
        return true;
    }

    /**
     * Bu method verilen objeyi linkedlist methodlarini kullanmasini engeller.
     * @param obj
     * @return
     * @throws Exception if obj isnt in list
     */
    public boolean disable(E obj) throws Exception {
        disabled.add(new Pair(obj,super.indexOf(obj)));
        withoutDisabled.remove(findInDisabled(obj));
        return true;
    }


    /**
     * Bu method verilen inputa gore disabled linkedlistindeki yerini verir.
     * @param input
     * @return location
     * @throws Exception
     */
    private int findInDisabled(int input) throws Exception {
        for(int i =0; i < disabled.size();++i)
            if(input == disabled.get(i).getValue())
                return i;


        //else
        throw new Exception();
    }

    /**
     * Bu method verilen inputa gore disabled linkedlistindeki yerini verir.
     * @param inp
     * @return
     * @throws Exception
     */
    private int findInDisabled(E inp)throws Exception{
        for(int i =0; i < disabled.size();++i)
            if(inp == disabled.get(i).getKey())
                return i;
        //else
        throw new Exception();
    }

    /**
     * Disable edilen objenin enable edilmesini ve tekrardan methodlari kullanabilmesini saglar.
     * @param index
     * @return
     * @throws Exception
     */
    public boolean enable(int index) throws Exception {
        if(isDisabled(index)){
            withoutDisabled.add(index,disabled.get(findInDisabled(index)).getKey());
            disabled.remove(findInDisabled(index));
            return true;
        }
        //else
        return false;
    }

    /**
     * Disable edilen objenin enable edilmesini ve tekrardan methodlari kullanabilmesini saglar.
     * @param obj
     * @return
     */
    public boolean enable(E obj){
        if(isDisabled(indexOf(obj))){
            withoutDisabled.add(disabled.get(disabled.indexOf(obj)).getValue(),obj);
            disabled.remove(obj);
            return true;
        }
        //else
        return false;
    }

    /**
     * Verilen indexin disabled linked listinde olup olmadigini kontrol eder.
     * @param index
     * @return
     */
    private boolean isDisabled(int index){
        for(int i =0; i<disabled.size();++i)
            if(disabled.get(i).getValue() == index)
                return true;
        //else
        return false;
    }

    /**
     * Overrided for disabled objects.
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        if(isDisabled(index)){
            System.out.printf("You cant use get method with %d index\n",index);
            return null;
        }
        //else
        return super.get(index);
    }

    /**
     * Overrided for disabled objects.
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index,E element) {
        if(isDisabled(index)){
            System.out.printf("You cant use set method with %d index\n",index);
            return null;
        }
        //else
        withoutDisabled.set(index,element);
        return super.set(index,element);
    }

    /**
     * Overrided cunku sizein disabled edilen objeleri saymamasi gerekiyor.
     * @return
     */
    @Override
    public int size(){
        return super.size()-disabled.size();
    }

    /**
     * Overrided for disabled objects.
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o){
        if(isDisabled(super.indexOf(o)))
            return false;
        //else
        withoutDisabled.remove(o);
        return super.remove(o);
    }


    /**
     * Disabled objeler icin overload edildi kullanim kolayligi acisindan.
     * @param index
     * @return
     */
    public ListIterator<E> listIterator(int index) {
        return withoutDisabled.listIterator(index);
    }

    /**
     * Overrided for disabled objects.
     * @return
     */
    @Override
    public ListIterator<E> listIterator() {
        return withoutDisabled.listIterator();
    }

    public void showDisabled(){
        System.out.println("Disabled Ones");
        for(int i = 0; i < disabled.size();i++)
            System.out.println(disabled.get(i).getKey());
    }

    /**
     * Overrided for disabled objects.
     * @param e
     * @return
     */
    @Override
    public boolean add(E e){
        withoutDisabled.add(e);
        return super.add(e);
    }




}
