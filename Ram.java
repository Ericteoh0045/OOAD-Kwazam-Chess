//Purpose: This class is a subclass of the Piece class, each representing 
//a type of piece with its own specific movement rules and images.
//Design Pattern: Inheritance is utilized to create different types of pieces based 
//on the common Piece superclass.

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ram extends Piece {

    //Constructor of the Ram piece
    public Ram(Board board, int col, int row, boolean isRed) {
        super(board);
        this.col = col;
        this.row = row;
        this.isRed = isRed;

        this.xPosition = col * board.tileSize;
        this.yPosition = row * board.tileSize;

        this.name = "Ram";
        String filename = isRed ? "pieces/RedRam.png" : "pieces/BlueRam.png";

        try {
            pieceImage = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    //valid movement of the Ram(TEOH KAI LOON)
    public boolean isValidMovement(int col, int row) {
        
    return (this.row - row == 2 && this.col - col == 0) || (this.row - row == 1 && this.col - col == 0);

    }
    //Prevents from skipping over, check whether collision and prevent going out of the board (TEOH KAI LOON)

    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        if (col < 0 || col >= 6 || row < 0 || row >= 8) {
            return true; // Collision, as the target position is outside the board
        }
        if(this.row > row) //go up
        {
            for(int r = this.row - 1; r > row; r--)
            {
                if(board.getPiece(this.col, r) != null)
                {
                    return true;
                }
            }
        }
        return false;
    }
}