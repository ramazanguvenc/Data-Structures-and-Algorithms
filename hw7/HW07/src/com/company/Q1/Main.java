package com.company.Q1;

import com.company.Edge;
import com.company.myGraph;

public class Main {

    public static void main(String[] args) {
        myGraph graph = new myGraph(10,true);
        graph.insert(new Edge(0,1, (int) Math.round(Math.random()*10)));
        graph.insert(new Edge(1,2, (int) Math.round(Math.random()*10)));
        graph.insert(new Edge(1,3, (int) Math.round(Math.random()*10)));
        graph.insert(new Edge(2,4, (int) Math.round(Math.random()*10)));
        graph.insert(new Edge(2,5, (int) Math.round(Math.random()*10)));
        graph.insert(new Edge(3,6, (int) Math.round(Math.random()*10)));
        graph.insert(new Edge(4,7, (int) Math.round(Math.random()*10)));
        graph.insert(new Edge(5,7, (int) Math.round(Math.random()*10)));
        graph.insert(new Edge(6,7, (int) Math.round(Math.random()*10)));
        graph.insert(new Edge(6,5, (int) Math.round(Math.random()*10)));
        graph.insert(new Edge(7,8, (int) Math.round(Math.random()*10)));
        System.out.println("Is directed ? : "+graph.isDirectedd());
        System.out.println("Is acyclic ? : "+graph.is_acyclic_graph());
        graph.plot_graph();
        graph.shortest_path(0,6);
        graph.shortest_path(1,4);
        graph.shortest_path(3,7);
    }
}
