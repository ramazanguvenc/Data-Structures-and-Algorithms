package Q2;

import java.util.*;


public class RecursiveHashSet<E> implements Set {

    private LinkedList<E []> table;
    private int size,tableSize,index;
    private LinkedList<Integer> deletedIndexes;

    /**
     * tableSize = 23, tableSize prime sayilardan olusuyor.
     */
    public RecursiveHashSet(){
        table = new LinkedList<>();
        deletedIndexes = new LinkedList<>();
        tableSize = 23;
        size = 0;
        index = 0;
        E [] tmp = (E[]) new Object[tableSize];
        Arrays.fill(tmp,null);
        table.add(tmp);
    }

    /**
     *
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     *
     * @return size == 0
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @param o
     * @return true if your object contains
     */
    @Override
    public boolean contains(Object o) {
        if(size == 0)
            return false;
        int hash = getHash(o);
        for(int i =0;i<table.size();i++){
            if(table.get(i)[hash] != null)
                if(table.get(i)[hash].equals(o))
                    return true;
            if(table.get(i)[hash] == null && (!deletedIndexes.contains(hash)))
                return false;
        }
        return false;

    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /**
     * LoadBalance  1 e gelince table i genisletir. Yoksa hashing kullanmanin bir anlami yok zaten.
     */
    private void checkLoadBalance(){
        if(tableSize == size){
            int oldTablesize = tableSize;
            tableSize = getNextPrime();
            E [] tmp = (E[]) new Object[size];
            E [] tmpArr = (E[]) new Object[tableSize];
            Arrays.fill(tmpArr,null);
            int a =0;
            for(int i =0;i<table.size();i++){
                for(int j = 0 ; j < oldTablesize; j++)
                    if(table.get(i)[j] != null)
                        tmp[a++] = table.get(i)[j];
            }
            table.clear();
            deletedIndexes.clear();
            table.add(tmpArr);
            for(int i =0; i < a; i++)
                this.add(tmp[i]);
        }
    }

    /**
     * returns next prime for tableSize
     * @return
     */
    private int getNextPrime(){
        for(int i = tableSize*2; i < 4 * tableSize; i++)
            if(isPrime(i))
                return i;
        return -1;
    }

    /**
     *
     * @param number number
     * @return true if number is prime
     */
    private boolean isPrime(int number){
        if(number == 1 || number == 0 || number < 0)
            return false;
        for (int i = 2; i*i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verilen Objecti eger Setin icinde yoksa ekler
     * @param o eklenmesi istenen obje
     * @return true if contains(o) == false
     */
    @Override
    public boolean add(Object o) {
        if(contains(o))
            return false;
        checkLoadBalance();
        int hash = getHash(o);
        index =0;
        return addR((E) o,hash,table.get(index));
    }

    /**
     * helper method
     * @param hash hash
     * @param o o
     * @return true if success
     */
    private boolean addIfNextEmpty(int hash, E o){
        if(index >= table.size()){
            E [] tmp = (E[]) new Object[tableSize];
            Arrays.fill(tmp,null);
            tmp[hash] =  o;
            table.add(tmp);
            return true;
        }
        return false;
    }

    /**
     * Recursive kullanmak icin yazilan helper method
     * @param e eklenmesi istene item
     * @param hash hash
     * @param data nereye eklenecegi
     * @return true if success
     */
    private boolean addR(E e,int hash,E [] data){
        if(data[hash] == null){
            data[hash] = e;
            size++;
            return true;
        }
        index++;
        if(addIfNextEmpty(hash,e)){
            size++;
            return true;
        }
        return addR(e,hash,table.get(index));
    }

    /**
     *
     * @param o silinecek item
     * @return true if success
     */
    @Override
    public boolean remove(Object o) {
        if(size == 0)
            return false;
        int hash = getHash(o);
        for(int i =0;i<table.size();i++){
            if(table.get(i)[hash].equals(o)){
                deletedIndexes.add(hash);
                table.get(i)[hash] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {
        table.clear();
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    public void printSet(){
        for(int i =0;i<table.size();i++){
            for(int j = 0 ; j < tableSize; j++)
                System.out.println(table.get(i)[j]);
        }
    }

    private int getHash(Object o){
        int hash = o.hashCode() % tableSize;
        if(hash < 0)
            hash += tableSize;
        return hash;
    }
}