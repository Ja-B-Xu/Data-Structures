// Name:Jason Xu
// Date:1/9/19

import java.util.*;

public class Postfix
{
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("4 3 ^");
      postfixExp.add("5 !");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("24 3 %");
      postfixExp.add("1 1 1 1 1 + + + + !");
      postfixExp.add("3 4 5 + * 2 - 5 /");
   
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static int eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Stack<Integer> stk=new Stack<Integer>();
      for(String s:postfixParts){
         if(isOperator(s))
            stk.push(eval(stk.pop(),stk.pop(),s));
         else if(s.equals("!")){
            int val=stk.pop();
            int v=val-1;
            while(v>1){
               val=val*v;
               v--;
            }
            stk.push(val);
         }
            
         else
            stk.push(Integer.parseInt(s));
      }
      return stk.pop();
   }
   
   public static int eval(int a, int b, String ch)
   {
      if(ch.equals("+"))
         return a+b;
      else if(ch.equals("-"))
         return b-a;
      else if(ch.equals("*"))
         return a*b;
      else if(ch.equals("/"))
         return b/a;
      else if(ch.equals("%"))
         return b%a;
      else if(ch.equals("^"))
         return (int)Math.pow(b,a);
      else
         return a;
   }
   
   public static boolean isOperator(String op)
   {
      String s="+-*%/^";
      if(s.contains(op))
         return true;
      return false;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/