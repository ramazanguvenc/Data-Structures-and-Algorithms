package com.company;

import java.util.LinkedList;

/**
 * This class extends BinaryTree but act like a general tree.
 * @param <E> can be anything
 */
public class Part1<E> extends BinaryTree {
    private Node<E> result;
    private int size = 0;


    /**
     *
     * @param parentItem should be null if you want to add root, otherwise send which parent if you want.
     * @param childItem item you want to add on this tree.
     * @return
     */
    public boolean add(E parentItem, E childItem) {
        if (parentItem == null) {
            if (root == null) {
                root = new Node<>(childItem);
                size++;
                return true;
            }
            //else
            Node<E> tmp = root;
            root = new Node<>(childItem);
            root.left = tmp;
            size++;
            return true;
        }
        Node<E> item = postOrderSearch(parentItem);

        if (item == null)
            return false;
        if (item.left == null) {
            item.left = new Node<>(childItem);
            size++;
            return true;
        }
        //else
        item = item.left;
        if (item.right == null) {
            item.right = new Node<>(childItem);
            size++;
            return true;
        }
        boolean control = true;
        while (control) {
            if (item.right == null)
                control = false;
            else {
                item = item.right;
            }
        }
        item.right = new Node<>(childItem);
        size++;
        return true;

    }

    /**
     *
     * @param item The item you want to find
     * @param main Root at the beginning i dont have any idea about the rest because this method is recursive.
     * @return Node which should be Node.data == item. Returns null if this method can't find your input.
     */
    private Node<E> postOrderSearch(E item, Node<E> main){
        Node<E> result;
        if(main.left != null){
            result = postOrderSearch(item,main.left);
            if(result != null)
                return result;
        }

        System.out.printf("%s ==? %s \n",item,main.data);
        if(main.data.equals(item))
            return main;
        if(main.right != null){
            result = postOrderSearch(item,main.right);
            if(result != null)
                return result;
        }

        return null;
    }

    /**
     * This method uses helper method such as PostOrderSearch(E item,Node main).
     * Use this method if you think your items in higher levels.(Which means its far from root.)
     * @param item The item you want to find
     * @return Node which should be Node.data == item. Returns null if this method can't find your input.
     */
    public Node<E> postOrderSearch(E item){
        Node<E> result;
        if(root.left != null){
            result = postOrderSearch(item,root.left);
            if(result != null)
                return result;
        }
        if(root.right != null){
            result = postOrderSearch(item,root.right);
            if(result != null)
                return result;
        }
        System.out.printf("%s ==? %s \n",item,root.data);
        if(root.data.equals(item))
            return root;
        return null;

    }


    /**
     * This method uses helper method such as levelOrderSearch(E item,Node main,LinkedList list).
     * Use this method if you think your item you looking for is close to root.
     * @param item The item you want to find
     * @return Node which should be Node.data == item. Returns null if this method can't find your input.
     */
   public Node<E> levelOrderSearch(E item){
        System.out.printf("%s ==? %s \n",item,root.data);
        if(root.data.equals(item))
            return root;
        Node<E> main = root.left;
        Node<E> result = checkBrothers(item,main);
        if(result != null)
            return result;
        LinkedList<Node<E>> list = checkBrothersHaveKids(main);
        return levelOrderSearch(item,main.left,list);

   }

    /**
     *
     * @param item the item you looking for.
     * @param main which node you are currently looking.
     * @param list adds list your brothers kids and looks them after look at your kids.
     * @return Node which should be Node.data == item. Returns null if this method can't find your input.
     */
   private Node<E> levelOrderSearch(E item, Node<E> main, LinkedList<Node<E>> list){
        if(main == null)
            return null;
       System.out.printf("%s ==? %s \n",item,main.data);
       if(main.data.equals(item))
           return main;
       Node<E> result = checkBrothers(item,main.right);
       if(result != null)
           return result;
       for(int i =0; i< list.size();i++){
           result = checkBrothers(item,list.get(i));
           if(result != null)
               return result;
       }
       LinkedList<Node<E>> list1 = checkBrothersHaveKids(main);
       return levelOrderSearch(item,main.left,list1);

   }

    /**
     *
     * @param item which item you looking for.
     * @param main which node you want to look for his brothers.
     * @return null if you cant find your item on your brothers. Otherwise returns your brother.
     */
   private Node<E> checkBrothers(E item, Node<E> main){
        while(main != null){
            System.out.printf("%s ==? %s \n",item,main.data);
            if(main.data.equals(item))
                return main;
            main = main.right;
        }
        return null;
   }

    /**
     *
     * @param main Which node you want to look for his brothers have kids.
     * @return the kids if your brothers have one
     */
   private LinkedList<Node<E>> checkBrothersHaveKids(Node<E> main){
        LinkedList<Node<E>> list = new LinkedList<>();
        while(main.right != null){
            main = main.right;
            if(main.left != null)
                list.add(main.left);
        }
        if(list.size() == 0)
            return null;
        return list;
   }

    /**
     * same in BinaryTree.java except preOrderTraverse(node.right, depth+1, sb) part
     */
   private void preOrderTraverse(Node<E> node, int depth,
                                 StringBuilder sb){
       for (int i = 1; i < depth; i++) {
           sb.append("  ");
       }
       if (node == null) {
           sb.append("null\n");
       } else {
           sb.append(node.toString());
           sb.append("\n");
           preOrderTraverse(node.left, depth + 1, sb);
           preOrderTraverse(node.right, depth, sb);
       }
   }

}
