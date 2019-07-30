package freshdesk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Readdesk {
   Scanner inpt = new Scanner(System.in);
    String FileName = new String();
    JSONObject json1 = new JSONObject(); 
    
    void getFileName()
    {
        System.out.print("\nEnter the FileName : ");
        FileName = inpt.nextLine();
    }
    @SuppressWarnings("unchecked")
    void read1() throws FileNotFoundException, IOException, ParseException, JSONException
    {
        while(true)
        {
            getFileName();
            File file1 = new File("./Dataname/"+FileName+".json");
            if(!file1.exists())
            {
                System.out.println("Invalid Filename, Please Try Again..!");
            }
            else
            {
                JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(new FileReader(file1));
            json1 = (JSONObject) object;
                
                Set<String> kes = json1.keySet();
                Iterator<String> keys = kes.iterator();
                while(keys.hasNext()) 
                {
                    String key = keys.next();
                    System.out.println(key+"--"+json1.get(key));
                }
                System.out.println("\n\n");
                break;
            }
        }
    }
}
