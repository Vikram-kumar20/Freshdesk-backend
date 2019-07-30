package freshdesk;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
import java.lang.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Createdesk {
   Scanner inptl = new Scanner(System.in);
    String FileName = new String();
    String key = new String();
    String value = new String();
    JSONObject json1 = new JSONObject();
    File file1;
    void menu_FileName()
    {
        System.out.print("Enter File Name : ");
        FileName = inptl.nextLine();
    }
    void getKey(JSONObject json2) throws FileNotFoundException, IOException, ParseException
    {
        System.out.print("Enter the Key : ");
        key = inptl.nextLine();
        if(key.length()>=32)
            key = key.substring(0, 31);
       // Readdesk rd = new Readdesk();
        try
        {
            if(json2.has(key))
            {
                System.out.println("\nKey already Exists. Try Again");
                getKey(json2);
            }
        }
        catch(Exception e)
        {
            System.out.println();
        }
    }
    void getValue() throws JSONException, IOException
    {
        System.out.print("\nEnter the value for Key: ");
        value = inptl.nextLine();
        System.out.println("\n1   Set Time-to-live\nAny other Key to Continue\nEnter choice :");
        String opt = inptl.nextLine();
        if(opt.equals(""+1))
        {
            FileWriter ttl = new FileWriter("./bye.txt",true);
            System.out.println("Enter Number of Seconds to Live : ");
            String tolive = inptl.nextLine();
            String qry = new String(FileName+"   "+key+"   "+tolive+"\n");
            ttl.write(qry);
            ttl.close();
        }
            json1.put(key,value);
    }
    void menu_KeyEntry()
    {
        System.out.print("\n1   Enter Another Key-Value\n2   Exit");
    }
    void create() throws JSONException, FileNotFoundException, IOException, ParseException
    {
        file1 = new File("./Dataname/");
        file1.mkdirs();
        while(true)
        {
            menu_FileName();
            file1 = new File("./Dataname/"+FileName+".json");
            
            if(file1.exists())
                System.out.println("\nA File with Similar name already exists..!\n");
            else
            {
                break;
            }
        }
        while(true)
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream ();
            ObjectOutputStream obStream = new ObjectOutputStream(baos);
            obStream.writeObject(json1.toString());
            byte[] boasArray = baos.toByteArray();
            baos.close();
            int size = boasArray.length;
            if(size<128000)
            {
                getKey(json1);
                if(json1.optBoolean(key))
                {
                    System.out.println("\nThe Key Already Exists..! Try Again\n");
                }
                else
                {
                    getValue();
                    System.out.print("\nPress 1 to exit\nAny Other key to continue adding data : ");
                    String option = inptl.nextLine();
                    if(option.equals(""+1))
                    {

                        break;
                    }
                }
            }
            else
            {
                System.out.println("Limit Exceeded.!");
                break;
            }
        }
        
        file1.createNewFile();
        PrintWriter pw = new PrintWriter(file1);
        pw.write(json1.toString());
        pw.flush(); 
        pw.close(); 
    }
}
