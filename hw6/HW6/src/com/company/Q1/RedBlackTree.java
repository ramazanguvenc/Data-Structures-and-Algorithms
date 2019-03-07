/*<listing chapter="9" section="3">*/
package com.company.Q1;

import com.company.BinarySearchTreeWithRotate;

/**
 * RedBlackTree.java
 * Implements a Red-Black binary search tree
 * Red-Black trees were invented by Bayer with refinments
 * (the color convention) introduced by Guibas and Sedgewick.
 * @author Koffman and Wolfgang
 */
public class RedBlackTree<E extends Comparable<E>>
         extends BinarySearchTreeWithRotate<E> {

    /*<listing chapter="9" number="4">*/
    /** Nested class to represent a Red-Black node. */
    private static class RedBlackNode<E> extends Node<E> {
        // Additional data members

        /** Color indicator. True if red, false if black. */
        private boolean isRed;

        // Constructor
        /**
         * Create a RedBlackNode with the default color of red
         * and the given data field.
         * @param item The data field
         */
        public RedBlackNode(E item) {
            super(item);
            isRed = true;
        }

        // Methods
        /**
         * Return a string representation of this object.
         * The color (red or black) is appended to the
         * node's contents.
         * @return String representation of this object
         */
        @Override
        public String toString() {
            if (isRed) {
                return "Red  : " + super.toString();
            } else {
                return "Black: " + super.toString();
            }
        }
    }
    /*</listing>*/
// Insert solution to programming project 6, chapter -1 here

    /**
     * Insert an item into the tree. This is the starter method
     * of a recursive process.
     * @param item - The item to be inserted
     * @return true if item inserted, false if item already in the tree.
     */
    @Override
    public boolean add(E item) {
        if (root == null) {
            root = new RedBlackNode<E>(item);
            ((RedBlackNode<E>) root).isRed = false; // root is black.
            return true;
        } else {
            root = add((RedBlackNode<E>) root, item);
            ((RedBlackNode<E>) root).isRed = false; // root is always black.
            return addReturn;
        }
    }

    /**
     * Recursive add method.
     * @param localRoot - The root of the subtree
     * @param item - The item to be inserted
     * @return  updated local root of the subtree
     * @post insertReturn is set false if item is already in the tree
     */
    private Node<E> add(RedBlackNode<E> localRoot, E item) {
        if (item.compareTo(localRoot.data) == 0) {
            // item already in the tree.
            addReturn = false;
            return localRoot;
        }

        else if (item.compareTo(localRoot.data) < 0) {
            // item < localRoot.data.
            if (localRoot.left == null) {
                // Create new left child.
                localRoot.left = new RedBlackNode < E > (item);
                addReturn = true;
                return localRoot;
            }

            else { // Need to search.
                // Check for two red children, swap colors if found.
                moveBlackDown(localRoot);
                // Recursively add on the left.
                localRoot.left = add( (RedBlackNode < E > ) localRoot.left, item);

                // See whether the left child is now red
                if ( ( (RedBlackNode < E > ) localRoot.left).isRed) {

                    if (localRoot.left.left != null
                            && ( (RedBlackNode < E > ) localRoot.left.left).isRed) {
                        // Left-left grandchild is also red.

                        // Single rotation is necessary.
                        ( (RedBlackNode < E > ) localRoot.left).isRed = false;
                        localRoot.isRed = true;
                        return rotateRight(localRoot);
                    }
                    else if (localRoot.left.right != null
                            && ( (RedBlackNode < E > ) localRoot.left.right).isRed) {
                        // Left-right grandchild is also red.
                        // Double rotation is necessary.
                        localRoot.left = rotateLeft(localRoot.left);
                        ( (RedBlackNode < E > ) localRoot.left).isRed = false;
                        localRoot.isRed = true;
                        return rotateRight(localRoot);
                    }
                }
                return localRoot;
            }
        }

        else {
            // item > localRoot.data
            if (localRoot.right == null) {
                /**** BEGIN EXERCISE ****/
                // create new right child
                localRoot.right = new RedBlackNode < E > ( (E) item);
                addReturn = true;
                return localRoot;
            }
            else { // need to search
                // check for two red children swap colors
                moveBlackDown(localRoot);
                // recursively insert on the right
                localRoot.right =
                        add( (RedBlackNode < E > ) localRoot.right, item);
                // see if the right child is now red
                if ( ( (RedBlackNode) localRoot.right).isRed) {
                    if (localRoot.right.right != null
                            && ( (RedBlackNode) localRoot.right.right).isRed) {
                        // right-right grandchild is also red
                        // single rotate is necessary
                        ( (RedBlackNode) localRoot.right).isRed = false;
                        localRoot.isRed = true;
                        return rotateLeft(localRoot);
                    }
                    else if (localRoot.right.left != null
                            && ( (RedBlackNode) localRoot.right.left).isRed) {
                        // left-right grandchild is also red
                        // double rotate is necessary
                        localRoot.right = rotateRight(localRoot.right);
                        ( (RedBlackNode) localRoot.right).isRed = false;
                        localRoot.isRed = true;
                        return rotateLeft(localRoot);
                    }
                }
                return localRoot;
            }
            /**** END EXERCISE ****/
        }
    }

    private void moveBlackDown(RedBlackNode<E> root){
        if(root.left != null && root.right != null && (( (RedBlackNode) root.left).isRed
                && ( (RedBlackNode) root.right).isRed) ){
            root.isRed = true;
            ((RedBlackNode) root.left).isRed = false;
            ((RedBlackNode) root.right).isRed = false;
        }

    }

// Insert solution to programming project 2, chapter -1 here

// Insert solution to programming project 6, chapter -1 here
}
/*</listing>*/
