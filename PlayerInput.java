//Purpose: This class handles mouse events for dragging and selecting pieces on the game board.
//Design Pattern: Observer pattern is implemented as this class observes the Board class
//to receive updates and changes for mouse events, and actions are taken accordingly.
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayerInput extends MouseAdapter {

    Board board;

    // Create constructor(DYLAN CHOO)
    public PlayerInput(Board board) {
        this.board = board;
    }

    // When mouse is dragged, piece would be dragged as well(DYLAN CHOO)
    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.selectedPiece != null) {
            board.selectedPiece.xPosition = e.getX() - board.tileSize / 2;
            board.selectedPiece.yPosition = e.getY() - board.tileSize / 2;

            board.repaint();
        }
    }

    // When mouse is pressed, piece is selected(DYLAN CHOO,TEOH KAI LOON)
    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        Piece pieceXY = board.getPiece(col, row);
        if (board.getCount() % 2 == 0) {
            if ((pieceXY != null) && (pieceXY.isRed == false)) {
                board.selectedPiece = pieceXY;
            }

        } else {
            if ((pieceXY != null) && (pieceXY.isRed == true)) {
                board.selectedPiece = pieceXY;
            }

        }
    }

    // When mouse is released, piece receive new x position and y position(DYLAN
    // CHOO,TEOH KAI LOON,CHEW SHEN ,LEE JIA SHENG)
    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / board.tileSize;
        int row = e.getY() / board.tileSize;

        if (board.selectedPiece != null) {
            Move move = new Move(board, board.selectedPiece, col, row);

            if (board.isValidMove(move)) {

                board.makeMove(move);
            } else {
                board.selectedPiece.xPosition = board.selectedPiece.col * board.tileSize;
                board.selectedPiece.yPosition = board.selectedPiece.row * board.tileSize;
            }
        }
        board.selectedPiece = null;
        board.repaint();
    }

}
