import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MagicApple extends Apples {
    
    private static final String magicname = "files/heart.png";
    
    // Constructor
    public MagicApple(int courtWidth, int courtHeight, int posx, int posy) {
        super(magicname, courtWidth, courtHeight, posx, posy);
    }
    
    @Override
    public void eatEffect(Snake snake) {
        snake.addLife();
    }
    
    @Override
    public boolean mayVanish() {
        return true;
    }
    
    @Override
    public int duration() {
        return 10;
    }
}