/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        
        String hello = "";
        hello = "Hello, Welcome to the awesome game SNAKE!!!" + '\n';
        hello = hello + "At first, your snake will have a length of 5, and lives of 3." + '\n'+'\n';
        hello = hello + "However, when you eat an GoodApple(which looks exactly like a good apple)" + '\n';
        hello = hello + "You will be longer, and length will add one. " + '\n';
        hello = hello + "When you eat a Heart (which is a blue heart), you will gain one life" + '\n'+'\n';
        hello = hello + "Try avoiding the poisonous mushroom!!! It will make you lose one life!!!" + '\n'+'\n';
        hello = hello + "But don't be frightened, the poisounous mushroom will vanish after 50 ticks" + '\n';
        hello = hello + "To make it a fair game, the blue heart will vanish after only 10 ticks." + '\n'+'\n';
        hello = hello + "During the game, there will always be two apples on the screen." + '\n';
        hello = hello + "When an apple is eaten/vanishes, it will replaced by a random apple" + '\n';
        hello = hello + "(50%-GoodApple, 25%-MagicApple, 25%-BadApple) at a random location!" +'\n' + '\n';
        
        hello = hello + "BE CAREFUL: the game will directly be OVER if you HIT THE WALL or HIT YOURSELF!!!" + '\n' + '\n';
          
        hello = hello + "Anyway, ENJOY and GOOD LUCK!!!";
        
        
        
        JOptionPane.showMessageDialog(null, hello, "Welcome!!!", JOptionPane.INFORMATION_MESSAGE);
        
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("SNAKE!!!");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        status_panel. setLayout(new GridLayout(3,1));
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);
        final JLabel Life = new JLabel("~~~|| LIFE: 3 ||~~~");
        final JLabel Length = new JLabel("~~~|| LENGTH: 5 ||~~~");
        status_panel.add(Life);
        status_panel.add(Length);

        // Main playing area
        final GameCourt court = new GameCourt(status,Life,Length);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
      
        // Start game
        court.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}