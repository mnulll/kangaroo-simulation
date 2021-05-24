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
import java.util.ArrayList;

public class Graph<V extends Comparable<V>, E> {
    private GraphNode<point,point> head;
    
    public Graph() {
        head = null;
    }

    public boolean isEmpty() {
        return (head==null);
    }
    
    public int getSize() {
        int count=0;    
        GraphNode currentNode = head;
        while (currentNode != null) {
            currentNode = currentNode.getVerticeLink();
            count++;
        }
        return count;
    }
    
    public void clear() {
        head=null;
    }
    public GraphNode PointtoNode(point a){
        GraphNode currentNode =head;
        while(currentNode != null){
            if(a.compareTo((point)currentNode.getVertice())==0)
                return currentNode;
            currentNode=currentNode.getVerticeLink();
        }
        return null;
    }
    
    public void showGraph() {
        GraphNode currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.toString());
            Edge edgeNode = (Edge) currentNode.getEdgeLink();
            while (edgeNode != null) {
                  System.out.print(edgeNode.toString());
                  edgeNode = edgeNode.getEdgeLink();
            }
            System.out.println();
            currentNode = currentNode.getVerticeLink();
        }
    }
    public GraphNode getVerticeIndex(int a){
        int count=0;    
        GraphNode currentNode = head;
        if(a==0) return head;
        while (currentNode != null) {
            if(a==count){
                return currentNode;
            }
            currentNode = currentNode.getVerticeLink();
            count++;
        }
        return null;
    }
    public GraphNode hasVertice(V a) {
        GraphNode currentNode = head;        
        if (isEmpty()) {
            return null;
        }
        else {        
            while (currentNode!= null) {
                if (a.compareTo( (V) currentNode.getVertice())==0) {
                    return currentNode;
                }
                currentNode = currentNode.getVerticeLink();
            }
        }
        return null;
    }
    public void replaceVertice(V a){
        GraphNode current=head;
        if(isEmpty()){
            System.out.println("nothing to replace");
        }else{
            while(current!=null){
                System.out.println(a.compareTo((V)current.getVerticeLink().getVertice()));
                if(a.compareTo((V)current.getVerticeLink().getVertice())==0){
                    GraphNode temp=current.getVerticeLink();
                    GraphNode New=new GraphNode(a,temp.getVerticeLink());
                    current.setVerticeLink(New);
                    New.setEdgeLink(temp.getEdgeLink());
                    break;
                }
                current=current.getVerticeLink();
            }
            System.out.println("nothing to replace");
        }
    }
    
    public void addVertice(V a) {
        GraphNode newNode =new GraphNode(a, null);
        GraphNode currentNode = head;        
        if (head==null) {
            head = newNode;
        }
        else {        
            while (currentNode.getVerticeLink()!= null) 
                currentNode = currentNode.getVerticeLink();            
            currentNode.setVerticeLink(newNode);
        }
    }
    public void removeVertice(V a){
        GraphNode node=new GraphNode(a,null);
        GraphNode current=head;
        GraphNode current2=head;
        while(current2!=null){
//                        System.out.println(current2);
//                        System.out.println("from "+current2.getVertice()+" to "+a);
//                        System.out.println(isEdge((V)current2.getVertice(),a));
                        deleteEdge((V)current2.getVertice(),a);
//                        else{System.out.println("not found link");}
                        current2=current2.getVerticeLink();
                    }
        while (current!= null) {
                if (a.compareTo( (V) current.getVerticeLink().getVertice())==0) {
//                    System.out.println("removing "+ a);
                    GraphNode temp=current.getVerticeLink();
                    current.setVerticeLink(temp.getVerticeLink());  
//                    System.out.println(a+" removed");
                    break;
                }
//                System.out.println("\ncurrent: "+current);
                current = current.getVerticeLink();
            }
//        System.out.println("remove finish");
       
    }
    
    public void markVertice(V a) {
        if (hasVertice(a)!=null) {
            GraphNode currentNode = head;       
            while (currentNode!= null) {
                if (a.compareTo( (V) currentNode.getVertice())==0) {
                    currentNode.setMarked(true);
                    break;
                }
                else
                    currentNode = currentNode.getVerticeLink();
            }        
        }
    }
    
    public boolean isMarked(V a) {
        if (hasVertice(a)!=null) {
            GraphNode currentNode = head;       
            while (currentNode!= null) {
                if (a.compareTo( (V) currentNode.getVertice())==0) 
                    return currentNode.getMarked();                    
                else
                    currentNode = currentNode.getVerticeLink();
            }   
        }
        return false;
    }
        
    public boolean addEge(V from, V to, E weight) {
        if (hasVertice(from)==null || hasVertice(to)==null||isEdge(from,to)||from.compareTo(to)==0){
            return false;}
        else {
            GraphNode currentNode = head;       
            while (currentNode!= null) {
                
               if (from.compareTo( (V) currentNode.getVertice())==0) {                
                    GraphNode temp = hasVertice(to);                    
                    Edge newNode = new Edge(temp, weight, null);
                    Edge edgeNode = (Edge) currentNode.getEdgeLink();
                    if (edgeNode==null)
                        currentNode.setEdgeLink(newNode);
                    else {
                        while(edgeNode.getEdgeLink()!=null)
                            edgeNode = edgeNode.getEdgeLink();
                        edgeNode.setEdgeLink(newNode);
                    }
                    return true;
                }
                else
                   currentNode = currentNode.getVerticeLink();
            }
        }
        return false;
    }
    
    public boolean deleteEdge(V from, V to) {
        if (!isEdge(from, to))
            return false;
        else {
            GraphNode currentNode = head;       
            while (currentNode!= null) {
                if (from.compareTo( (V) currentNode.getVertice())==0) {     
                    GraphNode temp = hasVertice(to); 
                    Edge previousNode =null;
                    Edge edgeNode = (Edge) currentNode.getEdgeLink();
                    while(edgeNode!=null) {
                        if (edgeNode.getVerticeLink()==temp) {
                            if (previousNode==null)
                                currentNode.setEdgeLink(edgeNode.getEdgeLink());
                            else 
                                previousNode.setEdgeLink(edgeNode.getEdgeLink());
                            return true;
                        }
                        else {
                            previousNode = edgeNode;
                            edgeNode = edgeNode.getEdgeLink();
                        }    
                    }
                }    
                else
                   currentNode = currentNode.getVerticeLink();
            }
        }
        return false;
    }
    
    public boolean isEdge(V from, V to) {
        if (hasVertice(from)==null || hasVertice(to)==null)
            return false;
        else {
            GraphNode currentNode = head;       
            while (currentNode!= null) {
//                System.out.println("check hey");//debug
//                System.out.println("\nin is egde");
//                System.out.println("current : "+currentNode.getVertice()+" compare to "+ from);
                if (from.compareTo( (V) currentNode.getVertice())==0) {     
                    Edge edgeNode = (Edge) currentNode.getEdgeLink();
                    if (edgeNode==null)
                        return false;
                    else {
                        while(edgeNode!=null) {
//                            System.out.println("check target");//debug
                            if (((V)edgeNode.getVerticeLink().getVertice()).compareTo(to)==0)
                                return true;
                            
                            edgeNode = edgeNode.getEdgeLink();
                        }return false;
                    }
                }
                else
                   currentNode = currentNode.getVerticeLink();
            }
        }
        return false;
    }
    
    public E getWeight(V from, V to) {
         if (isEdge(from, to)) {
            GraphNode currentNode = head;       
            while (currentNode!= null) {
                if (from.compareTo( (V) currentNode.getVertice())==0) {     
                    GraphNode temp = hasVertice(to); 
                    Edge edgeNode = (Edge) currentNode.getEdgeLink();
                    while(edgeNode!=null) {
                        if (edgeNode.getVerticeLink()==temp)
                            return (E) edgeNode.getWeight();
                        edgeNode = edgeNode.getEdgeLink();
                    }
                }    
                else
                   currentNode = currentNode.getVerticeLink();
            }
         }
         return null;    
    }
    public void setWeight(V from, V to,E weights) {
         if (isEdge(from, to)) {
            GraphNode currentNode = head;       
            while (currentNode!= null) {
                System.out.println("test");
                if (from.compareTo( (V) currentNode.getVertice())==0) {     
                    GraphNode temp = hasVertice(to); 
                    Edge edgeNode = (Edge) currentNode.getEdgeLink();
                    while(edgeNode!=null) {
                        if (edgeNode.getVerticeLink()==temp){
                            System.out.println("weight setted");
                            edgeNode.setWeight(weights);
                        break;}
                        else
                            edgeNode = edgeNode.getEdgeLink();
                    }break;
                }    
                else
                   currentNode = currentNode.getVerticeLink();
            }
         } 
    }
    
    public ArrayList getAdjacent(V a) {
        ArrayList<V> array = new ArrayList();
        if (hasVertice(a)!=null) {
            GraphNode currentNode = head;       
            while (currentNode!= null) {
                if (a.compareTo( (V) currentNode.getVertice())==0) {  
                    Edge edgeNode = (Edge) currentNode.getEdgeLink();
                    while (edgeNode!=null) {
                        array.add((V)edgeNode.getVerticeLink().getVertice());
                        edgeNode = edgeNode.getEdgeLink();
                    }
                    break;
                }  
                else
                    currentNode = currentNode.getVerticeLink();
            }   
        }       
        return array;
    }
    public Object[] VtoArray(){
        ArrayList<V> a=new ArrayList();
        GraphNode current=head;
        int i=0;
        while(current!=null){
            a.add((V) current.getVertice());
            current=current.getVerticeLink();
        }
        return a.toArray();
    }
}
