
/** 
 * Though Snake is not a typical GameObj(i.e. it doesn't have a 
 * fixed size, its intersect definition is somehow different etc.)
 * but I decided to let the Snake Class extend GameObj because it is
 * part of the game and require printed every time 
 */
 
// The Snake subclass use the position of SnakeHead
// 

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Snake extends GameObj{
    private int l;
    private int life;
    private int headposx;
    private int headposy;
    
    /** 
     * Table: int[30][30]
     * 2D array to store the information about the snake
     * 0: not occupied by a snake
     * 1: occupied, moving up (vy < 0)
     * 2: occupied, moving down (vy > 0)
     * 3: occupied, moving left (vx < 0)
     * 4: occupied, moving right (vx > 0)
     **/
     
    private int[][] table = new int[30][30];
    
    //Constructor
    //the width and height are set to -1 because I don't think it's wise to assign those value to the Snake 
       
    public Snake (int arrayX, int arrayY, int INIT_SNAKEPOSX, int INIT_SNAKEPOSY, int courtWidth, int courtHeight) {
        super(0, -10, INIT_SNAKEPOSX, INIT_SNAKEPOSY, 10, 10, courtWidth, courtHeight);
        l = 5;
        life = 3;
        headposx = arrayX;
        headposy = arrayY;
        for (int i = 0; i < 30; i++)
            for (int j = 0; j < 30; j++)
                table[i][j] = 0;
        for (int i = headposy; i < headposy + l; i++)
            table[arrayX][i] = 1;
    }
    
    @Override
    public void move() {
        super.move();
        int vx = this.getVx();
        int vy = this.getVy();
        int v = 0;
        if (vy < 0) {headposy--; v = 1;}
        else if (vy > 0) {headposy++; v = 2;}
        else if (vx < 0) {headposx--; v = 3;}
        else if (vx > 0) {headposx++; v = 4;}
        
        // Could write like this because grid_speed is 1
        this.table[headposx][headposy] = v;
        //System.out.println(headposx + "," + headposy);
     
        int x = headposx;
        int y = headposy;
        //This for loop helps me to find the tail
        for (int i = 0; i < l; i++) {
            if (table[x][y] == 1) {y++;}
            else if (table[x][y] == 2) {y--;}
            else if (table[x][y] == 3) {x++;}
            else if (table[x][y] == 4) {x--;}
        }
        table[x][y] = 0;
    }
    
    //Accessor
    public int length() {
        return l;
    }
    
    public int getLife() {
        return life;
    }
    
    public boolean occupied(int i, int j) {
        return table[i][j] > 0;
    }
    
    //return true if the snake is going to eat itself in the next step
    //return false if not
    public boolean eatItself() {
        int vx = this.getVx();
        int vy = this.getVy();
        if (vy < 0) return table[headposx][headposy-1] > 0;
        else if (vy > 0) return table[headposx][headposy+1] > 0;
        else if (vx < 0) return table[headposx-1][headposy] > 0;
        else return table[headposx+1][headposy] > 0;
    }
        
    //Updator
    public void addLife() {
        life++;
    }
    
    public void deleteLife() {
        life--;
    }
    
    public void addLength() {
        
        super.move();
        int vx = this.getVx();
        int vy = this.getVy();
        int v = 0;
        if (vy < 0) {headposy--; v = 1;}
        else if (vy > 0) {headposy++; v = 2;}
        else if (vx < 0) {headposx--; v = 3;}
        else if (vx > 0) {headposx++; v = 4;}
                
        l++;
        table[headposx][headposy] = v;
    }
    
    @Override
    public void draw (Graphics g) {
        g.setColor(Color.green);
        int w = 10;
        int h = 10;
        for (int i = 0; i < 30; i++)
            for (int j = 0; j < 30; j++)
                if (table[i][j]>0) g.fillOval(10*i, 10*j, w,h);
    }
}