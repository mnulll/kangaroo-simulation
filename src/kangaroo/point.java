/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kangaroo;

import java.util.ArrayList;

/**
 *
 * @author Anoire
 */
import java.util.ArrayList;
public class point implements Comparable<point>{
    //gui
     private int type=0;
      int x;
      int y;
     private int threshold;
     private boolean colony=false;
     ArrayList<Kangaroo> kang=new ArrayList<>();
      
     //point stuff
     protected int ID;
     private int food=1,limit=1,kangarooF,kangarooM,kangaroo;
//     private ArrayList<path> paths =new ArrayList(); 

   
     
     public point(int type,int x,int y){
       this.type=type;
       this.x=x;
       this.y=y;
       threshold=4;
       limit=5;
     }
     public point(){
         type=-1;
         x=-1;
         y=-1;
         ID=0;
     }
//   public void addPath(point dest){
//       paths.add(new path(dest));
//   }
        public int getType() {return type;}
        public int getX() {return x;}
        public int getY() {return y;}
        public int getID() {return ID;}
        public int getFood() {return food;}
        public int getLimit() {return limit;}

    public int getThreshold() {
        return threshold;
    }

    public boolean isColony() {
        return colony;
    }
        
        
//        public ArrayList<path> getPaths() {return paths;}
        
    public void setType(int type) {this.type = type;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setID(int id) {ID = id;}
    public void setFood(int Food) {food=Food;}    
    public void setLimit(int limit) {this.limit = limit;}

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
    
//    public void setPaths(ArrayList<path> paths) {this.paths = paths;}
    
        public void FemaleIn(Kangaroo a) {
       kangarooF++;
       kangaroo++;
       kang.add(a);
        if(kangaroo>=threshold){
           colony=true;
       }
        
     
    }public void FemaleOut(Kangaroo a){
        for(int i=0;i<kang.size();i++){
            if(a.compareTo((Kangaroo) kang.get(i))==0){
                kang.remove(kang.get(i));
            }
        }
        kangarooF--;
        kangaroo--;
    }
        public void MaleIn(Kangaroo a) {
       kangarooM++;
       kangaroo++;
       kang.add(a);
       if(kangaroo>=threshold){
           colony=true;
       }
    }
    public void KangOut(Kangaroo a){
        for(int i=0;i<kang.size();i++){
            if(a.compareTo((Kangaroo) kang.get(i))==0){
                kang.remove(kang.get(i));
            }
        }if(a.getGender()==0){
        kangarooM--;}
        else{kangarooF--;}
        kangaroo--;
    }
    @Override
    public String toString() {
        if(ID==0) return "Choose a point";
        return ""+ ID+" kangaroo "+ kang.toString()+" food "+ food;
    }

    @Override
    public int compareTo(point o) {
       if(x==o.x&&y==o.y)return 0;
       return -1;
    }

    public int getKangarooF() {
        return kangarooF;
    }

    public int getKangarooM() {
        return kangarooM;
    }

    public int getKangaroo() {
        return kangaroo;
    } 
    
    public void gift(){
        for(int i=0;i<kang.size();i++){
            if(((Kangaroo)kang.get(i)).getFoodPouch()<((Kangaroo)kang.get(i)).getMaxfoodPouch()){
          ((Kangaroo)kang.get(i)).setFoodPouch(((Kangaroo)kang.get(i)).getFoodPouch()+1);
            }
        }
    }public void show(){
        for(int i=0;i<kang.size();i++){
            System.out.println(((Kangaroo)kang.get(i)).getId());
        }
    }
    
    
 }
