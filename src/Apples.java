import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Apples extends GameObj {
    
    public static final int SIZE = 10;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    public BufferedImage img = null;
    
    // Constructor. Create an Apple object given its image, courtWidth+Height, posx+y
    public Apples(String filename, int courtWidth, int courtHeight, int posx, int posy) {
        super(INIT_VEL_X, INIT_VEL_Y, posx, posy, SIZE, SIZE, courtWidth, courtHeight);
        
        try {
            if (img == null) {
                img = ImageIO.read(new File(filename));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);
    }
    
    // The effect when apple is eaten (+1 length or -1 life or +0.5 life)
    public abstract void eatEffect(Snake snake); 
    
    public abstract boolean mayVanish();
    public abstract int duration();
}
