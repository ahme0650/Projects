package CSCI1933P2;
//ahme0650 (Hamza Ahmed)
import java.util.Scanner;
public class Pawn extends Piece {

    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */
    public Pawn(int row, int col, boolean isBlack) {
        // Set Parent class's row instance variable
        super.row = row;

        // Set Parent class's col instance variable
        super.col = col;

        // Boolean representing Piece object's color (white/black)
        super.isBlack = isBlack;

        // Setting the representation of the Pawn object (based on the color)
        // View the Unicode table in the writeup for picking Piece char representations.
        if (isBlack){
            // Black Pawn
            super.representation = '\u265F';
        }
        else{
            // White Pawn
            super.representation = '\u2659';
        }
    }

    /**
     * Handle promotion of a pawn.
     * @param board Board instance
     * @param row Current row of the pawn
     * @param col Current col of the pawn
     * @param isBlack Color of the pawn
     */
    //Promotes The Pawn When It Reaches The End Of The Board
    public void promotePawn(Board board, int row, int col, boolean isBlack) {
        if (board.getPiece(row, col) instanceof Pawn && (row == 7 || row == 0)){
            Scanner s = new Scanner(System.in);
            System.out.println("Choose A Promotion!\nYour Choices Are: Queen, Bishop, Knight, or Rook");
            String promotion = s.nextLine();
            if (promotion.equals("Queen")){
               Queen q = new Queen(row, col, isBlack);
                board.setPiece(row, col, q);
            }else if (promotion.equals("Bishop")){
                Bishop b = new Bishop(row, col, isBlack);
                board.setPiece(row, col, b);
            }else if(promotion.equals("Knight")){
                Knight k = new Knight(row, col, isBlack);
                board.setPiece(row, col,k);
            }else if (promotion.equals("Rook")){
                Rook r = new Rook(row, col, isBlack);
                board.setPiece(row, col, r);
            }
        }
    }

    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifyVertical(row, col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
            // Case 1: Forward movement to empty square.
            // Determine if the distance being moved is valid.
            if (this.isBlack) {
                return (endRow == this.row + 1) || ((endRow == this.row + 2) && (this.row == 1));
            } else {
                return (endRow == this.row - 1) || ((endRow == this.row - 2) && (this.row == 6));
            }
        }

        else if (this.col == endCol+1 || this.col == endCol-1) {
            // Case 2: Capturing a piece.
            if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
                // There is a piece of the opposite color to be captured.
                if (this.isBlack) {
                    return (endRow == this.row + 1);
                } else {
                    return (endRow == this.row - 1);
                }
            } else {
                return false;
            }
        }

        else {
            // Case 3: Moving in a non-adjacent column. (illegal move)
            return false;
        }
    }

    

}
