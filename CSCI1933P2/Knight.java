package CSCI1933P2;
//ahme0650 (Hamza Ahmed)
public class Knight extends Piece{

    //Constructor
    public Knight(int row, int col, boolean isBlack){
        super.row = row;

        super.col =col;

        super.isBlack = isBlack;

        if (isBlack){
            // Black Knight
            super.representation = '\u265E';
        }
        else{
            // White Knight
            super.representation = '\u2658';
        }
    }

    //Method To Determine If A Knights Move Is Legal
    @Override
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)) {
            if ((board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack) && this.row + 2 == endRow && (this.col - 1 == endCol)) {
                return true;
            } else if ((board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack) && this.row - 2 == endRow && (this.col - 1 == endCol)) {
                return true;
            } else if ((board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack) && this.col + 2 == endCol && (this.row - 1 == endRow)) {
                return true;
            } else if ((board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack) && this.col - 2 == endCol && (this.row - 1 == endRow)) {
                return true;
            } else if ((board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack) && this.row + 2 == endRow && (this.col + 1 == endCol)) {
                return true;
            } else if ((board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack) && this.row - 2 == endRow && (this.col + 1 == endCol)) {
                return true;
            } else if ((board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack) && this.col + 2 == endCol && (this.row + 1 == endRow)) {
                return true;
            } else if ((board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack) && this.col - 2 == endCol && (this.row + 1 == endRow)) {
                return true;
            }
        }return false;
    }
}
