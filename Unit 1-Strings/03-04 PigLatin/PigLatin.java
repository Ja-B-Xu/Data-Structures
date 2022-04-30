// Name: Jason Xu
// Date:9/14/2018
 
import java.util.*;
import java.io.*;

public class PigLatin
{
   public static void main(String[] args)
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
   }
   
   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if (s.equals("-1"))
            System.exit(0);
         String p = pig(s);
         System.out.println( p );
      }        
   }
   public static String pig(String s)
   {
      String punct="\".,?!:;";
      String vows="aeiouAEIOU";
      String punctReplace="";
      String punctReplaceBefore="";
      String s1="";
      String s2="";
      Boolean check=true;
      for(int i=0;i<s.length();i++){
         if(Character.isLetter(s.charAt(i))&&check==true){
            punctReplaceBefore=s.substring(0,i);
            s1=s.substring(i,s.length());
            check=false;
         }
      }
      //problem with 2nd part
      check=true;
      for(int i=s1.length()-1;i>-1;i--){
         if(Character.isLetter(s1.charAt(i))&&check==true){
            punctReplace=s1.substring(i+1);
            for(int z=0;z<vows.length();z++){
               if(s1.charAt(0)==vows.charAt(z)){
                  return punctReplaceBefore+s1.substring(0,i+1)+"way"+punctReplace;
               }
            }
            s2=s1.substring(0,i+1);
            check=false;
         }
      }
      if(s1.length()==1){
         if(Character.isLetter(s1.charAt(0))){
            s2=s1;
            for(int z=0;z<vows.length();z++){
               if(s1.charAt(0)==vows.charAt(z)){
                  return punctReplaceBefore+s1.substring(0,1)+"way"+punctReplace;
               }
            }
         }
      }
      boolean vwl;
      int count=0;
      boolean firstQ=false;
      char[] word = s2.toCharArray();
      char[] replace= new char[50];
      if(word.length==0){
         return"****NO VOWEL****";
      }
      while(word[count] != 'a' && word[count] != 'e' && word[count] != 'i' && word[count] != 'o' && word[count] != 'u' && word[count] != 'A' && word[count] != 'E' && word[count] != 'I' && word[count] != 'O' && word[count] != 'U' ){

            if(word[count]=='Y'||word[count]=='y'){
               if(count!=0){
                  break;
               }
            }     
            if(count==word.length-1){
               return"****NO VOWEL****";
            }

            replace[count]=word[count];
            count++;
      }
         if(word[count]=='u'||word[count]=='U'){
            if(word[count-1]=='q'||word[count-1]=='Q'){
               firstQ=true;
            }
         }

      if(firstQ==true){
         return qHandler(word,count,punctReplace,punctReplaceBefore);
      }
      //
      boolean caps = Character.isUpperCase(word[0]);
      char first = Character.toLowerCase(word[0]);
      for (int y=0;y<count;y++){
         for(int x=0;x<word.length-1;x++){
            word[x]=word[x+1];
            
         }
      }
      char[] fnlWord= Arrays.copyOfRange(word,0,word.length-count+1);
      if (caps==true){
         char c = Character.toUpperCase(fnlWord[0]);
         fnlWord[0]=c;  
      }
      String finalStr="";
      for(int x=0;x<fnlWord.length-1;x++){
         finalStr+=fnlWord[x];
      }
      
      finalStr+=first;
      for(int x=1;x<count;x++){
         finalStr+=replace[x];
      }
      finalStr+="ay";
      finalStr=punctReplaceBefore+finalStr;
      finalStr+=punctReplace;
      return finalStr;

   }
   
   public static String qHandler(char[] c,int i,String punctReplace,String punctReplaceBefore){
      char[] c1=Arrays.copyOfRange(c,i+1,c.length+1);
      boolean capsBool=caseHandler(c);
      if(capsBool==true){
         char m=Character.toUpperCase(c1[0]);
         c1[0]=m;
      
      }
      String finalStr="";
      for(int x=0;x<c1.length-1;x++){
         finalStr+=c1[x];
      }
      char ch=Character.toLowerCase(c[0]);
      finalStr+=ch;
      for(int x=1;x<i+1;x++){
         finalStr+=c[x];
      }
      finalStr+="ay";
      finalStr=punctReplaceBefore+finalStr;
      finalStr+=punctReplace;
      return finalStr;
   }  
   
   public static boolean caseHandler(char[] c){
      boolean cap=Character.isUpperCase(c[0]);
      return cap;
   }

   public static void part_2_using_piglatenizeFile()
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/******************************
*  precondition:  both Strings include .txt
*  postcondition:  output a piglatinized .txt file
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
       
      /*  Enter your code here.  Try to preserve lines and paragraphs. ***/
      while(infile.hasNextLine()){
         String word = infile.nextLine();
         String[] word1=word.split(" ");
         for(int x=0;x<word1.length;x++){
            if(word1[x].equals("")||word1.length==0){
               break;
            }
            String pigL=pig(word1[x]);
            outfile.print(pigL+" ");
         }
         outfile.println("");
      }
  
      outfile.close();
      infile.close();
   }
}
