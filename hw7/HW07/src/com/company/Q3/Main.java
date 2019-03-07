package com.company.Q3;

import com.company.BreadthFirstSearch;
import com.company.DepthFirstSearch;
import com.company.Edge;
import com.company.myGraph;

public class Main {

    public static void main(String[] args) {
        myGraph graph = new myGraph(10,false);
        graph.insert(new Edge(0,1));
        graph.insert(new Edge(0,2));
        graph.insert(new Edge(0,3));
        graph.insert(new Edge(1,4));
        graph.insert(new Edge(4,0));
        graph.insert(new Edge(3,2));
        graph.insert(new Edge(4,5));
        graph.insert(new Edge(1,6));
        graph.insert(new Edge(6,4));
        graph.insert(new Edge(6,7));
        graph.insert(new Edge(7,8));
        graph.insert(new Edge(8,9));
        System.out.println("Is directed ? : "+graph.isDirected());
        System.out.println("Is acyclic ? : "+graph.is_acyclic_graph());
        System.out.println("\n---------------");
        System.out.println("DFS yapiliyor");
        DepthFirstSearch dfs = new DepthFirstSearch(graph);
        System.out.println("\n---------------");
        System.out.println("\nBreadth First Search yapilyior\n");
        BreadthFirstSearch.breadthFirstSearch(graph,0);
        System.out.println("\n---------------");
    }
}
