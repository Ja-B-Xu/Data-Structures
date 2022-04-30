// Name:Jason Xu
//Date:9/20/2018
import java.util.Scanner;
import java.io.File;
import java.text.DecimalFormat;

public class Cemetery
{
   public static void main (String [] args)
   {
      File file = new File("cemetery.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      //for testing only
      //for (int i = 0; i < cemetery.length; i++) 
         //System.out.println(cemetery[i]);
      System.out.println("In the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge());     
   }
   
   /* Counts and returns the number of entries in File f.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f)
   {
      int x=0;
      Scanner infile=null;
      try{
         infile=new Scanner(f);
      }
      catch(Exception e){;
         System.out.println("File does not exist");
         System.exit(0);
      }
      while(infile.hasNextLine()){
         x++;
         String p=infile.nextLine();
      }
      return x;
   }

   /* Reads the data.
      Fills the array with Person objects.
      Uses a try-catch block.   
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray (File f, int num)
   {
      Scanner infile=null;
      try{
         infile=new Scanner(f);
      }
      catch(Exception e){
         System.out.println("File does not exist");
         System.exit(0);
      }
      Person[] list=new Person[num];
      for(int x=0;x<num;x++){
         list[x]=makeObjects(infile.nextLine());
      }
      return list;
   }
   
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
   */
   private static Person makeObjects(String entry)//ok
   {
      
      String nameVal=entry.substring(0,25);
      nameVal=nameVal.trim();
      String dateVal=entry.substring(25,37);
      dateVal=dateVal.trim();
      String ageVal=entry.substring(37,42);
      ageVal=ageVal.trim();
      Person p=new Person(nameVal,dateVal,ageVal);
      return p;
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      double min=100;
      int minnum=0;
      for(int x=0;x<arr.length;x++){
         if(arr[x].getAge()<min){
            minnum=x;
            min=arr[x].getAge();
         }
      
      }
      return minnum;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      double max=0;
      int maxnum=0;
      for(int x=0;x<arr.length;x++){
         if(arr[x].getAge()>max){
            max=arr[x].getAge();
            maxnum=x;
         }
      }
      return maxnum;
   
   }        
} 

class Person
{
   /* private fields  */
   String myName;
   String myDate;
   String myAgeStr;
   double myAge;
   private DecimalFormat df = new DecimalFormat("0.000");
      
   /* a three-arg constructor  */
   public Person(String name, String date, String age){
      myName=name;
      myDate=date;
      myAgeStr=age;
   }
   
   /* any necessary accessor methods */
   public String getName(){
      return myName;
   }
   public double getAge(){
      myAge=calculateAge(myAgeStr);
      return myAge;
   }
   
   public double calculateAge(String a)
   {
      double val;

      if(a.charAt(a.length()-1)=='w'){
         double numWeeks=52.0;
         String num=a.substring(0,a.length()-1);
         val=Double.valueOf(num);
         val=val/numWeeks;
         num=df.format(val);
         val=Double.valueOf(num);
      }
      else if(a.charAt(a.length()-1)=='d'){
         double numDays=365.0;
         String num=a.substring(0,a.length()-1);
         val=Double.valueOf(num);
         val=val/numDays;
         num=df.format(val);
         val=Double.valueOf(num);
      }
      else{
         String num=a;
         val=Double.valueOf(num);
      }
      return val;
   }
}