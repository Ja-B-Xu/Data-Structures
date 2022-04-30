// Name:Jason Xu
// Date: 3/11/19

import java.util.*;
import java.io.*;

public class SetsOfLetters
{
   public static void main(String[] args) throws FileNotFoundException
   {
      String fileName = "declarationLast.txt";
      fillTheSets(fileName);
   }
   
   public static void fillTheSets(String fn) throws FileNotFoundException
   {
      Scanner infile = new Scanner(new File(fn));
      /*  enter your code here  */
      Set<String> allLower=new TreeSet<String>();
      Set<String> allUpper=new TreeSet<String>();
      Set<String> allOther=new TreeSet<String>();
      boolean first=true;
      while(infile.hasNextLine()){
         String line=infile.nextLine();
         String[] wordArray=line.split(" ");
         for(String s:wordArray){
            System.out.print(s+" ");
         }
         System.out.println();
         char[] charArray=line.toCharArray();
         Set<String> lower=new TreeSet<String>();
         Set<String> upper=new TreeSet<String>();
         Set<String> other=new TreeSet<String>();
         for(char c:charArray){
            if(Character.isUpperCase(c))
               upper.add(String.valueOf(c));
            else if(Character.isLetter(c))
               lower.add(String.valueOf(c));
            else if(!String.valueOf(c).equals(" "))
               other.add(String.valueOf(c));
         }
         if(first){
            for(String s:lower)
               allLower.add(s);
            for(String s:upper)
               allUpper.add(s);
            for(String s:other)
               allOther.add(s);
            first=false;
         }
         else{
            Queue<String> lowers=new LinkedList<String>();
            Queue<String> uppers=new LinkedList<String>();
            Queue<String> others=new LinkedList<String>();
            for(String s:allLower){
               if(!lower.contains(s))
                  lowers.add(s);
            }
            for(String s:allUpper){
               if(!upper.contains(s))
                  uppers.add(s);
            }
            for(String s:allOther){
               if(!other.contains(s))
                  others.add(s);
            }
            while(!lowers.isEmpty())
               allLower.remove(lowers.remove());
            while(!uppers.isEmpty())
               allUpper.remove(uppers.remove());
            while(!others.isEmpty())
               allOther.remove(others.remove());
         }
         System.out.println("Lower Case: "+lower);
         System.out.println("Upper Case: "+upper);
         System.out.println("Other: "+other);
         //
         System.out.println();
      }
      System.out.println("Common Lower Case: "+allLower);
      System.out.println("Common Upper Case: "+allUpper);
      System.out.println("Common Other: "+allOther);
   }
}

/***********************************
  ----jGRASP exec: java SetsOfLetters_teacher
 
 We, therefore, the Representatives of the united States of 
 Lower Case: [a, d, e, f, h, i, n, o, p, r, s, t, u, v]
 Upper Case: [R, S, W]
 Other: [ , ,]
 
 America, in General Congress, Assembled, appealing to the 
 Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, p, r, s, t]
 Upper Case: [A, C, G]
 Other: [ , ,]
 
 Supreme Judge of the world for the rectitude of our intentions,
 Lower Case: [c, d, e, f, g, h, i, l, m, n, o, p, r, s, t, u, w]
 Upper Case: [J, S]
 Other: [ , ,]
 
 do, in the Name, and by the Authority of the good People of 
 Lower Case: [a, b, d, e, f, g, h, i, l, m, n, o, p, r, t, u, y]
 Upper Case: [A, N, P]
 Other: [ , ,]
 
 these Colonies, solemnly publish and declare, That these United 
 Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, u, y]
 Upper Case: [C, T, U]
 Other: [ , ,]
 
 Colonies are, and of Right ought to be Free and Independent 
 Lower Case: [a, b, d, e, f, g, h, i, l, n, o, p, r, s, t, u]
 Upper Case: [C, F, I, R]
 Other: [ , ,]
 
 States; that they are Absolved from all Allegiance to the 
 Lower Case: [a, b, c, d, e, f, g, h, i, l, m, n, o, r, s, t, v, y]
 Upper Case: [A, S]
 Other: [ , ;]
 
 British Crown, and that all political connection between them 
 Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, w]
 Upper Case: [B, C]
 Other: [ , ,]
 
 and the State of Great Britain, is and ought to be totally 
 Lower Case: [a, b, d, e, f, g, h, i, l, n, o, r, s, t, u, y]
 Upper Case: [B, G, S]
 Other: [ , ,]
 
 dissolved; and that as Free and Independent States, they have 
 Lower Case: [a, d, e, h, i, l, n, o, p, r, s, t, v, y]
 Upper Case: [F, I, S]
 Other: [ , ,, ;]
 
 full Power to levy War, conclude Peace, contract Alliances, 
 Lower Case: [a, c, d, e, f, i, l, n, o, r, s, t, u, v, w, y]
 Upper Case: [A, P, W]
 Other: [ , ,]
 
 establish Commerce, and to do all other Acts and Things which 
 Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, r, s, t, w]
 Upper Case: [A, C, T]
 Other: [ , ,]
 
 Independent States may of right do. And for the support of this 
 Lower Case: [a, d, e, f, g, h, i, m, n, o, p, r, s, t, u, y]
 Upper Case: [A, I, S]
 Other: [ , .]
 
 Declaration, with a firm reliance on the protection of divine 
 Lower Case: [a, c, d, e, f, h, i, l, m, n, o, p, r, t, v, w]
 Upper Case: [D]
 Other: [ , ,]
 
 Providence, we mutually pledge to each other our Lives, our 
 Lower Case: [a, c, d, e, g, h, i, l, m, n, o, p, r, s, t, u, v, w, y]
 Upper Case: [L, P]
 Other: [ , ,]
 
 Fortunes and our sacred Honor.
 Lower Case: [a, c, d, e, n, o, r, s, t, u]
 Upper Case: [F, H]
 Other: [ , .]
 
 Common Lower Case: [d, e, n, o, r, t]
 Common Upper Case: []
 Common Other: [ ]
  ----jGRASP: operation complete.
  ************************************/