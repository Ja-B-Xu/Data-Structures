// Name:Jason Xu
// Date:1/16/19

import java.util.*;

public class Infix_Extension
{
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
     /*build your list of Infix expressions here  */
      ArrayList<String> infixExp = new ArrayList<String>();
      infixExp.add("9 % 4 * 5");
      infixExp.add("24 / 12");
      infixExp.add("-24 / -12");
      infixExp.add("12 / 0");
      infixExp.add("2.5 * 2");
      infixExp.add("-2.5 * 2");
      infixExp.add("( 10 + 5 ) - 15 * 3");
      infixExp.add("3 ? 2");
      infixExp.add("( ( }");
      infixExp.add("( ( } ) }");
      infixExp.add("( )");
      infixExp.add("( 3 + 2");
      infixExp.add("3 + 2 ]");
   
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);
         System.out.println(infix + "\t\t\t" + pf);  //Postfix must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> infixParts = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      /* enter your code here  */
      String result = "";
      String left  = "([{<";
      String right = ")]}>";
      String[] l =left.split("");
      String[] r =right.split("");
      int parenNumber=0;
      Stack<String> operators = new Stack<String>();
      for(String s : infixParts){
         if(Postfix.isOperator(s)){
            while(!operators.isEmpty()&&!operators.peek().equals("(")&&isLower(s.charAt(0),operators.peek().charAt(0))){
               result+=operators.pop()+" ";
            }
            operators.push(s);
         }
         else if(left.contains(s)){
            operators.push(s);
            parenNumber++;
         }
         else if(right.contains(s)){
            try{
               int x=0;
               int y=0;
               String popped=operators.pop();
               while(!r[x].equals(s))
                  x++;
               while(!left.contains(popped)){
                  result+=popped + " ";
                  popped=operators.pop();
               }
               while(!l[y].equals(popped))
                  y++;
               if(x!=y){
                  result="ERROR: String does not have matching parentheses";
                  return result;
               }
               parenNumber--;
            }
            catch(EmptyStackException e){
               result="ERROR: String does not have matching parentheses";
               return result;
            }
         }
         else{
            try{
               double i=Double.parseDouble(s);
            }
            catch(NumberFormatException e){
               result="ERROR: String contains an invalid character";
               return result;
            }
            result+= s+" ";
         }
      }
      while(!operators.isEmpty()){
         String op = operators.pop();
         if(left.contains(op)){
            result="ERROR: String does not have matching parentheses";
            return result;
         }
         result+=op+" ";
      }
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