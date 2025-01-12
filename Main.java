//Purpose: This class contains the main methods responsible for initializing the game 
//by creating the JFrame and adding the board and menu components.

import javax.swing.*;
import java.awt.*;


public class Main {
    // main class to run code(CHEW SHEN)
    public static void main(String[] args)
    {
        //Create JFrame for the chess board
        JFrame frame = new JFrame();
        frame.setMinimumSize(new Dimension(1000,1000));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        
        // Add the chess board into the frame
        Board board = new Board();
        
        MainMenu menu = new MainMenu(board, frame);

        frame.add(menu);

        // Add the Options panel into the frame

    

        frame.setVisible(true);
 

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
