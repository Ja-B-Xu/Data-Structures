//Name:Jason Xu    
//Date:9/21/2018

import java.util.*;
public class StringCoderDriver
{
   public static void main(String[] args)
   {
      StringCoder sc = new StringCoder("sixtyzipperswerequicklypickedfromthewovenjutebag"); 
      StringPart[] sp = sc.encodeString("overeager");
      for(int i=0; i<sp.length; i++)
         System.out.print(sp[i]+", ");
      System.out.println();
      String s = sc.decodeString(sp);
      System.out.println(s);
         
      StringPart[] sp2 = sc.encodeString("kippers");
      for(int i=0; i<sp2.length; i++)
         System.out.print(sp2[i]+", ");
      System.out.println();
      String s2 = sc.decodeString(sp2);
      System.out.println(s2);
      	
      StringPart[] sp3 = sc.encodeString("colonials");
      for(int i=0; i<sp3.length; i++)
         System.out.print(sp3[i]+", ");
      System.out.println();
      String s3 = sc.decodeString(sp3);
      System.out.println(s3);
      	
      StringPart[] sp4 = sc.encodeString("werewolf");
      for(int i=0; i<sp4.length; i++)
         System.out.print(sp4[i]+", ");
      System.out.println();
      String s4 = sc.decodeString(sp4);
      System.out.println(s4);
      
      StringPart[] sp5 = sc.encodeString("abcdefghijklmnopqrstuvwxyz");
      for(int i=0; i<sp5.length; i++)
         System.out.print(sp5[i]+", ");
      System.out.println();
      String s5 = sc.decodeString(sp5);
      System.out.println(s5);
   }
}

  
class StringCoder
{
   private String masterString;
   /** @param master the master string for the StringCoder
   * Precondition: the master string contains all the letters of the alphabet
   */
   public StringCoder(String master)
   {
      masterString = master; 
   }
   
   /** @param parts an array of string parts that are valid in the master string
   * Precondition: parts.size() > 0
   * @return the string obtained by concatenating the parts of the master string
   */
      //PART A:																		// =8
   public String decodeString(StringPart[] parts)
   {
       String s="";
       for(int x=0;x<parts.length;x++){
            s+=masterString.substring(parts[x].getStart(),parts[x].getStart()+parts[x].getLength());
       }
       return s;
                                                    // 1
   }
   
   
   /** @param str the string to encode using the master string
   * Precondition: all of the characters in str appear in the master string;
   * str.length() > 0
   * @return a string part in the master string that matches the beginning of str.
   * The returned string part has length at least 1.
   */
   private StringPart findPart(String str)
   { 
      int x = 0;
      String s = str.substring(0, x);
      while( masterString.contains(s) )
      {
         x++;
         if(x > str.length())
            break;
         s = str.substring(0, x);
      }     
      s = str.substring(0, x - 1);
      int start = masterString.indexOf(s);
      StringPart sp = new StringPart(start, s.length());
      return sp;
   }
   
   
   /** @param word the string to be encoded
   * Precondition: all of the characters in word appear in the master string;
   * word.length() > 0
   * @return an ArrayList of string parts of the master string that can be combined
   * to create word
   */
   // Part B																		// =12
   public StringPart[] encodeString(String word)
   {
      StringPart[] temp=new StringPart[100];
      StringPart part;
      int len=0;
      int x=0;
      while(word.length()>0){
         part=findPart(word);
         temp[x]=part;
         x++;
         len=part.getLength();
         word=word.substring(len,word.length());
      }
      StringPart[] arr=Arrays.copyOf(temp,x);
      return arr;
   }
      
      
      
      
      /*StringPart[] parts=new StringPart[word.length()];
      for(int x=0;x<word.length();x++){
         StringPart s=findPart(word.substring(x,x+1));
         parts[x]=s;
      }
      return parts;*/
      
   
}

class StringPart
{
   private int start;
   private int length;
   /** @param start the starting position of the substring in a master string
   * @param length the length of the substring in a master string
   */
   public StringPart(int start, int length)
   {
      this.start = start;
      this.length = length;
   }
   
   /** @return the starting position of the substring in a master string
   */
   public int getStart()
   { 
      return start;
   }
   
   /** @return the length of the substring in a master string
   */
   public int getLength()
   { 
      return length;
   }
   public String toString()
   {
      return "(" + start + ", " + length + ")";
   }
}

