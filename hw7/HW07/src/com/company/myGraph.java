package com.company;

import java.util.Iterator;
import java.util.LinkedList;

/*****************************************************
 *    Title: MatrixGraph.java
 *    Author: Koffman and Wolfgang
 *    Site owner/sponsor: wiley.com
 *    Date: 21/04/2010
 *    Code version:
 *    Availability: http://eu.wiley.com/WileyCDA/WileyTitle/productCd-EHEP001607.html(Accessed November 2014)- Only available to Instructors
 *    Modified:  No
 *****************************************************/
public class myGraph implements Graph {

    /**** BEGIN EXERCISE ****/
    // Data field
    /** The two dimensional array to represen an edge */

    private int numV;
    private boolean directed;


    private int[][] matrix;


    public void plot_graph(){
        for(int i =0;i<matrix.length;i++)
            for(int j =0;j<matrix[i].length;j++)
                if(matrix[i][j] != 0 )
                    System.out.println(i+" --->"+j);
    }

    public myGraph(int nV, boolean direct){
            this.directed = direct;
            this.numV = nV;
        matrix = new int[nV][nV];
        for (int row = 0; row < nV; row++)
            for (int col = 0; col < nV; col++)
                matrix[row][col] = 0;
    }

    public boolean isEdge(int source, int dest) {
        return matrix[source][dest] != 0;
    }

    public void insert(Edge edge) {
        matrix[edge.getSource()][edge.getDest()] = edge.getWeight();
        if (!isDirected()) {
            matrix[edge.getDest()][edge.getSource()] = edge.getWeight();
        }
    }

    public void remove(Edge edge) {
        //complete this method
        // as for insert method
        matrix[edge.getSource()][edge.getDest()] = 0;

        if (!isDirected()) {
            matrix[edge.getDest()][edge.getSource()] = 0;
        }
    }

    public boolean is_acyclic_graph(){
        if(isDirected()){
            for(int a = 0;a<matrix.length;a++){
                for(int j =0;j<matrix[a].length;j++){
                    if(matrix[a][j] != 0){
                        if(matrix[j][a] != 0)
                            return false;
                        for(int i =j+1;i<matrix[j].length;i++)
                            if(matrix[j][i] == 1 && matrix[i][a] != 0)
                                return false;
                    }
                }
            }
            return true;
        }
        else{
            return is_acyclic_graph(0);
        }

    }

    public boolean is_acyclic_graph(int x){
        for(int a = 0;a<matrix.length;a++){
            for(int j =0;j<matrix[a].length;j++){
                if(matrix[a][j] == 1){
                    for(int i =j+1;i<matrix[j].length;i++)
                        if(matrix[j][i] != 0 && matrix[i][a] != 0)
                            return false;
                }
            }
        }
        return true;
    }

    public boolean isDirected(){
        return directed;
    }

    public boolean isDirectedd(){
        for(int i =0;i<matrix.length;i++){
            for(int j =0;j<matrix[i].length;j++){
                if(matrix[i][j] != 0 && matrix[j][i] == 0)
                    return true;
            }
        }
        //else
        return false;
    }

    public void shortest_path(int start,int end){
        Dijkstra.dijkstra(matrix,start,end,getNumV());
    }
    public boolean is_connected(int x,int y){
        return matrix[x][y] != 0 && matrix[y][x] != 0;
    }



    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return new Iter(source);
    }

    @Override
    public int getNumV() {
        return numV;
    }

    private class Iter
            implements Iterator < Edge > {
        // Data fields
        /** The source vertix for this iterator */
        private int source;

        /** The current index for this iterator */
        private int index;

        // Constructor
        /** Construct an EdgeIterator for a given vertix
         *  @param source - The source vertix
         */
        public Iter(int source) {
            this.source = source;
            index = -1;
            advanceIndex();
        }

        /** Return true if there are more edges
         *  @return true if there are more edges
         */
        public boolean hasNext() {
            return index != getNumV();
        }

        public Edge next() {
            if (index == getNumV()) {
                throw new java.util.NoSuchElementException();
            }
            Edge returnValue = new Edge(source, index);
            advanceIndex();
            return returnValue;
        }


        public void remove() {
            throw new UnsupportedOperationException();
        }

        /** Advance the index to the next edge */
        private void advanceIndex() {
            do {
                index++;
            }
            while (index != getNumV()
                    && 0 == getEdgeValue(source, index));
        }
    }

    private int getEdgeValue(int source, int dest) {
        if (isDirected() | source <= dest) {
            return matrix[source][dest];
        }
        else {
            return matrix[dest][source];
        }
    }



}
