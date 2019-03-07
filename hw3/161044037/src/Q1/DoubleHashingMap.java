package Q1;



import java.util.*;


/**
 * Double hashing and open addressing. Load balance 0.5.
 * @param <K>
 * @param <V>
 */

public class DoubleHashingMap<K,V> implements Map<K,V> {

    private Pair<K,V> [] data;
    private int tableSize,size;
    private LinkedList<Integer> deletedIndexes;


    /**
     * tableSize = 16 , tableSize i 2^n seklinde tutuyorum.
     */
    DoubleHashingMap(){
        deletedIndexes = new LinkedList<>();
        tableSize = 16;
        size = 0;
        data = new Pair[tableSize];
        for(int i = 0; i < tableSize ; i++)
            data[i] = null;
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
     * @return size == 0;
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @param key bu key var mi kontrol eder
     * @return get(key) != null;
     */
    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    /**
     * Bu method O(n) zamanda calisiyor cunku value yu ye gore hashlenmiyor.
     * @param value
     * @return true if contains.
     */
    @Override
    public boolean containsValue(Object value) {
        for(int i = 0; i < tableSize ; i++)
            if(data[i].getValue().equals(value))
                return true;
        return false;
    }

    /**
     * Eger value yoksa null return eder.
     * @param key your key
     * @return value
     */
    @Override
    public V get(Object key) {
        int k = h1((K) key);
        if(data[k].getKey().equals(key))
            return data[k].getValue();
        if(data[k] == null && (!deletedIndexes.contains(k)))
            return null;
        int hash2 = h2(k);
        k = (k + hash2) % tableSize;
        if(data[k] == null && (!deletedIndexes.contains(k)))
            return null;
        int i = 2;
        do{
            if(data[k].getKey().equals(key))
                return data[k].getValue();
            k = (k + i*hash2) % tableSize;
            i++;

        } while(data[k] != null && (!deletedIndexes.contains(k)));
        return null;
    }


    /**
     * Ilk hash methodum. tableSize a gore modunu alir. Javanin kendi hashCodeunu kullanir.
     * @param key
     * @return (int) hash
     */
    private int h1(K key){
        int hash =key.hashCode();
        //hashcode eger cift sayi ise 1 azaltarak tek sayi yapmayi amacliyorum cunku table size 2 nin ustu seklinde gidicek.
        hash = hash % tableSize;
        if(hash % 2 == 0)
            hash -- ;

        if(hash < 0 )
            hash += tableSize;
        return hash;
    }

    /**
     *
     * @param key bu keye eslenicek sekilde valueyu yerlestirir
     * @param value value
     * @return oldValue if exist otherwise null
     */
    @Override
    public V put(K key, V value) {
        checkLoadFactor();
        int hash = h1(key);
        if(data[hash] == null || data[hash].getKey().equals(key)){
            if(data[hash] == null){
                putData(key,value,hash);
                return value;
            }
            else{
                V tmp = data[hash].getValue();
                data[hash] = new Pair<>(key,value);
                size++;
                return tmp;
            }
        }

        //collusion olduysa.
        return add(key,value,hash);

    }


    /**
     * Constant time da calismaya devam etmesi icin 0.5 i dolduktan sonra tableSize i iki katina cikarir.
     */
    private void checkLoadFactor(){
        if((double) size / (double) tableSize > 0.5 ){
            Pair<K,V>[] tmpArray = new Pair[tableSize*2];
            Arrays.fill(tmpArray,null);
            Pair<K,V> [] tmpArr = new Pair[tableSize];
            System.arraycopy(data,0,tmpArr,0,tableSize);
            data = tmpArray;
            tableSize *= 2;
            for(int  i=0 ; i < tableSize/2;i++){
                if(tmpArr[i] != null)
                    this.put(tmpArr[i].getKey(),tmpArr[i].getValue());
            }
            deletedIndexes.clear();


        }


    }

    /**
     * Bu algoritmayi MIT OPEN COURSE videosundan ogrendim.
     * @param k old h1()
     * @return (int) new hash
     */
    private int h2(int k){
        int p,a,b;
        do{
            p =getNextPrime()%tableSize;
            a = (int) (Math.round(Math.random() * 10000) %p);
            b = (int) (Math.round(Math.random() * 10000)%p);
        }while(p > 0 && a > 0 && b > 0);
        int hash2 = (((a*k) + b) % p) % tableSize;
        if(hash2 <= 0 )
            hash2 += tableSize-1;
        return hash2;
    }

    /**
     * Bu method collusion oldugunda cagrilir
     * @param key key
     * @param value value
     * @param k hash1
     * @return oldValue if exist otherwise null
     */

    private V add(K key,V value,int k){
        int hash2 = h2(k);
        int i = 1;
        do{
            k = (k + i * hash2) % tableSize;
            if(k < 0)
                k +=tableSize;
            if(data[k] == null || data[k].getKey().equals(key)){
                if(data[k] == null){
                    putData(key,value,k);
                    return null;
                }
                else{
                    V tmp = data[k].getValue();
                    putData(key,value,k);
                    return tmp;
                }
            }
            i++;
        }while(data[k] != null);


        data[k] = new Pair<>(key,value);
        size++;
        return null;

    }

    /**
     *
     * @param key key
     * @param value value
     * @param k hash1
     */
    private void putData(K key,V value,int k){
        data[k] = new Pair<>(key,value);
        size++;
    }

    /**
     * SOURCE : WIKIPEDIA
     * @return tablesize dan buyuk bir prime return eder.
     */
    private int getNextPrime(){
        for(int nextPrime = (int) (Math.round(Math.random() * 10000)%tableSize); nextPrime < 2 * tableSize ; nextPrime++){
            if(isPrime(nextPrime))
                return nextPrime;
        }



        return getNextPrime();
    }

    /**
     * SOURCE :baeldung.com
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
     * helper method
     * @param hash hash
     * @return oldValue
     */
    private V del(int hash){
        deletedIndexes.add(hash);
        V tmp = data[hash].getValue();
        data[hash] = null;
        size --;
        return tmp;
    }

    /**
     * remove method
     * @param key silinmesi istediginiz key
     * @return oldvalue
     */
    @Override
    public V remove(Object key) {
        if(!containsKey(key))
            return null;
        int hash = h1((K) key);
        if(data[hash].getKey().equals(key)){
            return del(hash);
        }
        int hash2 = h2(hash);
        int i = 1;
        do{
            hash = (hash + i * hash2) % tableSize;
            if(hash < 0)
                hash +=tableSize;
            if(data[hash].getKey().equals(key)){
                return del(hash);
            }
            i++;
        }while(data[hash] != null || deletedIndexes.contains(hash));


        return null;

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    public  void printMap(){
        for(Pair p : data)
            System.out.println(p);
    }
}
