// Name:Jason Xu
// Date:1/16/19

import java.io.*;
import java.util.*;

public class SongQueue
{
   private static Scanner keyboard;  //use this global Scanner for this lab only
   private static Queue<String> songQueue;
   
   public static void main(String[] args) throws Exception
   {
      keyboard = new Scanner(System.in);
      songQueue = readPlayList();
      printSongList();
      
      String prompt = "Add song (A), Play song (P), Delete song (D), Quit (Q):  ";
      String str = "";
      do{      
         System.out.print(prompt);
         str = keyboard.nextLine().toUpperCase();
         processRequest( str );
         System.out.println();
      }while(!str.equals("Q"));
   }
   
   public static Queue<String> readPlayList() throws IOException
   {
      Scanner infile = new Scanner (new File("songs.txt"));  
      Queue<String> q =new LinkedList<String>();
      while(infile.hasNextLine()){
         String s = infile.nextLine();
         String[] sArray = s.split("");
         int x=0;
         String name="";
         while(!sArray[x].equals("-")){
            name+=sArray[x];
            x++;
         }  
         name=name.substring(0,name.length()-1);
         q.add(name);
      }
      songQueue=q;
      return q;
   }
   
   public static void processRequest(String str)
   {
      if(str.equals("A")){
         Scanner input=new Scanner(System.in);
         System.out.println("Song to add?");
         String s=input.nextLine();
         songQueue.add(s);
         
      }
      else if(str.equals("P")){
         if(songQueue.isEmpty()){
            System.out.println("Empty queue!");
            printSongList();
            return;
         }
         else
            System.out.println("Now playing: "+songQueue.remove());
      }
      else if(str.equals("D")){
         Queue<String> q =new LinkedList<String>();
         Scanner input=new Scanner(System.in);
         System.out.println("Delete which song (exact match)?");
         String s=input.nextLine();
         boolean contain=false;
         while(!songQueue.isEmpty()){
            String removed=songQueue.remove();
            if(!removed.equals(s))
               q.add(removed);
            else
               contain=true;
         }
         if(contain==false)
            System.out.println("Error: Song not in list.");
         songQueue=q;
         
      }
      else if(str.equals("Q")){
         System.out.println("No more music for you today. Goodbye!"); 
         return;
      }
      printSongList();
   }

   public static void printSongList()
   {
      String pString="Your music queue: [";
      Queue<String> q = new LinkedList<String>();
      while(!songQueue.isEmpty()){
         String s=songQueue.remove();
         pString+=s+", ";
         q.add(s);
      }
      songQueue=q;
      pString=pString.substring(0,pString.length()-2);
      if(pString.equals("Your music queue:"))
         pString+=" [";
      pString+="]";
      System.out.println(pString);
   }
   
   public static Queue<String> getQueue()
   {
      return songQueue;
   }
}

/*********************************************

 Your music queue: [Really Love, Uptown Funk, Thinking Out Loud, Alright, Traveller, Alright]
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Really Love
 Your music queue: [Uptown Funk, Thinking Out Loud, Alright, Traveller, Alright]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Uptown Funk
 Your music queue: [Thinking Out Loud, Alright, Traveller, Alright]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  d
 Your music queue: [Thinking Out Loud, Alright, Traveller, Alright]
 Delete which song (exact match)?  Alright
 Your music queue: [Thinking Out Loud, Traveller]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  d
 Your music queue: [Thinking Out Loud, Traveller]
 Delete which song (exact match)?  xxx
 Error:  Song not in list.
 Your music queue: [Thinking Out Loud, Traveller]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  a
 Song to add? Girl Crush
 Your music queue: [Thinking Out Loud, Traveller, Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Thinking Out Loud
 Your music queue: [Traveller, Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Traveller
 Your music queue: [Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Girl Crush
 Your music queue: []
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Empty queue!
 Your music queue: []
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  q
 
 No more music today!

**********************************************/