/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kangaroo;

/**
 *
 * @author Luqmanul Hakim
 */
public class Kangaroo implements Comparable<Kangaroo>  {
    private int id;
    private point pos;
    private int gender;// 0=male 1=female
    private int foodPouch;
    private int MaxfoodPouch;
    private boolean StopMove;

    public Kangaroo(int id) {
        this.id = id;
        gender=0;
        pos=new point();
//        this.foodPouch = foodPouch;
    }
    public Kangaroo() {
        id=0;
        gender=-1;
        foodPouch=-1;
    }

    public int getId() {
        return id;
    }

    public point getPoint() {
        return pos;
    }
    public boolean getStopMove(){
        return StopMove;
    }

    public int getGender() {
        return gender;
    }

    public int getFoodPouch() {
        return foodPouch;
    }
  
    public void setId(int id) {
        this.id = id;
    }

    public void setPoint(point poin) {
     if(poin.getKangaroo()<poin.getLimit()){
      pos = poin;     
      if(gender==1){
          pos.FemaleIn(this);
          
      }else{
          pos.MaleIn(this);
         
      }
      if(pos.getFood()>=getMaxfoodPouch())
      {
      pos.setFood(pos.getFood()-getMaxfoodPouch());
      setFoodPouch(MaxfoodPouch);
      }
      else{
             setFoodPouch(pos.getFood());
              pos.setFood(0);
              }
     }
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setFoodPouch(int foodPouch) {
        this.foodPouch = foodPouch;
    }

    public void setMaxfoodPouch(int MaxfoodPouch) {
        this.MaxfoodPouch = MaxfoodPouch;
    }

    public point getPos() {
        return pos;
    }

    public int getMaxfoodPouch() {
        return MaxfoodPouch;
    }

    @Override
    public String toString() {
        if(id==0) return "Choose a kangaroo";
        return ""+ id;
    }
    
     
    public void thinkmove(Graph graph)
    { if(gender!=1){
      Graph map=graph;
      GraphNode point=map.PointtoNode(pos);
      point pointtemp;
      point pointnext =pos;
      int currentfood=pos.getFood();
      int foodtemp=0;
      int pouchtemp=0;
      int foodneeded=0;
      int currentFemale=pos.getKangarooF();
      int femaletemp;
      int currentpouch=0;
      Edge EdgeNode=(Edge)point.getEdgeLink();
        while(EdgeNode!=null){
         pointtemp=(point)(EdgeNode.getVerticeLink().getVertice());
         if(pointtemp.getKangaroo()<pointtemp.getLimit()&&pos.isColony()==false)
         {
         foodneeded=(int) (this.foodPouch*0.5+(int)EdgeNode.getWeight());
         pouchtemp=this.foodPouch;
         foodtemp=pointtemp.getFood();
         if(foodtemp>=foodneeded){
             foodtemp-=foodneeded;
             foodneeded=0;
             if(foodtemp>=(this.MaxfoodPouch-this.foodPouch)){
                 foodtemp=foodtemp-(this.MaxfoodPouch-this.foodPouch);
                 pouchtemp=this.MaxfoodPouch;
             }else{
               pouchtemp=this.foodPouch+foodtemp;
               foodtemp=0;
               
             }
         }else{
             foodneeded=foodneeded-foodtemp;
             foodtemp=0;
             pouchtemp=pouchtemp-foodneeded;
         }
          if(pointtemp.isColony()==true){
             pouchtemp-=pointtemp.getKangaroo();
         }
        
         if(pouchtemp>=0)
         {

        
         if(foodtemp>currentfood){
            currentfood=foodtemp;
             pointnext=pointtemp;
            currentpouch=pouchtemp;
         }else if(foodtemp==currentfood){
             if(pouchtemp>currentpouch){
                    currentfood=foodtemp;
                    pointnext=pointtemp;
                   currentpouch=pouchtemp;
                  
               
         }else if(pouchtemp==currentpouch){
             femaletemp=pointtemp.getKangarooF();
             if(femaletemp>currentFemale){
                  currentfood=foodtemp;
                    pointnext=pointtemp;
                   currentpouch=pouchtemp;
                   currentFemale=femaletemp;
             }
         }
             
         }
        
        
         }
        }
         EdgeNode=EdgeNode.getEdgeLink();
       }
//        if(pos.compareTo(pointnext)==0){
//         while(EdgeNode!=null){
//         pointtemp=(point)(EdgeNode.getVerticeLink().getVertice());
//         pouchtemp=(int) (this.foodPouch-(this.foodPouch*0.5+(int)EdgeNode.getWeight()));
//         if(pouchtemp>=0)
//         {
//         if(pointtemp.getFood()-(this.MaxfoodPouch-pouchtemp)>=0)
//         {foodtemp=pointtemp.getFood()-(this.MaxfoodPouch-pouchtemp);
//         pouchtemp=this.MaxfoodPouch;
//         }else{
//             pouchtemp+=pointtemp.getFood();
//             foodtemp=0;
//             
//         }
//         
//         femaletemp=pointtemp.getKangarooF();
//           if(foodtemp==currentfood){
//               if(femaletemp>currentFemale){
//                   pointnext=pointtemp;
//               }
//                     }
//        
//        
//         }EdgeNode=EdgeNode.getEdgeLink();
//         }
//        } 
        if(pos.compareTo(pointnext)==0){
        Edge Edgenode=(Edge)point.getEdgeLink();
        while(Edgenode!=null){
         pointtemp=(point)(Edgenode.getVerticeLink().getVertice());
         if(pointtemp.getKangaroo()<pointtemp.getLimit()&&pos.isColony()==false)
         {
         foodneeded=(int) (this.foodPouch*0.5+(int)Edgenode.getWeight());
         pouchtemp=this.foodPouch;
         foodtemp=pointtemp.getFood();
         if(foodtemp>=foodneeded){
             foodtemp-=foodneeded;
             foodneeded=0;
             if(foodtemp>=(this.MaxfoodPouch-this.foodPouch)){
                 foodtemp=foodtemp-(this.MaxfoodPouch-this.foodPouch);
                 pouchtemp=this.MaxfoodPouch;
             }else{
               pouchtemp=this.foodPouch+foodtemp;
               foodtemp=0;
               
             }
         }else{
             foodneeded=foodneeded-foodtemp;
             foodtemp=0;
             pouchtemp-=foodneeded;
         }
          if(pointtemp.isColony()==true){
             pouchtemp-=pointtemp.getKangaroo();
         }
        
         if(pouchtemp>=0)
         {

            
          femaletemp=pointtemp.getKangarooF();
             if(femaletemp>currentFemale){
                 currentfood=foodtemp;
                 pointnext=pointtemp;
                 currentpouch=pouchtemp;
               }
         
        
        
         }
        }
         Edgenode=Edgenode.getEdgeLink();
       }
    }
    if(pos.compareTo(pointnext)!=0){
       pos.KangOut(this);
       pos=pointnext;
       if(pos.isColony()){
           pos.gift();
       }
       pos.MaleIn(this);
       pos.setFood(currentfood);
       this.foodPouch=currentpouch;
        System.out.println(this.id+" Jump to "+pos.getID()+" food remaining in "+pos.getID()+" is "+pos.getFood()+" Food in pouch "+this.foodPouch);
    }else{
        System.out.println("No solution");
    }
    }else{
                System.out.println("Female are not allowed to jump");
    }
    }

    @Override
    public int compareTo(Kangaroo o) {
        if(id==o.getId())
            return 0;
        else
            return 1;
    }
}

