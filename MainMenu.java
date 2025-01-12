//Purpose: This class displays the manu options for starting a new game, saving, loading, and quitting the game

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    public Board board;
    private boolean gameStarted = false; // New variable to track game state
    //main menu (LEE JIA SHENG,DYLAN CHOO,TEOH KAI LOON,CHEW SHEN)
    public MainMenu(Board board, JFrame frame) {
        this.board = board;

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        ImageIcon backgroundImage = new ImageIcon("pieces/Title.jpg");
        // add(startButton, BorderLayout.SOUTH);

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(null);

        panel.add(backgroundLabel, BorderLayout.NORTH);
        frame.add(panel);
        JPanel buttonPanel = new JPanel();
        JButton startGame = new JButton("Start Game");
        //start game(LEE JIA SHENG,DYLAN CHOO,TEOH KAI LOON,CHEW SHEN)
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!gameStarted) { // Check if the game has not been started yet
                    gameStarted = true; // Set the game as started

                    // Create JFrame for the chess board
                    JFrame boardFrame = new JFrame();
                    boardFrame.setMinimumSize(new Dimension(1000, 1000));
                    boardFrame.setLocationRelativeTo(null);
                    boardFrame.setLayout(new GridBagLayout());

                    // Add the chess board into the frame
                    Board board = new Board();
                    GridBagConstraints boardConstraints = new GridBagConstraints();
                    boardConstraints.gridx = 0;
                    boardConstraints.gridy = 0;
                    boardFrame.add(board, boardConstraints);

                    // Add the Options panel into the frame
                    Options save = new Options(board, boardFrame,MainMenu.this);
                    GridBagConstraints optionsConstraints = new GridBagConstraints();
                    optionsConstraints.gridx = 0;
                    optionsConstraints.gridy = 1;
                    boardFrame.add(save, optionsConstraints);

                    boardFrame.setVisible(true);

                    boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                }

            }

        });
        JButton exitGame = new JButton(" Quit");
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }

        });
        buttonPanel.add(startGame);
        buttonPanel.add(exitGame);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        frame.add(panel);

    }
    //this is to check whether game started is true or false(chew shen,chong jia le)
    public  void setGameStarted(boolean gameStarted){
        this.gameStarted=gameStarted;
    }

}
