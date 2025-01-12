//Purpose: This class is a subclass of the Piece class, each representing 
//a type of piece with its own specific movement rules and images.
//Design Pattern: Inheritance is utilized to create different types of pieces based 
//on the common Piece superclass.

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Xor extends Piece {
    //Constructor for Xor piece
    public Xor(Board board, int col, int row, boolean isRed)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.isRed = isRed;
        
        this.xPosition = col * board.tileSize;
        this.yPosition = row * board.tileSize;

        this.name = "Xor";
        String filename = isRed ? "pieces/RedXor.png" : "pieces/BlueXor.png";

        try{
            pieceImage = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    //valid movement of the Biz(DYLAN CHOO)
    public boolean isValidMovement(int col, int row) {
        return Math.abs(this.col - col) == Math.abs(this.row - row);
    }
//Check if there is collision of the pieces(DYLAN CHOO)
    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        if (col < 0 || col >= 6 || row < 0 || row >= 8) {
            return true;  // Collision, as the target position is outside the board
        }
        //Up left
        if(this.col > col && this.row > row)
        {
            for(int i = 1; i < Math.abs(this.col - col); i++) {
                if(board.getPiece(this.col - i, this.row - i) != null)
                {
                    return true;
                }
            }
        }
        //Up right
        if(this.col < col && this.row > row)
        {
            for(int i = 1; i < Math.abs(this.col - col); i++) {
                if(board.getPiece(this.col + i, this.row - i) != null)
                {
                    return true;
                }
            }
        }
        //Bottom left
        if(this.col > col && this.row < row)
        {
            for(int i = 1; i < Math.abs(this.col - col); i++) {
                if(board.getPiece(this.col - i, this.row + i) != null)
                {
                    return true;
                }
            }
        }
        //Bottom right
        if(this.col < col && this.row < row)
        {
            for(int i = 1; i < Math.abs(this.col - col); i++) {
                if(board.getPiece(this.col + i, this.row + i) != null)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
