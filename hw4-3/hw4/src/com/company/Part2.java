package com.company;

import java.util.LinkedList;


/**
 * This class is general multi dimensional search tree but actually runs by BinaryTree.
 * @param <E> extends comparable
 */
//E extends Comparable StackOverFlowdan alinmistir
public class Part2<E extends Comparable<E>> extends BinaryTree implements searchTree{
    private int dimension;
    private LinkedList<E> myData = new LinkedList<>();

    /**
     * No parameter constructor takes dimension as a 3.
     */
    Part2(){
        dimension = 3;
    }

    /**
     *
     * @param i = dimension
     * @throws Exception if your input < 1
     */
    Part2(int i) throws Exception {
        if(i < 1)
            throw new Exception("Your dimension cant be smaller than 1");
        dimension = i;
    }


    /**
     * This method calls the other add method.
     * @param item The item you want to add on tree.
     * @return true if you added successfully. Otherwise returns false.
     */
    @Override
    public boolean add(Object item) {
        if(dimension != 1){
            System.out.println("You sent only one parameter");
            System.exit(1);
        }
        return add((E)item);
    }

    /**
     *
     * @param item The item you want to add on tree.
     * @return true if you added successfully. Otherwise returns false.
     */
    public boolean add(E item){
        if(root == null){
            System.out.println("root added");
            root = new Node(item);
            myData.add(item);
            return true;
        }
        //else
        Node<E> main = root;
        while(true){
            if(main.data.compareTo(item) == 0){
                System.out.println("they are equal");
                return false;
            }

            if(main.data.compareTo(item) > 0){
                System.out.println("root is bigger");
                if(main.right == null){
                    main.right = new Node<>(item);
                    myData.add(item);
                    return true;
                }
                //else
                main = main.right;
            }
            else if(main.data.compareTo(item) < 0){
                System.out.println("root is smaller");
                if(main.left == null){
                    main.left = new Node<>(item);
                    myData.add(item);
                    return true;
                }
                main = main.left;
            }
        }
    }

    /**
     * This method works for multi-dimensional items.
     * @param items  The item you want to add on tree
     * @return true if you added successfully. Otherwise returns false.
     */
    public boolean add(E ... items){
        LinkedList<E> datas = new LinkedList<>();
        for(E item : items )
            datas.add(item);
        if(datas.size() != dimension)
            System.out.println("dimension != datas.size() -- add method");
        if(root == null){
            root = new Node(datas.get(0));
            for(int i = 0 ;i < datas.size(); i++)
                myData.add(datas.get(i));
            return true;
        }
        int index = 0,i=0;
        Node<E> main = root;
        while(true){
            if(myData.get(index).compareTo(datas.get(i)) == 0){
                main = main.left;
                index = myData.indexOf(main.data);
            }
            else if(myData.get(index).compareTo(datas.get(i)) > 0){
                //System.out.println("left");
                if(main.left == null){
                    main.left = new Node(datas.get(0));
                    for(int j = 0; j < datas.size();j++)
                        myData.add(datas.get(j));
                    return true;
                }
                main = main.left;
                index = myData.indexOf(main.data);
            }
            else if(myData.get(index).compareTo(datas.get(i)) < 0){
                //System.out.println("right");
                if(main.right == null){
                    main.right = new Node(datas.get(0));
                    for(int j = 0; j < datas.size();j++)
                        myData.add(datas.get(j));
                    return true;
                }
                main = main.right;
                index = myData.indexOf(main.data);
            }
            if(i+1 == dimension){
                index -= i;
                i=0;
            }
            else{
                i++;
                index++;
            }



        }
    }

    /**
     *
     * @param target The item you want to check if this item contains or not.
     * @return true if your item contains.
     */
    @Override
    public boolean contains(Object target) {
        return myData.contains(target);
    }

    /**
     *
     * @param target The item you want to find.
     * @return Your item if this method able to find it. Otherwise returns null.
     */
    @Override
    public Object find(Object target) {
        LinkedList<E> input = new LinkedList<>();
        input.add((E) target);
        return findMulti(input);

    }

