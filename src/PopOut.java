
import java.util.Iterator;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.lang.*;
import java.io.BufferedWriter;


// This class helps to handle with the I/O issue after the user finished playing
// the game 


public class PopOut {
    
    private String username;
    private int length;
    private BufferedReader in;
    private int[] scores;
    private String[] users;
    private int pointer;
    
    //Constructor (I choose to finish reading the file in the constructor)
    public PopOut(String filename, String u, int l) throws FileNotFoundException, IOException{
        in = new BufferedReader(new FileReader(filename));
        username = u;
        length = l;
        scores = new int[5];
        users = new String[5];
        
        String st = in.readLine();
        if (st!=null) st=st.trim();
        pointer = 0;
        String user;
        while (st!=null && !st.equals("") && pointer<=4) {
            int x = st.indexOf(" ");
            if (x!=0 && x!=-1) 
            {user = st.substring(0, x); }
            else 
            {user = "";}
            int score = java.lang.Integer.parseInt((st.substring(x+1).trim()));
            scores[pointer] = score;
            users[pointer] = user;
            pointer++;
            st = in.readLine();
            if (st!=null) st=st.trim();
        }
        
        if (pointer == 5) pointer--;
        
        if ((pointer == 5) && (length > scores[4])) {
            pointer--;
            int i = 3;
            while (i>=0 && scores[i] < length) {
                scores[i+1] = scores[i];
                users[i+1] = users[i];
                i--;
            }
            i++;
            scores[i] = length;
            users[i] = username;
        } else if ((pointer < 5) && pointer!=0) {
            int i = pointer - 1;
            while (i>=0 && scores[i] < length) {
                scores[i+1] = scores[i];
                users[i+1] = users[i];
                i--;
            }
            i++;
            scores[i] = length;
            users[i] = username;
        } else if (pointer==0) {
            scores[0] = length;
            users[0] = username;
        }
        
        // Update the information to the original file in the Constructor
        
        Writer out = new BufferedWriter(new FileWriter(filename));
        out.flush();
        String t="";
        //System.out.println(pointer);
        for (int i=0; i < pointer+1; i++) {
            t = t + users[i] + " " + ((Integer) scores[i]).toString();
            t = t +'\n';            
        }
        t = t.substring(0, t.length()-1);
        out.append(t);
        out.close();
    }
        
    // Return the String to display
    public String message() {
        String st="";
        for (int i=0; i < 5; i++) {
            if (i <= pointer) 
                st = st + users[i] + " ~~~ " + scores[i];
            else 
                st = st + "Null ~~~ Null";
            st = st +'\n';            
        }
        return st;
    }
    
    
    
    
}