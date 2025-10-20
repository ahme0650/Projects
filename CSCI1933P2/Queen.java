package CSCI1933P2;
//ahme0650 (Hamza Ahmed)
public class Queen extends Piece{

    //Constructor
    public Queen(int row, int col, boolean isBlack){
        super.row = row;

        super.col =col;

        super.isBlack = isBlack;

        if (isBlack){
            // Black Pawn
            super.representation = '\u265B';
        }
        else{
            // White Pawn
            super.representation = '\u2655';
        }
    }

    //Method To Determine If A Queens Move Is Legal
    @Override
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(row, col, endRow, endCol, isBlack)){
        if (board.verifyDiagonal(row, col, endRow, endCol) &&  ( board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack) ){
            return true;
        }else if (board.verifyVertical(row, col, endRow, endCol) && (board.getPiece(endRow, endCol) == null || board.getPiece(endRow, endCol).isBlack != this.isBlack)){
            return true;
        }else if (board.verifyHorizontal(row, col, endRow, endCol) && (board.getPiece(endRow, endCol) == null ||board.getPiece(endRow, endCol).isBlack != this.isBlack  )){
            return true;
        }
    }
        return false;
    }


}
