package CSCI1933P2;
//ahme0650 (Hamza Ahmed)
import java.util.Scanner;
public class Game {
    //Where The Chess Game Will Be Played !
    public static void main(String[] args) {
        System.out.println("Welcome To Chess!\n");
        Scanner s = new Scanner(System.in);
        Board chess = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", chess);
        boolean myMove = true;
        System.out.println("Determine Who Is White And Who is Black");
        System.out.println("Make Sure To Correctly Input Your Moves When Prompted!");
        System.out.println("Lets Begin !: Type 'White' ");
        String user = s.nextLine();
        while (!chess.isGameOver()){
            if (user.equals("White")){
                chess.display();
                System.out.println("Whites Turn. Whats Your Move !\nFormat {start row} {start col} {end row} {end col}");
                user = s.nextLine();
                args = user.split(" ");
                int startR = Integer.parseInt(args[0]);
                int startC = Integer.parseInt(args[1]);
                int endR = Integer.parseInt(args[2]);
                int endC = Integer.parseInt(args[3]);
                if (chess.getPiece(startR, startC) != null && !chess.getPiece(startR, startC).isBlack && chess.getPiece(startR, startC).isMoveLegal(chess, endR, endC)){
                    chess.movePiece(startR, startC, endR, endC);
                    if (chess.getPiece(endR, endC) instanceof Pawn && endR == 0){
                        ((Pawn) chess.getPiece(endR, endC)).promotePawn(chess, endR, endC,chess.getPiece(endR, endC).isBlack);
                    }
                    user = "Black";
                } else {
                    System.out.println("Invalid Input!\nPlease Try Again");
                    user = "White";
                }
            }
            else if (user.equals("Black")){
                chess.display();
                System.out.println("Blacks Turn. Whats Your Move !\nFormat {start row} {start col} {end row} {end col}");
                user = s.nextLine();
                args = user.split(" ");
                int startR = Integer.parseInt(args[0]);
                int startC = Integer.parseInt(args[1]);
                int endR = Integer.parseInt(args[2]);
                int endC = Integer.parseInt(args[3]);
                if (chess.getPiece(startR, startC) != null && chess.getPiece(startR, startC).isBlack && chess.getPiece(startR, startC).isMoveLegal(chess, endR, endC)){
                    chess.movePiece(startR, startC, endR, endC);
                    if (chess.getPiece(endR, endC) instanceof Pawn && endR == 7){
                        ((Pawn) chess.getPiece(endR, endC)).promotePawn(chess, endR, endC,chess.getPiece(endR, endC).isBlack);
                    }
                    user = "White";
                } else {
                    System.out.println("Invalid Input!\nPlease Try Again");
                    user = "Black";
                }
            }else {
                System.out.println("Please Type 'White' To Begin");
                user =s.nextLine();
            }
        }chess.display();
        chess.winner();
        System.out.println("Game Over !");
    }
        }


