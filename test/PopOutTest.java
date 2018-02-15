import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;

import org.junit.Test;

public class PopOutTest {
    
    @Test(timeout=500)
    public void LackFile() {
        boolean f = false;
        try {
            PopOut t = new PopOut("files/notexist.txt", "", 546);
        } catch (FileNotFoundException e){
            f = true;
        } catch (IOException e) {
            f = true;
        }
        assertTrue(f);
    }
    
    @Test(timeout=500)
    public void InsertBeginning() {
        boolean f = true;
        PopOut t;
        String st = "";
        String expected = "";
        try{
          Writer out = new FileWriter("files/test.txt");
          out.flush();
          out.write("User1 990");
          out.append('\n');
          out.close();
          
          t = new PopOut("files/test.txt", "Hanyu", 1000);
          st = t.message();
          expected = "";
          expected = "Hanyu ~~~ 1000";
          expected = expected +'\n';
          expected = expected +"User1 ~~~ 990" + '\n';
          expected = expected +"Null ~~~ Null" + '\n';
          expected = expected +"Null ~~~ Null" + '\n';
          expected = expected + "Null ~~~ Null" + '\n';
        } catch(FileNotFoundException e) {
            f = false;
        } catch(IOException e) {
            f = false;
        } 
        
        assertTrue(f);
        assertEquals(st, expected); 
    }
    
    @Test(timeout=500)
    public void InsertEnd() {
        boolean f = true;
        PopOut t;
        String st = "";
        String expected = "";
        try{
          Writer out = new FileWriter("files/test.txt");
          out.flush();
          out.write("User1 990");
          out.append('\n');
          out.close();
          
          t = new PopOut("files/test.txt", "Hanyu", 10);
          st = t.message();
          expected = "";
          expected = "User1 ~~~ 990";
          expected = expected +'\n';
          expected = expected +"Hanyu ~~~ 10" + '\n';
          expected = expected +"Null ~~~ Null" + '\n';
          expected = expected +"Null ~~~ Null" + '\n';
          expected = expected + "Null ~~~ Null" + '\n';
        } catch(FileNotFoundException e) {
            f = false;
        } catch(IOException e) {
            f = false;
        } 
        
        assertTrue(f);
        assertEquals(st, expected); 
    }
    
    @Test(timeout=500)
    public void InsertMiddle() {
        boolean f = true;
        PopOut t;
        String st = "";
        String expected = "";
        try{
          Writer out = new FileWriter("files/test.txt");
          out.flush();
          out.write("User1 990");
          out.append('\n');
          out.append("User2 770");
          out.append('\n');
          out.close();
          
          t = new PopOut("files/test.txt", "Hanyu", 880);
          st = t.message();
          expected = "";
          expected = "User1 ~~~ 990";
          expected = expected +'\n';
          expected = expected +"Hanyu ~~~ 880" + '\n';
          expected = expected +"User2 ~~~ 770" + '\n';
          expected = expected +"Null ~~~ Null" + '\n';
          expected = expected + "Null ~~~ Null" + '\n';
        } catch(FileNotFoundException e) {
            f = false;
        } catch(IOException e) {
            f = false;
        } 
        
        assertTrue(f);
        assertEquals(st, expected); 
    } 
}