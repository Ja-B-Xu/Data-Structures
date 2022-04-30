// Name:Jason Xu
// Date:1/7/19

import java.util.*;

public class ParenMatch
{
   public static final String left  = "([{<";
   public static final String right = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
      parenExp.add("5+7");
      parenExp.add("(5+7)");
      parenExp.add(")5+7(");
      parenExp.add("((5+7)*3)");
      parenExp.add("<{5+7}*3>");
      parenExp.add("[(5+7)*]3");
      parenExp.add("(5+7)*3");
      parenExp.add("5+(7*3)");
      parenExp.add("((5+7)*3");
      parenExp.add("[(5+7)*3])");
      parenExp.add("([(5+7)*3]");
   
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
   
   public static boolean checkParen(String exp)
   {
      String[] cArray=exp.split("");
      Stack<String> stk = new Stack<String>();
      for(String s : cArray){
         if(left.contains(s)){   
            stk.push(s);
         }
         if(right.contains(s)){
            if(stk.isEmpty())
               return false;
            String[] l =left.split("");
            String[] r =right.split("");
            int count=0;
            int pos=0;
            int pos2=0;
            for(String type:l){
               if(stk.peek().equals(type))
                  pos=count;
               count++;
            }   
            count=0;
            for(String type:r){
               if(s.equals(type))
                  pos2=count;
               count++;
            } 
            if(pos==pos2)    
               stk.pop(); 
         }
      }
      if(stk.isEmpty())
         return true;
      return false;
   }
}

/*
 Parentheses Match
 5+7	 good!
 (5+7)	 good!
 )5+7(	 BAD
 ((5+7)*3)	 good!
 <{5+7}*3>	 good!
 [(5+7)*]3	 good!
 (5+7)*3	 good!
 5+(7*3)	 good!
 ((5+7)*3	 BAD
 [(5+7]*3)	 BAD
 [(5+7)*3])	 BAD
 ([(5+7)*3]	 BAD
 */
