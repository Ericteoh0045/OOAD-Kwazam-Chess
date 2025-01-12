//Purpose: This class is a subclass of the Piece class, each representing 
//a type of piece with its own specific movement rules and images.
//Design Pattern: Inheritance is utilized to create different types of pieces based 
//on the common Piece superclass.

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Biz extends Piece {
    //Constructor for the Biz piece
    public Biz(Board board, int col, int row, boolean isRed)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.isRed = isRed;
        
        this.xPosition = col * board.tileSize;
        this.yPosition = row * board.tileSize;

        this.name = "Biz";
        String filename = isRed ? "pieces/RedBiz.png" : "pieces/BlueBiz.png";

        try{
            pieceImage = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    //Valid movement of the Biz(DYLAN CHOO)
    public boolean isValidMovement(int col, int row) {
        return (Math.abs(col - this.col)) * (Math.abs(row - this.row)) == 2;
    }
    // Prevent going out of the board(DYLAN CHOO)
    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        if (col < 0 || col >= 6 || row < 0 || row >= 8) {
            return true;  // Collision, as the target position is outside the board
        }
        return false;
    }
}
