/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import java.io.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
    private Snake snake;

    private Apples apple1;
    private Apples apple2;
    private int apple1X;
    private int apple1Y;
    private int apple2X;
    private int apple2Y;
    
    //To tick for apple1 and apple2
    private int tick1;
    private int tick2;
    
    public boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."
    private JLabel lifel;
    private JLabel lengthl;

    // Game constants
    public static final int COURT_WIDTH = 300;
    public static final int COURT_HEIGHT = 300;
    public static final int SQUARE_VELOCITY = 10;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 400;

    public GameCourt(JLabel status, JLabel lifel, JLabel lengthl) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // The timer is an object which triggers an action periodically with the given INTERVAL. We
        // register an ActionListener with this timer, whose actionPerformed() method is called each
        // time the timer triggers. We define a helper method called tick() that actually does
        // everything that should be done in a single timestep.
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {tick();}
                catch (IOException ee){
                JOptionPane.showMessageDialog(null,"File has been damaged/changed", "alert!", JOptionPane.INFORMATION_MESSAGE);}
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        // This key listener allows the square to move as long as an arrow key is pressed, by
        // changing the square's velocity accordingly. (The tick method below actually moves the
        // square.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    snake.setVx(-SQUARE_VELOCITY);
                    snake.setVy(0);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    snake.setVx(SQUARE_VELOCITY);
                    snake.setVy(0);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    snake.setVy(SQUARE_VELOCITY);
                    snake.setVx(0);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    snake.setVy(-SQUARE_VELOCITY);
                    snake.setVx(0);
                }
            }

            //public void keyReleased(KeyEvent e) {
            //    snake.setVx(0);
            //    snake.setVy(0);
            //}
        });

        this.status = status;
        this.lifel = lifel;
        this.lengthl = lengthl;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    
    public void reset() {        
        snake = new Snake(28, 20, 28*10, 20*10, COURT_WIDTH, COURT_HEIGHT);
       // life = 3;

        playing = true;
        status.setText("Running...");
        lifel.setText("~~~|| LIFE: 3 ||~~~");
        lengthl.setText("~~~|| LENGTH: 5 ||~~~");
        
        tick1 = 0;
        tick2 = 0;
        
        int x1 = (int) (Math.random() * 895);
        int x2 = (int) (Math.random() * 895);
        if (x2 == x1) x2 = x1 - 1;
        if (x2 < 0) x2 = x1 + 1;
        
        for (int i=0; i < 30; i++) {
            for (int j=0; j < 30; j++)
            {
                if (!snake.occupied(i,j)) {
                    if (x1 == 0) {apple1 = new GoodApple(300, 300, i*10, j*10);
                                 apple1X=i; apple1Y=j;}
                    if (x2 == 0) {apple2 = new GoodApple(300, 300, i*10, j*10);
                                 apple2X=i; apple2Y=j;}
                    if ((x1==0) && (x2==0)) break;
                    x1--; x2--;
                }
            }
        if ((x1==0) && (x2==0)) break;}
        
        repaint();
        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() throws FileNotFoundException, IOException{
        if (playing) {
            // advance the square and snitch in their current direction.
            //snake.move();
            //snitch.move();
            
            tick1++;
            tick2++;
            
            repaint();

            // make the snitch bounce off walls...
            //snitch.bounce(snitch.hitWall());
            // ...and the mushroom
            //snitch.bounce(snitch.hitObj(poison));

            // check for the game end conditions
            if (snake.willIntersect(apple1)) {
                apple1.eatEffect(snake);
                lengthl.setText("~~~|| LENGTH: " + snake.length() + " ||~~~");
                lifel.setText("~~~|| LIFE: " + snake.getLife() + " ||~~~");
                int x = (int) (Math.random() * (899-snake.length()));
                int y = (int) (Math.random() *  4);
                boolean f = false;
                for (int i = 0; i < 30; i++) {
                   for (int j = 0; j < 30; j++)
                       {
                          if (!snake.occupied(i,j) && (apple2X!=i || apple2Y!=j)) {
                               if (x == 0) 
                               {
                                if ((y == 1) || (y == 2)) apple1 = new GoodApple(300, 300, i*10, j*10);
                                   else if (y == 3) apple1 = new BadApple(300, 300, i*10, j*10);
                                   else apple1 = new MagicApple (300, 300, i*10, j*10);
                                tick1 = 0;
                                apple1X = i;
                                apple1Y = j; 
                                f = true;
                                break;
                               }
                               x--;
                        }
                        }
                    if (f) break; }
                
                //playing = false;
                //status.setText("You lose!");
            } 
            if (snake.willIntersect(apple2)) {
               apple2.eatEffect(snake);
               lengthl.setText("~~~|| LENGTH: " + snake.length() + " ||~~~");
               lifel.setText("~~~|| LIFE: " + snake.getLife() + " ||~~~");
               int x = (int) (Math.random() * (899-snake.length()));
               int y = (int) (Math.random() * 4);
               boolean f=false;
               for (int i = 0; i < 30; i++) {
                   for (int j = 0; j < 30; j++)
                       {
                          if (!snake.occupied(i,j) && (apple1X!=i || apple1Y!=j)) {
                               if (x == 0) 
                               { if ((y == 1) || (y == 2)) apple2 = new GoodApple(300, 300, i*10, j*10);
                                   else if (y == 3) apple2 = new BadApple(300, 300, i*10, j*10);
                                   else apple2 = new MagicApple (300, 300, i*10, j*10);
                                apple2 = new GoodApple(300, 300, i*10, j*10);
                                apple2X = i;
                                apple2Y = j;
                                tick2 = 0;
                                f=true;
                                break;
                               }
                               x--;
                        }
                        }
                    if (f) break; }
                //playing = false;
                //status.setText("You win!");
            }
            
            
            if (snake. hitWall() != null)
               {playing = false;
                status.setText("You hit the wall!");
                lifel.setText("~~~|| LIFE: " + 0 + " ||~~~");
                this.aftergame();}
            else if (snake. eatItself())
               {playing = false;
                status.setText("You hit yourself!");
                lifel.setText("~~~|| LIFE: " + 0 + " ||~~~");
                this.aftergame();}
            else if (snake. getLife() == 0) 
               {playing = false;
                status.setText("You are poisoned");
                this.aftergame();
               } else  snake.move();
            
            
            if (apple1. mayVanish() && tick1 == apple1.duration())
            {int x = (int) (Math.random() * (899-snake.length()));
                int y = (int) (Math.random() * 4);
                boolean f = false;
                for (int i = 0; i < 30; i++) {
                   for (int j = 0; j < 30; j++)
                       {
                          if (!snake.occupied(i,j) && (apple2X!=i || apple2Y!=j)) {
                               if (x == 0) 
                               {
                                if ((y == 1) || (y == 2)) apple1 = new GoodApple(300, 300, i*10, j*10);
                                   else if (y == 3) apple1 = new BadApple(300, 300, i*10, j*10);
                                   else apple1 = new MagicApple (300, 300, i*10, j*10);
                                tick1 = 0;
                                apple1X = i;
                                apple1Y = j; 
                                f = true;
                                break;
                               }
                               x--;
                        }
                        }
                    if (f) break; }}
            
            if (apple2. mayVanish() && tick2 == apple2. duration())
            {int x = (int) (Math.random() * (899-snake.length()));
                int y = (int) (Math.random() * 4);
                boolean f = false;
                for (int i = 0; i < 30; i++) {
                   for (int j = 0; j < 30; j++)
                       {
                          if (!snake.occupied(i,j) && (apple1X!=i || apple1Y!=j)) {
                               if (x == 0) 
                               {
                                if ((y == 1) || (y == 2)) apple2 = new GoodApple(300, 300, i*10, j*10);
                                   else if (y == 3) apple2 = new BadApple(300, 300, i*10, j*10);
                                   else apple2 = new MagicApple (300, 300, i*10, j*10);
                                tick2 = 0;
                                apple2X = i;
                                apple2Y = j; 
                                f = true;
                                break;
                               }
                               x--;
                        }
                        }
                    if (f) break; }}
            
            // update the display
            repaint();
        }
    }
    
    public void aftergame() throws FileNotFoundException, IOException{
        String st = JOptionPane.showInputDialog("Please type in your name!!!");
        PopOut x = new PopOut("files/game.txt", st, snake.length());
        st = x. message();
        JOptionPane.showMessageDialog(null, st, "High Scores!!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        apple1.draw(g);
        apple2.draw(g);
        //poison.draw(g);
        //snitch.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}