package CSCI1933P2;
//ahme0650 (Hamza Ahmed)
public class Bishop extends Piece{

    //Constructor
    public Bishop(int row, int col, boolean isBlack){
        super.row = row;

        super.col =col;

        super.isBlack = isBlack;

        if (isBlack){
            // Black Bishop
            super.representation = '\u265D';
        }
        else{
            // White Bishop
            super.representation = '\u2657';
        }
    }

    //Method To Determine If A Bishop's Move Is Legal
    @Override
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            return board.verifyDiagonal(row, col, endRow, endCol) && (board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack);
        }
        return false;
    }

}
