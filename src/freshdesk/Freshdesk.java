/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freshdesk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.simple.parser.ParseException;

public class Freshdesk {
     public static void main(String[] args)
    {
        Thread t = new Freshed();
        t.start();   
    } 
}
class Freshed extends Thread {

    /**
     * @param args the command line arguments
     */
    
    @Override
    public void run()
    {
        try {
            simple();
        } catch (JSONException ex) {
            Logger.getLogger(Freshed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Freshed.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Freshed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static long getFolderSize(File folder) 
    {
        if(!folder.exists())
        {
            folder.mkdirs();
        }
    long filelen = 0;
    File[] files = folder.listFiles();
 
    int count = files.length;
 
    for (int i = 0; i < count; i++) {
        if (files[i].isFile()) {
            filelen += files[i].length();
        }
        else {
            filelen += getFolderSize(files[i]);
        }
    }
    return filelen;
    }
    
        void simple() throws JSONException, FileNotFoundException, IOException, ParseException {
       String str = new String();
       Scanner inpt = new Scanner(System.in);
       while(true)
       {
           System.out.println("Menu:");
           System.out.println("1  Create\n2   Read\n3   Delete\n4   Exit");
           System.out.print("Please provide your option : ");
           String option = inpt.nextLine();
           if(option.equals(""+4))
               break;
           else
           {
                switch(option)
                {
                    case "1":
                    {
                        BigInteger fsize= new BigInteger(""+getFolderSize(new File("./Dataname/")));
                        BigInteger gbsize = new BigInteger(""+1073741824);
                        if(fsize.compareTo(gbsize)<=-1)
                        {
                            Createdesk cd = new Createdesk();
                            cd.create();
                            break;
                        }
                        else
                        {
                            System.out.println("Data Limit crossed!!");
                            break;
                        }
                    }
                    case "2":
                    {
                        Readdesk rd1 = new Readdesk();
                        rd1.read1();
                        break;
                    }
                    case "3":
                    {
                        Deletedesk dust= new Deletedesk();
                        dust.delete1();
                        break;
                    }
                    default:
                    {
                        System.out.println("\n\nPlease Select a Valid Option\n");
                         System.out.println("Menu:");
                         System.out.println("1  Create\n2   Read\n3   Delete\n4   Exit");
                         System.out.println("Please Select your option : ");
                         option = inpt.nextLine();
                         break;
                    }
                }
           }
       }
       inpt.close();
    }
    
}

    

