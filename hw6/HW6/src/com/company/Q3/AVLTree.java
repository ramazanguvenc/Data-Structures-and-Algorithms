package com.company.Q3;



import com.company.BinarySearchTreeWithRotate;
import com.company.BinaryTree;


/**
 * Self-balancing binary search tree using the algorithm defined
 * by Adelson-Velskii and Landis.
 * @author Koffman and Wolfgang
 */
/** Self-balancing binary search tree using the algorithm defined
 *  Sadece istediginiz constructoru ben yazdim. Geri kalan methodlar zaten kitabin yayinladigi kodlarda varmis sizin verdiginiz link degilde.
 *  Kendim yazmayi denedim fakat olmadi yani ben bu methodlari kendim yazmam bu kadar kisa surede baskasinin kodunun ustunden imkansizdi en azindan benim icin.
 *  by Adelson-Velskii and Landis.
 *  @author Koffman and Wolfgang
 */

public class AVLTree < E
        extends Comparable < E >>
        extends BinarySearchTreeWithRotate < E > {

    //Used in constructor.
    /**
     * No parameter constructor.
     */
    public AVLTree(){

    }

    /**
     *
     * @param in BinaryTree will be check its AVL or not.
     */
    public AVLTree(BinaryTree<E> in){
        BinaryTree<E> tmp = in;

        if(checkItsAVL(in) && in != null && isSorted(in))
            System.out.println("Its AVL");
        else
            System.out.println("Its not an AVL");


    }

    /**
     *
     * @param in sorted yani search tree olup olmadigi kontrol edilen input
     * @return true if its sorted.
     */
    private boolean isSorted(BinaryTree<E> in){
        if(in == null)
            return true;
        if(in.getRightSubtree() != null)
            if(in.getData().compareTo(in.getRightSubtree().getData()) > 0 )
                return false;
        if(in.getLeftSubtree() != null)
            if(in.getData().compareTo(in.getLeftSubtree().getData()) < 0)
                return false;
        return isSorted(in.getRightSubtree()) || isSorted(in.getLeftSubtree());


    }

    /**
     * Parametreyle gelen agacin AVL olup olmadigini yukseklikleri karsilastirarak kontrol ediyor.
     * Kisaca tum subtreelere bakarak height farkininin 1 den fazla olup olmadigini kontrol ediyor diger komsu agacla
     * @param in AVL olup olmadigi kontrol edilen BinaryTree
     * @return Eger in AVL tree ise true yoksa false.
     */
    private boolean checkItsAVL(BinaryTree<E> in){
        if(in == null)
            return true;
        boolean a = checkItsAVLHelper(in.getLeftSubtree());
        boolean b = checkItsAVLHelper(in.getRightSubtree());
        if(!a || !b)
            return false;
        boolean c = checkItsAVL(in.getRightSubtree());
        boolean d = checkItsAVL(in.getLeftSubtree());
        if(!c || !d)
            return false;
        return true;
    }

    /**
     * LeftSubtree ile RightSubtree arasindaki height farkini kontrol ediyor.
     * Yukardaki method ise bu methodu her subTree icin cagirarak eger bir tane bile false varsa false donduruyor.
     * @param in AVL olup olmadigi kontrol edilen BinaryTree
     * @return Eger iki subTree arasinda height problemi yoksa true yoksa false.
     */
    private boolean checkItsAVLHelper(BinaryTree<E> in){
        if(in == null)
            return true;
        int h1 = height(in.getRightSubtree());
        int h2 = height(in.getLeftSubtree());
        return h1+1 == h2 || h1 == h2 || h2+1 == h1;
    }

    /**
     * Gonderilen agacin yuksekligini hesaplar
     * @param in yuksekliginin veya depth daha dogru bi tanim hesaplanmasini istediginiz agac.
     * @return height
     */
    private int height(BinaryTree<E> in) {
        if(in == null)
            return 0;
        int left = height(in.getLeftSubtree());
        int right = height(in.getRightSubtree());
        return left > right ? left+1 : right+1;

    }

    // Insert nested class AVLNode<E> here.

    // Data Fields
    /** Flag to indicate that height of tree has increased. */
    private boolean increase;

    /** Flag to indicate that height of tree has decreased */
    private boolean decrease;

    /** Class to represent an AVL Node. It extends the
     BinaryTree.Node by adding the balance field. */
    private static class AVLNode < E > extends Node < E > {
        /** Constant to indicate left-heavy */
        public static final int LEFT_HEAVY = -1;

        /** Constant to indicate balanced */
        public static final int BALANCED = 0;

        /** Constant to indicate right-heavy */
        public static final int RIGHT_HEAVY = 1;

        /** balance is right subtree height – left subtree height */
        private int balance;

        // Methods
        /** Construct a node with the given item as the data field.
         @param item The data field
         */
        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }

        /** Return a string representation of this object.
         The balance value is appended to the contents.
         @return String representation of this object
         */
        public String toString() {
            return balance + ": " + super.toString();
        }
    }

    /** add starter method.
     pre: the item to insert implements the Comparable interface.
     @param item The item being inserted.
     @return true if the object is inserted; false
     if the object already exists in the tree
     @throws ClassCastException if item is not Comparable
     */
    public boolean add(E item) {
        increase = false;
        root = add( (AVLNode < E > ) root, item);
        return addReturn;
    }



    /** Recursive add method. Inserts the given object into the tree.
     post: addReturn is set true if the item is inserted,
     false if the item is already in the tree.
     @param localRoot The local root of the subtree
     @param item The object to be inserted
     @return The new local root of the subtree with the item
     inserted
     */
    private AVLNode < E > add(AVLNode < E > localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode < E > (item);
        }

        if (item.compareTo(localRoot.data) == 0) {
            // Item is already in the tree.
            increase = false;
            addReturn = false;
            return localRoot;
        }

        else if (item.compareTo(localRoot.data) < 0) {
            // item < data
            localRoot.left = add( (AVLNode < E > ) localRoot.left, item);

            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot; // Rebalance not needed.
        }
        else {
            // item > data
            localRoot.right = add( (AVLNode < E > ) localRoot.right, item);
            if (increase) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    return rebalanceRight(localRoot);
                }
                else {
                    return localRoot;
                }
            }
            else {
                return localRoot;
            }
        }

    }

    /**** BEGIN EXERCISE ****/
    /** Delete starter method. Removes the given object
     from the AVL tree.
     @param item - The object to be removed.
     @return The object from the tree that was removed
     or null if the object was not in the tree.
     */
    public E delete(E item) {
        decrease = false;
        root = delete( (AVLNode < E > ) root, item);
        return deleteReturn;
    }

    /** Recursive delete method. Removes the given object
     from the AVL tree.
     is set to the object that was removed, otherwise
     it is set false.
     @param localRoot The root of the local subtree
     @param item The item to be removed
     @return The new root of the local subtree with the item
     removed.
     */
    private AVLNode < E > delete(AVLNode < E > localRoot, E item) {
        if (localRoot == null) { // item is not in tree
            deleteReturn = null;
            return localRoot;
        }
        if (item.compareTo(localRoot.data) == 0) {
            // item is in the tree -- need to remove it
            deleteReturn = localRoot.data;
            return findReplacementNode(localRoot);
        }
        else if (item.compareTo(localRoot.data) < 0) {
            // item is < localRoot.data
            localRoot.left = delete( (AVLNode < E > ) localRoot.left, item);
            if (decrease) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    return rebalanceRightL( (AVLNode < E > ) localRoot);
                }
                else {
                    return localRoot;
                }
            }
            else {
                return localRoot;
            }
        }
        else {
            // item is > localRoot.data
            localRoot.right = delete( (AVLNode < E > ) localRoot.right, item);
            if (decrease) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    return rebalanceLeftR(localRoot);
                }
                else {
                    return localRoot;
                }
            }
            else {
                return localRoot;
            }
        }
    }

    /** Function to find a replacement for a node that is being
     deleted from a binary search tree.  If the node has a null
     child, then the replacement is the other child.  If neither
     are null, then the replacement is the largest value less
     than the item being removed.
     @param node The node to be deleted or replaced
     @return null if both of node's children are null
     node.left if node.right is null
     node.right if node.left is null
     modified copy of node with its data field changed
     */
    private AVLNode < E > findReplacementNode(AVLNode < E > node) {
        if (node.left == null) {
            decrease = true;
            return (AVLNode < E > ) node.right;
        }
        else if (node.right == null) {
            decrease = true;
            return (AVLNode < E > ) node.left;
        }
        else {
            if (node.left.right == null) {
                node.data = node.left.data;
                node.left = node.left.left;
                incrementBalance(node);
                return node;
            }
            else {
                node.data = findLargestChild( (AVLNode < E > ) node.left);
                if ( ( (AVLNode < E > ) node.left).balance < AVLNode.LEFT_HEAVY) {
                    node.left = rebalanceLeft( (AVLNode < E > ) node.left);
                }
                if (decrease) {
                    incrementBalance(node);
                }
                return node;
            }
        }
    }

    /** Find the node such that parent.right.right == null
     by its left child (if any)
     @param parent - The possible parent
     @return the value of the found node
     */
    private E findLargestChild(AVLNode < E > parent) {
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            decrementBalance(parent);
            return returnValue;
        }
        else {
            E returnValue = findLargestChild( (AVLNode < E > ) parent.right);
            if ( ( (AVLNode < E > ) parent.right).balance < AVLNode.LEFT_HEAVY) {
                parent.right = rebalanceLeft( (AVLNode < E > ) parent.right);
            }
            if (decrease) {
                decrementBalance(parent);
            }
            return returnValue;
        }
    }

    /** Method to increment the balance field and to reset the value of
     increase or decrease.
     removal, and an item is either been added to the right or removed
     from the left.
     flags are set to false if the overall height of this subtree
     has not changed.
     @param node The AVL node whose balance is to be incremented
     */
    private void incrementBalance(AVLNode < E > node) {
        node.balance++;
        if (node.balance > AVLNode.BALANCED) {
      /* if now right heavy, the overall height has increased, but
         it has not decreased */
            increase = true;
            decrease = false;
        }
        else {
      /* if now balanced, the overall height has not increased, but
         it has decreased. */
            increase = false;
            decrease = true;
        }
    }

    /** rebalanceRight
     more than one right heavy.
     @param localRoot Root of the AVL subtree that needs rebalancing
     @return a new localRoot
     */
    private AVLNode < E > rebalanceRight(AVLNode < E > localRoot) {
        // Obtain reference to right child
        AVLNode < E > rightChild = (AVLNode < E > ) localRoot.right;
        // See if right-left heavy
        if (rightChild.balance < AVLNode.BALANCED) {
            // Obtain reference to right-left child
            AVLNode < E > rightLeftChild = (AVLNode < E > ) rightChild.left;
      /* Adjust the balances to be their new values after
         the rotates are performed.
       */
            if (rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            }
            else if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            else {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            /** After the rotates the overall height will be
             reduced thus increase is now false, but
             decrease is now true.
             */
            increase = false;
            decrease = true;
            // Perform double rotation
            localRoot.right = rotateRight(rightChild);
            return (AVLNode < E > ) rotateLeft(localRoot);
        }
        else {
      /* In this case both the rightChild (the new root)
         and the root (new left child) will both be balanced
         after the rotate. Also the overall height will be
         reduced, thus increase will be fasle, but decrease
         will be true.
       */
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
            increase = false;
            decrease = true;
            // Now rotate the
            return (AVLNode < E > ) rotateLeft(localRoot);
        }
    }

    /** rebalanceLeftR
     more than one left heavy.
     @param localRoot Root of the AVL subtree that needs rebalancing
     @return a new localRoot
     */
    private AVLNode < E > rebalanceLeftR(AVLNode < E > localRoot) {
        // Obtain reference to left child
        AVLNode < E > leftChild = (AVLNode < E > ) localRoot.left;
        // See if left-right heavy
        if (leftChild.balance > AVLNode.BALANCED) {
            // Obtain reference to left-right child
            AVLNode < E > leftRightChild = (AVLNode < E > ) leftChild.right;
      /* Adjust the balances to be their new values after
         the rotates are performed.
       */
            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            else if (leftRightChild.balance > AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            }
            else {
                leftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            /** After the rotates the overall height will be
             reduced thus increase is now false, but
             decrease is now true.
             */
            increase = false;
            decrease = true;
            // Perform double rotation
            localRoot.left = rotateLeft(leftChild);
            return (AVLNode < E > ) rotateRight(localRoot);
        }
        if (leftChild.balance < AVLNode.BALANCED) {
      /* In this case both the leftChild (the new root)
         and the root (new right child) will both be balanced
         after the rotate. Also the overall height will be
         reduced, thus increase will be fasle, but decrease
         will be true.
       */
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
            increase = false;
            decrease = true;
        }
        else {
      /* After the rotate the leftChild (new root) will
         be right heavy, and the local root (new right child)
         will be left heavy. The overall height of the tree
         will not change, thus increase and decrease remain
         unchanged.
       */
            leftChild.balance = AVLNode.RIGHT_HEAVY;
            localRoot.balance = AVLNode.LEFT_HEAVY;
        }
        // Now rotate the
        return (AVLNode < E > ) rotateRight(localRoot);
    }

    /** rebalanceRightL
     more than one right heavy.
     @param localRoot Root of the AVL subtree that needs rebalancing
     @return a new localRoot
     */
    private AVLNode < E > rebalanceRightL(AVLNode < E > localRoot) {
        // Obtain reference to right child
        AVLNode < E > rightChild = (AVLNode < E > ) localRoot.right;
        // See if right-left heavy
        if (rightChild.balance < AVLNode.BALANCED) {
            // Obtain reference to right-left child
            AVLNode < E > rightLeftChild = (AVLNode < E > ) rightChild.left;
      /* Adjust the balances to be their new values after
         the rotates are performed.
       */
            if (rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            else if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            }
            else {
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            /** After the rotates the overall height will be
             reduced thus increase is now false, but
             decrease is now true.
             */
            increase = false;
            decrease = true;
            // Perform double rotation
            localRoot.right = rotateRight(rightChild);
            return (AVLNode < E > ) rotateLeft(localRoot);
        }
        if (rightChild.balance > AVLNode.BALANCED) {
      /* In this case both the rightChild (the new root)
         and the root (new left child) will both be balanced
         after the rotate. Also the overall height will be
         reduced, thus increase will be fasle, but decrease
         will be true.
       */
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
            increase = false;
            decrease = true;
        }
        else {
      /* After the rotate the rightChild (new root) will
         be left heavy, and the local root (new left child)
         will be right heavy. The overall height of the tree
         will not change, thus increase and decrease remain
         unchanged.
       */
            rightChild.balance = AVLNode.LEFT_HEAVY;
            localRoot.balance = AVLNode.RIGHT_HEAVY;
        }
        // Now rotate the
        return (AVLNode < E > ) rotateLeft(localRoot);
    }

    /**** END EXERCISE ****/

    /** Method to rebalance left.
     pre: localRoot is the root of an AVL subtree that is
     critically left-heavy.
     post: Balance is restored.
     @param localRoot Root of the AVL subtree
     that needs rebalancing
     @return a new localRoot
     */
    private AVLNode < E > rebalanceLeft(AVLNode < E > localRoot) {
        // Obtain reference to left child.
        AVLNode < E > leftChild = (AVLNode < E > ) localRoot.left;
        // See whether left-right heavy.
        if (leftChild.balance > AVLNode.BALANCED) {
            // Obtain reference to left-right child.
            AVLNode < E > leftRightChild = (AVLNode < E > ) leftChild.right;
            /** Adjust the balances to be their new values after
             the rotations are performed.
             */
            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            }
            else {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform left rotation.
            localRoot.left = rotateLeft(leftChild);
        }
        else { //Left-Left case
            /** In this case the leftChild (the new root)
             and the root (new right child) will both be balanced
             after the rotation.
             */
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root right.
        return (AVLNode < E > ) rotateRight(localRoot);
    }

    private void decrementBalance(AVLNode < E > node) {
        // Decrement the balance.
        node.balance--;
        if (node.balance == AVLNode.BALANCED) {
            /** If now balanced, overall height has not increased. */
            increase = false;
        }
    }

}
