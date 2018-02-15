import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GoodApple extends Apples {
    
    private static final String goodname = "files/GoodApple.png";
    
    // Constructor
    public GoodApple(int courtWidth, int courtHeight, int posx, int posy) {
        super(goodname, courtWidth, courtHeight, posx, posy);
    }
    
    @Override
    public void eatEffect(Snake snake) {
        snake.addLength();
    }
    
    @Override
    public boolean mayVanish() {
        return false;
    }
    
    public int duration() {
        return -1;
    }
}