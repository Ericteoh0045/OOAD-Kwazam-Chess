//Purpose: This class contains the game logics and handles the rendering of the pieces.

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Board extends JPanel {
    int cols = 5; // Number of columns of the chess board
    int rows = 8; // Number of rows of the chess board
    int tileSize = 70; // The size of each tiles
    int round = 0;// this number is used to change the position of Tor and Xor
    int count = 2;// this number is to check Red or blue turn
    String colour;// check colour for the Sau
    int change = 4;


    ArrayList<Piece> newPieces = new ArrayList<>();//create extra arraylist to store the piece

    ArrayList<Piece> pieceList = new ArrayList<>(); // Create array list for piece for painting components

    public Piece selectedPiece;

    PlayerInput input = new PlayerInput(this);

    //Constructor for the chess board
    public Board() {
        this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
        this.setBackground(Color.GREEN);

        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addPieces();

    }
    //Return round(TEOH KAI LOON)
    public int getRound() {
        return round;
    }
    //Return count(TEOH KAI LOON)
    public int getCount() {
        return count;
    }
    //Update tile size (LEE JIA SHENG ,TEOH KAI LOON AND DYLAN CHOO)
    public void updateTileSize(int size){
        this.tileSize=size;
         setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
         getTileSize(size);
            for (Piece piece : pieceList) {
        piece.xPosition = (piece.col * tileSize);
        piece.yPosition = (piece.row * tileSize);
    }

        //return tileSize;
        revalidate();
         repaint(); 
        System.out.println(tileSize);
    }

//return tileSize(chew shen ,chong jia le)
    public int getTileSize(int tileSize)
    {
        return tileSize;
        
    }


    //Get the updated count when load
    public int updateCount(int update) {
        count = update;
        return count;
        
    }

    //Get the updated round when load
    public int updateRound(int updates) {
        round = updates;
        return round;
    }

    //Change normal Ram to inverted Ram(TEOH KAI LOON)
    public void changeRam(int col, int row) {
        for (Piece piece : pieceList) {
            if (piece.col == col && piece.row == row && piece.isRed == true) {

                newPieces.add(new InvertedRam(this, col, row, true));
            } else if (piece.col == col && piece.row == row && piece.isRed == false) {

                newPieces.add(new InvertedRam(this, col, row, false));
            } else {
                newPieces.add(piece);
            }
        }
        //Clear the original list and add the new pieces
        pieceList.clear();
        pieceList.addAll(newPieces);
        newPieces.clear();
        repaint();
        selectedPiece = null;
    }

    //Change the inverted Ram to normal Ram(TEOH KAI LOON)
    public void changeInvertedRam(int col, int row) {
        for (Piece piece : pieceList) {
            if (piece.col == col && piece.row == row && piece.isRed == true) {

                newPieces.add(new Ram(this, col, row, true));

            } else if (piece.col == col && piece.row == row && piece.isRed == false) {
                newPieces.add(new Ram(this, col, row, false));
            } else {
                newPieces.add(piece);
            }

        }
        //Clear the original list and add the new pieces
        pieceList.clear();
        pieceList.addAll(newPieces);
        newPieces.clear();
        repaint();
        selectedPiece = null;
    }

    //Get pieces from the initial position and move it to the next position(DYLAN CHOO)
    public Piece getPiece(int col, int row) {
        for (Piece piece : pieceList)
            if (piece.col == col && piece.row == row) {
                return piece;
            }
        return null;
    }

    //Method to make move(DYLAN CHOO)
    public void makeMove(Move move) {
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;

        move.piece.xPosition = move.newCol * tileSize;
        move.piece.yPosition = move.newRow * tileSize;
        round = round + 1;
        count = count + 1;

        capture(move);

    }
    
    //Check the colour of Sau that has been captured(TEOH KAI LOON)
    public void checkSau(Move move) {
        if (move.capture.name=="Sau") {
            if (move.capture.isRed) {
                colour = "blue";
            } else {
                colour = "Red";
            }
            message(colour);
        }
        selectedPiece = null;
    }
    //Pop up message when the game end ,give the player choose want new game or exit game(TEOH KAI LOON)
    public void message(String colour) {
        String[] response = { "new game", "exit game" };
        ImageIcon icon=new ImageIcon("pieces/winner.jpg");
        int x = JOptionPane.showOptionDialog(null, "Congratulations,Team " + colour + " wins", "Winner!!!",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, response, 0);
        if (x == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            pieceList.clear();
            addPieces();
            count = 1;
            round = 0;
            updateCount(count);
            updateRound(round);
            repaint();
        }
    }

    //Change the Tor and Xor piece after every 2 round(TEOH KAI LOON)
    public void changePiece() {
        
        for (Piece piece : pieceList) {
            if (piece.name.equals("Xor") && piece.isRed == true) {
                int col = piece.col;
                int row = piece.row;
                newPieces.add(new Tor(this, col, row, true));
            }
            if (piece.name.equals("Xor") && piece.isRed == false) {
                int col = piece.col;
                int row = piece.row;
                newPieces.add(new Tor(this, col, row, false));
            }
            if (piece.name.equals("Tor") && piece.isRed == false) {
                int col = piece.col;
                int row = piece.row;
                newPieces.add(new Xor(this, col, row, false));
            }
            if (piece.name.equals("Tor") && piece.isRed == true) {
                int col = piece.col;
                int row = piece.row;
                newPieces.add(new Xor(this, col, row, true));
            }
            if (piece.name != "Tor" && piece.name != "Xor") {
                newPieces.add(piece);
            }
        }

        //Clear the original list and add the new pieces
        pieceList.clear();
        pieceList.addAll(newPieces);
        newPieces.clear();
        change+=4;
        selectedPiece = null;
        
    }

    //Change the position of piece every round(TEOH KAI LOON)
    public void changepositionPiece() {
        
        for (Piece piece : pieceList) {
            if (piece.name.equals("Xor") && piece.isRed == true) {
                int col = -(piece.col - 4);
                int row = -(piece.row - 7);
                newPieces.add(new Xor(this, col, row, true));
            }
            if (piece.name.equals("Xor") && piece.isRed == false) {
                int col = -(piece.col - 4);
                int row = -(piece.row - 7);
                newPieces.add(new Xor(this, col, row, false));
            }
            if (piece.name.equals("Tor") && piece.isRed == false) {
                int col = -(piece.col - 4);
                int row = -(piece.row - 7);
                newPieces.add(new Tor(this, col, row, false));
            }
            if (piece.name.equals("Tor") && piece.isRed == true) {
                int col = -(piece.col - 4);
                int row = -(piece.row - 7);
                newPieces.add(new Tor(this, col, row, true));
            }
            if (piece.name.equals("Sau") && piece.isRed == true) {
                int col = -(piece.col - 4);
                int row = -(piece.row - 7);
                newPieces.add(new Sau(this, col, row, true));
            }
            if (piece.name.equals("Sau") && piece.isRed == false) {
                int col = -(piece.col - 4);
                int row = -(piece.row - 7);
                newPieces.add(new Sau(this, col, row, false));
            }
            if (piece.name.equals("Ram") && piece.isRed == false) {
                int col = -(piece.col - 4);
                int row = -(piece.row - 7);
                newPieces.add(new Ram(this, col, row, false));
            }
            if (piece.name.equals("Ram") && piece.isRed == true) {
                int col = -(piece.col - 4);
                int row = -(piece.row - 7);
                newPieces.add(new Ram(this, col, row, true));
            }
            if (piece.name.equals("InvertedRam") && piece.isRed == false) {
                int col = -(piece.col - 4);
                int row = -(piece.row - 7);
                newPieces.add(new InvertedRam(this, col, row, false));
            }
            if (piece.name.equals("InvertedRam") && piece.isRed == true) {
                int col = -(piece.col - 4);
                int row = -(piece.row - 7);
                newPieces.add(new InvertedRam(this, col, row, true));
            }
            if (piece.name.equals("Biz") && piece.isRed == true) {
                int col = -(piece.col - 4);
                int row = -(piece.row - 7);
                newPieces.add(new Biz(this, col, row, true));
            }
            if (piece.name.equals("Biz") && piece.isRed == false) {
                int col = -(piece.col - 4);
                int row = -(piece.row - 7);
                newPieces.add(new Biz(this, col, row, false));
            }

        }

        //Clear the original list and add the new pieces
        pieceList.clear();
        pieceList.addAll(newPieces);
        newPieces.clear();
    }

    //This is to check and change the piece when it reach the top or bottom(TEOH KAI LOON)
    public void checkPosition() {
        
        for (Piece piece : pieceList) {
            if (piece.row == 0 && piece.name == "Ram") {
                changeRam(piece.col, piece.row);

            }
            if (piece.row == 5 && piece.name == "InvertedRam") {
                changeInvertedRam(piece.col, piece.row);

            }
        }
        selectedPiece = null;

    }

    //Method to capture when unit is defeated(DYLAN CHOO)
    public void capture(Move move) {
        pieceList.remove(move.capture);
        repaint();
       

        if (round==4 && count ==5) {

            
            changePiece();

            round =0;
            count =1;

        }

        changepositionPiece();
        checkPosition();
        checkSau(move);

        selectedPiece = null;

    }

    //Method to check whether valid move(DYLAN CHOO)
    public boolean isValidMove(Move move) {
        if (sameTeam(move.piece, move.capture)) {
            return false;
        }
        if (!move.piece.isValidMovement(move.newCol, move.newRow)) 
                                                                   
        {
            return false;
        }
        if (move.piece.moveCollidesWithPiece(move.newCol, move.newRow)) {
            return false;
        }

        return true;

    }

    //Check if the two pieces are in the same team.(CHONG JIA LE)
    public boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        return p1.isRed == p2.isRed;
    }

    //Add pieces into the chess board(CHONG JIA LE)
    public void addPieces() {
        pieceList.add(new Biz(this, 1, 7, false));
        pieceList.add(new Biz(this, 3, 7, false));
        pieceList.add(new Biz(this, 1, 0, true));
        pieceList.add(new Biz(this, 3, 0, true));

        pieceList.add(new Tor(this, 4, 7, false));
        //pieceList.add(new Tor(this, 6, 0, false));
        pieceList.add(new Tor(this, 0, 0, true));
        //pieceList.add(new Tor(this, 6, 5, true));

        pieceList.add(new Ram(this, 0, 6, false));
        pieceList.add(new Ram(this, 1, 6, false));
        pieceList.add(new Ram(this, 2, 6, false));
        pieceList.add(new Ram(this, 3, 6, false));
        pieceList.add(new Ram(this, 4, 6, false));

        pieceList.add(new Ram(this, 0, 1, true));
        pieceList.add(new Ram(this, 1, 1, true));
        pieceList.add(new Ram(this, 2, 1, true));
        pieceList.add(new Ram(this, 3, 1, true));
        pieceList.add(new Ram(this, 4, 1, true));
        

        pieceList.add(new Sau(this, 2, 7, false));
        pieceList.add(new Sau(this, 2, 0, true));

        pieceList.add(new Xor(this, 0, 7, false));
        //pieceList.add(new Xor(this, 4, 0, false));
        pieceList.add(new Xor(this, 4, 0, true));
        //pieceList.add(new Xor(this, 4, 5, true));

    }

    // Painting method(DYLAN CHOO)
    public void paintComponent(Graphics g) {
        // Paint tiles of the board

        Graphics2D g2d = (Graphics2D) g; // Cast graphics2d to g

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g2d.setColor(new Color(0, 0, 139));
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g2d.setColor(Color.WHITE);
                g2d.fillRect(c * tileSize + 3, r * tileSize + 3, tileSize - 5, tileSize - 5);
            }
        }

        if (selectedPiece != null) {
            // Shows the piece highlights where the piece can move
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    // To check every columns and rows
                    if (isValidMove(new Move(this, selectedPiece, c, r))) {
                        g2d.setColor(new Color(68, 180, 57, 190));
                        g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                    }
                }
            }
        }
        // Paint pieces
        for (Piece piece : pieceList) {
            piece.paint(g2d);
        }
    }

}
