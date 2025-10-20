// Import Section
import java.util.Random;
public class Minefield {
    /**
    Global Section
    */
    public static final String ANSI_YELLOW_BRIGHT = "\u001B[33;1m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BRIGHT = "\u001b[34;1m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_RED_BRIGHT = "\u001b[31;1m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001b[47m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001b[45m";
    public static final String ANSI_GREY_BACKGROUND = "\u001b[0m";



    //Class Atrributes
    private int rows;
    private int columns;
    private static int flags;
    private Cell[][] minefield;

    


    //Constructor !
    public Minefield(int rows, int columns, int flags) {
        this.rows = rows;
        this.columns = columns;
        this.flags = flags;
        this.minefield = new Cell[rows][columns];

        for (int i =0; i < minefield.length; i++){
            for (int j = 0; j < minefield[i].length; j++){
                minefield[i][j] = new Cell(false, "0");
            }
        }

    }





    //Initializes the statuses of the tiles that are next to a mine
    public void evaluateField() {
        for (int i = 0; i < minefield.length; i++){
            for (int j = 0; j < minefield[i].length; j++) {
                int numMines = 0;
                if (!minefield[i][j].getStatus().equals("M")){

                    for (int k = -1; k < 2; k++){
                        for (int l = -1; l < 2; l++){
                            if (inBounds(minefield, i+k, j+l) && minefield[i+k][j+l].getStatus().equals("M")){
                                //increment num mines
                               // System.out.println(numMines);
                                numMines += 1;
                            }
                        }
                    }minefield[i][j].setStatus(Integer.toString(numMines) );


                }
            }
        }
    }

    //Generates mines at random positions
    public void createMines(int x, int y, int mines) {
        int count = 0;
        int startX = x;
        int startY = y;

        while (count != mines){
            //System.out.println("mine");

            x = new Random().nextInt(0, minefield.length-1);
            y = new Random().nextInt(0, minefield[x].length-1);

            while ((x == startX && y == startY) || minefield[x][y].getStatus().equals("M")){
                x = new Random().nextInt(0, minefield.length-1);
                y = new Random().nextInt(0, minefield[x].length-1);
            }

            minefield[x][y].setStatus("M");
            count++;
        }

    }


    //The method that allows the user to guess a position
    public boolean guess(int x, int y, boolean flag) {
        if (!inBounds(minefield, x, y)){
            return false;
        }

        minefield[x][y].setRevealed(true);

        if (flag && flags != 0){
            minefield[x][y].setStatus("F");
            flags--;
            return false;
        }

        else if (minefield[x][y].getStatus().equals("0")){
            revealZeroes(x, y);
        }

        else if (!flag && minefield[x][y].getStatus().equals("M")){
            gameOver();
            return true;
        }

        return false;
    }


    public boolean gameOver() {

        boolean revealed = true;
        for (int i = 0; i < minefield.length; i++){
            for (int j = 0; j < minefield[i].length; j++){
                if (minefield[i][j].getStatus().equals("M") && minefield[i][j].getRevealed()){
                    System.out.println("You've Lost =(");
                    return true;
                }
                if (!minefield[i][j].getRevealed()){
                    revealed = false;
                }
            }
        }if (revealed){
            System.out.println("You Win !");
            return true;
        }
        return false;

    }


    public void revealZeroes(int x, int y) {
        Stack1Gen <int[]> s = new Stack1Gen<>();
        s.push(new int[] {x, y});
        while (!s.isEmpty()){

            int[] curCell = s.pop();
            x = curCell[0];
            y= curCell[1];

            minefield[x][y].setRevealed(true);

            if (inBounds(minefield, x, y-1) && !minefield[x][y - 1].getRevealed() && minefield[x][y-1].getStatus().equals("0")){
                s.push(new int[] {x,y-1});
            }

            if (inBounds(minefield, x, y+1) && !minefield[x][y+1].getRevealed()&& minefield[x][y+1].getStatus().equals("0")){
                s.push(new int[] {x,y+1});
            }

            if (inBounds(minefield, x+1, y) && !minefield[x+1][y].getRevealed() && minefield[x+1][y].getStatus().equals("0")){
                s.push(new int[] {x+1,y});
            }

            if (inBounds(minefield, x-1, y) && !minefield[x-1][y].getRevealed()&& minefield[x-1][y].getStatus().equals("0")){
                s.push(new int[] {x-1,y});
            }

        }

    }

    //Helper function used to determine if a coordinate is in bounds.
    public static boolean inBounds(Cell[][] field, int row, int col) {
        if (row >= field.length || col >= field[0].length || row < 0 || col < 0){
            return false;
        }return true;
    }

    public void revealStartingArea(int x, int y) {
        Q1Gen <int[]> q = new Q1Gen<>();
        q.add(new int[] {x, y});
        while (q.length() != 0){
            int[] curCell = q.remove();
            x = curCell[0];
            y = curCell[1];


            if (minefield[x][y].getStatus().equals("M")){
                break;
            }

            minefield[x][y].setRevealed(true);

            if (inBounds(minefield, x, y-1) && !minefield[x][y - 1].getRevealed()){
                q.add(new int[] {x,y-1});
            }
            if (inBounds(minefield, x, y+1) && !minefield[x][y+1].getRevealed()){
                q.add(new int[] {x,y+1});
            }
            if (inBounds(minefield, x+1, y) && !minefield[x+1][y].getRevealed()){
                q.add(new int[] {x+1,y});
            }
            if (inBounds(minefield, x-1, y) && !minefield[x-1][y].getRevealed()){
                q.add(new int[] {x-1,y});
            }

        }
    }


    //Prints the whole board
    public void debug() {
        String result = "   ";
        for (int p = 0; p < minefield.length; p++){
            result += "    " + p;
        }
        result+= "\n";
        int count = 0;
        //Prints the whole field
        for (int i = 0; i < minefield.length; i++){
            result+= "  " + i;
            for (int j = 0; j < minefield[i].length; j++) {
                if (minefield[i][j].getStatus().equals("F")) {
                    result += "    " + ANSI_GREY_BACKGROUND + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                }
                if (minefield[i][j].getStatus().equals("M")) {
                    result += "    " + ANSI_RED+ minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                }
                if (minefield[i][j].getStatus().equals("0")) {
                    result += "    " + ANSI_YELLOW + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                }
                if (minefield[i][j].getStatus().equals("1")) {
                    result += "    " + ANSI_CYAN + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                }
                if (minefield[i][j].getStatus().equals("2")) {
                    result += "    " + ANSI_PURPLE + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                }
                if (minefield[i][j].getStatus().equals("3")) {
                    result += "    " + ANSI_BLUE_BRIGHT + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                }
                if (minefield[i][j].getStatus().equals("4")) {
                    result += "    " + ANSI_GREEN + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                }
                if (minefield[i][j].getStatus().equals("5")) {
                    result += "    " + ANSI_RED_BRIGHT + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                }
                if (minefield[i][j].getStatus().equals("6")) {
                    result += "    " + ANSI_YELLOW_BRIGHT + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                }
                if (minefield[i][j].getStatus().equals("7")) {
                    result += "    " + ANSI_BLUE + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                }
                if (minefield[i][j].getStatus().equals("8")) {
                    result += "    " + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                }
            }result += "\n";
        }System.out.println(result);
    }


    @Override
    //Prints only the revealed cells
    public String toString() {
        String result = "   ";
        for (int i = 0; i < minefield.length; i++) {
            result +="    " + i;
        }
        result += "\n";
        for (int i = 0; i < minefield.length; i++){
            result+= "  " + i;
            for (int j = 0; j < minefield[i].length; j++){
//                if (count != 0 && count % columns== 0){
//                    result+= "\n";
//                }
                // if get revealed
                if (minefield[i][j].getRevealed()) {
                    if (minefield[i][j].getStatus().equals("F")) {
                        result += "    " + ANSI_GREY_BACKGROUND + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                    }
                    if (minefield[i][j].getStatus().equals("M")) {
                        result += "    " + ANSI_RED+ minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                    }
                    if (minefield[i][j].getStatus().equals("0")) {
                        result += "    " + ANSI_YELLOW + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                    }
                    if (minefield[i][j].getStatus().equals("1")) {
                        result += "    " + ANSI_CYAN + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                    }
                    if (minefield[i][j].getStatus().equals("2")) {
                        result += "    " + ANSI_PURPLE + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                    }
                    if (minefield[i][j].getStatus().equals("3")) {
                        result += "    " + ANSI_BLUE_BRIGHT + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                    }
                    if (minefield[i][j].getStatus().equals("4")) {
                        result +="    " + ANSI_GREEN + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                    }
                    if (minefield[i][j].getStatus().equals("5")) {
                        result += "    " + ANSI_RED_BRIGHT + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                    }
                    if (minefield[i][j].getStatus().equals("6")) {
                        result += "    " + ANSI_YELLOW_BRIGHT + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                    }
                    if (minefield[i][j].getStatus().equals("7")) {
                        result += "    " + ANSI_BLUE + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                    }
                    if (minefield[i][j].getStatus().equals("8")) {
                        result += "    " + minefield[i][j].getStatus() + ANSI_GREY_BACKGROUND;
                    }

                }else  {
                    result += "    " + "-";

                }
            }
            result += "\n";

        }return result;
    }

    //Getter for the # of flags
    public static int getFlags(){
        return flags;
    }




}
