package CSCI1933P2;
//ahmed0650 (Hamza Ahmed)
public class Rook extends Piece{

    //Constructor
    public Rook(int row, int col, boolean isBlack){
        super.row = row;

        super.col =col;

        super.isBlack = isBlack;

        if (isBlack){
            // Black Pawn
            super.representation = '\u265C';
        }
        else{
            // White Pawn
            super.representation = '\u2656';
        }
    }


    @Override
    //Method used for determining if a Rook's move is legal
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)){
        if (row == endRow){
            return board.verifyHorizontal(row, col, endRow, endCol) && (board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack);
        }else if (col == endCol){
            return board.verifyVertical(row, col, endRow, endCol)&& (board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack);
        }
        }
        return false;
    }
}
