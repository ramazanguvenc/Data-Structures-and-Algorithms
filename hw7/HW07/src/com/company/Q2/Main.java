package com.company.Q2;

import com.company.Edge;
import com.company.myGraph;

public class Main {

    public static void main(String[] args) {
        myGraph graph = new myGraph(15,false);
        for(int i =0;i<14;i++)
            graph.insert(new Edge(i,i+1));
        System.out.println("Is directed ? : "+graph.isDirected());
        System.out.println("Is acyclic ? : "+graph.is_acyclic_graph());
        System.out.println("Is 0 and 1 connected ? : "+graph.is_connected(0,1));
        System.out.println("Is 2 and 5 connected ? : "+graph.is_connected(2,5));
        System.out.println("Is 10 and 11 connected ? : "+graph.is_connected(10,11));
    }
}
