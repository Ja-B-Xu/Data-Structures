 // Name:Jason Xu   
 // Date: 3/15/19

import java.util.*;

public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();    // 2x^3 + -4x + 2
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println("Map:  " + poly.getMap());
      System.out.println("String:  " + poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      System.out.println("-----------");
      Polynomial poly2 = new Polynomial();  // 2x^4 + x^2 + -5x + -3
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1);
      System.out.println("Map:  " + poly2.getMap()); 
      System.out.println("String:  " +poly2.toString());
      evaluateAt = -10.5;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      
      System.out.println("-----------");
      System.out.println("Sum: " + poly.add(poly2));
      System.out.println("Product:  " + poly.multiply(poly2));
      
      /*  Another case:   (x+1)(x-1) -->  x^2 + -1    */
      // System.out.println("===========================");
      // Polynomial poly3 = new Polynomial();   // (x+1)
      // poly3.makeTerm(1, 1);
      // poly3.makeTerm(0, 1);
      // System.out.println("Map:  " + poly3.getMap());
      // System.out.println("String:  " + poly3.toString());
   //       
      // Polynomial poly4 = new Polynomial();    // (x-1)
      // poly4.makeTerm(1, 1);
      // poly4.makeTerm(0, -1);
      // System.out.println("Map:  " + poly4.getMap());
      // System.out.println("String:  " + poly4.toString());
      // System.out.println("Product:  " + poly4.multiply(poly3));   // x^2 + -1 
   //    
   //    /*  testing the one-arg constructor  */
      // System.out.println("==========================="); 
      // Polynomial poly5 = new Polynomial("2x^3 + 4x^2 + 6x^1 + -3");
      // System.out.println("Map:  " + poly5.getMap());  
      // System.out.println(poly5);

   }
}
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public Map<Integer, Integer> getMap();
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   private Map<Integer, Integer> map;
   public Polynomial(){
      map=new TreeMap<Integer,Integer>();
   }
   public void makeTerm(Integer exp, Integer coef){
      map.put(exp,coef);
   }
   public Map<Integer, Integer> getMap(){
      return map;
   }
   public double evaluateAt(double x){
      double val=0;
      for(int power:map.keySet()){
         int coef=map.get(power);
         val+=(coef*Math.pow(x,power));
      }
      return val;
   }
   public Polynomial add(Polynomial other){
      Polynomial poly=new Polynomial();
      for(int x:map.keySet()){
         if(other.getMap().containsKey(x)){
            int coef=map.get(x)+other.getMap().get(x);
            poly.makeTerm(x,coef);
         }
         else
            poly.makeTerm(x,map.get(x));
      }
      for(int x:other.getMap().keySet()){
         if(!poly.getMap().containsKey(x))
            poly.makeTerm(x,other.getMap().get(x));
      }
      return poly;
   }
   public Polynomial multiply(Polynomial other){
      Polynomial poly=new Polynomial();
      for(int pow1:map.keySet()){
         for(int pow2:other.map.keySet()){
            if(!poly.getMap().containsKey(pow1+pow2))
               poly.makeTerm(pow1+pow2,map.get(pow1)*other.getMap().get(pow2));
            else{
               int coef=poly.getMap().get(pow1+pow2);
               coef+=(map.get(pow1)*other.getMap().get(pow2));
               poly.makeTerm(pow1+pow2,coef);
            }
         }
      }
      return poly;
   }
   public String toString(){
      String s="";
      for(int x:map.keySet()){
         String power="";
         String coef="";
         if(x!=0)
            power="x^"+x;
         if(x==1)
            power="x";
         if(map.get(x)!=1)
            coef=""+map.get(x);
         s=coef+power+" + "+s;
      }
      return s.substring(0,s.length()-2);
   }
}


/***************************************  
  ----jGRASP exec: java Polynomial_teacher
 Map:  {0=2, 1=-4, 3=2}
 String:  2x^3 + -4x + 2
 Evaluated at 2.0: 10.0
 -----------
 Map:  {0=-3, 1=-5, 2=1, 4=2}
 String:  2x^4 + x^2 + -5x + -3
 Evaluated at -10.5: -2271.25
 -----------
 Sum: 2x^4 + 2x^3 + x^2 + -9x + -1
 Product:  4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 
  ----jGRASP: operation complete.
 ********************************************/