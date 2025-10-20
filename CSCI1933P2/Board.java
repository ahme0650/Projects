package CSCI1933P2;
//ahme0650 (Hamza Ahmed)
public class Board {
    // Instance variables (add more if you need)
    private Piece[][] board;


    /**
     * Default Constructor
     */
    public Board() {
        // initialize the board to chessboard dimensions.
        this.board = new Piece[8][8];
    }

    // Accessor Methods

    /**
     * Gets the piece at a particular row and column of the board.
     * @param row       The row of the piece to be accessed.
     * @param col       The column of the piece to be accessed.
     * @return          The piece at the specified row and column of the board.
     */
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    /**
     * Sets the piece at a particular row and column of the board.
     * @param row       The row to place the piece at.
     * @param col       The column to place the piece at.
     * @param piece     The piece to place at the specified row and column.
     */
    public void setPiece(int row, int col, Piece piece) {
        this.board[row][col] = piece;
    }

    // Movement helper functions
    /**
     * Verifies that the source and destination of a move are valid by performing the following checks:
     *  1. ALL rows and columns provided must be >= 0.
     *  2. ALL rows and columns provided must be < 8.
     *  3. The start position of the move must contain a piece.
     *  4. The piece at the starting position must be the correct color.
     *  5. The destination must be empty OR must contain a piece of the opposite color.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @param isBlack   The expected color of the starting piece.
     * @return True if the above conditions are met, false otherwise.
     */
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startCol >= 0 && startRow >=0 && endCol >= 0 && endRow >=0){
            if (startCol < 8 && startRow < 8 && endCol < 8 && endRow < 8){
                if (board[startRow][startCol] != null && board[startRow][startCol].isBlack == isBlack){
                    if (board[endRow][endCol] == null || board[endRow][endCol].isBlack != isBlack){
                        return true;
                    }

                }

            }
        }return false;
    }

    /**
     * Verifies that the source and destination of a move are adjacent squares (within 1 square of each other)
     * Example, Piece P is adjacent to the spots marked X:
     * OOOOO
     * OXXXO
     * OXPXO
     * OXXXO
     * OOOOO
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if the source and destination squares are adjacent, false otherwise.
     */
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol){
            return true;
        }
        else if (startRow - 1 == endRow && startCol == endCol){
            return true;
        }
        else if (startRow + 1 == endRow && startCol == endCol){
            return true;
        }
        else if (startRow == endRow && startCol + 1 == endCol){
            return true;
        }
        else if (startRow == endRow && startCol - 1 == endCol){
            return true;
        }
        else if (startRow - 1 == endRow && startCol -1 == endCol){
            return true;
        }
        else if (startRow - 1 == endRow && startCol + 1 == endCol){
            return true;
        }
        else if (startRow + 1 == endRow && startCol -1 == endCol){
            return true;
        }
        else if (startRow + 1 == endRow && startCol + 1 == endCol){
            return true;
        }
        return false;
    }

    /**
     * Verifies that a source and destination are in the same row and that there are no pieces on squares
     * between the source and the destination.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if source and destination are in same row with no pieces between them, false otherwise.
     */
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startCol == endCol && startRow == endRow){
            return true;
        }
        else if (startRow == endRow){
            int lower = Math.min(endCol, startCol);
            int upper = Math.max(endCol, startCol);
            int count = 0;
                for (int i = 0; i < board.length; i++){
                    for (int j = 0; j <board[i].length; j++){
                        if (j > lower && j < upper && i == startRow){
                            if (board[i][j] == null){
                                count++;
                            }
                        }
                    }
                }
            return count == (upper - lower) - 1;
        } return false;
    }

    /**
     * Verifies that a source and destination are in the same column and that there are no pieces on squares
     * between the source and the destination.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if source and destination are in same column with no pieces between them, false otherwise.
     */
    //
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol == endCol && endRow == startRow){
            return true;
        }
        else if (startCol == endCol){
            int lower = Math.min(endRow, startRow);
            int upper = Math.max(endRow, startRow);
            int count = 0;
            for (int i = 0; i < board.length; i++){
                for (int j = 0; j <board[i].length; j++){
                    if (i > lower && i < upper && j == startCol){
                        if (board[i][j] == null){
                            count++;
                        }
                    }
                }
            }
            return count == (upper - lower) - 1;
        } return false;
    }

    /**
     * Verifies that a source and destination are on the same diagonal and that there are no pieces on squares
     * between the source and the destination.
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return True if source and destination are on the same diagonal with no pieces between them, false otherwise.
     */
    //Checks If There Is A Piece Between Two Points
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int lowerRow = Math.min(startRow, endRow);
        int upperRow = Math.max(startRow, endRow);
        int lowerCol = Math.min(startCol, endCol);
        int upperCol = Math.max(startCol, endCol);
        if (startCol == endCol && startRow == endRow){
            return true;
        }
        else if ((startCol + startRow) == (endRow + endCol)) {
            int counter = Math.max(startCol, endCol);
            for (int i = lowerRow; i < upperRow; i++) {
                if (i > lowerRow) {
                    if (board[i][counter] != null) {
                        return false;
                    }
                }counter--;
            }
            return true;
        }

        else if (startRow - startCol == endRow - endCol){
            int counter = Math.min(startCol, endCol);
            for (int i = lowerRow; i < upperRow; i++) {
                if (i > lowerRow) {
                    if (board[i][counter] != null) {
                        return false;
                    }
                }counter++;
            }
            return true;
        }
            return false;
    }

    // Game functionality methods

    /**
     * Moves the piece from startRow, startCol to endRow, endCol if it is legal to do so.
     * IMPORTANT: Make sure to update the internal position of the piece, and the starting position of the piece to null!
     * @param startRow  The starting row of the move.
     * @param startCol  The starting column of the move.
     * @param endRow    The ending row of the move.
     * @param endCol    The ending column of the move.
     * @return Whether the move was successfully completed or not. (Moves are not completed if they are not legal.)
     */
    //Method That Moves A Piece
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece p = getPiece(startRow, startCol);
        if (p == null){
            return false;
        }
        else if (p.isMoveLegal(this, endRow, endCol)){
            board[endRow][endCol] = p;
            board[startRow][startCol] = null;
            p.setPosition(endRow, endCol);
            return true;
        }
        return false;
    }

    /**
     * Returns true if there are fewer than TWO kings on the board.
     * @return If the game is in a game over state.
     */
    public boolean isGameOver() {
        int kingCount = 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j< board.length; j++){
                if (board[i][j] instanceof  King){
                    kingCount++;
                }
            }
        } return (kingCount < 2);
    }

    //Helper Function That Determines Who Won The Game !
    public void winner(){
        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] instanceof King){
                    if (board[i][j].isBlack){
                        System.out.println("Black Wins!");
                    }else if(!board[i][j].isBlack){
                        System.out.println("White Wins!");
                    }
                }
            }
        }
    }

    /**
     * Sets all indexes in the board to null
     */
    public void clear() {
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = null;
            }
        }
    }


    public void display() {
        System.out.print("\t\t\t");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "\t\t");
        }
        System.out.print("\n");
        for (int i = 0; i < 8; i++) {
            System.out.print("\t" + i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("|\t\t");
                } else {
                    System.out.print("|\t" + board[i][j] + "\t");
                }
            }
            System.out.print("|\n");
        }
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }
}