    /**
     * This method works for multi-dimensional items.
     * @param target The item you want to find.
     * @return Your item if this method able to find it. Otherwise returns null.
     */
    public Node<E> [] findMulti(LinkedList<E> target){
        if(!myData.contains(target.get(0)))
            return null;
        if(root == null)
            return null;
        Node<E> main = root;
        Node<E> oldMain = main;
        int index = myData.indexOf(root.data), i = 0;
        //System.out.println("index ="+index +" " + myData.get(index));
        if(target.size() != dimension)
            System.out.println("findMulti method targetsize != dimension");
        while(true){
            //System.out.println(target.get(i)+" ==? "+ myData.get(index));
            if(myData.get(index).compareTo(target.get(i)) == 0){
                Node<E> [] result = new Node[2];
                result[0] = main;
                result[1] = oldMain;
                return result;
            }
            if(myData.get(index).compareTo(target.get(i)) < 0){
                oldMain = main;
                main = main.right;
                index = myData.indexOf(main.data);

            }

            else if(myData.get(index).compareTo(target.get(i)) > 0){
                oldMain = main;
                main = main.left;
                index = myData.indexOf(main.data);
            }
            if(dimension-1 == i){
                i=0;
            }
            else{
                i++;
                index +=i;
            }



        }
    }


    /**
     * This method works for multi-dimensional items.
     * @param targets The item you want to delete
     * @return deleted item as a array and array[0] means your first dimension.
     */
    public E [] delete(E ... targets){
        LinkedList<E> list = new LinkedList<>();
        for(E target : targets)
            list.add(target);
        Node<E> [] found = findMulti(list);
        Node<E> main = found[0];
        //System.out.println("Deleted item = "+main.data+" "+myData.get(myData.indexOf(main.data)+1));

        Node<E> oldMain = found[1];
        if (main == null){
            System.out.println("delete null");
            return null;
        }
        if(main.right == null && main.left == null){
            E [] result = (E[]) new Comparable[dimension];
            for(int i =0,index = myData.indexOf(main.data);i<dimension;i++)
                result[i] = myData.remove(index);
            if(oldMain.right == main)
                oldMain.right = null;
            else
                oldMain.left = null;
            return result;
        }
        if(main.right != null && main.left != null){
            E [] result = (E[]) new Comparable[dimension];
            for(int i =0,index = myData.indexOf(main.data);i<dimension;i++)
                result[i] = myData.remove(index);
            Node<E> tmp = main.right;
            Node<E> mainBU = main;
            main = main.left;
            while(main.right != null)
                main = main.right;
            main.right = tmp;
            if(oldMain.right == mainBU){
                oldMain.right = main;
                return result;
            }
            oldMain.left = main;
            return result;
        }
        if(main.right != null && main.left == null){
            E [] result = (E[]) new Comparable[dimension];
            for(int i =0,index = myData.indexOf(main.data);i<dimension;i++)
                result[i] = myData.remove(index);
            if(oldMain.right == main){
                main = main.right;
                oldMain.right = main;
                return result;
            }
            main = main.right;
            oldMain.left = main;
            return result;
        }

        E [] result = (E[]) new Comparable[dimension];
        for(int i =0,index = myData.indexOf(main.data);i<dimension;i++)
            result[i] = myData.remove(index);
        if(oldMain.right == main){
            main = main.left;
            oldMain.right = main;
            return result;
        }
        main = main.left;
        oldMain.left = main;
        return result;
        }


    /**
     *
      * @param target The item you want to delete
     * @return deleted item as a array and array[0] means your first dimension.
     */
    @Override
    public Object delete(Object target) {
        E [] tmp = (E[]) new Comparable[1];
        tmp[0] = (E) target;
        return delete(tmp);
    }

    /**
     * This methods calls delete method.
     * @param target The item you want to delete
     * @return returns true if remove is successful.
     */
    @Override
    public boolean remove(Object target) {
        return delete(target) == null ? false : true;
    }


    /**
     * Overrided for good output
     * @return String.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int a = 0;
        for(int i = 0;i<myData.size();i++){
            if(a == 0 || a == dimension)
                sb.append("(");
            sb.append(myData.get(i));
            sb.append(",");
            a++;
            if(a == dimension){
                a=0;
                sb.deleteCharAt(sb.length()-1);
                sb.append(")");
                sb.append("\n");
            }


        }
        return sb.toString();
    }


}
