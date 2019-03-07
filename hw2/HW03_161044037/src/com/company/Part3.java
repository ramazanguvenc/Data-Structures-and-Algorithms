package com.company;

/**
 * Bu class kendi yazdigim iteratoru olmayan onun yerine classin icinde direk next methodu olan bir ozel bir inked listtir.
 * Diger Link listlerden farki ozel bir format icin tasarlanmis icerisinde normal ve circular link list bulundurmasidir.
 * Circular link list sadece ayni semesterlar icin vardir ve buna nextInSemester methoduyla ulasilabilir.
 * @param <E>
 */
public class Part3<E> {
        private Node<E> head;
        private Node<E> tail;
        private int size,index;
        private boolean control;

        public Part3(){
              size=0;
              index = 0;
              control = true;
        }

    /**
     * deletes given object
     * @param e
     * @return false if cant find input in list.
     */
    public boolean remove(E e){
            if(head.data.equals(e)){
                head = head.next;
                size--;
                return true;
            }
            if(head.next.data.equals(e)){

                head.next = head.next.next;
                size--;
                return true;
            }
            //else
            Node<E> current = head.next;
            for(int i =1 ; i<size;i++){
                if(current.next.data.equals(e)){
                    current.next = current.next.next;
                    size--;
                    return true;
                }
            }
            return false;
        }

    /**
     * add input end of the list.
     * @param e
     * @return
     */
    public boolean add(E e){
                Node<E> t = new Node<>(e);
                if(head == null){
                    head = t;
                    tail = head;
                }
                else{
                    Node<E> tmp = new Node<>(e);
                    tail.next = tmp;
                    tail = tail.next;
                }
                size++;
                return true;


        }

    /**
     *
     * @return true if next() != tail.
     */
    public boolean hasNext(){
            return index < size && control;
        }

    /**
     *
     * @return next object
     */
    public E next(){
        if(index==0){
            index++;
            return head.data;
            }
            //else
        return current(index++).data;
        }

        public Node<E> current(int index){
            Node<E> current = head.next;
            for(int i =0 ; i<index-1;++i)
                current = current.next;
            return current;
        }

    /**
     *
     * @return next object which equals to semester prev object.
     */
    public E nextInSemester(){
        control=true;
        if(index==0){
            index++;
            return head.data;
        }

        //else if
        Node<E> current = current(index);
        for(int i=index+1;i<size;i++)
            if(current.semester == current(i).semester){
                if(index==1){
                    index = i;
                    return current(1).data;
                }
                index = i;
                return current(index).data;
            }
            control=false;
        if(current.semester == head.semester){
            index=1;
            return head.data;
        }
        //else
        int tmp = index;
        index = 1;
        while(tmp != index){
            if(current(tmp).semester == current(index).semester)
                return current(index).data;
            index++;
        }
        return current(tmp).data;
    }



        public int size(){
            return size;
        }

    /**
     * makes index=0, this method should use if you want to use next() method again at the beginning.
     */
    public void indexZero(){
            index = 0;
        }

    /**
     * This class keeps next and your data.
     * @param <E>
     */
    private class Node<E>{
            private Node<E> next;
            private Node<E> nextSemester;
            private E data;
            private int semester;

            public Node(E e){
                this(e,null);
            }
            public Node(E e,Node<E>next){
                this.data = e;
                this.next = next;
                String tmp = (String) data;
                this.semester = Integer.parseInt(tmp.substring(0,1));
            }


            public Node<E> next(){
                return this.next;
            }



        }
}
