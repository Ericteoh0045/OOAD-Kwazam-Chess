//Purpose: This class is a subclass of the Piece class, each representing 
//a type of piece with its own specific movement rules and images.
//Design Pattern: Inheritance is utilized to create different types of pieces based 
//on the common Piece superclass.

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tor extends Piece {
    //Constructor of the Tor piece
    public Tor(Board board, int col, int row, boolean isRed)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.isRed = isRed;
        
        this.xPosition = col * board.tileSize;
        this.yPosition = row * board.tileSize;

        this.name = "Tor";
        String filename = isRed ? "pieces/RedTor.png" : "pieces/BlueTor.png";

        try{
            pieceImage = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    //valid movement of the Tor(DYLAN CHOO)
    public boolean isValidMovement(int col, int row) {
        return this.col == col || this.row == row;
    }
    @Override
    //Prevents from skipping over, check whether collision and prevent going out of the board(DYLAN CHOO)
    public boolean moveCollidesWithPiece(int col, int row)
    {
        if (col < 0 || col >= 6 || row < 0 || row >= 8) {
            return true;  // Collision, as the target position is outside the board
        }
        //left
        if(this.col > col)
        {
            for(int c = this.col - 1; c > col; c--)
            {
                if(board.getPiece(c, this.row) != null)
                {
                    return true;
                }
            }
        }
        //Right
        if(this.col < col)
        {
            for(int c = this.col + 1; c < col; c++)
            {
                if(board.getPiece(c, this.row) != null)
                {
                    return true;
                }
            }
        }
        //Up
        if(this.row > row)
        {
            for(int r = this.row - 1; r > row; r--)
            {
                if(board.getPiece(this.col, r) != null)
                {
                    return true;
                }
            }
        }
        //Down
         if(this.row < row)
        {
            for(int r = this.row + 1; r < row; r++)
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
