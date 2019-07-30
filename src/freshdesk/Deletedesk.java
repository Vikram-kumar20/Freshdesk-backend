package freshdesk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Deletedesk {
     
     void delete1() throws FileNotFoundException, IOException, ParseException{
    File fan = null;
    Scanner scan = new Scanner(System.in);
    String FileName = new String();
    String option;
     boolean bool = false;
     while(true)
     {
        System.out.println("\n1   Delete Using KEY\n2   Delete the Object\nEnter your choice: ");
        option = scan.nextLine();
        if(! (option.equals("1") || option.equals("2")))
            System.out.println("Please enter a Correct Option : ");
        else
            break;
     }
     if(option.equals("2"))
     {
            try {
                int temp=0;
              while(true)
              {
                   System.out.print("\nEnter the Name : ");
                   FileName = scan.nextLine();
                   fan = new File("./Dataname/"+FileName+".json");
                   while(fan.exists())
                   {
                       fan.delete();
                       temp=1;
                       break;
                   }
                   if(temp==1)
                       break;
                   System.out.println("File not exists");
              }      
            } catch(Exception e) {
               e.printStackTrace();
            }
     }
     else if (option.equals("1"))
     {
         File XtoFind;
         while(true)
         {
            System.out.println("\nEnter the ObjectName : ");
            FileName = scan.nextLine();
            XtoFind = new File("./Dataname/"+FileName+".json");
            if(XtoFind.exists())
                break;
         }
         
         JSONParser jsonParser = new JSONParser();
         Object object = jsonParser.parse(new FileReader(XtoFind));
         JSONObject jo = (JSONObject) object;
                
         Set<String> kes = jo.keySet();
         Iterator<String> keys = kes.iterator();
         
         while(true)
         {
             System.out.println("\nEnter the Key : ");
             String Key = scan.nextLine();
             if(jo.containsKey(Key))
             {
                 jo.remove(Key);
                 FileWriter pw = new FileWriter(XtoFind);
                    pw.write(jo.toString());
                    pw.flush(); 
                    pw.close();
                    break;
             }
             else
             {
                 System.out.println("\nKey not Found. Try Again");
             }
         }
         
     }
  }   
}