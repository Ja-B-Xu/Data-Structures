// Name:Jason Xu
// Date:1/11/19

import java.util.*;

public class Infix
{
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
     /*build your list of Infix expressions here  */
      ArrayList<String> infixExp = new ArrayList<String>();
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("( -5 + 15 ) - 6 / 3");
      infixExp.add("( 3 + 4 ) * ( 5 + 6 )");
      infixExp.add("( 3 * ( 4 + 5 ) - 2 ) / 5");
      infixExp.add("8 + -1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
      infixExp.add("4 * 3 ^ 3");
   
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + Postfix.eval(pf));  //Postfix must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> infixParts = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      /* enter your code here  */
      String result = "";
      int parenNumber=0;
      Stack<String> operators = new Stack<String>();
      for(String s : infixParts){
         if(Postfix.isOperator(s)){
            while(!operators.isEmpty()&&!operators.peek().equals("(")&&isLower(s.charAt(0),operators.peek().charAt(0))){
               result+=operators.pop()+" ";
            }
            operators.push(s);
         }
         else if(s.equals("(")){
            operators.push(s);
            parenNumber++;
         }
         else if(s.equals(")")){
            String popped=operators.pop();
            while(!popped.equals("(")){
            result+=popped + " ";
               popped=operators.pop();
            }
            parenNumber--;
         }
         else{
            result+= s+" ";
         }
      }
      while(!operators.isEmpty())
         result+=operators.pop()+" ";
      return result;
   }
   
	//returns true if c1 has lower or equal precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      int c1val, c2val;
      if((""+c1).equals("+")||(""+c1).equals("-"))
         c1val=0;
      else if((""+c1).equals("*")||(""+c1).equals("/"))
         c1val=1;
      else if((""+c1).equals("^"))
         c1val=2;
      else
         c1val=3;
      if((""+c2).equals("+")||(""+c2).equals("-"))
         c2val=0;
      else if((""+c2).equals("*")||(""+c2).equals("/"))
         c2val=1;
      else if((""+c2).equals("^"))
         c2val=2;
      else
         c2val=3;
      if(c2val>c1val)
         return true;
      return false;
   }
}
	
/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * +			23
 3 * 4 + 5			3 4 * 5 +			17
 ( -5 + 15 ) - 6 / 3			-5 15 + 6 3 / -			8
 ( 3 + 4 ) * ( 5 + 6 )			3 4 + 5 6 + *			77
 ( 3 * ( 4 + 5 ) - 2 ) / 5			3 4 5 + * 2 - 5 /			5
 8 + -1 * 2 - 9 / 3			8 -1 2 * + 9 3 / -			3
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78
 
***********************************************/