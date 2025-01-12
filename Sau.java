//Purpose: This class is a subclass of the Piece class, each representing 
//a type of piece with its own specific movement rules and images.
//Design Pattern: Inheritance is utilized to create different types of pieces based 
//on the common Piece superclass.

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sau extends Piece {
    //Constructor of the Sau piece 
    public Sau(Board board, int col, int row, boolean isRed)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.isRed = isRed;
        
        this.xPosition = col * board.tileSize;
        this.yPosition = row * board.tileSize;

        this.name = "Sau";
        String filename = isRed ? "pieces/RedSau.png" : "pieces/BlueSau.png";

        try{
            pieceImage = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    //Valid movement of the Sau (TEOH KAI LOON)
    public boolean isValidMovement(int col, int row) {
        return this.col-col==1&& this.row-row==1 || this.col-col==-1&& this.row-row==1||this.col-col==1&& this.row-row==-1||this.col-col==-1&& this.row-row==-1 ||(this.col-col == 1 && this.row-row==0)||(this.row-row == 1 && this.col-col==0)||(this.col-col == -1 && this.row-row==0)||(this.row-row == -1 && this.col-col==0); 
    }

    @Override
    //Check if there is collision of the pieces(TEOH KAI LOON)
    public boolean moveCollidesWithPiece(int col, int row) {
        if (col < 0 || col >= 6 || row < 0 || row >= 8) {
            return true;  // Collision, as the target position is outside the board
        }
        return false;
    }

}
