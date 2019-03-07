package com.company;



public class Edge{

    private int source,dest;
    private int weight;

    public Edge(int s, int d, int w) {
        source = s;
        dest = d;
        weight = w;
    }

    public Edge(int s,int d){
        source = s;
        dest = d;
        weight = 1;
    }

    public int getSource() {
        return source;
    }



    public int getDest() {
        return dest;
    }



    public int getWeight() {
        return weight;
    }

    public boolean equals(Edge edge){
        return this.source == edge.getSource() && this.dest == edge.getDest();
    }




}