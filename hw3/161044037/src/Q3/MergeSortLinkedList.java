package Q3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * LinkedList icin merge sort.
 * @param <E>
 */
public class MergeSortLinkedList<E extends Comparable> {

    public MergeSortLinkedList(){

    }

    /**
     * mergeSort cagirir
     * @param arr Verdiginiz listeyi otamatik olarak sortlar.
     */
    public MergeSortLinkedList(LinkedList<E> arr){
        mergeSort(arr);
    }

    /**
     * Normal merge sort. Takes a LinkedList as a parameter.
     * @param arr Sortlanmasini istediginiz liste
     */
    public void mergeSort(LinkedList<E> arr){
        if(arr.size() < 2)
            return;
        LinkedList<E> left = new LinkedList<>();
        LinkedList<E> right = new LinkedList<>();
        int i =0;
        Iterator<E> itr = arr.iterator();
        while(i<arr.size()/2){
            left.addFirst(itr.next());
            i++;
        }

        while(i<arr.size()){
            right.addFirst(itr.next());
            i++;
        }

        mergeSort(left);
        mergeSort(right);
        merge(left,right,arr);
    }

    /**
     * normal merge sort algoritmasindaki birlestirme kismi
     * @param left left list
     * @param right right list
     * @param arr main list
     */
    private void merge(LinkedList<E> left , LinkedList<E> right, LinkedList<E> arr){
        int i=0,j=0,r=0;
        Iterator<E> leftItr = left.iterator();
        Iterator<E> rightItr = right.iterator();
        ListIterator<E> itr = arr.listIterator();
        if(leftItr.hasNext() && rightItr.hasNext()){
            E leftTmp = leftItr.next();
            E rightTmp = rightItr.next();
            boolean flag = true;
            while(flag){
                if(leftTmp.compareTo(rightTmp) > 0){
                    itr.next();
                    addRemove(leftTmp,i++,r++,itr);
                    if(leftItr.hasNext())
                        leftTmp = leftItr.next();
                    else{
                        flag = false;
                        while(j < right.size()){
                            itr.next();
                            r=5;
                            addRemove(rightTmp,j++,r++,itr);
                            if(rightItr.hasNext())
                                rightTmp = rightItr.next();
                        }

                    }

                }

                else{
                    itr.next();
                    addRemove(rightTmp,j++,r++,itr);
                    if(rightItr.hasNext())
                        rightTmp = rightItr.next();
                    else{
                        flag = false;
                        int x = 5;
                        while(i < left.size()){
                            itr.next();
                            addRemove(leftTmp,i++,r++,itr);
                            if(leftItr.hasNext())
                                leftTmp = leftItr.next();
                        }

                    }

                }


            }
        }

        while(i<left.size()){
            itr.next();
            addRemove(leftItr.next(),i++,r++,itr);
        }


        while(j < right.size()){
            itr.next();
            addRemove(rightItr.next(),j++,r++,itr);
        }


    }

    /**
     *
     * @param item eklenecek item
     * @param i iterator kullanmadan once kullaniyordum kodun diger taraflarinda kaldigi icin silmedim
     * @param r i ile ayni
     * @param itr arr[k++] ya atariz normal merge sortta onun aynisi bir nevi.
     */
    private void addRemove(E item,int i , int r, ListIterator<E> itr){
        itr.set(item);
    }




}
