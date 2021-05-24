/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kangaroo;

/**
 *
 * @author Anoire
 */
public class Edge<E> {
    private GraphNode verticeLink;
    private Edge edgeLink;
    private E weight;
    
    public Edge() {
        verticeLink = null;
        weight=null;
        edgeLink = null;
    }
    
    public Edge(GraphNode a, E b, Edge c) {
        verticeLink = a; 
        weight = b;
        edgeLink = c;
    }
    
    public void setWeight(E a) {weight = a;}
    public E getWeight() {return weight;}    
    public void setVerticeLink(GraphNode a) { verticeLink = a;}
    public GraphNode getVerticeLink() { return verticeLink; }
    public void setEdgeLink(Edge a) { edgeLink = a;}
    public Edge getEdgeLink() { return edgeLink; }    
    public String toString() { return " -> " + verticeLink.getVertice() + " : " + weight; }    
}
