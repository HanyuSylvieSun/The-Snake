import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BadApple extends Apples {
    
    private static final String badname = "files/poison.png";
    
    // Constructor
    public BadApple(int courtWidth, int courtHeight, int posx, int posy) {
        super(badname, courtWidth, courtHeight, posx, posy);
    }
    
    @Override
    public void eatEffect(Snake snake) {
        snake.deleteLife();
    }
    
    @Override
    public boolean mayVanish() {
        return true;
    }
    @Override
    public int duration() {
        return 50;
    }
    
    //@Override
    //public 
}