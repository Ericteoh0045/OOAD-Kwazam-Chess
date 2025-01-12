//Purpose: This class provides for saving, loading, starting a new game/ reset the game and setting the board size.
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Options extends JPanel {
    JFrame boardFrame;
    private Board board;
    private int count; // Variable to store whose turn it is
    private int round;
    public MainMenu menu;
    private int requestSize;
    private String[] sizeChoice = { "Default", "Medium", "Large" };
  // Constructor for option menu for save,load and new game(Chew Shen,CHONG JIA LE)

    public Options(Board board, JFrame boardFrame, MainMenu menu) {
        this.board = board;
        this.boardFrame = boardFrame;
        this.menu = menu;
        
        JButton saveButton = new JButton("Save Game");
        JButton loadButton = new JButton("Load Game");
        JButton newgameButton = new JButton("New Game");
        JButton quitButton = new JButton("Return to menu");
        JComboBox<String> sizeComboBox = new JComboBox<>(sizeChoice);

        sizeComboBox.setSelectedIndex(0);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        add(buttonPanel.add(saveButton));
        add(buttonPanel.add(loadButton));
        add(buttonPanel.add(quitButton));
        add(buttonPanel.add(newgameButton));
        add(buttonPanel.add(sizeComboBox));

        // Save button to save the state of the game(Chew Shen,CHONG JIA LE)
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame("saved_game.txt");
            }
        });

        // Load button to load last saved game state(Chew Shen,CHONG JIA LE)
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame("saved_game.txt");
            }
        });

        // Quit button to quit game(Chew Shen,CHONG JIA LE)
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setGameStarted(false);
                boardFrame.dispose();
            }
        });

        // New game button to restart the state of the game(Chew Shen,CHONG JIA LE)
        newgameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });

        // drop-down menu to resize the board(Chew Shen,CHONG JIA LE)
        sizeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                String selected = (String) sizeComboBox.getSelectedItem();

                if (selected.equals("Default")) {

                    requestSize = 70;
                    board.tileSize = requestSize;
                } else if (selected.equals("Medium")) {
                    requestSize = 95;
                    board.tileSize = requestSize;
                } else if (selected.equals("Large")) {
                    requestSize = 120;
                    board.tileSize = requestSize;
                }
                board.updateTileSize(requestSize);
                repaint();
            }
        });
    }
//SAVE GAME (Chew Shen,CHONG JIA LE)
    private void saveGame(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            // Save game state to the file
            writer.println(board.getCount()); // Save whose turn it is
            writer.println(board.getRound());

            // Write each piece's information to the file
            for (Piece piece : board.pieceList) {
                writer.println(piece.name + "," + piece.col + "," + piece.row + "," + piece.isRed);
            }
            System.out.println("Game Saved to: " + filePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //new game (CHEW SHEN,CHONG JIA LE)
    private void newGame(){
        
        board.pieceList.clear();
        board.addPieces();
        count = 1;
        round = 0;
        board.updateCount(count);
        board.updateRound(round);
        board.repaint();

    }
//LOAD GAME (Chew Shen,CHONG JIA LE)
    private void loadGame(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Clear existing pieces from the board
            board.pieceList.clear();

            String line;
            if ((line = reader.readLine()) != null) {
                // Read whose turn it is and set the current turn accordingly
                count = Integer.parseInt(line);// set whose turn it is

            }
            // read the second line in the text file
            round = Integer.parseInt(reader.readLine().trim());

            while ((line = reader.readLine()) != null) {
                // Read each line and create pieces based on the saved information
                String[] data = line.split(",");
                String pieceName = data[0];
                int col = Integer.parseInt(data[1]);
                int row = Integer.parseInt(data[2]);
                boolean isRed = Boolean.parseBoolean(data[3]);

                // Create and add the pieces to the board
                switch (pieceName) {
                    case "Ram":
                        board.pieceList.add(new Ram(board, col, row, isRed));
                        break;
                    case "Biz":
                        board.pieceList.add(new Biz(board, col, row, isRed));
                        break;
                    case "Tor":
                        board.pieceList.add(new Tor(board, col, row, isRed));
                        break;
                    case "Xor":
                        board.pieceList.add(new Xor(board, col, row, isRed));
                        break;
                    case "Sau":
                        board.pieceList.add(new Sau(board, col, row, isRed));
                        break;
                    case "InvertedRam":
                        board.pieceList.add(new InvertedRam(board, col, row, isRed));
                        break;
                    // Add cases for other piece types if needed
                }
            }

            // Set the current turn according to the loaded value
            board.repaint();
            board.updateCount(count);
            board.updateRound(round);

            System.out.println("Game Loaded from: " + filePath);
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

}