/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freshdesk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Hello {
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException
    {
        Hello nc = new Hello();
        nc.TimeToLive();
    }
    void TimeToLive() throws FileNotFoundException, IOException, ParseException
    {
        while(true)
        {
            File file = new File("./bye.txt");
            try (BufferedReader buff = new BufferedReader(new FileReader(file))) 
            {
                String line;
                while ((line = buff.readLine()) != null) 
                {
                   String[] arr  = line.split("   ");
                   File f1 = new File("./Dataname/"+arr[0]+".json");
                   if(f1.exists())
                   {
                       long temp =  (System.currentTimeMillis()-file.lastModified())/1000;
                       if(Long.parseLong(arr[2]) < temp)
                       {
                           JSONParser jsonParser = new JSONParser();
                            Object object = jsonParser.parse(new FileReader(f1));
                            JSONObject json1 = (JSONObject) object;
                
                            json1.remove(arr[1]);
                            PrintWriter pw = new PrintWriter(f1);
                            pw.write(json1.toJSONString());
                            pw.flush();
                            pw.close();
                       }

                   }
                }
            }
        }
    }
}
