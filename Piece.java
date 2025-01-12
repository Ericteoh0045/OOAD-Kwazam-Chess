//Purpose: This is an abstract class representing a generic piece.
//It contains common attributes and methods for all pieces.
//Design Pattern: Factory Method is implemented here, as it serves as the superclass
//for all types of pieces available in the game.

import java.awt.*;
public abstract class Piece {

    public int col, row;
    public int xPosition, yPosition;

    public boolean isRed;
    public String name;
    public int value;

    Image pieceImage;
    Board board;
    
    //Constructor for Piece(DYLAN CHOO)
    public Piece(Board board)
    {
        this.board = board;
    }

    public abstract boolean isValidMovement(int col, int row);  //Default boolean
    public abstract boolean moveCollidesWithPiece(int col, int row) ;//Default boolean

    //This method is to draw the pieceImage image on the given x and y position.(DYLAN CHOO)

    public void paint(Graphics2D g2d)
    {
        g2d.drawImage(pieceImage, xPosition+15, yPosition+15, null);
    }

}
