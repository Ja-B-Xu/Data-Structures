// Name:Jason Xu
// Date: 3/13/18

import java.io.*;
import java.util.*;

public class Dictionary //Extension
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      PrintStream printer=System.out;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
      
      /* Extension starts here
      /
      /
      /
      /
      */
      
      Map<String, Set<String>> eWords=new TreeMap<String,Set<String>>();
      Map<String, Set<String>> sWords=new TreeMap<String,Set<String>>();
      Scanner reader=null;
      try{
         reader=new Scanner(new File("dictionaryOutput.txt"));
      }
      catch(Exception E){
      }
      readDict(reader, eWords, sWords);
      
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
      System.setOut(printer);
      System.out.println("1: English to Spanish\n2: Spanish to English");
      Scanner input=new Scanner(System.in);
      String inp=input.next();
      System.out.println("What word?");
      String inputWord=input.next();
      if(Integer.parseInt(inp)==1){
         lookup(inputWord, eWords);
      }
      else if(Integer.parseInt(inp)==2){
         lookup(inputWord, sWords);
         System.out.println(sWords.keySet());
      }
      else
         System.out.println("Error: invalid input");
   }
   
   private static void lookup(String word, Map<String, Set<String>> map){
      Set<String> defs=map.get(word);
      if(defs!=null)
         System.out.println("Translations: "+defs);
      else
         System.out.println("Sorry, that word is not in the dictionary.");
   }
   
   public static void readDict(Scanner sc, Map<String, Set<String>> eng, Map<String, Set<String>> span){
      boolean isEng=true;
      while(sc.hasNextLine()){
         String line=sc.nextLine();
         if(!line.equals("ENGLISH TO SPANISH")){
            if(isEng){
               if(!line.equals("SPANISH TO ENGLISH")){
                  read(eng,line);
               }
               else
                  isEng=false;
            }
            else{
               read(span,line);
            }
         }
      }
   }
   
   private static void read(Map<String, Set<String>> map, String s){
      s=s.trim();
      s=s.replace("[","");
      s=s.replace("]","");
      s=s.replace(",","");
      String[] sArray=s.split(" ");
      Set<String> defs=new TreeSet<String>();
      for(int pos=1;pos<sArray.length;pos++)
         defs.add(sArray[pos]);
      map.put(sArray[0],defs);
   
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String, Set<String>>map=new TreeMap<String, Set<String>>();
      while(infile.hasNextLine()){
         String s1=infile.nextLine();
         if(!infile.hasNextLine())
            throw new NoSuchElementException("Wrong number of terms in the txt file");
         add(map,s1,infile.nextLine());
      }
      return map;
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   {
      if(dictionary.get(word)==null)
         dictionary.put(word,new TreeSet<String>());
      dictionary.get(word).add(translation);
   }
   
   public static void display(Map<String, Set<String>> m)
   {
      Set<String> kSet=m.keySet();
      for(String word:kSet){
         System.out.print("\t"+word+" ");
         System.out.println(m.get(word));
      }
      System.out.println();
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      Map<String, Set<String>> rev=new TreeMap<String, Set<String>>();
      for(String word:dictionary.keySet()){
         for(String def:dictionary.get(word)){
            if(rev.get(def)==null)
               rev.put(def,new TreeSet<String>());
            rev.get(def).add(word);
         }
      }
      return rev;
   }
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/