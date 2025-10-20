package CSCI1933P2;
//ahme0650 (Hamza Ahmed)
public class King extends Piece{

    //Constructor
    public King(int row, int col, boolean isBlack){
        super.row = row;

        super.col =col;

        super.isBlack = isBlack;

        if (isBlack){
            // Black Pawn
            super.representation = '\u265A';
        }
        else{
            // White Pawn
            super.representation = '\u2654';
        }
    }

    //Method To Determine If A Kings Move Is Legal
    @Override
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            return (board.verifyAdjacent(row, col, endRow, endCol) && (board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack));
        }return false;
    }
}
